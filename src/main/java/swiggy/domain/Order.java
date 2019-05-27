package swiggy.domain;


import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import swiggy.services.EncodingDecoding;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="orders")
public class Order {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="order_identifier")
    private Integer orderIdentifier;

    @Column(name="order_cost")
    private Integer orderCost;

    @Column(name="order_status")
    private String orderStatus;

    @Column(name="food_count")
    private Integer foodCount;

    @Column(name="created_time")
    private Timestamp createdTime;

    @Column(name="updated_time")
    private Timestamp updatedTme;

    @Column(name="delete_flag")
    private Boolean deleteFlag;

    @Column(name="user_identifier")
    private Integer userIdentifier;

    @Column(name="restaurant_identifier")
    private Integer restaurantIdentifier;

    @Column(name="food_identifier")
    private Integer foodIdentifier;

    @Column(name="customization_identifier")
    private String customizationIdentifier;

    @Column(name="delivery_member_identifier")
    private Integer deliveryMemberIdentifier;

    @Column(name="offer_identifier")
    private Integer offerIdentifier;

    @Column(name="order_group_identifier")
    private Integer orderGroupIdentifier;

    @Transient
    private String foodName;

    @Transient
    private String restaurantName;

    @Transient
    private String offerCode;

    public Integer getOrderGroupIdentifier() {
        return orderGroupIdentifier;
    }

    public void setOrderGroupIdentifier(Integer orderGroupIdentifier) {
        this.orderGroupIdentifier = orderGroupIdentifier;
    }

    public Integer getOrderIdentifier() {
        return orderIdentifier;
    }

    public void setOrderIdentifier(Integer orderIdentifier) {
        this.orderIdentifier = orderIdentifier;
    }

    public Integer getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(Integer orderCost) {
        this.orderCost = orderCost;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(Integer foodCount) {
        this.foodCount = foodCount;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getUpdatedTme() {
        return updatedTme;
    }

    public void setUpdatedTme(Timestamp updatedTme) {
        this.updatedTme = updatedTme;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(Integer userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    public Integer getRestaurantIdentifier() {
        return restaurantIdentifier;
    }

    public void setRestaurantIdentifier(Integer restaurantIdentifier) {
        this.restaurantIdentifier = restaurantIdentifier;
    }

    public Integer getFoodIdentifier() {
        return foodIdentifier;
    }

    public void setFoodIdentifier(Integer foodIdentifier) {
        this.foodIdentifier = foodIdentifier;
    }

    public String getCustomizationIdentifier() {
        return customizationIdentifier;
    }

    public void setCustomizationIdentifier(String customizationIdentifier) {
        this.customizationIdentifier = customizationIdentifier;
    }

    public Integer getDeliveryMemberIdentifier() {
        return deliveryMemberIdentifier;
    }

    public void setDeliveryMemberIdentifier(Integer deliveryMemberIdentifier) {
        this.deliveryMemberIdentifier = deliveryMemberIdentifier;
    }

    public Integer getOfferIdentifier() {
        return offerIdentifier;
    }

    public void setOfferIdentifier(Integer offerIdentifier) {
        this.offerIdentifier = offerIdentifier;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getOfferCode() {
        return offerCode;
    }

    public void setOfferCode(String offerCode) {
        this.offerCode = offerCode;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderIdentifier=" + orderIdentifier +
                ", orderCost=" + orderCost +
                ", orderStatus='" + orderStatus + '\'' +
                ", foodCount=" + foodCount +
                ", createdTime=" + createdTime +
                ", updatedTme=" + updatedTme +
//                ", deleteFlag=" + deleteFlag +
                ", userIdentifier=" + userIdentifier +
                ", restaurantIdentifier=" + restaurantIdentifier +
                ", foodIdentifier=" +foodIdentifier +
                ", customizationIdentifier=" + (customizationIdentifier )+
                ", deliveryMemberIdentifier=" + Integer.toHexString(deliveryMemberIdentifier) +
                ", offerIdentifier=" + offerIdentifier +
                '}';
    }
}
