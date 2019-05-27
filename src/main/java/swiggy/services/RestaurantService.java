package swiggy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swiggy.domain.Response;
import swiggy.domain.Restaurant;
import swiggy.repository.RestaurantRepository;

import java.sql.Timestamp;
import java.util.List;

@Service
public class RestaurantService {


    @Autowired
    RestaurantRepository restaurantRepository;

    public String createRestaurant(Restaurant restaurant) {

        if(restaurantRepository.countByName(restaurant.getRestaurantName())>0) {
            return "existing";
        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        restaurant.setCreatedTime(timestamp);
        restaurant.setUpdatedTime(timestamp);
        restaurant.setDeleteFlag(false);
        restaurant.setAvailable(true);

        try {
            Restaurant createdRestaurant = restaurantRepository.save(restaurant);
            System.out.println(createdRestaurant.getRestaurantIdentifier());

            return "done";
        }
        catch(Exception e) {

            System.out.print(e);
            return "not done";
        }
    }

    public Restaurant findRestaurant(Restaurant restaurant) {


        Restaurant foundRestaurant;
        if (restaurant.getRestaurantIdentifier() == null) {

            foundRestaurant = restaurantRepository.findOne(restaurant.getRestaurantIdentifier());

        } else if (restaurantRepository.countByName(restaurant.getRestaurantName()) == 1) {
            foundRestaurant = restaurantRepository.findByRestaurantName(restaurant.getRestaurantName());

        } else {
            return null;
        }
        if (foundRestaurant.getDeleteFlag() == true)
            return null;

        return foundRestaurant;

    }


    public String updateRestaurant(Restaurant restaurant) {

        try {
            Restaurant toUpdateRestaurant=findRestaurant(restaurant);

                if (toUpdateRestaurant != null)  {

                    toUpdateRestaurant.setUpdatedTime(new Timestamp(System.currentTimeMillis()));


                    if (restaurant.getOpenTime() != null) {
                        toUpdateRestaurant.setOpenTime(restaurant.getOpenTime());
                    }
                    if (restaurant.getCloseTime() != null) {
                        toUpdateRestaurant.setCloseTime(restaurant.getCloseTime());
                    }
                    if (restaurant.getRestaurantCharges() != null ) {
                        toUpdateRestaurant.setRestaurantCharges(restaurant.getRestaurantCharges());
                    }

                    return restaurantRepository.save(toUpdateRestaurant).toString();
                }

                else
                {
                    return "no such user exists";
                }



        } catch (Exception e) {

            System.out.print(e);
            return e.toString();
        }

    }


    public String deleteRestaurant(Restaurant restaurant) {

        Restaurant toDeleteRestaurant =findRestaurant(restaurant);

        toDeleteRestaurant.setDeleteFlag(true);

        return restaurantRepository.save(toDeleteRestaurant).toString();

    }


    public List readRestaurants() {

        List<Restaurant> restaurants= (List<Restaurant>) restaurantRepository.findAll();

        return restaurants;
    }


}
