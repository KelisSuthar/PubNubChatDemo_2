package com.addedfooddelivery_user.home;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user._common.CommonGps;
import com.addedfooddelivery_user._common.GlobalData;
import com.addedfooddelivery_user._common.views.CustomButton;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import de.mateware.snacky.Snacky;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static com.addedfooddelivery_user._common.AppConstants.PERMISSION_LOCATION_REQUEST_CODE;
import static com.addedfooddelivery_user._common.AppConstants.REQUEST_ENABLE_MULTIPLE;
import static com.addedfooddelivery_user._common.CommonGps.openGpsEnableSetting;

public class MainActivity extends AppCompatActivity {
    NavController navController;
    private FusedLocationProviderClient mFusedLocationClient;
    private double wayLatitude = 0.0, wayLongitude = 0.0;
    Geocoder geocoder;
    List<Address> addresses;
    CoordinatorLayout ConstraintLayout;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navView.setItemIconTintList(null);
        NavigationUI.setupWithNavController(navView, navController);
        checkPermission(this);
        ConstraintLayout = findViewById(R.id.mainContainer);

    }


    private void checkPermission(MainActivity mainActivity) {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CALL_PHONE}, REQUEST_ENABLE_MULTIPLE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_ENABLE_MULTIPLE:
                if (grantResults.length > 0) {
                    boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (locationAccepted)
                        checkGPS();
                    else {
                        Snacky.builder()
                                .setActivity(MainActivity.this)
                                .setActionText("Grant")
                                .setActionTextColor(getResources().getColor(R.color.white))
                                .setBackgroundColor(getResources().getColor(R.color.light_primary))
                                .setTextSize(12)
                                .setActionTextSize(12)
                                .setTextColor(getResources().getColor(R.color.white))
                                .setTextTypefaceStyle(Typeface.BOLD)
                                .setActionClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                            requestPermissions(new String[]{ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
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
                break;
            case PERMISSION_LOCATION_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (locationAccepted)
                        checkGPS();
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean coarsePermissionCheck = (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED);
        boolean finePermissionCheck = (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
        if (coarsePermissionCheck && finePermissionCheck) {
            checkGPS();
        }
    }


    private void checkGPS() {
        new CommonGps(this).turnGPSOn(new CommonGps.onGpsListener() {
            @Override
            public void gpsStatus(boolean isGPSEnable) {
                if (isGPSEnable) {
                    if (alertDialog != null) {
                        alertDialog.dismiss();
                    }
                    getLocation();
                } else {
                    CustomeDialog(MainActivity.this);
                }
            }
        });
    }

    private void getLocation() {
        if (mFusedLocationClient == null) {
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);
        }

        mFusedLocationClient.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    wayLatitude = location.getLatitude();
                    wayLongitude = location.getLongitude();

                    geocoder = new Geocoder(MainActivity.this, Locale.getDefault());

                    try {
                        addresses = geocoder.getFromLocation(wayLatitude, wayLongitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    GlobalData.CurrentAddress = addresses.get(0).getAddressLine(0);

                    /*Toast.makeText(MainActivity.this, "Success"+String.valueOf(address.trim()), Toast.LENGTH_SHORT).show();*/
                }
            }
        });
    }

    public void CustomeDialog(Activity activity) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.MyDialogTheme_1);

            final FrameLayout frameView = new FrameLayout(activity);
            //frameView.setBackground(activity.getResources().getDrawable(R.drawable.dialog_bg));
            builder.setView(frameView);

            AlertDialog alertDialog = builder.create();
            LayoutInflater inflater = alertDialog.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.custome_popup, frameView);

            alertDialog.show();
            alertDialog.getWindow().setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.dialog_bg));


            CustomButton customButton = dialogView.findViewById(R.id.btLocation);
            customButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                    openGpsEnableSetting(activity);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
