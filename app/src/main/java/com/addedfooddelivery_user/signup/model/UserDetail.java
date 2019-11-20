package com.addedfooddelivery_user.signup.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetail implements Parcelable
{

@SerializedName("customerID")
@Expose
private Integer customerID;
@SerializedName("loginType")
@Expose
private String loginType;
@SerializedName("userName")
@Expose
private String userName;
@SerializedName("email")
@Expose
private String email;
@SerializedName("socialID")
@Expose
private String socialID;
@SerializedName("accessToken")
@Expose
private String accessToken;
public final static Parcelable.Creator<UserDetail> CREATOR = new Creator<UserDetail>() {


@SuppressWarnings({
"unchecked"
})
public UserDetail createFromParcel(Parcel in) {
return new UserDetail(in);
}

public UserDetail[] newArray(int size) {
return (new UserDetail[size]);
}

}
;

protected UserDetail(Parcel in) {
this.customerID = ((Integer) in.readValue((Integer.class.getClassLoader())));
this.loginType = ((String) in.readValue((String.class.getClassLoader())));
this.userName = ((String) in.readValue((String.class.getClassLoader())));
this.email = ((String) in.readValue((String.class.getClassLoader())));
this.socialID = ((String) in.readValue((String.class.getClassLoader())));
this.accessToken = ((String) in.readValue((String.class.getClassLoader())));
}

/**
* No args constructor for use in serialization
*
*/
public UserDetail() {
}

/**
*
* @param loginType
* @param socialID
* @param customerID
* @param userName
* @param accessToken
* @param email
*/
public UserDetail(Integer customerID, String loginType, String userName, String email, String socialID, String accessToken) {
super();
this.customerID = customerID;
this.loginType = loginType;
this.userName = userName;
this.email = email;
this.socialID = socialID;
this.accessToken = accessToken;
}

public Integer getCustomerID() {
return customerID;
}

public void setCustomerID(Integer customerID) {
this.customerID = customerID;
}

public String getLoginType() {
return loginType;
}

public void setLoginType(String loginType) {
this.loginType = loginType;
}

public String getUserName() {
return userName;
}

public void setUserName(String userName) {
this.userName = userName;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getSocialID() {
return socialID;
}

public void setSocialID(String socialID) {
this.socialID = socialID;
}

public String getAccessToken() {
return accessToken;
}

public void setAccessToken(String accessToken) {
this.accessToken = accessToken;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(customerID);
dest.writeValue(loginType);
dest.writeValue(userName);
dest.writeValue(email);
dest.writeValue(socialID);
dest.writeValue(accessToken);
}

public int describeContents() {
return 0;
}

}