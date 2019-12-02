package com.addedfooddelivery_user.cart.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CouponList implements Parcelable {

    public final static Parcelable.Creator<CouponList> CREATOR = new Creator<CouponList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CouponList createFromParcel(Parcel in) {
            return new CouponList(in);
        }

        public CouponList[] newArray(int size) {
            return (new CouponList[size]);
        }

    };
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private List<CouponListData> data = null;
    @SerializedName("message")
    @Expose
    private String message;

    protected CouponList(Parcel in) {
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.data, (CouponListData.class.getClassLoader()));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CouponList() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<CouponListData> getData() {
        return data;
    }

    public void setData(List<CouponListData> data) {
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