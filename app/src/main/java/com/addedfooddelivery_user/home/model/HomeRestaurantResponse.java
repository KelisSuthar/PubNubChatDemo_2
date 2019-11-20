package com.addedfooddelivery_user.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeRestaurantResponse implements Parcelable {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private HomeRestaurantData data;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<HomeRestaurantResponse> CREATOR = new Creator<HomeRestaurantResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public HomeRestaurantResponse createFromParcel(Parcel in) {
            return new HomeRestaurantResponse(in);
        }

        public HomeRestaurantResponse[] newArray(int size) {
            return (new HomeRestaurantResponse[size]);
        }

    };

    protected HomeRestaurantResponse(Parcel in) {
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.data = ((HomeRestaurantData) in.readValue((HomeRestaurantData.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public HomeRestaurantResponse() {
    }

    /**
     * @param data
     * @param message
     * @param status
     */
    public HomeRestaurantResponse(Integer status, HomeRestaurantData data, String message) {
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

    public HomeRestaurantData getData() {
        return data;
    }

    public void setData(HomeRestaurantData data) {
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