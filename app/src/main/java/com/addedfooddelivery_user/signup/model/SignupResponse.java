package com.addedfooddelivery_user.signup.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignupResponse implements Parcelable
{

@SerializedName("status")
@Expose
private Integer status;
@SerializedName("data")
@Expose
private Data data;
@SerializedName("message")
@Expose
private String message;
public final static Parcelable.Creator<SignupResponse> CREATOR = new Creator<SignupResponse>() {


@SuppressWarnings({
"unchecked"
})
public SignupResponse createFromParcel(Parcel in) {
return new SignupResponse(in);
}

public SignupResponse[] newArray(int size) {
return (new SignupResponse[size]);
}

}
;

protected SignupResponse(Parcel in) {
this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
this.data = ((Data) in.readValue((Data.class.getClassLoader())));
this.message = ((String) in.readValue((String.class.getClassLoader())));
}

/**
* No args constructor for use in serialization
*
*/
public SignupResponse() {
}

/**
*
* @param data
* @param message
* @param status
*/
public SignupResponse(Integer status, Data data, String message) {
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

public Data getData() {
return data;
}

public void setData(Data data) {
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