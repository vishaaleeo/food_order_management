package swiggy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import swiggy.domain.Offer;
import swiggy.services.OfferService;

@Controller
@RequestMapping("/offer")
public class OfferController {


    @Autowired
    OfferService offerService;


    @PostMapping("/create")
    public ResponseEntity createOffer(Offer offer) {

        return new ResponseEntity(offerService.createOffer(offer), HttpStatus.OK);

    }

    @PostMapping("/update")
    public ResponseEntity updateOffer(Offer offer) {

        return new ResponseEntity(offerService.updateOffer(offer),HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity deleteOffer(Offer offer) {

        return new ResponseEntity(offerService.deleteOffer(offer),HttpStatus.OK);
    }

    @GetMapping(value="/display",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity displayOffer() {

        return new ResponseEntity(offerService.displayOffers(),HttpStatus.OK);
    }
}
