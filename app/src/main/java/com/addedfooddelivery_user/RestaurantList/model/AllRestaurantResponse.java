package com.addedfooddelivery_user.RestaurantList.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AllRestaurantResponse implements Parcelable {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private List<AllRestaurantData> data = new ArrayList<AllRestaurantData>();
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<AllRestaurantResponse> CREATOR = new Creator<AllRestaurantResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AllRestaurantResponse createFromParcel(Parcel in) {
            return new AllRestaurantResponse(in);
        }

        public AllRestaurantResponse[] newArray(int size) {
            return (new AllRestaurantResponse[size]);
        }

    };

    protected AllRestaurantResponse(Parcel in) {
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.data, (AllRestaurantData.class.getClassLoader()));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public AllRestaurantResponse() {
    }

    /**
     * @param data
     * @param message
     * @param status
     */
    public AllRestaurantResponse(Integer status, List<AllRestaurantData> data, String message) {
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

    public List<AllRestaurantData> getData() {
        return data;
    }

    public void setData(List<AllRestaurantData> data) {
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