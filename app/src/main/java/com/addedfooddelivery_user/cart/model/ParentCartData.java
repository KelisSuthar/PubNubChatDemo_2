package com.addedfooddelivery_user.cart.model;

import android.annotation.SuppressLint;

import com.addedfooddelivery_user.RestaurantDetails.model.CategoryList;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ParcelCreator")
public class ParentCartData extends ExpandableGroup<RestaurantItem> {

    public ParentCartData(String title, List<RestaurantItem> items){
        super(title,items);
    }
}