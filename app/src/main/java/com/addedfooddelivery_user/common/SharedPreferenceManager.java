package com.addedfooddelivery_user.common;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager {
    public final static String PREF_NAME = AppConstants.APP_NAME + "_pref";
    private static boolean isInit = false;
    private static SharedPreferences prefs;
    private static SharedPreferences.Editor editor;

    public static void init(Context context) {
        if (isInit)
            return;
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
        isInit = true;
    }

    public static void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void putString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public static void putFloat(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();
    }

    public static void putLong(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    public static void putInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return prefs.getBoolean(key, defaultValue);
    }

    public static String getString(String key, String defValue) {
        return prefs.getString(key, defValue);
    }

    public static float getFloat(String key, float defValue) {
        return prefs.getFloat(key, defValue);
    }

    public static int getInt(String key, int defValue) {
        return prefs.getInt(key, defValue);
    }

    public static long getLong(String key, long defValue) {
        return prefs.getLong(key, defValue);
    }

    public static void removeAllData() {
//        String ApiKey = AppConstants.API_KEY_VALUE;
//        AppConstants.DEVICE_TOKEN = getString(DEVICE_ID, "");
        editor.clear().commit();
//        putString(Constants.API_KEY, ApiKey);
        //putBoolean(AppConstants.SKIP_INTRO, true);
    }

}
