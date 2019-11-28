package com.addedfooddelivery_user.RestaurantDetails.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantImage implements Parcelable
{

@SerializedName("restauantImagesID")
@Expose
private Integer restauantImagesID;
@SerializedName("restaurantID")
@Expose
private Integer restaurantID;
@SerializedName("restauantImage")
@Expose
private String restauantImage;
public final static Parcelable.Creator<RestaurantImage> CREATOR = new Creator<RestaurantImage>() {


@SuppressWarnings({
"unchecked"
})
public RestaurantImage createFromParcel(Parcel in) {
return new RestaurantImage(in);
}

public RestaurantImage[] newArray(int size) {
return (new RestaurantImage[size]);
}

}
;

protected RestaurantImage(Parcel in) {
this.restauantImagesID = ((Integer) in.readValue((Integer.class.getClassLoader())));
this.restaurantID = ((Integer) in.readValue((Integer.class.getClassLoader())));
this.restauantImage = ((String) in.readValue((String.class.getClassLoader())));
}

/**
* No args constructor for use in serialization
*
*/
public RestaurantImage() {
}

/**
*
* @param restauantImage
* @param restauantImagesID
* @param restaurantID
*/
public RestaurantImage(Integer restauantImagesID, Integer restaurantID, String restauantImage) {
super();
this.restauantImagesID = restauantImagesID;
this.restaurantID = restaurantID;
this.restauantImage = restauantImage;
}

public Integer getRestauantImagesID() {
return restauantImagesID;
}

public void setRestauantImagesID(Integer restauantImagesID) {
this.restauantImagesID = restauantImagesID;
}

public Integer getRestaurantID() {
return restaurantID;
}

public void setRestaurantID(Integer restaurantID) {
this.restaurantID = restaurantID;
}

public String getRestauantImage() {
return restauantImage;
}

public void setRestauantImage(String restauantImage) {
this.restauantImage = restauantImage;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(restauantImagesID);
dest.writeValue(restaurantID);
dest.writeValue(restauantImage);
}

public int describeContents() {
return 0;
}

}