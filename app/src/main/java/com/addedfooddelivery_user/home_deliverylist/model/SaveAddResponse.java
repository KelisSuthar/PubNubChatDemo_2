package com.addedfooddelivery_user.home_deliverylist.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveAddResponse implements Parcelable {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<SaveAddResponse> CREATOR = new Creator<SaveAddResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SaveAddResponse createFromParcel(Parcel in) {
            return new SaveAddResponse(in);
        }

        public SaveAddResponse[] newArray(int size) {
            return (new SaveAddResponse[size]);
        }

    };

    protected SaveAddResponse(Parcel in) {
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public SaveAddResponse() {
    }

    /**
     * @param message
     * @param status
     */
    public SaveAddResponse(Integer status, String message) {
        super();
        this.status = status;
        this.message = message;
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