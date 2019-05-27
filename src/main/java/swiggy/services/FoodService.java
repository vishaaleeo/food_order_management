package swiggy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swiggy.domain.Food;
import swiggy.repository.FoodRepostiory;

import java.sql.Timestamp;
import java.util.List;

@Service
public class FoodService {

    @Autowired
    FoodRepostiory foodRepostiory;

    public String createFood(Food food) {

        if(foodRepostiory.countByRestaurantIdentfierAndFoodName(food.getRestaurantIdentifier(),food.getFoodName())>0)
            return "existing";

        food.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        food.setUpdatedTime(new Timestamp(System.currentTimeMillis()));
        food.setDeleteFlag(false);
        if(food.getIsAvailable()==null || food.getIsAvailable().equals("")) {
            food.setIsAvailable(true);
        }


        //System.out.print(food);
        Food savedFood=foodRepostiory.save(food);
        return savedFood.toString();
    }

    public String updateFood(Food food){

        Food toUpdateFood=null;

        if(food.getFoodIdentifier()==null) {
            toUpdateFood = foodRepostiory.findByRestaurantIdentifierAndFoodName(food.getRestaurantIdentifier(), food.getFoodName());
        }
        else {
            toUpdateFood=foodRepostiory.findOne(food.getFoodIdentifier());
        }
        if(toUpdateFood==null) {
            return "invalid food";
        }
        else {
            if(food.getFoodCategory()!=null) {
                toUpdateFood.setFoodCategory(food.getFoodCategory());
            }

            if(food.getFoodType()!=null) {
                toUpdateFood.setFoodType(food.getFoodType());
            }

            if(food.getFoodDescription()!=null) {
                toUpdateFood.setFoodDescription(food.getFoodDescription());
            }

            if(food.getFoodCost()!=null) {
                toUpdateFood.setFoodCost(food.getFoodCost());
            }

            if(food.getIsCustomizable()!=null) {
                toUpdateFood.setIsCustomizable(food.getIsCustomizable());
            }

            if(food.getIsAvailable()!=null) {
                toUpdateFood.setIsAvailable(food.getIsAvailable());
            }
            toUpdateFood.setUpdatedTime(new Timestamp(System.currentTimeMillis()));

            food=foodRepostiory.save(toUpdateFood);
            return food.toString();
        }
    }

    public String deleteFood(Food food) {

        Food toDeleteFood=null;
        if(food.getFoodIdentifier()==null) {
            toDeleteFood = foodRepostiory.findByRestaurantIdentifierAndFoodName(food.getRestaurantIdentifier(), food.getFoodName());
        }
        else {
            toDeleteFood=foodRepostiory.findOne(food.getFoodIdentifier());
        }
        if (toDeleteFood == null) {
            return "invalid food";
        }

        toDeleteFood.setDeleteFlag(true);

        foodRepostiory.save(toDeleteFood);

        return "deleted";
    }

    public List<String> displayByCategory(Integer restaurantIdentifier ) {

        return foodRepostiory.findCategories(restaurantIdentifier);
    }

    public List<Food> displayListByCategory(Integer restaurantIdentifier,String foodCategory) {

        return foodRepostiory.findByFoodCategory(restaurantIdentifier,foodCategory);
    }

}

