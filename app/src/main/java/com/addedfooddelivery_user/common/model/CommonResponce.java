package com.addedfooddelivery_user.common.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommonResponce implements Parcelable {

    public final static Parcelable.Creator<CommonResponce> CREATOR = new Creator<CommonResponce>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CommonResponce createFromParcel(Parcel in) {
            return new CommonResponce(in);
        }

        public CommonResponce[] newArray(int size) {
            return (new CommonResponce[size]);
        }

    };
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;

    protected CommonResponce(Parcel in) {
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CommonResponce() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
    }

    public int describeContents() {
        return 0;
    }

}