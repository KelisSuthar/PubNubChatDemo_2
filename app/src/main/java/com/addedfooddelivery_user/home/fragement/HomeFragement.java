package com.addedfooddelivery_user.home.fragement;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.RestaurantList.RestaurantListActivity;
import com.addedfooddelivery_user.common.CommonGps;
import com.addedfooddelivery_user.common.CustomeToast;
import com.addedfooddelivery_user.common.GlobalData;
import com.addedfooddelivery_user.common.ReusedMethod;
import com.addedfooddelivery_user.common.SharedPreferenceManager;
import com.addedfooddelivery_user.common.views.CustomTextView;
import com.addedfooddelivery_user.home.api.HomeConstructor;
import com.addedfooddelivery_user.home.api.HomePresenter;
import com.addedfooddelivery_user.home.model.HomeRestaurantResponse;
import com.addedfooddelivery_user.home.model.Popular;
import com.addedfooddelivery_user.home.model.Trending;
import com.addedfooddelivery_user.home_filter.FiltersActivity;
import com.addedfooddelivery_user.home_deliverylist.DeliveryListActivity;
import com.addedfooddelivery_user.home.adpter.PopularRestaurantListAdpter;
import com.addedfooddelivery_user.home.adpter.TrendingRestaurantListAdpter;
import com.addedfooddelivery_user.loginEmail.api.LoginPresenter;
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

import static com.addedfooddelivery_user.common.AppConstants.IS_LOGIN;
import static com.addedfooddelivery_user.common.AppConstants.REQUEST_ENABLE_MULTIPLE;
import static com.addedfooddelivery_user.common.GlobalData.CurrentAddress;

public class HomeFragement extends Fragment implements HomeConstructor.View {
    @BindView(R.id.ll_home_data)
    LinearLayout llHome;
    @BindView(R.id.ll_home_no_data)
    LinearLayout llHomeNoData;

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
    private ArrayList<Trending> trendingRestaurantList;
    private ArrayList<Popular> popularRestaurantList;
    LinearLayoutManager mLayoutManagerWeek;
    GridLayoutManager mLayoutManagerPopular;

    private FusedLocationProviderClient mFusedLocationClient;
    private double wayLatitude = 0.0, wayLongitude = 0.0;
    private static final int REQUEST_ENABLE_GPS = 516;

    Geocoder geocoder;
    List<Address> addresses;
    private boolean exit = false;

    HomePresenter homePresenter;
    Dialog dialog;
    boolean recevierCall=true;
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (CurrentAddress != null) {
                String address = CurrentAddress.get(0).getAddressLine(0);
                txtAddress.setText(address.trim());
                String city = CurrentAddress.get(0).getLocality();
                if(recevierCall) {
                    homePresenter.requestAPIRestaurant(getActivity(), city);
                    recevierCall=false;
                }
            }
        }
    };

    public static HomeFragement newInstance() {
        return new HomeFragement();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        trendingRestaurantList = new ArrayList<>();
        popularRestaurantList = new ArrayList<>();

        setRestaurantData();

        initProgressBar();
        homePresenter = new HomePresenter(this);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(false) {
            @Override
            public void handleOnBackPressed() {
                if (exit)
                    getActivity().finish();
                else {
                    Toast.makeText(getActivity(), "Press Back again to Exit.",
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
        });
        return view;
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



    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mMessageReceiver);
    }

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMessageReceiver,
                new IntentFilter("location"));
        if (CurrentAddress != null) {
            String city = CurrentAddress.get(0).getLocality();
            homePresenter.requestAPIRestaurant(getActivity(), city);
        }
    }


    @OnClick({R.id.img_fillter, R.id.ll_adddress, R.id.txtViewAllPopular, R.id.txtViewAllTrending})
    public void OnViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_fillter:
                startActivity(new Intent(getContext(), FiltersActivity.class));
                getActivity().overridePendingTransition(R.anim.rightto, R.anim.left);
                getActivity().finish();
                break;
            case R.id.ll_adddress:
                boolean userLogin;
                userLogin = SharedPreferenceManager.getBoolean(IS_LOGIN, false);
                if (userLogin) {
                    startActivity(new Intent(getContext(), DeliveryListActivity.class));
                    getActivity().overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
                } else {
                    ReusedMethod.showSnackBar(getActivity(), context.getResources().getString(R.string.please_login), 1);
                }
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
                    CurrentAddress = addresses;
                    if (addresses != null) {
                        String address = addresses.get(0).getAddressLine(0);
                        txtAddress.setText(address.trim());
                    }
                    //Toast.makeText(MainActivity.this, "Success"+String.valueOf(wayLatitude), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onHomeResponseFailure(Throwable throwable) {

        displayMessage(throwable.getMessage());
    }

    @Override
    public void onHomeResponseSuccess(HomeRestaurantResponse response) {
        if (response.getStatus() == 1) {
            llHomeNoData.setVisibility(View.GONE);
            llHome.setVisibility(View.VISIBLE);
            if(trendingRestaurantList.size()>0){
                trendingRestaurantList.clear();
            }
            trendingRestaurantList.addAll(response.getData().getTrending());
            if(popularRestaurantList.size()>0){
                popularRestaurantList.clear();
            }
            popularRestaurantList.addAll(response.getData().getPopular());

        } else {
            llHomeNoData.setVisibility(View.VISIBLE);
            llHome.setVisibility(View.GONE);
        }
    }

    @Override
    public void showLoadingIndicator(boolean isShow) {
        if (dialog != null) {
            if (isShow) {
                dialog.show();
            } else {
                dialog.dismiss();
                dialog.cancel();
            }
        }
    }

    @Override
    public void displayMessage(String message) {
        CustomeToast.showToast(
                getActivity(),
                message,
                true,
                getResources().getColor(R.color.white),
                getResources().getColor(R.color.colorPrimary),
                true);
    }

    @Override
    public void initProgressBar() {
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progress_dialog);

        dialog.setCancelable(false);
    }

    @Override
    public Activity getContext() {
        return getActivity();
    }
}

