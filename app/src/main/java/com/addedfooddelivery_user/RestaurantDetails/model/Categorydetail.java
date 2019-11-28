package com.addedfooddelivery_user.RestaurantDetails.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Categorydetail implements Parcelable
{

@SerializedName("foodCategoryName")
@Expose
private String foodCategoryName;
@SerializedName("category_list")
@Expose
private List<CategoryList> categoryList = new ArrayList<CategoryList>();
public final static Parcelable.Creator<Categorydetail> CREATOR = new Creator<Categorydetail>() {


@SuppressWarnings({
"unchecked"
})
public Categorydetail createFromParcel(Parcel in) {
return new Categorydetail(in);
}

public Categorydetail[] newArray(int size) {
return (new Categorydetail[size]);
}

}
;

protected Categorydetail(Parcel in) {
this.foodCategoryName = ((String) in.readValue((String.class.getClassLoader())));
in.readList(this.categoryList, (com.addedfooddelivery_user.RestaurantDetails.model.CategoryList.class.getClassLoader()));
}

/**
* No args constructor for use in serialization
*
*/
public Categorydetail() {
}

/**
*
* @param categoryList
* @param foodCategoryName
*/
public Categorydetail(String foodCategoryName, List<CategoryList> categoryList) {
super();
this.foodCategoryName = foodCategoryName;
this.categoryList = categoryList;
}

public String getFoodCategoryName() {
return foodCategoryName;
}

public void setFoodCategoryName(String foodCategoryName) {
this.foodCategoryName = foodCategoryName;
}

public List<CategoryList> getCategoryList() {
return categoryList;
}

public void setCategoryList(List<CategoryList> categoryList) {
this.categoryList = categoryList;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(foodCategoryName);
dest.writeList(categoryList);
}

public int describeContents() {
return 0;
}

}