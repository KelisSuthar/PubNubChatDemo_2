package com.addedfooddelivery_user.cart.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillingDetail implements Parcelable {

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
    @SerializedName("totalAmount")
    @Expose
    private String totalAmount;
    @SerializedName("itemTotle")
    @Expose
    private Integer itemTotle;
    @SerializedName("tax")
    @Expose
    private String tax;
    @SerializedName("deliveryfee")
    @Expose
    private String deliveryfee;
    @SerializedName("to_pay")
    @Expose
    private String toPay;
    @SerializedName("discount")
    @Expose
    private String discount;

    protected BillingDetail(Parcel in) {
        this.totalAmount = ((String) in.readValue((String.class.getClassLoader())));
        this.itemTotle = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.tax = ((String) in.readValue((String.class.getClassLoader())));
        this.deliveryfee = ((String) in.readValue((String.class.getClassLoader())));
        this.toPay = ((String) in.readValue((String.class.getClassLoader())));
        this.discount = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public BillingDetail() {
    }
    /**
     * @param totalAmount
     * @param deliveryfee
     * @param tax
     * @param toPay
     * @param itemTotle
     */
    public BillingDetail(String totalAmount, Integer itemTotle, String tax, String deliveryfee, String toPay,String discount) {
        super();
        this.totalAmount = totalAmount;
        this.itemTotle = itemTotle;
        this.tax = tax;
        this.deliveryfee = deliveryfee;
        this.toPay = toPay;
        this.discount=discount;
    }

    public static Creator<BillingDetail> getCREATOR() {
        return CREATOR;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getItemTotle() {
        return itemTotle;
    }

    public void setItemTotle(Integer itemTotle) {
        this.itemTotle = itemTotle;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getDeliveryfee() {
        return deliveryfee;
    }

    public void setDeliveryfee(String deliveryfee) {
        this.deliveryfee = deliveryfee;
    }

    public String getToPay() {
        return toPay;
    }

    public void setToPay(String toPay) {
        this.toPay = toPay;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(totalAmount);
        dest.writeValue(itemTotle);
        dest.writeValue(tax);
        dest.writeValue(deliveryfee);
        dest.writeValue(toPay);
        dest.writeValue(discount);
    }

    public int describeContents() {
        return 0;
    }

}