package swiggy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swiggy.domain.Offer;

import java.util.List;


@Repository
public interface OfferRepository extends JpaRepository<Offer,Integer> {


   @Query("select r from Offer r where r.restaurantIdentifier=?1 and r.deleteFlag!=true")
    Offer findByRestaurantIdentifierAndOfferCode(Integer restaurantIdentifier,String offerCode);


   @Query("select r from Offer r where r.deleteFlag!=true")
   List<Offer> findOffers();


}
