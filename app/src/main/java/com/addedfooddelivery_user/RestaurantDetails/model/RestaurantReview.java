package com.addedfooddelivery_user.RestaurantDetails.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantReview implements Parcelable
{

@SerializedName("customerID")
@Expose
private Integer customerID;
@SerializedName("userName")
@Expose
private String userName;
@SerializedName("customerAvatar")
@Expose
private String customerAvatar;
@SerializedName("comment")
@Expose
private String comment;
@SerializedName("ratings")
@Expose
private String ratings;
public final static Parcelable.Creator<RestaurantReview> CREATOR = new Creator<RestaurantReview>() {


@SuppressWarnings({
"unchecked"
})
public RestaurantReview createFromParcel(Parcel in) {
return new RestaurantReview(in);
}

public RestaurantReview[] newArray(int size) {
return (new RestaurantReview[size]);
}

}
;

protected RestaurantReview(Parcel in) {
this.customerID = ((Integer) in.readValue((Integer.class.getClassLoader())));
this.userName = ((String) in.readValue((String.class.getClassLoader())));
this.customerAvatar = ((String) in.readValue((String.class.getClassLoader())));
this.comment = ((String) in.readValue((String.class.getClassLoader())));
this.ratings = ((String) in.readValue((String.class.getClassLoader())));
}

/**
* No args constructor for use in serialization
*
*/
public RestaurantReview() {
}

/**
*
* @param customerAvatar
* @param ratings
* @param customerID
* @param comment
* @param userName
*/
public RestaurantReview(Integer customerID, String userName, String customerAvatar, String comment, String ratings) {
super();
this.customerID = customerID;
this.userName = userName;
this.customerAvatar = customerAvatar;
this.comment = comment;
this.ratings = ratings;
}

public Integer getCustomerID() {
return customerID;
}

public void setCustomerID(Integer customerID) {
this.customerID = customerID;
}

public String getUserName() {
return userName;
}

public void setUserName(String userName) {
this.userName = userName;
}

public String getCustomerAvatar() {
return customerAvatar;
}

public void setCustomerAvatar(String customerAvatar) {
this.customerAvatar = customerAvatar;
}

public String getComment() {
return comment;
}

public void setComment(String comment) {
this.comment = comment;
}

public String getRatings() {
return ratings;
}

public void setRatings(String ratings) {
this.ratings = ratings;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(customerID);
dest.writeValue(userName);
dest.writeValue(customerAvatar);
dest.writeValue(comment);
dest.writeValue(ratings);
}

public int describeContents() {
return 0;
}

}