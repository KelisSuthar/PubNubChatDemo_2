package com.addedfooddelivery_user.RestaurantDetails.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestDetailsResponse implements Parcelable
{

@SerializedName("status")
@Expose
private Integer status;
@SerializedName("data")
@Expose
private CategoryDataResponce data;
@SerializedName("message")
@Expose
private String message;
public final static Parcelable.Creator<RestDetailsResponse> CREATOR = new Creator<RestDetailsResponse>() {


@SuppressWarnings({
"unchecked"
})
public RestDetailsResponse createFromParcel(Parcel in) {
return new RestDetailsResponse(in);
}

public RestDetailsResponse[] newArray(int size) {
return (new RestDetailsResponse[size]);
}

}
;

protected RestDetailsResponse(Parcel in) {
this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
this.data = ((CategoryDataResponce) in.readValue((CategoryDataResponce.class.getClassLoader())));
this.message = ((String) in.readValue((String.class.getClassLoader())));
}

/**
* No args constructor for use in serialization
*
*/
public RestDetailsResponse() {
}

/**
*
* @param data
* @param message
* @param status
*/
public RestDetailsResponse(Integer status, CategoryDataResponce data, String message) {
super();
this.status = status;
this.data = data;
this.message = message;
}

public Integer getStatus() {
return status;
}

public void setStatus(Integer status) {
this.status = status;
}

public CategoryDataResponce getData() {
return data;
}

public void setData(CategoryDataResponce data) {
this.data = data;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(status);
dest.writeValue(data);
dest.writeValue(message);
}

public int describeContents() {
return 0;
}

}