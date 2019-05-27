package swiggy.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="offer")
public class Offer {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="offer_identifier")
    private Integer offerIdentifier;

    @Column(name="offer_type")
    private String offerType;

    @Column(name="created_time")
    private Timestamp createdTime;

    @Column(name="updated_time")
    private Timestamp updatedTime;

    @Column(name="ratre_of_discount")
    private Double rateOfDiscount;

    @Column(name="minimum_order_value")
    private Integer minimumOrderValue;

    @Column(name="maximum_discount_amount")
    private Integer maximumDiscountAmount;

    @Column(name="offer_code")
    private String offerCode;

    @Column(name="delete_flag")
    private Boolean deleteFlag;

    @Column(name="restaurant_identifier")
    private Integer restaurantIdentifier;

    public Integer getOfferIdentifier() {
        return offerIdentifier;
    }

    public void setOfferIdentifier(Integer offerIdentifier) {
        this.offerIdentifier = offerIdentifier;
    }

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Double getRateOfDiscount() {
        return rateOfDiscount;
    }

    public void setRateOfDiscount(Double rateOfDiscount) {
        this.rateOfDiscount = rateOfDiscount;
    }

    public Integer getMinimumOrderValue() {
        return minimumOrderValue;
    }

    public void setMinimumOrderValue(Integer minimumOrderValue) {
        this.minimumOrderValue = minimumOrderValue;
    }

    public Integer getMaximumDiscountAmount() {
        return maximumDiscountAmount;
    }

    public void setMaximumDiscountAmount(Integer maximumDiscountAccount) {
        this.maximumDiscountAmount = maximumDiscountAccount;
    }

    public String getOfferCode() {
        return offerCode;
    }

    public void setOfferCode(String offerCode) {
        this.offerCode = offerCode;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "offerIdentifier=" + offerIdentifier +
                ", offerType='" + offerType + '\'' +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", rateOfDiscount=" + rateOfDiscount +
                ", minimumOrderValue=" + minimumOrderValue +
                ", maximumDiscountAccount=" + maximumDiscountAmount +
                ", offerCode='" + offerCode + '\'' +
                ", deleteFlag=" + deleteFlag +
                ", restaurant_identifier=" + restaurantIdentifier +
                '}';
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getRestaurantIdentifier() {
        return restaurantIdentifier;
    }

    public void setRestaurantIdentifier(Integer restaurant_identifier) {
        this.restaurantIdentifier = restaurant_identifier;
    }
}
