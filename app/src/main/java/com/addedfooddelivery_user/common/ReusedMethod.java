package com.addedfooddelivery_user.common;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
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
import com.addedfooddelivery_user.common.views.CustomButton;
import com.addedfooddelivery_user.home_deliverylist.DeliveryListActivity;
import com.addedfooddelivery_user.home_deliverylist.SaveAddressActivity;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import de.mateware.snacky.Snacky;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static com.addedfooddelivery_user.common.AppConstants.PERMISSION_LOCATION_REQUEST_CODE;
import static com.addedfooddelivery_user.common.CommonGps.openGpsEnableSetting;


public class ReusedMethod {
    AlertDialog alertDialog;
    public static List<Place.Field> fields = Arrays.asList(
            Place.Field.ADDRESS,
            Place.Field.ADDRESS_COMPONENTS,
            Place.Field.ID,
            Place.Field.LAT_LNG,
            Place.Field.NAME,
            Place.Field.OPENING_HOURS,
            Place.Field.PHONE_NUMBER,
            Place.Field.PHOTO_METADATAS,
            Place.Field.PLUS_CODE,
            Place.Field.PRICE_LEVEL,
            Place.Field.RATING,
            Place.Field.TYPES,
            Place.Field.USER_RATINGS_TOTAL,
            Place.Field.UTC_OFFSET,
            Place.Field.VIEWPORT,
            Place.Field.WEBSITE_URI);

    public static List<Place.Field> CurrentPlacefields = Arrays.asList(
            Place.Field.ADDRESS,
            Place.Field.ID,
            Place.Field.LAT_LNG,
            Place.Field.NAME,
            Place.Field.PHOTO_METADATAS,
            Place.Field.PLUS_CODE,
            Place.Field.PRICE_LEVEL,
            Place.Field.RATING,
            Place.Field.TYPES,
            Place.Field.USER_RATINGS_TOTAL,
            Place.Field.VIEWPORT);

    static String getApplicationName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);
    }

    static public void showSnackBar(Activity context, String message, int length) {
        if (context != null) {
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

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        View f = activity.getCurrentFocus();
        if (null != f && null != f.getWindowToken() && EditText.class.isAssignableFrom(f.getClass()))
            imm.hideSoftInputFromWindow(f.getWindowToken(), 0);
        else
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void CustomeDialog(Activity activity, AlertDialog alertDialog) {
        try {

            AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.MyDialogTheme_1);

            final FrameLayout frameView = new FrameLayout(activity);
            //frameView.setBackground(activity.getResources().getDrawable(R.drawable.dialog_bg));
            builder.setView(frameView);

            alertDialog = builder.create();
            LayoutInflater inflater = alertDialog.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.custome_popup, frameView);

            alertDialog.show();
            alertDialog.getWindow().setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.dialog_bg));

            CustomButton customButton = dialogView.findViewById(R.id.btLocation);
            customButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    openGpsEnableSetting(activity);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void locationPermission(Activity activity) {
        if (activity != null) {
            Snacky.builder()
                    .setActivity(activity)
                    .setActionText("Grant")
                    .setActionTextColor(activity.getResources().getColor(R.color.white))
                    .setBackgroundColor(activity.getResources().getColor(R.color.light_primary))
                    .setTextSize(12)
                    .setActionTextSize(12)
                    .setTextColor(activity.getResources().getColor(R.color.white))
                    .setTextTypefaceStyle(Typeface.BOLD)
                    .setActionClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                activity.requestPermissions(new String[]{ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                                        PERMISSION_LOCATION_REQUEST_CODE);
                            }
                        }
                    })
                    .setText("Permission Denied, You cannot access location data")
                    .setDuration(Snacky.LENGTH_INDEFINITE)
                    .build()
                    .show();
        }
    }

    public static boolean checkPermission(Activity activity) {
        boolean coarsePermissionCheck = (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED);
        boolean finePermissionCheck = (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
        if (coarsePermissionCheck && finePermissionCheck) {
            return true;
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.requestPermissions(new String[]{ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        PERMISSION_LOCATION_REQUEST_CODE);
            }
        }
        return false;
    }
 /*   private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }*/

    // navigating user to app settings
 /*   private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getContext().getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }*/
}
