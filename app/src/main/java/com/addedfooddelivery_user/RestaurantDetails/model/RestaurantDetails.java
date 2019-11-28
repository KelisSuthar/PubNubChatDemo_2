package com.addedfooddelivery_user.RestaurantDetails.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantDetails implements Parcelable
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
@SerializedName("restaurantApt")
@Expose
private String restaurantApt;
@SerializedName("restaurantFirstName")
@Expose
private String restaurantFirstName;
@SerializedName("restaurantLastName")
@Expose
private String restaurantLastName;
@SerializedName("yourRole")
@Expose
private String yourRole;
@SerializedName("restaurantRatingAVG")
@Expose
private Integer restaurantRatingAVG;
public final static Parcelable.Creator<RestaurantDetails> CREATOR = new Creator<RestaurantDetails>() {


@SuppressWarnings({
"unchecked"
})
public RestaurantDetails createFromParcel(Parcel in) {
return new RestaurantDetails(in);
}

public RestaurantDetails[] newArray(int size) {
return (new RestaurantDetails[size]);
}

}
;

protected RestaurantDetails(Parcel in) {
this.restaurantID = ((Integer) in.readValue((Integer.class.getClassLoader())));
this.restaurantName = ((String) in.readValue((String.class.getClassLoader())));
this.restaurantAddress = ((String) in.readValue((String.class.getClassLoader())));
this.restaurantLatitude = ((String) in.readValue((String.class.getClassLoader())));
this.restaurantLongitude = ((String) in.readValue((String.class.getClassLoader())));
this.restaurantApt = ((String) in.readValue((String.class.getClassLoader())));
this.restaurantFirstName = ((String) in.readValue((String.class.getClassLoader())));
this.restaurantLastName = ((String) in.readValue((String.class.getClassLoader())));
this.yourRole = ((String) in.readValue((String.class.getClassLoader())));
this.restaurantRatingAVG = ((Integer) in.readValue((Integer.class.getClassLoader())));
}

/**
* No args constructor for use in serialization
*
*/
public RestaurantDetails() {
}

/**
*
* @param restaurantApt
* @param restaurantAddress
* @param restaurantName
* @param restaurantLastName
* @param restaurantFirstName
* @param restaurantLatitude
* @param restaurantID
* @param restaurantRatingAVG
* @param restaurantLongitude
* @param yourRole
*/
public RestaurantDetails(Integer restaurantID, String restaurantName, String restaurantAddress, String restaurantLatitude, String restaurantLongitude, String restaurantApt, String restaurantFirstName, String restaurantLastName, String yourRole, Integer restaurantRatingAVG) {
super();
this.restaurantID = restaurantID;
this.restaurantName = restaurantName;
this.restaurantAddress = restaurantAddress;
this.restaurantLatitude = restaurantLatitude;
this.restaurantLongitude = restaurantLongitude;
this.restaurantApt = restaurantApt;
this.restaurantFirstName = restaurantFirstName;
this.restaurantLastName = restaurantLastName;
this.yourRole = yourRole;
this.restaurantRatingAVG = restaurantRatingAVG;
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

public String getRestaurantApt() {
return restaurantApt;
}

public void setRestaurantApt(String restaurantApt) {
this.restaurantApt = restaurantApt;
}

public String getRestaurantFirstName() {
return restaurantFirstName;
}

public void setRestaurantFirstName(String restaurantFirstName) {
this.restaurantFirstName = restaurantFirstName;
}

public String getRestaurantLastName() {
return restaurantLastName;
}

public void setRestaurantLastName(String restaurantLastName) {
this.restaurantLastName = restaurantLastName;
}

public String getYourRole() {
return yourRole;
}

public void setYourRole(String yourRole) {
this.yourRole = yourRole;
}

public Integer getRestaurantRatingAVG() {
return restaurantRatingAVG;
}

public void setRestaurantRatingAVG(Integer restaurantRatingAVG) {
this.restaurantRatingAVG = restaurantRatingAVG;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(restaurantID);
dest.writeValue(restaurantName);
dest.writeValue(restaurantAddress);
dest.writeValue(restaurantLatitude);
dest.writeValue(restaurantLongitude);
dest.writeValue(restaurantApt);
dest.writeValue(restaurantFirstName);
dest.writeValue(restaurantLastName);
dest.writeValue(yourRole);
dest.writeValue(restaurantRatingAVG);
}

public int describeContents() {
return 0;
}

}


