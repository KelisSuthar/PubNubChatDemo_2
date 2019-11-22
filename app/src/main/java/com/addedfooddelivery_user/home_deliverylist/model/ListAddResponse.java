package com.addedfooddelivery_user.home_deliverylist.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ListAddResponse implements Parcelable
{

@SerializedName("status")
@Expose
private Integer status;
@SerializedName("data")
@Expose
private List<ListAddData> data = new ArrayList<ListAddData>();
@SerializedName("message")
@Expose
private String message;
public final static Parcelable.Creator<ListAddResponse> CREATOR = new Creator<ListAddResponse>() {


@SuppressWarnings({
"unchecked"
})
public ListAddResponse createFromParcel(Parcel in) {
return new ListAddResponse(in);
}

public ListAddResponse[] newArray(int size) {
return (new ListAddResponse[size]);
}

}
;

protected ListAddResponse(Parcel in) {
this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
in.readList(this.data, (ListAddData.class.getClassLoader()));
this.message = ((String) in.readValue((String.class.getClassLoader())));
}

/**
* No args constructor for use in serialization
*
*/
public ListAddResponse() {
}

/**
*
* @param data
* @param message
* @param status
*/
public ListAddResponse(Integer status, List<ListAddData> data, String message) {
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

public List<ListAddData> getData() {
return data;
}

public void setData(List<ListAddData> data) {
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
dest.writeList(data);
dest.writeValue(message);
}

public int describeContents() {
return 0;
}

}