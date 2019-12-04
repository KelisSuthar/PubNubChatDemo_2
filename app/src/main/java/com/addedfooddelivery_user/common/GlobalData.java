package com.addedfooddelivery_user.common;

import android.location.Address;

import com.addedfooddelivery_user.common.model.AddressCommon;

import java.util.List;

public class GlobalData {

    public static List<Address> CurrentAddress;

    public static List<Address> SavedAddress;
    public static String addressType;
    public static String rest_type="";
    public static String sort_by = "";
    public static String direction = "asc";
    public static String category = "";
    public static String price = "";

}
