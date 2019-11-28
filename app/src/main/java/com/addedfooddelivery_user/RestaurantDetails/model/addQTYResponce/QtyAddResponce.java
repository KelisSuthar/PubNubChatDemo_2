package com.addedfooddelivery_user.RestaurantDetails.model.addQTYResponce;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QtyAddResponce implements Parcelable {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private QtyResponceData data;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<QtyAddResponce> CREATOR = new Creator<QtyAddResponce>() {


        @SuppressWarnings({
                "unchecked"
        })
        public QtyAddResponce createFromParcel(Parcel in) {
            return new QtyAddResponce(in);
        }

        public QtyAddResponce[] newArray(int size) {
            return (new QtyAddResponce[size]);
        }

    };

    protected QtyAddResponce(Parcel in) {
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.data = ((QtyResponceData) in.readValue((QtyResponceData.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public QtyAddResponce() {
    }

    /**
     * @param data
     * @param message
     * @param status
     */
    public QtyAddResponce(Integer status, QtyResponceData data, String message) {
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

    public QtyResponceData getData() {
        return data;
    }

    public void setData(QtyResponceData data) {
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