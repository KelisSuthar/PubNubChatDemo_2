package com.addedfooddelivery_user.common;

import android.content.Context;
import android.graphics.Typeface;

public class TypeFactory {

    public static Typeface montserratREGULAR;
    public static Typeface montserratSemiBold;
    public static Typeface montserratBold;
    public static Typeface montserratMedium;

    private static String MONTSERRAT_REGULAR = "fonts/Montserrat-Regular.ttf";
    private static String MONTSERRAT_SemiBold = "fonts/Montserrat-SemiBold.ttf";
    private static String MONTSERRAT_Bold = "fonts/Montserrat-Bold.ttf";
    private static String MONTSERRAT_MEDIUM = "fonts/Montserrat-Medium.ttf";


    public TypeFactory(Context context) {
        montserratREGULAR = Typeface.createFromAsset(context.getAssets(), MONTSERRAT_REGULAR);
        montserratSemiBold = Typeface.createFromAsset(context.getAssets(), MONTSERRAT_SemiBold);
        montserratBold = Typeface.createFromAsset(context.getAssets(), MONTSERRAT_Bold);
        montserratMedium = Typeface.createFromAsset(context.getAssets(), MONTSERRAT_MEDIUM);

    }
}