package com.addedfooddelivery_user._common;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
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
    public static void hideKeyboard( Activity activity ) {
        InputMethodManager imm = (InputMethodManager)activity.getSystemService( Context.INPUT_METHOD_SERVICE );
        View f = activity.getCurrentFocus();
        if( null != f && null != f.getWindowToken() && EditText.class.isAssignableFrom( f.getClass() ) )
            imm.hideSoftInputFromWindow( f.getWindowToken(), 0 );
        else
            activity.getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN );
    }

}
