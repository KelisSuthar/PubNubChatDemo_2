package com.addedfooddelivery_user.RestaurantDetails.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryList implements Parcelable
{

@SerializedName("itemID")
@Expose
private Integer itemID;
@SerializedName("itemName")
@Expose
private String itemName;
@SerializedName("itemQuantity")
@Expose
private Integer itemQuantity;
@SerializedName("itemDescription")
@Expose
private String itemDescription;
@SerializedName("itemComment")
@Expose
private String itemComment;
@SerializedName("itemType")
@Expose
private String itemType;
@SerializedName("itemImage")
@Expose
private String itemImage;
@SerializedName("itemPrice")
@Expose
private String itemPrice;
public final static Parcelable.Creator<CategoryList> CREATOR = new Creator<CategoryList>() {


@SuppressWarnings({
"unchecked"
})
public CategoryList createFromParcel(Parcel in) {
return new CategoryList(in);
}

public CategoryList[] newArray(int size) {
return (new CategoryList[size]);
}

}
;

protected CategoryList(Parcel in) {
this.itemID = ((Integer) in.readValue((Integer.class.getClassLoader())));
this.itemName = ((String) in.readValue((String.class.getClassLoader())));
this.itemQuantity = ((Integer) in.readValue((Integer.class.getClassLoader())));
this.itemDescription = ((String) in.readValue((String.class.getClassLoader())));
this.itemComment = ((String) in.readValue((String.class.getClassLoader())));
this.itemType = ((String) in.readValue((String.class.getClassLoader())));
this.itemImage = ((String) in.readValue((String.class.getClassLoader())));
this.itemPrice = ((String) in.readValue((String.class.getClassLoader())));
}

/**
* No args constructor for use in serialization
*
*/
public CategoryList() {
}

/**
*
* @param itemID
* @param itemName
* @param itemType
* @param itemQuantity
* @param itemPrice
* @param itemDescription
* @param itemComment
* @param itemImage
*/
public CategoryList(Integer itemID, String itemName, Integer itemQuantity, String itemDescription, String itemComment, String itemType, String itemImage, String itemPrice) {
super();
this.itemID = itemID;
this.itemName = itemName;
this.itemQuantity = itemQuantity;
this.itemDescription = itemDescription;
this.itemComment = itemComment;
this.itemType = itemType;
this.itemImage = itemImage;
this.itemPrice = itemPrice;
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

public Integer getItemQuantity() {
return itemQuantity;
}

public void setItemQuantity(Integer itemQuantity) {
this.itemQuantity = itemQuantity;
}

public String getItemDescription() {
return itemDescription;
}

public void setItemDescription(String itemDescription) {
this.itemDescription = itemDescription;
}

public String getItemComment() {
return itemComment;
}

public void setItemComment(String itemComment) {
this.itemComment = itemComment;
}

public String getItemType() {
return itemType;
}

public void setItemType(String itemType) {
this.itemType = itemType;
}

public String getItemImage() {
return itemImage;
}

public void setItemImage(String itemImage) {
this.itemImage = itemImage;
}

public String getItemPrice() {
return itemPrice;
}

public void setItemPrice(String itemPrice) {
this.itemPrice = itemPrice;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(itemID);
dest.writeValue(itemName);
dest.writeValue(itemQuantity);
dest.writeValue(itemDescription);
dest.writeValue(itemComment);
dest.writeValue(itemType);
dest.writeValue(itemImage);
dest.writeValue(itemPrice);
}

public int describeContents() {
return 0;
}

}