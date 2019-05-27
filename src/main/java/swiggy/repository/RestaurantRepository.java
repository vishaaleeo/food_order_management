package swiggy.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;
import swiggy.domain.Restaurant;
import swiggy.domain.User;


@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant,Integer> {

        @Query("select r from Restaurant r where r.restaurantName=?1 and r.deleteFlag!=true")
        Restaurant findByRestaurantName(String restaurantName);

        @Query("select count(r) from Restaurant r where r.restaurantName=?1 and r.deleteFlag!=true")
        Long countByName(String restaurantName);

}
