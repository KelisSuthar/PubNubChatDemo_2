package com.addedfooddelivery_user.home.fragement;

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

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.RestaurantList.RestaurantListActivity;
import com.addedfooddelivery_user.common.CommonGps;
import com.addedfooddelivery_user.common.GlobalData;
import com.addedfooddelivery_user.common.views.CustomTextView;
import com.addedfooddelivery_user.home.FiltersActivity;
import com.addedfooddelivery_user.home.DeliveryListActivity;
import com.addedfooddelivery_user.home.MainActivity;
import com.addedfooddelivery_user.home.fragement.adpter.PopularRestaurantListAdpter;
import com.addedfooddelivery_user.home.fragement.adpter.TrendingRestaurantListAdpter;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.addedfooddelivery_user.common.AppConstants.REQUEST_ENABLE_MULTIPLE;

public class HomeFragement extends Fragment {
    @BindView(R.id.txtViewAllTrending)
    CustomTextView txtTrendingall;
    @BindView(R.id.txtViewAllPopular)
    CustomTextView txtPopularall;
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (GlobalData.CurrentAddress != null) {
            txtAddress.setText(GlobalData.CurrentAddress.toString());
            llAddress.setVisibility(View.VISIBLE);
        }


        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                //setEnabled(false); // call this to disable listener
                //remove(); // call to remove listener
                Toast.makeText(getContext(), "Listing for back press from this fragment", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        /*  checkPermission(getActivity());*/
        trendingRestaurantList = new ArrayList<>();
        popularRestaurantList = new ArrayList<>();
        fillRecords();
        setRestaurantData();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }


    private void setRestaurantData() {
        adpter = new TrendingRestaurantListAdpter(getActivity(), trendingRestaurantList);

        mLayoutManagerWeek = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        rcyWeek.setLayoutManager(mLayoutManagerWeek);

        rcyWeek.setItemAnimator(new DefaultItemAnimator());
        rcyWeek.setAdapter(adpter);

        /*mostpopular restaurant list*/
        mAdpter = new PopularRestaurantListAdpter(getActivity(), popularRestaurantList);

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

    @Override
    public void onResume() {
        super.onResume();
        boolean coarsePermissionCheck = (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED);
        boolean finePermissionCheck = (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
        if (coarsePermissionCheck && finePermissionCheck) {
            checkGPS();
        }
    }


    @OnClick({R.id.img_fillter, R.id.ll_adddress,R.id.txtViewAllPopular,R.id.txtViewAllTrending})
    public void OnViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_fillter:
                startActivity(new Intent(getContext(), FiltersActivity.class));
                getActivity().overridePendingTransition(R.anim.rightto, R.anim.left);
                getActivity().finish();
                break;
            case R.id.ll_adddress:
                startActivity(new Intent(getContext(), DeliveryListActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);

                break;
            case R.id.txtViewAllPopular:
                startActivity(new Intent(getContext(), RestaurantListActivity.class));
                getActivity().overridePendingTransition(R.anim.rightto, R.anim.left);
                break;
            case R.id.txtViewAllTrending:
                startActivity(new Intent(getContext(), RestaurantListActivity.class));
                getActivity().overridePendingTransition(R.anim.rightto, R.anim.left);

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

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {
            case REQUEST_ENABLE_MULTIPLE:
                if (grantResults.length > 0) {
                    boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (locationAccepted)
                        checkGPS();
                }
        }
    }

    private void checkGPS() {
        new CommonGps(getActivity()).turnGPSOn(new CommonGps.onGpsListener() {
            @Override
            public void gpsStatus(boolean isGPSEnable) {
                // turn on GPS
                if (isGPSEnable) {
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

