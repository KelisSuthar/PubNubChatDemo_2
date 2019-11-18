package com.addedfooddelivery_user.common;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.addedfooddelivery_user.R;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;


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

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    static public int getResponseCode(JSONObject response) {
        if (response.has("status")) {
            try {
                return response.getInt("status");
            } catch (JSONException e) {
                return 422;
            }
        }

        return 422;
    }

}
