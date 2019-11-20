package com.addedfooddelivery_user.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class HomeRestaurantData implements Parcelable {

    @SerializedName("trending")
    @Expose
    private List<Trending> trending = new ArrayList<Trending>();
    @SerializedName("popular")
    @Expose
    private List<Popular> popular = new ArrayList<Popular>();
    public final static Parcelable.Creator<HomeRestaurantData> CREATOR = new Creator<HomeRestaurantData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public HomeRestaurantData createFromParcel(Parcel in) {
            return new HomeRestaurantData(in);
        }

        public HomeRestaurantData[] newArray(int size) {
            return (new HomeRestaurantData[size]);
        }

    };

    protected HomeRestaurantData(Parcel in) {
        in.readList(this.trending, (Trending.class.getClassLoader()));
        in.readList(this.popular, (Popular.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     */
    public HomeRestaurantData() {
    }

    /**
     * @param trending
     * @param popular
     */
    public HomeRestaurantData(List<Trending> trending, List<Popular> popular) {
        super();
        this.trending = trending;
        this.popular = popular;
    }

    public List<Trending> getTrending() {
        return trending;
    }

    public void setTrending(List<Trending> trending) {
        this.trending = trending;
    }

    public List<Popular> getPopular() {
        return popular;
    }

    public void setPopular(List<Popular> popular) {
        this.popular = popular;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(trending);
        dest.writeList(popular);
    }

    public int describeContents() {
        return 0;
    }

}