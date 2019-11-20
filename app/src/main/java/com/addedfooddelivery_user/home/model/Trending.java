package com.addedfooddelivery_user.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trending implements Parcelable
{

@SerializedName("restaurantID")
@Expose
private Integer restaurantID;
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
private Integer restaurantRatingAVG;
public final static Parcelable.Creator<Trending> CREATOR = new Creator<Trending>() {


@SuppressWarnings({
"unchecked"
})
public Trending createFromParcel(Parcel in) {
return new Trending(in);
}

public Trending[] newArray(int size) {
return (new Trending[size]);
}

}
;

protected Trending(Parcel in) {
this.restaurantID = ((Integer) in.readValue((Integer.class.getClassLoader())));
this.foodCategoryName = ((String) in.readValue((String.class.getClassLoader())));
this.itemName = ((String) in.readValue((String.class.getClassLoader())));
this.itemPrice = ((String) in.readValue((String.class.getClassLoader())));
this.itemImage = ((String) in.readValue((String.class.getClassLoader())));
this.restaurantRatingAVG = ((Integer) in.readValue((Integer.class.getClassLoader())));
}

/**
* No args constructor for use in serialization
*
*/
public Trending() {
}

/**
*
* @param itemName
* @param itemPrice
* @param restaurantID
* @param foodCategoryName
* @param restaurantRatingAVG
* @param itemImage
*/
public Trending(Integer restaurantID, String foodCategoryName, String itemName, String itemPrice, String itemImage, Integer restaurantRatingAVG) {
super();
this.restaurantID = restaurantID;
this.foodCategoryName = foodCategoryName;
this.itemName = itemName;
this.itemPrice = itemPrice;
this.itemImage = itemImage;
this.restaurantRatingAVG = restaurantRatingAVG;
}

public Integer getRestaurantID() {
return restaurantID;
}

public void setRestaurantID(Integer restaurantID) {
this.restaurantID = restaurantID;
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

public Integer getRestaurantRatingAVG() {
return restaurantRatingAVG;
}

public void setRestaurantRatingAVG(Integer restaurantRatingAVG) {
this.restaurantRatingAVG = restaurantRatingAVG;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(restaurantID);
dest.writeValue(foodCategoryName);
dest.writeValue(itemName);
dest.writeValue(itemPrice);
dest.writeValue(itemImage);
dest.writeValue(restaurantRatingAVG);
}

public int describeContents() {
return 0;
}

}