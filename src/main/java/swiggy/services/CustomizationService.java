package swiggy.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swiggy.domain.Customization;
import swiggy.repository.CustomizationRepository;

import java.sql.Timestamp;

@Service
public class CustomizationService {

    @Autowired
    CustomizationRepository customizationRepository;

    public String createCustomization(Customization customization) {

        customization.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        customization.setUpdatedTime(new Timestamp(System.currentTimeMillis()));
        customization.setDeleteFlag(false);

        if(customizationRepository.findByCustomizationName(customization.getCustomizationName())==null) {

            return customizationRepository.save(customization).toString();
        }
        else {

            return "existing";
        }


    }

    public String updateCustomization(Customization customization) {

        Customization toUpdateCustomization=customizationRepository.findByCustomizationName(customization.getCustomizationName());

        if(toUpdateCustomization==null) {

            return "invalid customization option";

        }
        if(customization.getCustomizationCost()!=null) {

            toUpdateCustomization.setCustomizationCost(customization.getCustomizationCost());
        }

        if(customization.getIsAvailable()!=null) {

            toUpdateCustomization.setIsAvailable(customization.getIsAvailable());
        }

        return customizationRepository.save(toUpdateCustomization).toString();

    }

    public String deleteCustomization(Customization customization) {

        Customization toDeleteCustomization=customizationRepository.findByCustomizationName((customization.getCustomizationName()));

        if(toDeleteCustomization==null) {

            return "invalid customization option";
        }

        toDeleteCustomization.setDeleteFlag(true);

        customizationRepository.save(toDeleteCustomization);

        return "deleted";
    }

}
