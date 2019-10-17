package com.addedfooddelivery_user.home.fragement;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user._common.CommonGps;
import com.addedfooddelivery_user._common.ReusedMethod;
import com.addedfooddelivery_user._common.views.CustomTextView;
import com.addedfooddelivery_user.home.FiltersActivity;
import com.addedfooddelivery_user.home.MainActivity;
import com.addedfooddelivery_user.home.fragement.adpter.PopularRestaurantListAdpter;
import com.addedfooddelivery_user.home.fragement.adpter.TrendingRestaurantListAdpter;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static androidx.core.content.ContextCompat.checkSelfPermission;
import static com.addedfooddelivery_user._common.AppConstants.PERMISSIONS_REQUEST_CODE;
import static com.facebook.FacebookSdk.getApplicationContext;

public class HomeFragement extends Fragment {
    @BindView(R.id.ll_adddress)
    LinearLayout llAddress;
    @BindView(R.id.recyclerViewWeek)
    RecyclerView rcyWeek;
    @BindView(R.id.recyclerMostPopular)
    RecyclerView rcyMost;
    @BindView(R.id.img_fillter)
    ImageView imfFillter;
    @BindView(R.id.txtAddress)
    CustomTextView txtAddress;
    private Context context;
    TrendingRestaurantListAdpter adpter;
    PopularRestaurantListAdpter mAdpter;
    private ArrayList<String> trendingRestaurantList;
    private ArrayList<String> popularRestaurantList;
    LinearLayoutManager mLayoutManagerWeek;
    GridLayoutManager mLayoutManagerPopular;

    private FusedLocationProviderClient mFusedLocationClient;
    private double wayLatitude = 0.0, wayLongitude = 0.0;
    private static final int REQUEST_ENABLE_GPS = 516;
    private boolean isPermissionGranted = false;
    private static final int ASK_MULTIPLE_PERMISSION_REQUEST_CODE = 0;
    Geocoder geocoder;
    List<Address> addresses;

    public static HomeFragement newInstance() {
        return new HomeFragement();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        checkPermission(getActivity());
        trendingRestaurantList = new ArrayList<>();
        popularRestaurantList = new ArrayList<>();
        fillRecords();
        setRestaurantData();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void setRestaurantData() {
        adpter = new TrendingRestaurantListAdpter(context, trendingRestaurantList, new TrendingRestaurantListAdpter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                switch (view.getId()) {

                }
            }
        });

        mLayoutManagerWeek = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        rcyWeek.setLayoutManager(mLayoutManagerWeek);

        rcyWeek.setItemAnimator(new DefaultItemAnimator());
        rcyWeek.setAdapter(adpter);

        /*mostpopular restaurant list*/
        mAdpter = new PopularRestaurantListAdpter(context, trendingRestaurantList, new PopularRestaurantListAdpter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                switch (view.getId()) {

                }
            }
        });

        mLayoutManagerPopular = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        rcyMost.setLayoutManager(mLayoutManagerPopular);

        rcyMost.setItemAnimator(new DefaultItemAnimator());
        rcyMost.setAdapter(mAdpter);
    }

    private void fillRecords() {
        trendingRestaurantList.add("1");
        trendingRestaurantList.add("2");
        trendingRestaurantList.add("3");
        trendingRestaurantList.add("4");
        trendingRestaurantList.add("5");


        popularRestaurantList.add("1");
        popularRestaurantList.add("2");
        popularRestaurantList.add("3");
        popularRestaurantList.add("4");
        popularRestaurantList.add("5");
    }


    @Override
    public void onDetach() {
        super.onDetach();

    }

    @OnClick({R.id.img_fillter})
    public void OnViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_fillter:
                startActivity(new Intent(getContext(), FiltersActivity.class));
                getActivity().finish();
                break;
            case R.id.ll_adddress:
                break;

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

    private void checkPermission(Activity context) {
        Dexter.withActivity(context)
                .withPermissions(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            checkGPS();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getContext(), "Error occurred! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    private void checkGPS() {
        new CommonGps(getActivity()).turnGPSOn(new CommonGps.onGpsListener() {
            @Override
            public void gpsStatus(boolean isGPSEnable) {
                // turn on GPS

                if (!isGPSEnable) {
                    ReusedMethod.CustomeDialog(getActivity());
                } else {

                    getLocation();
                }
            }
        });
    }

    private void getLocation() {
        if (mFusedLocationClient == null) {
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        }

        mFusedLocationClient.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    wayLatitude = location.getLatitude();
                    wayLongitude = location.getLongitude();

                    geocoder = new Geocoder(getActivity(), Locale.getDefault());

                    try {
                        addresses = geocoder.getFromLocation(wayLatitude, wayLongitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String address = addresses.get(0).getAddressLine(0);
                    txtAddress.setText(address.trim());
                    //Toast.makeText(MainActivity.this, "Success"+String.valueOf(wayLatitude), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    private void showSettingsDialog() {
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

    }

    // navigating user to app settings
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getContext().getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }
}

