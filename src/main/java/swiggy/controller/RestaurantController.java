package swiggy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import swiggy.domain.Restaurant;
import swiggy.services.RestaurantService;

import java.util.List;

@Controller
@RequestMapping("/res")
public class RestaurantController {


    @Autowired
    RestaurantService restaurantService;

    @PostMapping("/create")
    public ResponseEntity createRestaurant(@RequestBody Restaurant restaurant) {

        return new ResponseEntity(restaurantService.createRestaurant(restaurant), HttpStatus.valueOf(200));
    }

    @PostMapping("/find")
    public ResponseEntity findRestaurant(@RequestBody Restaurant restaurant) {

        return new ResponseEntity(restaurantService.findRestaurant(restaurant),HttpStatus.valueOf(200));
    }

    @PostMapping("/update")
    public ResponseEntity updateRestaurant(@RequestBody Restaurant restaurant) {

        return new ResponseEntity(restaurantService.updateRestaurant(restaurant),HttpStatus.valueOf(200));
    }

    @PostMapping("/delete")
    public ResponseEntity deleteRestaurant(@RequestBody Restaurant restaurant) {

        return new ResponseEntity(restaurantService.deleteRestaurant(restaurant),HttpStatus.valueOf(200));
    }

    @PostMapping(value="/display", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity readRestaurant(@RequestBody Restaurant restaurant) {

        List<Restaurant> restaurants=restaurantService.readRestaurants();
        return new ResponseEntity(restaurants,HttpStatus.OK);
    }
}
