package swiggy.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "food_item")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="food_item_identifier")
    private Integer foodIdentifier;

    @Column(name="food_item_name")
    private String foodName;

    @Column(name="food_category")
    private String foodCategory;

    @Column(name="food_type")
    private String foodType;

    @Column(name="food_description")
    private String foodDescription;

    @Column(name="created_time")
    private Timestamp createdTime;

    @Column(name="updated_time")
    private Timestamp updatedTime;

    @Column(name="is_available")
    private Boolean isAvailable;

    @Column(name="is_customizable")
    private Boolean isCustomizable;

    @Column(name="food_cost")
    private Integer foodCost;

    @Column(name="restaurant_identifier")
    private Integer restaurantIdentifier;

    @Column(name="delete_flag")
    private Boolean deleteFlag;


    public Integer getFoodIdentifier() {
        return foodIdentifier;
    }

    public void setFoodIdentifier(Integer foodIdentifier) {
        this.foodIdentifier = foodIdentifier;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
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

    public Boolean getIsCustomizable() {
        return isCustomizable;
    }

    public void setIsCustomizable(Boolean customizable) {
        isCustomizable = customizable;
    }

    public Integer getFoodCost() {
        return foodCost;
    }

    public void setFoodCost(Integer foodCost) {
        this.foodCost = foodCost;
    }

    public Integer getRestaurantIdentifier() {
        return restaurantIdentifier;
    }

    public void setRestaurantIdentifier(Integer restaurantIdentifier) {
        this.restaurantIdentifier = restaurantIdentifier;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "Food{" +
                "foodIdentifier=" + foodIdentifier +
                ", foodName='" + foodName + '\'' +
                ", foodCategory='" + foodCategory + '\'' +
                ", foodType='" + foodType + '\'' +
                ", foodDescription='" + foodDescription + '\'' +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", isAvailable=" + isAvailable +
                ", isCustomizable=" + isCustomizable +
                ", foodCost=" + foodCost +
                ", restaurantIdentifier=" + restaurantIdentifier +
                '}';
    }
}

