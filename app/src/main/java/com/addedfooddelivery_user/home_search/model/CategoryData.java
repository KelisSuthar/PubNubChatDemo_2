package com.addedfooddelivery_user.home_search.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryData implements Parcelable {

    @SerializedName("foodCategoryID")
    @Expose
    private Integer foodCategoryID;
    @SerializedName("foodCategoryName")
    @Expose
    private String foodCategoryName;
    @SerializedName("foodCategoryImage")
    @Expose
    private String foodCategoryImage;
    public final static Parcelable.Creator<CategoryData> CREATOR = new Creator<CategoryData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CategoryData createFromParcel(Parcel in) {
            return new CategoryData(in);
        }

        public CategoryData[] newArray(int size) {
            return (new CategoryData[size]);
        }

    };

    protected CategoryData(Parcel in) {
        this.foodCategoryID = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.foodCategoryName = ((String) in.readValue((String.class.getClassLoader())));
        this.foodCategoryImage = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public CategoryData() {
    }

    /**
     * @param foodCategoryID
     * @param foodCategoryImage
     * @param foodCategoryName
     */
    public CategoryData(Integer foodCategoryID, String foodCategoryName, String foodCategoryImage) {
        super();
        this.foodCategoryID = foodCategoryID;
        this.foodCategoryName = foodCategoryName;
        this.foodCategoryImage = foodCategoryImage;
    }

    public Integer getFoodCategoryID() {
        return foodCategoryID;
    }

    public void setFoodCategoryID(Integer foodCategoryID) {
        this.foodCategoryID = foodCategoryID;
    }

    public String getFoodCategoryName() {
        return foodCategoryName;
    }

    public void setFoodCategoryName(String foodCategoryName) {
        this.foodCategoryName = foodCategoryName;
    }

    public String getFoodCategoryImage() {
        return foodCategoryImage;
    }

    public void setFoodCategoryImage(String foodCategoryImage) {
        this.foodCategoryImage = foodCategoryImage;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(foodCategoryID);
        dest.writeValue(foodCategoryName);
        dest.writeValue(foodCategoryImage);
    }

    public int describeContents() {
        return 0;
    }

}