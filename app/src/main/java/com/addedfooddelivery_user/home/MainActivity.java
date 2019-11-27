package com.addedfooddelivery_user.home;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.common.CommonGps;
import com.addedfooddelivery_user.common.GlobalData;
import com.addedfooddelivery_user.common.ReusedMethod;
import com.addedfooddelivery_user.common.SharedPreferenceManager;
import com.addedfooddelivery_user.common.views.CustomButton;
import com.addedfooddelivery_user.home.fragement.HomeFragement;
import com.addedfooddelivery_user.home_deliverylist.DeliveryListActivity;
import com.addedfooddelivery_user.home_deliverylist.SaveAddressActivity;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import de.mateware.snacky.Snacky;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static com.addedfooddelivery_user.common.AppConstants.IS_LOGIN;
import static com.addedfooddelivery_user.common.AppConstants.PERMISSION_LOCATION_REQUEST_CODE;
import static com.addedfooddelivery_user.common.AppConstants.REQUEST_ENABLE_MULTIPLE;
import static com.addedfooddelivery_user.common.CommonGps.REQUEST_ENABLE_GPS;
import static com.addedfooddelivery_user.common.CommonGps.openGpsEnableSetting;

public class MainActivity extends AppCompatActivity implements LocationListener {
    public static NavController navController;
    private FusedLocationProviderClient mFusedLocationClient;
    private double wayLatitude = 0.0, wayLongitude = 0.0;
    Geocoder geocoder;
    List<Address> addresses;
    CoordinatorLayout ConstraintLayout;
    AlertDialog alertDialog;
    private boolean exit = false;

    PlacesClient placesClient;

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
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                int id = destination.getId();
                switch (id) {
                    case R.id.navigation_home:

                        break;
                    case R.id.navigation_search:

                        break;
                    case R.id.navigation_profile:
                        boolean userLogin;
                        userLogin = SharedPreferenceManager.getBoolean(IS_LOGIN, false);

                        if (userLogin) {
                            destination.setId(0);
                        } else {

                        }
                        break;

                }
            }
        });

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
                        locationPermission();
                    }
                }
                break;
            case PERMISSION_LOCATION_REQUEST_CODE:
                if (grantResults.length > 0) {

                    boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (locationAccepted)
                        checkGPS();
                    else {
                        locationPermission();
                    }
                }
                break;
        }
    }

    public void locationPermission() {
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

    @Override
    protected void onResume() {
        super.onResume();
        boolean coarsePermissionCheck = (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED);
        boolean finePermissionCheck = (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
        if (coarsePermissionCheck && finePermissionCheck) {
            checkGPS();
        } else {

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
                    if (alertDialog == null)

                        CustomeDialog(MainActivity.this);

                }
            }
        });
    }

    private void getLocation() {
        Places.initialize(this, getString(R.string.google_maps_key));
        placesClient = Places.createClient(this);

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

                    GlobalData.CurrentAddress = addresses;
                    Intent intent = new Intent("location");
                    intent.putExtra("getAdd", "getAdd");
                    LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(intent);
                    //Toast.makeText(MainActivity.this, "Success"+String.valueOf(address.trim()), Toast.LENGTH_SHORT).show();
                } else {
                    // Use the builder to create a FindCurrentPlaceRequest.
                    FindCurrentPlaceRequest request =
                            FindCurrentPlaceRequest.newInstance(ReusedMethod.CurrentPlacefields);

                    // Call findCurrentPlace and handle the response (first check that the user has granted permission).
                    Task<FindCurrentPlaceResponse> placeResponse = placesClient.findCurrentPlace(request);
                    placeResponse.addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            FindCurrentPlaceResponse response = task.getResult();
                            List<PlaceLikelihood> placeLikelihood = response.getPlaceLikelihoods();


                            wayLatitude = placeLikelihood.get(0).getPlace().getLatLng().latitude;
                            wayLongitude = placeLikelihood.get(0).getPlace().getLatLng().longitude;

                            geocoder = new Geocoder(MainActivity.this, Locale.getDefault());

                            try {
                                addresses = geocoder.getFromLocation(wayLatitude, wayLongitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            GlobalData.CurrentAddress = addresses;
                            Intent intent = new Intent("location");
                            intent.putExtra("getAdd", "getAdd");
                            LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(intent);

                        } else {
                            Exception exception = task.getException();
                            if (exception instanceof ApiException) {
                                ApiException apiException = (ApiException) exception;
                                Log.e("Place not found: ", String.valueOf(apiException.getStatusCode()));
                            }
                        }
                    });

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

            alertDialog = builder.create();
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ENABLE_GPS) {
            if (mFusedLocationClient != null) {
                getLocation();
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            wayLatitude = location.getLatitude();
            wayLongitude = location.getLongitude();

            geocoder = new Geocoder(MainActivity.this, Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(wayLatitude, wayLongitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            } catch (IOException e) {
                e.printStackTrace();
            }

            GlobalData.CurrentAddress = addresses;
            Intent intent = new Intent("location");
            intent.putExtra("getAdd", "getAdd");
            LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(intent);
            /*Toast.makeText(MainActivity.this, "Success"+String.valueOf(address.trim()), Toast.LENGTH_SHORT).show();*/
        }
    }
   /* @Override
    public void onBackPressed() {
        if (navController.getCurrentDestination().getId() == R.id.navigation_profile) {
            navController.navigate(R.id.navigation_home);
        } else if (navController.getCurrentDestination().getId() == R.id.navigation_search) {
            navController.navigate(R.id.navigation_home);
        } else {
            if (exit)
                MainActivity.this.finish();
            else {
                Toast.makeText(this, "Press Back again to Exit.",
                        Toast.LENGTH_SHORT).show();
                exit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        exit = false;
                    }
                }, 3 * 1000);

            }
        }

    }
*/

 /*   @Override
    public void onBackPressed() {
        if (exit)
            MainActivity.this.finish();
        else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }
    }*/
}
