package com.addedfooddelivery_user.verificationPhone.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhoneVerifyResponse implements Parcelable {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private Integer data;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<PhoneVerifyResponse> CREATOR = new Creator<PhoneVerifyResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PhoneVerifyResponse createFromParcel(Parcel in) {
            return new PhoneVerifyResponse(in);
        }

        public PhoneVerifyResponse[] newArray(int size) {
            return (new PhoneVerifyResponse[size]);
        }

    };

    protected PhoneVerifyResponse(Parcel in) {
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.data = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public PhoneVerifyResponse() {
    }

    /**
     * @param data
     * @param message
     * @param status
     */
    public PhoneVerifyResponse(Integer status, Integer data, String message) {
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

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
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