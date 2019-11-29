package com.addedfooddelivery_user.cart.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MayLike implements Parcelable
{

@SerializedName("itemID")
@Expose
private Integer itemID;
@SerializedName("itemName")
@Expose
private String itemName;
@SerializedName("restaurantID")
@Expose
private Integer restaurantID;
@SerializedName("itemPrice")
@Expose
private String itemPrice;
@SerializedName("itemImage")
@Expose
private String itemImage;
public final static Parcelable.Creator<MayLike> CREATOR = new Creator<MayLike>() {


@SuppressWarnings({
"unchecked"
})
public MayLike createFromParcel(Parcel in) {
return new MayLike(in);
}

public MayLike[] newArray(int size) {
return (new MayLike[size]);
}

}
;

protected MayLike(Parcel in) {
this.itemID = ((Integer) in.readValue((Integer.class.getClassLoader())));
this.itemName = ((String) in.readValue((String.class.getClassLoader())));
this.restaurantID = ((Integer) in.readValue((Integer.class.getClassLoader())));
this.itemPrice = ((String) in.readValue((String.class.getClassLoader())));
this.itemImage = ((String) in.readValue((String.class.getClassLoader())));
}

/**
* No args constructor for use in serialization
*
*/
public MayLike() {
}

/**
*
* @param itemID
* @param itemName
* @param itemPrice
* @param restaurantID
* @param itemImage
*/
public MayLike(Integer itemID, String itemName, Integer restaurantID, String itemPrice, String itemImage) {
super();
this.itemID = itemID;
this.itemName = itemName;
this.restaurantID = restaurantID;
this.itemPrice = itemPrice;
this.itemImage = itemImage;
}

public Integer getItemID() {
return itemID;
}

public void setItemID(Integer itemID) {
this.itemID = itemID;
}

public String getItemName() {
return itemName;
}

public void setItemName(String itemName) {
this.itemName = itemName;
}

public Integer getRestaurantID() {
return restaurantID;
}

public void setRestaurantID(Integer restaurantID) {
this.restaurantID = restaurantID;
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

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(itemID);
dest.writeValue(itemName);
dest.writeValue(restaurantID);
dest.writeValue(itemPrice);
dest.writeValue(itemImage);
}

public int describeContents() {
return 0;
}

}