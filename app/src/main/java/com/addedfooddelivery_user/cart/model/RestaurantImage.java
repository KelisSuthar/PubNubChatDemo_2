package com.addedfooddelivery_user.cart.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantImage implements Parcelable
{

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
*/
public RestaurantImage(String restauantImage) {
super();
this.restauantImage = restauantImage;
}

public String getRestauantImage() {
return restauantImage;
}

public void setRestauantImage(String restauantImage) {
this.restauantImage = restauantImage;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(restauantImage);
}

public int describeContents() {
return 0;
}

}