package swiggy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import swiggy.domain.Customization;
import swiggy.services.CustomizationService;

@Controller
@RequestMapping("/customization")
public class FoodCustomizationManagement {

    @Autowired
    CustomizationService customizationService;

    @PostMapping("/create")
    public ResponseEntity createCustomization(Customization customization) {

        return new ResponseEntity(customizationService.createCustomization(customization), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity updateCustomization(Customization customization) {

        return new ResponseEntity(customizationService.updateCustomization(customization),HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity deleteCustomization(Customization customization) {

        return new ResponseEntity(customizationService.deleteCustomization(customization),HttpStatus.OK);
    }
}
