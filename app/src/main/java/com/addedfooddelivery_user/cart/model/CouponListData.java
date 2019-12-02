package com.addedfooddelivery_user.cart.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CouponListData implements Parcelable {

    public final static Parcelable.Creator<CouponListData> CREATOR = new Creator<CouponListData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CouponListData createFromParcel(Parcel in) {
            return new CouponListData(in);
        }

        public CouponListData[] newArray(int size) {
            return (new CouponListData[size]);
        }

    };
    @SerializedName("couponID")
    @Expose
    private Integer couponID;
    @SerializedName("couponCode")
    @Expose
    private String couponCode;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("couponType")
    @Expose
    private String couponType;
    @SerializedName("couponExpireDate")
    @Expose
    private String couponExpireDate;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;

    protected CouponListData(Parcel in) {
        this.couponID = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.couponCode = ((String) in.readValue((String.class.getClassLoader())));
        this.amount = ((String) in.readValue((String.class.getClassLoader())));
        this.couponType = ((String) in.readValue((String.class.getClassLoader())));
        this.couponExpireDate = ((String) in.readValue((String.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CouponListData() {
    }

    public Integer getCouponID() {
        return couponID;
    }

    public void setCouponID(Integer couponID) {
        this.couponID = couponID;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public String getCouponExpireDate() {
        return couponExpireDate;
    }

    public void setCouponExpireDate(String couponExpireDate) {
        this.couponExpireDate = couponExpireDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(couponID);
        dest.writeValue(couponCode);
        dest.writeValue(amount);
        dest.writeValue(couponType);
        dest.writeValue(couponExpireDate);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
    }

    public int describeContents() {
        return 0;
    }

}