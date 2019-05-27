package swiggy.services;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import swiggy.domain.*;
import swiggy.repository.CustomizationRepository;
import swiggy.repository.FoodRepostiory;
import swiggy.repository.OfferRepository;
import swiggy.repository.OrderRepository;

import java.sql.Timestamp;
import java.util.List;

@Service
public class OrderService {


    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OfferRepository offerRepository;

    @Autowired
    FoodRepostiory foodRepostiory;

    @Autowired
    CustomizationRepository customizationRepository;

    EncodingDecoding encodingDecoding=new EncodingDecoding();



    public Order createOrder(Order order) {

        if(order.getUserIdentifier()==null) {
            AuthUser principal=(AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            order.setUserIdentifier(principal.getUserIdentifier());
        }


        order.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        order.setUpdatedTme(new Timestamp(System.currentTimeMillis()));
        order=setFoodDetails(order);
        order=setcustomizationCost(order);
        order.setDeleteFlag(false);
        if(order.getOrderStatus()==null || order.getOrderStatus()=="") {

            order.setOrderStatus("oncart");
        }


        List<Order> onCartOrders = orderRepository.findOnCart(order.getUserIdentifier());


        System.out.println(onCartOrders.toString());

        if (onCartOrders.isEmpty()) {
            Integer identifier = orderRepository.findMaxGroupOrderIdentifierByUser();

            if(identifier==null) {
                identifier=0;
            }

            order.setOrderGroupIdentifier(identifier+1);


            return orderRepository.save(order);
        }

        else if (onCartOrders.get(0).getRestaurantIdentifier() == order.getRestaurantIdentifier()) {

            for (Order onCartOrder : onCartOrders) {

                if (onCartOrder.getFoodIdentifier() == order.getFoodIdentifier()) {

                    if(order.getFoodCount()!=onCartOrder.getFoodCount()) {

                        onCartOrder.setFoodCount(onCartOrder.getFoodCount());
                        onCartOrder=setFoodDetails(onCartOrder);
                        onCartOrder.setUpdatedTme(new Timestamp(System.currentTimeMillis()));
                        return orderRepository.save(onCartOrder);
                    }

                }
            }
            order.setOrderGroupIdentifier(onCartOrders.get(0).getOrderGroupIdentifier());
            return orderRepository.save(order);

        }
        else {
            return null;
        }
    }





    public Order updateOrder(Order order) {

        Order toUpdateOrder =orderRepository.findOne(order.getOrderIdentifier());
        if(order.getOrderStatus()!=null){
            toUpdateOrder.setOrderStatus(order.getOrderStatus());
        }

        if(order.getFoodCount()!=null && order.getFoodCount()!=toUpdateOrder.getFoodCount()) {

            if(order.getFoodCount()==0) {
                deleteOrder(toUpdateOrder);
            }
            toUpdateOrder.setFoodCount(order.getFoodCount());
            toUpdateOrder=setFoodDetails(toUpdateOrder);
        }

        if(order.getCustomizationIdentifier()!=null && order.getCustomizationIdentifier()!="") {

            toUpdateOrder.setCustomizationIdentifier(order.getCustomizationIdentifier());
            toUpdateOrder=setcustomizationCost(toUpdateOrder);
        }

        if(order.getOrderStatus()!=null && order.getOrderStatus()!="") {

            toUpdateOrder.setOrderStatus(order.getOrderStatus());
        }
        toUpdateOrder.setUpdatedTme(new Timestamp(System.currentTimeMillis()));
        return orderRepository.save(toUpdateOrder);

    }



    public void deleteOrder(Order toDeleteOrder) {

        toDeleteOrder.setDeleteFlag(true);
        orderRepository.save(toDeleteOrder);


    }



    public Order checkOffer(Order order) {

        Offer offer=offerRepository.findOne(order.getOfferIdentifier());
        if(offer==null)
            return null;
        if(offer.getRestaurantIdentifier().equals(order.getRestaurantIdentifier())  &&  offer.getOfferCode().equals(order.getOfferCode())) {

            applyOffer(offer,order);
        }
        return order;

    }




    public String applyOffer(Offer offer,Order order) {

        Double priceCut;
     //   if(offer.getMinimumOrderValue()<
        AuthUser principal=(AuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Order> onCartOrders = orderRepository.findOnCart(principal.getUserIdentifier());

        Double totalCost=(Double)calculateTotalCost(onCartOrders);

        Gson onCartGson=new Gson();

        String onCartList=onCartGson.toJson(onCartOrders);


        if(totalCost > offer.getMinimumOrderValue()) {

            priceCut=totalCost*(offer.getRateOfDiscount()/100);

            if(priceCut<=offer.getMaximumDiscountAmount()) {

                totalCost=totalCost-priceCut;

            }
            else {
                totalCost=totalCost-offer.getMaximumDiscountAmount();
            }

        }

        return "{ \"orders\" :"+onCartList+", \"totalCost\" : \""+totalCost+"\"}";

    }



    public String displayOrder(Order order) {

        List<Order> orders=orderRepository.findByOrderGroupIdentifier(order.getOrderGroupIdentifier());

        int totalCost=0;
        for(Order orderIterator :orders){

            totalCost+=orderIterator.getOrderCost();
        }
        return orders.toString();

    }



    public String onCartDisplay(){

        AuthUser principal=(AuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Order> onCartOrders = orderRepository.findOnCart(principal.getUserIdentifier());


//       Gson onCartGson=new Gson();

  //     String onCartList=onCartGson.toJson(onCartOrders);
        String onCartList="";
        int iterator=0;

        for(iterator=0;iterator<onCartOrders.size()-1;iterator++) {

            onCartList+=toJSON(onCartOrders.get(iterator))+",";
        }


        onCartList+=toJSON(onCartOrders.get(iterator));

        Double totalCost=calculateTotalCost(onCartOrders);

       // System.out.println("{"+onCartList+", \"totalCost\" : \""+totalCost+"\"}");

        return "{ \"orders\" : ["+onCartList+"], \"totalCost\" : \""+totalCost+"\"}";



    }

    public Order setFoodDetails(Order order){

        Food food=foodRepostiory.findOne(order.getFoodIdentifier());
        order.setFoodName(food.getFoodName());
        order.setOrderCost(order.getFoodCount()*food.getFoodCost());
        return order;

    }



    public Order setcustomizationCost(Order order) {

        if(order.getCustomizationIdentifier()==null || order.getCustomizationIdentifier()=="")
            return order;

        String[] customizationIdentifiers =order.getCustomizationIdentifier().split(",");

        for(String identifier:customizationIdentifiers) {
            Customization customization = customizationRepository.findOne(Integer.valueOf(identifier));
            order.setOrderCost(order.getOrderCost() + customization.getCustomizationCost());
        }
        return order;

    }




    public Double calculateTotalCost(List<Order> orders){

        Integer totalCost=0;
        for(Order order:orders) {

            totalCost+=order.getOrderCost();
        }

        return Double.valueOf(totalCost);
    }

   public String toJSON(Order order){


        String json="{";
        json+="\"orderIdentifier\" : \""+order.getOrderIdentifier()+"\",";
        json+="\"orderGroupIdentifier\" : \""+order.getOrderGroupIdentifier()+"\",";
        json+="\"restaurantIdentifier\" : \""+encodingDecoding.encode(order.getRestaurantIdentifier())+"\",";
        json+="\"foodIdentifier\" : \""+encodingDecoding.encode(order.getFoodIdentifier())+"\",";
        json+="\"foodName\" : \""+order.getFoodName()+"\"";
        json+="}";


        return json;
   }


}
