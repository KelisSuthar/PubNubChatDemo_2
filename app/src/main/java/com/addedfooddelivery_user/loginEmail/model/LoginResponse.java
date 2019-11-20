package com.addedfooddelivery_user.loginEmail.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse implements Parcelable
{

@SerializedName("status")
@Expose
private Integer status;
@SerializedName("data")
@Expose
private LoginData data;
@SerializedName("message")
@Expose
private String message;
public final static Parcelable.Creator<LoginResponse> CREATOR = new Creator<LoginResponse>() {


@SuppressWarnings({
"unchecked"
})
public LoginResponse createFromParcel(Parcel in) {
return new LoginResponse(in);
}

public LoginResponse[] newArray(int size) {
return (new LoginResponse[size]);
}

}
;

protected LoginResponse(Parcel in) {
this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
this.data = ((LoginData) in.readValue((LoginData.class.getClassLoader())));
this.message = ((String) in.readValue((String.class.getClassLoader())));
}

/**
* No args constructor for use in serialization
*
*/
public LoginResponse() {
}

/**
*
* @param data
* @param message
* @param status
*/
public LoginResponse(Integer status, LoginData data, String message) {
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

public LoginData getData() {
return data;
}

public void setData(LoginData data) {
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