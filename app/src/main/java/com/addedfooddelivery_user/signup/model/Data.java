package com.addedfooddelivery_user.signup.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Parcelable
{

@SerializedName("userDetail")
@Expose
private UserDetail userDetail;
public final static Parcelable.Creator<Data> CREATOR = new Creator<Data>() {


@SuppressWarnings({
"unchecked"
})
public Data createFromParcel(Parcel in) {
return new Data(in);
}

public Data[] newArray(int size) {
return (new Data[size]);
}

}
;

protected Data(Parcel in) {
this.userDetail = ((UserDetail) in.readValue((UserDetail.class.getClassLoader())));
}

/**
* No args constructor for use in serialization
*
*/
public Data() {
}

/**
*
* @param userDetail
*/
public Data(UserDetail userDetail) {
super();
this.userDetail = userDetail;
}

public UserDetail getUserDetail() {
return userDetail;
}

public void setUserDetail(UserDetail userDetail) {
this.userDetail = userDetail;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(userDetail);
}

public int describeContents() {
return 0;
}

}