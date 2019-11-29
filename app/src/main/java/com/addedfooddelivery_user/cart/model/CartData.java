package com.addedfooddelivery_user.cart.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CartData implements Parcelable {

    @SerializedName("CartDetails")
    @Expose
    private List<CartDetail> cartDetails = new ArrayList<CartDetail>();
    @SerializedName("billing_detail")
    @Expose
    private BillingDetail billingDetail;
    @SerializedName("customerDefaultCard")
    @Expose
    private List<Object> customerDefaultCard = new ArrayList<Object>();
    @SerializedName("UserDefaultAddress")
    @Expose
    private UserDefaultAddress userDefaultAddress;
    @SerializedName("may_like")
    @Expose
    private List<MayLike> mayLike = new ArrayList<MayLike>();
    public final static Parcelable.Creator<CartData> CREATOR = new Creator<CartData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CartData createFromParcel(Parcel in) {
            return new CartData(in);
        }

        public CartData[] newArray(int size) {
            return (new CartData[size]);
        }

    };

    protected CartData(Parcel in) {
        in.readList(this.cartDetails, (CartDetail.class.getClassLoader()));
        this.billingDetail = ((BillingDetail) in.readValue((BillingDetail.class.getClassLoader())));
        in.readList(this.customerDefaultCard, (java.lang.Object.class.getClassLoader()));
        this.userDefaultAddress = ((UserDefaultAddress) in.readValue((UserDefaultAddress.class.getClassLoader())));
        in.readList(this.mayLike, (MayLike.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     */
    public CartData() {
    }

    /**
     * @param userDefaultAddress
     * @param mayLike
     * @param cartDetails
     * @param billingDetail
     * @param customerDefaultCard
     */
    public CartData(List<CartDetail> cartDetails, BillingDetail billingDetail, List<Object> customerDefaultCard, UserDefaultAddress userDefaultAddress, List<MayLike> mayLike) {
        super();
        this.cartDetails = cartDetails;
        this.billingDetail = billingDetail;
        this.customerDefaultCard = customerDefaultCard;
        this.userDefaultAddress = userDefaultAddress;
        this.mayLike = mayLike;
    }

    public List<CartDetail> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(List<CartDetail> cartDetails) {
        this.cartDetails = cartDetails;
    }

    public BillingDetail getBillingDetail() {
        return billingDetail;
    }

    public void setBillingDetail(BillingDetail billingDetail) {
        this.billingDetail = billingDetail;
    }

    public List<Object> getCustomerDefaultCard() {
        return customerDefaultCard;
    }

    public void setCustomerDefaultCard(List<Object> customerDefaultCard) {
        this.customerDefaultCard = customerDefaultCard;
    }

    public UserDefaultAddress getUserDefaultAddress() {
        return userDefaultAddress;
    }

    public void setUserDefaultAddress(UserDefaultAddress userDefaultAddress) {
        this.userDefaultAddress = userDefaultAddress;
    }

    public List<MayLike> getMayLike() {
        return mayLike;
    }

    public void setMayLike(List<MayLike> mayLike) {
        this.mayLike = mayLike;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(cartDetails);
        dest.writeValue(billingDetail);
        dest.writeList(customerDefaultCard);
        dest.writeValue(userDefaultAddress);
        dest.writeList(mayLike);
    }

    public int describeContents() {
        return 0;
    }

}