package com.addedfooddelivery_user.restaurantList.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllRestCategoryData implements Parcelable {

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
    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("itemPrice")
    @Expose
    private String itemPrice;
    @SerializedName("itemImage")
    @Expose
    private String itemImage;
    @SerializedName("restaurantRatingAVG")
    @Expose
    private float restaurantRatingAVG;
    @SerializedName("restaurantImage")
    @Expose
    private String restaurantImage;
    public final static Parcelable.Creator<AllRestCategoryData> CREATOR = new Creator<AllRestCategoryData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AllRestCategoryData createFromParcel(Parcel in) {
            return new AllRestCategoryData(in);
        }

        public AllRestCategoryData[] newArray(int size) {
            return (new AllRestCategoryData[size]);
        }

    };

    protected AllRestCategoryData(Parcel in) {
        this.restaurantID = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.restaurantName = ((String) in.readValue((String.class.getClassLoader())));
        this.restaurantAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.foodCategoryName = ((String) in.readValue((String.class.getClassLoader())));
        this.itemName = ((String) in.readValue((String.class.getClassLoader())));
        this.itemPrice = ((String) in.readValue((String.class.getClassLoader())));
        this.itemImage = ((String) in.readValue((String.class.getClassLoader())));
        this.restaurantRatingAVG = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.restaurantImage = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public AllRestCategoryData() {
    }

    /**
     * @param itemName
     * @param restaurantAddress
     * @param restaurantName
     * @param restaurantImage
     * @param itemPrice
     * @param restaurantID
     * @param foodCategoryName
     * @param restaurantRatingAVG
     * @param itemImage
     */
    public AllRestCategoryData(Integer restaurantID, String restaurantName, String restaurantAddress, String foodCategoryName, String itemName, String itemPrice, String itemImage, Integer restaurantRatingAVG, String restaurantImage) {
        super();
        this.restaurantID = restaurantID;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.foodCategoryName = foodCategoryName;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemImage = itemImage;
        this.restaurantRatingAVG = restaurantRatingAVG;
        this.restaurantImage = restaurantImage;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(restaurantID);
        dest.writeValue(restaurantName);
        dest.writeValue(restaurantAddress);
        dest.writeValue(foodCategoryName);
        dest.writeValue(itemName);
        dest.writeValue(itemPrice);
        dest.writeValue(itemImage);
        dest.writeValue(restaurantRatingAVG);
        dest.writeValue(restaurantImage);
    }

    public int describeContents() {
        return 0;
    }

}