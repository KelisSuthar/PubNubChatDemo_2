package com.addedfooddelivery_user.verifyPhoneOtp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhoneOtpResponse implements Parcelable {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private Integer data;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<PhoneOtpResponse> CREATOR = new Creator<PhoneOtpResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PhoneOtpResponse createFromParcel(Parcel in) {
            return new PhoneOtpResponse(in);
        }

        public PhoneOtpResponse[] newArray(int size) {
            return (new PhoneOtpResponse[size]);
        }

    };

    protected PhoneOtpResponse(Parcel in) {
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.data = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public PhoneOtpResponse() {
    }

    /**
     * @param data
     * @param message
     * @param status
     */
    public PhoneOtpResponse(Integer status, Integer data, String message) {
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