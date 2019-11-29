package com.addedfooddelivery_user.RestaurantDetails.model;

import android.annotation.SuppressLint;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ParcelCreator")
public class ParentCategoryData extends ExpandableGroup<CategoryList> {
 
    public ParentCategoryData(String title, ArrayList<CategoryList> items){
        super(title,items);
    }
}