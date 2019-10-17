package com.addedfooddelivery_user._common;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user._common.views.CustomButton;
import com.google.android.material.snackbar.Snackbar;

import static com.addedfooddelivery_user._common.CommonGps.openGpsEnableSetting;


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

    public static void CustomeDialog(Activity activity) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity,R.style.MyDialogTheme_1);

            final FrameLayout frameView = new FrameLayout(activity);
            //frameView.setBackground(activity.getResources().getDrawable(R.drawable.dialog_bg));
            builder.setView(frameView);

            final AlertDialog alertDialog = builder.create();
            LayoutInflater inflater = alertDialog.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.custome_popup, frameView);


            CustomButton customButton=dialogView.findViewById(R.id.btLocation);
            customButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.cancel();
                    openGpsEnableSetting(activity);
                }
            });
            alertDialog.show();
            alertDialog.getWindow().setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.dialog_bg));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
