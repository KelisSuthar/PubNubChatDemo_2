package com.addedfooddelivery_user.loginEmail.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginData implements Parcelable
{

@SerializedName("userDetail")
@Expose
private UserDetail userDetail;
public final static Parcelable.Creator<LoginData> CREATOR = new Creator<LoginData>() {


@SuppressWarnings({
"unchecked"
})
public LoginData createFromParcel(Parcel in) {
return new LoginData(in);
}

public LoginData[] newArray(int size) {
return (new LoginData[size]);
}

}
;

protected LoginData(Parcel in) {
this.userDetail = ((UserDetail) in.readValue((UserDetail.class.getClassLoader())));
}

/**
* No args constructor for use in serialization
*
*/
public LoginData() {
}

/**
*
* @param userDetail
*/
public LoginData(UserDetail userDetail) {
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