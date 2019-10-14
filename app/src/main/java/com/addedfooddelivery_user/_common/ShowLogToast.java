package com.addedfooddelivery_user._common;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.addedfooddelivery_user.AddedApplication;

import static com.addedfooddelivery_user._common.AppConstants.LOG_DEBUG;

public class ShowLogToast {


    public static void ShowLog(String message) {
        String LOG_TAG = ReusedMethod.getApplicationName(AddedApplication.getAppContext()) + "_LOG";

        if (LOG_DEBUG)
            Log.d(LOG_TAG, message);
    }


    public static void ShowLog(String tag, String message) {
        if (LOG_DEBUG)
            Log.d(tag, message);
    }

    public static void ShowLogError(String tag, String message) {
        if (LOG_DEBUG)
            Log.e(tag, message);
    }

    public static void ShowLogInfo(String tag, String message) {
        if (LOG_DEBUG)
            Log.e(tag, message);
    }

    public static void ShowToast(Context context, String message, int time) {
        if (time == 0) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }


}