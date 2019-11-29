package com.addedfooddelivery_user.cart.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDefaultAddress implements Parcelable
{

@SerializedName("customerAddressID")
@Expose
private Integer customerAddressID;
@SerializedName("adderess")
@Expose
private String adderess;
@SerializedName("adderessLatitude")
@Expose
private String adderessLatitude;
@SerializedName("adderessLongitude")
@Expose
private String adderessLongitude;
public final static Parcelable.Creator<UserDefaultAddress> CREATOR = new Creator<UserDefaultAddress>() {


@SuppressWarnings({
"unchecked"
})
public UserDefaultAddress createFromParcel(Parcel in) {
return new UserDefaultAddress(in);
}

public UserDefaultAddress[] newArray(int size) {
return (new UserDefaultAddress[size]);
}

}
;

protected UserDefaultAddress(Parcel in) {
this.customerAddressID = ((Integer) in.readValue((Integer.class.getClassLoader())));
this.adderess = ((String) in.readValue((String.class.getClassLoader())));
this.adderessLatitude = ((String) in.readValue((String.class.getClassLoader())));
this.adderessLongitude = ((String) in.readValue((String.class.getClassLoader())));
}

/**
* No args constructor for use in serialization
*
*/
public UserDefaultAddress() {
}

/**
*
* @param customerAddressID
* @param adderessLongitude
* @param adderessLatitude
* @param adderess
*/
public UserDefaultAddress(Integer customerAddressID, String adderess, String adderessLatitude, String adderessLongitude) {
super();
this.customerAddressID = customerAddressID;
this.adderess = adderess;
this.adderessLatitude = adderessLatitude;
this.adderessLongitude = adderessLongitude;
}

public Integer getCustomerAddressID() {
return customerAddressID;
}

public void setCustomerAddressID(Integer customerAddressID) {
this.customerAddressID = customerAddressID;
}

public String getAdderess() {
return adderess;
}

public void setAdderess(String adderess) {
this.adderess = adderess;
}

public String getAdderessLatitude() {
return adderessLatitude;
}

public void setAdderessLatitude(String adderessLatitude) {
this.adderessLatitude = adderessLatitude;
}

public String getAdderessLongitude() {
return adderessLongitude;
}

public void setAdderessLongitude(String adderessLongitude) {
this.adderessLongitude = adderessLongitude;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(customerAddressID);
dest.writeValue(adderess);
dest.writeValue(adderessLatitude);
dest.writeValue(adderessLongitude);
}

public int describeContents() {
return 0;
}

}