package com.addedfooddelivery_user.cart.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.addedfooddelivery_user.RestaurantDetails.model.RestaurantImage;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CartDetail implements Parcelable
{

@SerializedName("restaurantID")
@Expose
private Integer restaurantID;
@SerializedName("restaurantName")
@Expose
private String restaurantName;
@SerializedName("restaurantAddress")
@Expose
private String restaurantAddress;
@SerializedName("restaurantLatitude")
@Expose
private String restaurantLatitude;
@SerializedName("restaurantLongitude")
@Expose
private String restaurantLongitude;
@SerializedName("restaurantImages")
@Expose
private List<RestaurantImage> restaurantImages = new ArrayList<RestaurantImage>();
@SerializedName("restaurantItem")
@Expose
private List<RestaurantItem> restaurantItem = new ArrayList<RestaurantItem>();
public final static Parcelable.Creator<CartDetail> CREATOR = new Creator<CartDetail>() {


@SuppressWarnings({
"unchecked"
})
public CartDetail createFromParcel(Parcel in) {
return new CartDetail(in);
}

public CartDetail[] newArray(int size) {
return (new CartDetail[size]);
}

}
;

protected CartDetail(Parcel in) {
this.restaurantID = ((Integer) in.readValue((Integer.class.getClassLoader())));
this.restaurantName = ((String) in.readValue((String.class.getClassLoader())));
this.restaurantAddress = ((String) in.readValue((String.class.getClassLoader())));
this.restaurantLatitude = ((String) in.readValue((String.class.getClassLoader())));
this.restaurantLongitude = ((String) in.readValue((String.class.getClassLoader())));
in.readList(this.restaurantImages, (RestaurantImage.class.getClassLoader()));
in.readList(this.restaurantItem, (RestaurantItem.class.getClassLoader()));
}

/**
* No args constructor for use in serialization
*
*/
public CartDetail() {
}

/**
*
* @param restaurantAddress
* @param restaurantImages
* @param restaurantName
* @param restaurantLatitude
* @param restaurantItem
* @param restaurantID
* @param restaurantLongitude
*/
public CartDetail(Integer restaurantID, String restaurantName, String restaurantAddress, String restaurantLatitude, String restaurantLongitude, List<RestaurantImage> restaurantImages, List<RestaurantItem> restaurantItem) {
super();
this.restaurantID = restaurantID;
this.restaurantName = restaurantName;
this.restaurantAddress = restaurantAddress;
this.restaurantLatitude = restaurantLatitude;
this.restaurantLongitude = restaurantLongitude;
this.restaurantImages = restaurantImages;
this.restaurantItem = restaurantItem;
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

public String getRestaurantLatitude() {
return restaurantLatitude;
}

public void setRestaurantLatitude(String restaurantLatitude) {
this.restaurantLatitude = restaurantLatitude;
}

public String getRestaurantLongitude() {
return restaurantLongitude;
}

public void setRestaurantLongitude(String restaurantLongitude) {
this.restaurantLongitude = restaurantLongitude;
}

public List<RestaurantImage> getRestaurantImages() {
return restaurantImages;
}

public void setRestaurantImages(List<RestaurantImage> restaurantImages) {
this.restaurantImages = restaurantImages;
}

public List<RestaurantItem> getRestaurantItem() {
return restaurantItem;
}

public void setRestaurantItem(List<RestaurantItem> restaurantItem) {
this.restaurantItem = restaurantItem;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(restaurantID);
dest.writeValue(restaurantName);
dest.writeValue(restaurantAddress);
dest.writeValue(restaurantLatitude);
dest.writeValue(restaurantLongitude);
dest.writeList(restaurantImages);
dest.writeList(restaurantItem);
}

public int describeContents() {
return 0;
}

}