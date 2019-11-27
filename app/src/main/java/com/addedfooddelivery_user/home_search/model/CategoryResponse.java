package com.addedfooddelivery_user.home_search.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CategoryResponse implements Parcelable
{

@SerializedName("status")
@Expose
private Integer status;
@SerializedName("data")
@Expose
private List<CategoryData> data = new ArrayList<CategoryData>();
@SerializedName("message")
@Expose
private String message;
public final static Parcelable.Creator<CategoryResponse> CREATOR = new Creator<CategoryResponse>() {


@SuppressWarnings({
"unchecked"
})
public CategoryResponse createFromParcel(Parcel in) {
return new CategoryResponse(in);
}

public CategoryResponse[] newArray(int size) {
return (new CategoryResponse[size]);
}

}
;

protected CategoryResponse(Parcel in) {
this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
in.readList(this.data, (CategoryData.class.getClassLoader()));
this.message = ((String) in.readValue((String.class.getClassLoader())));
}

/**
* No args constructor for use in serialization
*
*/
public CategoryResponse() {
}

/**
*
* @param data
* @param message
* @param status
*/
public CategoryResponse(Integer status, List<CategoryData> data, String message) {
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

public List<CategoryData> getData() {
return data;
}

public void setData(List<CategoryData> data) {
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