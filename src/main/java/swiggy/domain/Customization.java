package swiggy.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Customization {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="coustomization_identifier")
    private Integer customizationIdentifier;

    @Column(name="customization_name")
    private String customizationName;

    @Column(name="customization_amount")
    private Integer customizationCost;

    @Column(name="delete_flag")
    private Boolean deleteFlag;

    @Column(name="created_time")
    private Timestamp createdTime;

    @Column(name="updated_time")
    private Timestamp updatedTime;

    @Column(name="is_available")
    private Boolean isAvailable;

    @Column(name="food_identifier")
    private Integer foodIdentifier;

    @Column(name="restaurant_identifier")
    private Integer restaurantIdentifier;

    public Integer getCustomizationIdentifier() {
        return customizationIdentifier;
    }

    public void setCustomizationIdentifier(Integer customizationIdentifier) {
        this.customizationIdentifier = customizationIdentifier;
    }

    public String getCustomizationName() {
        return customizationName;
    }

    public void setCustomizationName(String customizationName) {
        this.customizationName = customizationName;
    }

    public Integer getCustomizationCost() {
        return customizationCost;
    }

    public void setCustomizationCost(Integer customizationCost) {
        this.customizationCost = customizationCost;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
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

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean available) {
        isAvailable = available;
    }

    public Integer getFoodIdentifier() {
        return foodIdentifier;
    }

    public void setFoodIdentifier(Integer foodIdentifier) {
        this.foodIdentifier = foodIdentifier;
    }

    public Integer getRestaurantIdentifier() {
        return restaurantIdentifier;
    }

    public void setRestaurantIdentifier(Integer restaurantIdentifier) {
        this.restaurantIdentifier = restaurantIdentifier;
    }

    @Override
    public String toString() {
        return "Customization{" +
                "customizationIdentifier=" + customizationIdentifier +
                ", customizationName='" + customizationName + '\'' +
                ", customizationCost=" + customizationCost +
                ", deleteFlag=" + deleteFlag +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", isAvailable=" + isAvailable +
                ", foodIdentifier=" + foodIdentifier +
                ", restaurantIdentifier=" + restaurantIdentifier +
                '}';
    }
}
