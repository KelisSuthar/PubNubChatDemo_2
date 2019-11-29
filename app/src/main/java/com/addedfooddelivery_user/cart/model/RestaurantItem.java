package com.addedfooddelivery_user.cart.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantItem implements Parcelable
{

@SerializedName("cartID")
@Expose
private Integer cartID;
@SerializedName("itemID")
@Expose
private Integer itemID;
@SerializedName("foodCategoryID")
@Expose
private Integer foodCategoryID;
@SerializedName("itemName")
@Expose
private String itemName;
@SerializedName("itemType")
@Expose
private String itemType;
@SerializedName("itemDescription")
@Expose
private String itemDescription;
@SerializedName("itemPrice")
@Expose
private String itemPrice;
@SerializedName("itemQuantity")
@Expose
private Integer itemQuantity;
@SerializedName("itemRestauantCharges")
@Expose
private String itemRestauantCharges;
@SerializedName("itemTaxes")
@Expose
private String itemTaxes;
public final static Parcelable.Creator<RestaurantItem> CREATOR = new Creator<RestaurantItem>() {


@SuppressWarnings({
"unchecked"
})
public RestaurantItem createFromParcel(Parcel in) {
return new RestaurantItem(in);
}

public RestaurantItem[] newArray(int size) {
return (new RestaurantItem[size]);
}

}
;

protected RestaurantItem(Parcel in) {
this.cartID = ((Integer) in.readValue((Integer.class.getClassLoader())));
this.itemID = ((Integer) in.readValue((Integer.class.getClassLoader())));
this.foodCategoryID = ((Integer) in.readValue((Integer.class.getClassLoader())));
this.itemName = ((String) in.readValue((String.class.getClassLoader())));
this.itemType = ((String) in.readValue((String.class.getClassLoader())));
this.itemDescription = ((String) in.readValue((String.class.getClassLoader())));
this.itemPrice = ((String) in.readValue((String.class.getClassLoader())));
this.itemQuantity = ((Integer) in.readValue((Integer.class.getClassLoader())));
this.itemRestauantCharges = ((String) in.readValue((String.class.getClassLoader())));
this.itemTaxes = ((String) in.readValue((String.class.getClassLoader())));
}

/**
* No args constructor for use in serialization
*
*/
public RestaurantItem() {
}

/**
*
* @param itemID
* @param itemName
* @param itemType
* @param foodCategoryID
* @param itemQuantity
* @param itemRestauantCharges
* @param cartID
* @param itemPrice
* @param itemDescription
* @param itemTaxes
*/
public RestaurantItem(Integer cartID, Integer itemID, Integer foodCategoryID, String itemName, String itemType, String itemDescription, String itemPrice, Integer itemQuantity, String itemRestauantCharges, String itemTaxes) {
super();
this.cartID = cartID;
this.itemID = itemID;
this.foodCategoryID = foodCategoryID;
this.itemName = itemName;
this.itemType = itemType;
this.itemDescription = itemDescription;
this.itemPrice = itemPrice;
this.itemQuantity = itemQuantity;
this.itemRestauantCharges = itemRestauantCharges;
this.itemTaxes = itemTaxes;
}

public Integer getCartID() {
return cartID;
}

public void setCartID(Integer cartID) {
this.cartID = cartID;
}

public Integer getItemID() {
return itemID;
}

public void setItemID(Integer itemID) {
this.itemID = itemID;
}

public Integer getFoodCategoryID() {
return foodCategoryID;
}

public void setFoodCategoryID(Integer foodCategoryID) {
this.foodCategoryID = foodCategoryID;
}

public String getItemName() {
return itemName;
}

public void setItemName(String itemName) {
this.itemName = itemName;
}

public String getItemType() {
return itemType;
}

public void setItemType(String itemType) {
this.itemType = itemType;
}

public String getItemDescription() {
return itemDescription;
}

public void setItemDescription(String itemDescription) {
this.itemDescription = itemDescription;
}

public String getItemPrice() {
return itemPrice;
}

public void setItemPrice(String itemPrice) {
this.itemPrice = itemPrice;
}

public Integer getItemQuantity() {
return itemQuantity;
}

public void setItemQuantity(Integer itemQuantity) {
this.itemQuantity = itemQuantity;
}

public String getItemRestauantCharges() {
return itemRestauantCharges;
}

public void setItemRestauantCharges(String itemRestauantCharges) {
this.itemRestauantCharges = itemRestauantCharges;
}

public String getItemTaxes() {
return itemTaxes;
}

public void setItemTaxes(String itemTaxes) {
this.itemTaxes = itemTaxes;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(cartID);
dest.writeValue(itemID);
dest.writeValue(foodCategoryID);
dest.writeValue(itemName);
dest.writeValue(itemType);
dest.writeValue(itemDescription);
dest.writeValue(itemPrice);
dest.writeValue(itemQuantity);
dest.writeValue(itemRestauantCharges);
dest.writeValue(itemTaxes);
}

public int describeContents() {
return 0;
}

}