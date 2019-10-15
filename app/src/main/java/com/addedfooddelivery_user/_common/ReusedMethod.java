package com.addedfooddelivery_user._common;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user._common.views.CustomTextView;
import com.google.android.material.snackbar.Snackbar;


public class ReusedMethod {
    static String getApplicationName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);
    }
    static public void showSnackBar(Activity context, String message, int length) {
        if(context!=null) {
            View contextView = context.findViewById(android.R.id.content);

            Snackbar snackbar = Snackbar.make(contextView, message, Snackbar.LENGTH_SHORT);
            View snackBarView = snackbar.getView();
            snackBarView.setBackground(context.getDrawable(R.drawable.email_rectangle));
            TextView tv = snackBarView.findViewById(R.id.snackbar_text);
            tv.setTextSize(12);
            tv.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));
            tv.setGravity(Gravity.CENTER);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) snackBarView.getLayoutParams();

            params.setMargins(2, params.topMargin, 2, params.bottomMargin + 0);

            snackBarView.setLayoutParams(params);
            snackbar.show();
        }

    }
}
