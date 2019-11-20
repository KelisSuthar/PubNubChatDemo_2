package com.addedfooddelivery_user.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Popular implements Parcelable {

    @SerializedName("restaurantID")
    @Expose
    private Integer restaurantID;
    @SerializedName("restaurantName")
    @Expose
    private String restaurantName;
    @SerializedName("restaurantAddress")
    @Expose
    private String restaurantAddress;
    @SerializedName("foodCategoryName")
    @Expose
    private String foodCategoryName;
    @SerializedName("restaurantRatingAVG")
    @Expose
    private Integer restaurantRatingAVG;
    @SerializedName("restaurantImage")
    @Expose
    private String restaurantImage;
    @SerializedName("itemPrice")
    @Expose
    private String itemPrice;
    public final static Parcelable.Creator<Popular> CREATOR = new Creator<Popular>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Popular createFromParcel(Parcel in) {
            return new Popular(in);
        }

        public Popular[] newArray(int size) {
            return (new Popular[size]);
        }

    };

    protected Popular(Parcel in) {
        this.restaurantID = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.restaurantName = ((String) in.readValue((String.class.getClassLoader())));
        this.restaurantAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.foodCategoryName = ((String) in.readValue((String.class.getClassLoader())));
        this.restaurantRatingAVG = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.restaurantImage = ((String) in.readValue((String.class.getClassLoader())));
        this.itemPrice = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public Popular() {
    }

    /**
     * @param restaurantAddress
     * @param restaurantName
     * @param restaurantImage
     * @param itemPrice
     * @param restaurantID
     * @param foodCategoryName
     * @param restaurantRatingAVG
     */
    public Popular(Integer restaurantID, String restaurantName, String restaurantAddress, String foodCategoryName, Integer restaurantRatingAVG, String restaurantImage, String itemPrice) {
        super();
        this.restaurantID = restaurantID;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.foodCategoryName = foodCategoryName;
        this.restaurantRatingAVG = restaurantRatingAVG;
        this.restaurantImage = restaurantImage;
        this.itemPrice = itemPrice;
    }

    public Integer getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(Integer restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getFoodCategoryName() {
        return foodCategoryName;
    }

    public void setFoodCategoryName(String foodCategoryName) {
        this.foodCategoryName = foodCategoryName;
    }

    public Integer getRestaurantRatingAVG() {
        return restaurantRatingAVG;
    }

    public void setRestaurantRatingAVG(Integer restaurantRatingAVG) {
        this.restaurantRatingAVG = restaurantRatingAVG;
    }

    public String getRestaurantImage() {
        return restaurantImage;
    }

    public void setRestaurantImage(String restaurantImage) {
        this.restaurantImage = restaurantImage;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(restaurantID);
        dest.writeValue(restaurantName);
        dest.writeValue(restaurantAddress);
        dest.writeValue(foodCategoryName);
        dest.writeValue(restaurantRatingAVG);
        dest.writeValue(restaurantImage);
        dest.writeValue(itemPrice);
    }

    public int describeContents() {
        return 0;
    }

}