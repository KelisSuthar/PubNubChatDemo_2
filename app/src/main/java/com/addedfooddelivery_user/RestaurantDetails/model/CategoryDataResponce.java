package com.addedfooddelivery_user.RestaurantDetails.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CategoryDataResponce implements Parcelable {

    @SerializedName("restaurantImages")
    @Expose
    private List<RestaurantImage> restaurantImages = new ArrayList<RestaurantImage>();
    @SerializedName("restaurantDetails")
    @Expose
    private RestaurantDetails restaurantDetails;
    @SerializedName("review")
    @Expose
    private List<RestaurantReview> review = new ArrayList<RestaurantReview>();
    @SerializedName("categorydetails")
    @Expose
    private List<Categorydetail> categorydetails = new ArrayList<Categorydetail>();
    @SerializedName("billing_detail")
    @Expose
    private BillingDetail billingDetail;
    public final static Parcelable.Creator<CategoryDataResponce> CREATOR = new Creator<CategoryDataResponce>() {
        @SuppressWarnings({
                "unchecked"
        })
        public CategoryDataResponce createFromParcel(Parcel in) {
            return new CategoryDataResponce(in);
        }

        public CategoryDataResponce[] newArray(int size) {
            return (new CategoryDataResponce[size]);
        }

    };

    protected CategoryDataResponce(Parcel in) {
        in.readList(this.restaurantImages, (com.addedfooddelivery_user.RestaurantDetails.model.RestaurantImage.class.getClassLoader()));
        this.restaurantDetails = ((RestaurantDetails) in.readValue((RestaurantDetails.class.getClassLoader())));
        in.readList(this.review, (RestaurantReview.class.getClassLoader()));
        in.readList(this.categorydetails, (com.addedfooddelivery_user.RestaurantDetails.model.Categorydetail.class.getClassLoader()));
        this.billingDetail = ((BillingDetail) in.readValue((BillingDetail.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public CategoryDataResponce() {
    }

    /**
     * @param restaurantDetails
     * @param categorydetails
     * @param restaurantImages
     * @param review
     */
    public CategoryDataResponce(List<RestaurantImage> restaurantImages, RestaurantDetails restaurantDetails, List<RestaurantReview> review, List<Categorydetail> categorydetails,BillingDetail billingDetail) {
        super();
        this.restaurantImages = restaurantImages;
        this.restaurantDetails = restaurantDetails;
        this.review = review;
        this.categorydetails = categorydetails;
        this.billingDetail = billingDetail;
    }

    public List<RestaurantImage> getRestaurantImages() {
        return restaurantImages;
    }

    public void setRestaurantImages(List<RestaurantImage> restaurantImages) {
        this.restaurantImages = restaurantImages;
    }

    public RestaurantDetails getRestaurantDetails() {
        return restaurantDetails;
    }

    public void setRestaurantDetails(RestaurantDetails restaurantDetails) {
        this.restaurantDetails = restaurantDetails;
    }

    public List<RestaurantReview> getReview() {
        return review;
    }

    public void setReview(List<RestaurantReview> review) {
        this.review = review;
    }

    public List<Categorydetail> getCategorydetails() {
        return categorydetails;
    }

    public void setCategorydetails(List<Categorydetail> categorydetails) {
        this.categorydetails = categorydetails;
    }
    public BillingDetail getBillingDetail() {
        return billingDetail;
    }

    public void setBillingDetail(BillingDetail billingDetail) {
        this.billingDetail = billingDetail;
    }
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(restaurantImages);
        dest.writeValue(restaurantDetails);
        dest.writeList(review);
        dest.writeList(categorydetails);
        dest.writeValue(billingDetail);
    }

    public int describeContents() {
        return 0;
    }

}