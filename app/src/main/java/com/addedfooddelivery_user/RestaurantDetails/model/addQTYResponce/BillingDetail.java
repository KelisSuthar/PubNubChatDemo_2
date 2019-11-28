package com.addedfooddelivery_user.RestaurantDetails.model.addQTYResponce;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillingDetail implements Parcelable {

    @SerializedName("totleItem")
    @Expose
    private Integer totleItem;
    @SerializedName("totle")
    @Expose
    private String totle;
    public final static Parcelable.Creator<BillingDetail> CREATOR = new Creator<BillingDetail>() {


        @SuppressWarnings({
                "unchecked"
        })
        public BillingDetail createFromParcel(Parcel in) {
            return new BillingDetail(in);
        }

        public BillingDetail[] newArray(int size) {
            return (new BillingDetail[size]);
        }

    };

    protected BillingDetail(Parcel in) {
        this.totleItem = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totle = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public BillingDetail() {
    }

    /**
     * @param totleItem
     * @param totle
     */
    public BillingDetail(Integer totleItem, String totle) {
        super();
        this.totleItem = totleItem;
        this.totle = totle;
    }

    public Integer getTotleItem() {
        return totleItem;
    }

    public void setTotleItem(Integer totleItem) {
        this.totleItem = totleItem;
    }

    public String getTotle() {
        return totle;
    }

    public void setTotle(String totle) {
        this.totle = totle;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(totleItem);
        dest.writeValue(totle);
    }

    public int describeContents() {
        return 0;
    }

}