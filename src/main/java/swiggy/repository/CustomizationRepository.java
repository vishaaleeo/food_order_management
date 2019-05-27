package swiggy.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swiggy.domain.Customization;

@Repository
public interface CustomizationRepository extends JpaRepository<Customization,Integer> {

  //  @Query("select c from customization c where c.customizationName=?1 and c.deleteFlag!=true")
    Customization findByCustomizationName(String customizationName);
}
