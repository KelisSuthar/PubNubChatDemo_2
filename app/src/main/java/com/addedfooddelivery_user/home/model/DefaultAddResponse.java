package com.addedfooddelivery_user.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DefaultAddResponse implements Parcelable {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private DefaultAddData data;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<DefaultAddResponse> CREATOR = new Creator<DefaultAddResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DefaultAddResponse createFromParcel(Parcel in) {
            return new DefaultAddResponse(in);
        }

        public DefaultAddResponse[] newArray(int size) {
            return (new DefaultAddResponse[size]);
        }

    };

    protected DefaultAddResponse(Parcel in) {
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.data = ((DefaultAddData) in.readValue((DefaultAddData.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public DefaultAddResponse() {
    }

    /**
     * @param data
     * @param message
     * @param status
     */
    public DefaultAddResponse(Integer status, DefaultAddData data, String message) {
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

    public DefaultAddData getData() {
        return data;
    }

    public void setData(DefaultAddData data) {
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