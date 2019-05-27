
package swiggy.services;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swiggy.domain.Offer;
import swiggy.repository.OfferRepository;

import java.sql.Timestamp;
import java.util.List;


@Service
public class OfferService {

    @Autowired
    OfferRepository offerRepository;

    public String createOffer(Offer offer) {

        if(offerRepository.findByRestaurantIdentifierAndOfferCode(offer.getRestaurantIdentifier(),offer.getOfferCode())!=null) {

            return "existing";
        }
        offer.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        offer.setUpdatedTime(new Timestamp(System.currentTimeMillis()));

        return offerRepository.save(offer).toString();
    }

    public String updateOffer(Offer offer) {

        Offer toUpdateOffer;

        if(offer.getOfferIdentifier()==null) {

            toUpdateOffer = offerRepository.findByRestaurantIdentifierAndOfferCode(offer.getRestaurantIdentifier(), offer.getOfferCode());
        }
        else {

            toUpdateOffer = offerRepository.findOne(offer.getOfferIdentifier());
        }
        if(toUpdateOffer==null) {

            return "Invalid";
        }
        else if(toUpdateOffer.getDeleteFlag()==true) {

            return "Invalid";
        }
        else {

                if(offer.getOfferType()!=null) {
                    toUpdateOffer.setOfferType(offer.getOfferType());
                }

                if(offer.getMaximumDiscountAmount()!=null) {
                    toUpdateOffer.setMaximumDiscountAmount(offer.getMaximumDiscountAmount());
                }

                if(offer.getMinimumOrderValue()!=null) {
                    toUpdateOffer.setMinimumOrderValue(offer.getMinimumOrderValue());
                }

                if(offer.getRateOfDiscount()!=null) {
                    toUpdateOffer.setRateOfDiscount(offer.getRateOfDiscount());
                }
                if(offer.getOfferCode()!=null) {
                    toUpdateOffer.setOfferCode(offer.getOfferCode());
                }

                return offerRepository.save(toUpdateOffer).toString();
            }
        }

    public String deleteOffer(Offer offer) {

        Offer toDeleteOffer;

        if(offer.getOfferIdentifier()==null) {

            toDeleteOffer = offerRepository.findByRestaurantIdentifierAndOfferCode(offer.getRestaurantIdentifier(), offer.getOfferCode());
        }
        else {

            toDeleteOffer = offerRepository.findOne(offer.getOfferIdentifier());
        }

        if(toDeleteOffer==null) {
            return "Invalid";
        }
        else {
            toDeleteOffer.setDeleteFlag(true);
            offerRepository.save(toDeleteOffer);
            return "deleted";
        }
    }

    public String displayOffers() {

        List<Offer>  offerList= offerRepository.findOffers();
        Gson offerGson =new Gson();

        String offers =offerGson.toJson(offerList);

        return offers;

    }
}
