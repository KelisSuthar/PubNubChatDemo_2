package com.addedfooddelivery_user.RestaurantList.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllRestaurantData implements Parcelable {

    @SerializedName("restaurantID")
    @Expose
    private Integer restaurantID;
    @SerializedName("restaurantName")
    @Expose
    private String restaurantName;
    @SerializedName("itemPrice")
    @Expose
    private String itemPrice;
    @SerializedName("restaurantAddress")
    @Expose
    private String restaurantAddress;
    @SerializedName("foodCategoryName")
    @Expose
    private String foodCategoryName;
    @SerializedName("restaurantRatingAVG")
    @Expose
    private float restaurantRatingAVG;
    @SerializedName("restaurantImage")
    @Expose
    private String restaurantImage;
    @SerializedName("itemImage")
    @Expose
    private String itemImage;
    public final static Parcelable.Creator<AllRestaurantData> CREATOR = new Creator<AllRestaurantData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AllRestaurantData createFromParcel(Parcel in) {
            return new AllRestaurantData(in);
        }

        public AllRestaurantData[] newArray(int size) {
            return (new AllRestaurantData[size]);
        }

    };

    protected AllRestaurantData(Parcel in) {
        this.restaurantID = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.restaurantName = ((String) in.readValue((String.class.getClassLoader())));
        this.itemPrice = ((String) in.readValue((String.class.getClassLoader())));
        this.restaurantAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.foodCategoryName = ((String) in.readValue((String.class.getClassLoader())));
        this.restaurantRatingAVG = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.restaurantImage = ((String) in.readValue((String.class.getClassLoader())));
        this.itemImage = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public AllRestaurantData() {
    }

    /**
     * @param restaurantAddress
     * @param restaurantName
     * @param restaurantImage
     * @param itemPrice
     * @param restaurantID
     * @param foodCategoryName
     * @param restaurantRatingAVG
     * @param itemImage
     */
    public AllRestaurantData(Integer restaurantID, String restaurantName, String itemPrice, String restaurantAddress, String foodCategoryName, Integer restaurantRatingAVG, String restaurantImage, String itemImage) {
        super();
        this.restaurantID = restaurantID;
        this.restaurantName = restaurantName;
        this.itemPrice = itemPrice;
        this.restaurantAddress = restaurantAddress;
        this.foodCategoryName = foodCategoryName;
        this.restaurantRatingAVG = restaurantRatingAVG;
        this.restaurantImage = restaurantImage;
        this.itemImage = itemImage;
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

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
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

    public float getRestaurantRatingAVG() {
        return restaurantRatingAVG;
    }

    public void setRestaurantRatingAVG(float restaurantRatingAVG) {
        this.restaurantRatingAVG = restaurantRatingAVG;
    }

    public String getRestaurantImage() {
        return restaurantImage;
    }

    public void setRestaurantImage(String restaurantImage) {
        this.restaurantImage = restaurantImage;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(restaurantID);
        dest.writeValue(restaurantName);
        dest.writeValue(itemPrice);
        dest.writeValue(restaurantAddress);
        dest.writeValue(foodCategoryName);
        dest.writeValue(restaurantRatingAVG);
        dest.writeValue(restaurantImage);
        dest.writeValue(itemImage);
    }

    public int describeContents() {
        return 0;
    }

}