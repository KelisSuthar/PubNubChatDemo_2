package com.addedfooddelivery_user.restaurantList.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AllRestCategoryResponse implements Parcelable {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private List<AllRestCategoryData> data = new ArrayList<AllRestCategoryData>();
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<AllRestCategoryResponse> CREATOR = new Creator<AllRestCategoryResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AllRestCategoryResponse createFromParcel(Parcel in) {
            return new AllRestCategoryResponse(in);
        }

        public AllRestCategoryResponse[] newArray(int size) {
            return (new AllRestCategoryResponse[size]);
        }

    };

    protected AllRestCategoryResponse(Parcel in) {
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.data, (AllRestCategoryData.class.getClassLoader()));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public AllRestCategoryResponse() {
    }

    /**
     * @param data
     * @param message
     * @param status
     */
    public AllRestCategoryResponse(Integer status, List<AllRestCategoryData> data, String message) {
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

    public List<AllRestCategoryData> getData() {
        return data;
    }

    public void setData(List<AllRestCategoryData> data) {
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