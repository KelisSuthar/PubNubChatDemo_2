package com.addedfooddelivery_user.RestaurantDetails.model;

import android.annotation.SuppressLint;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

@SuppressLint("ParcelCreator")
public class ParentData extends ExpandableGroup<CategoryList> {
 
    public ParentData(String title, List<CategoryList> items){
        super(title,items);
    }
}