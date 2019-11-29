package com.addedfooddelivery_user.cart.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartDataResponce implements Parcelable
{

@SerializedName("status")
@Expose
private Integer status;
@SerializedName("data")
@Expose
private CartData data;
@SerializedName("message")
@Expose
private String message;
public final static Parcelable.Creator<CartDataResponce> CREATOR = new Creator<CartDataResponce>() {


@SuppressWarnings({
"unchecked"
})
public CartDataResponce createFromParcel(Parcel in) {
return new CartDataResponce(in);
}

public CartDataResponce[] newArray(int size) {
return (new CartDataResponce[size]);
}

}
;

protected CartDataResponce(Parcel in) {
this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
this.data = ((CartData) in.readValue((CartData.class.getClassLoader())));
this.message = ((String) in.readValue((String.class.getClassLoader())));
}

/**
* No args constructor for use in serialization
*
*/
public CartDataResponce() {
}

/**
*
* @param data
* @param message
* @param status
*/
public CartDataResponce(Integer status, CartData data, String message) {
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

public CartData getData() {
return data;
}

public void setData(CartData data) {
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