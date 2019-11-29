package com.addedfooddelivery_user.home.fragement;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Address;
import android.location.Geocoder;
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
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.restaurantList.RestaurantListActivity;
import com.addedfooddelivery_user.common.CustomeToast;
import com.addedfooddelivery_user.common.GlobalData;
import com.addedfooddelivery_user.common.ReusedMethod;
import com.addedfooddelivery_user.common.SharedPreferenceManager;
import com.addedfooddelivery_user.common.views.CustomTextView;
import com.addedfooddelivery_user.home.adpter.PopularRestaurantListAdpter;
import com.addedfooddelivery_user.home.adpter.TrendingRestaurantListAdpter;
import com.addedfooddelivery_user.home.api.HomeConstructor;
import com.addedfooddelivery_user.home.api.HomePresenter;
import com.addedfooddelivery_user.home.model.DefaultAddResponse;
import com.addedfooddelivery_user.home.model.HomeRestaurantResponse;
import com.addedfooddelivery_user.home.model.Popular;
import com.addedfooddelivery_user.home.model.Trending;
import com.addedfooddelivery_user.home_deliverylist.DeliveryListActivity;
import com.google.android.gms.location.FusedLocationProviderClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.addedfooddelivery_user.common.AppConstants.IS_LOGIN;
import static com.addedfooddelivery_user.common.GlobalData.CurrentAddress;
import static com.addedfooddelivery_user.common.GlobalData.SavedAddress;
import static com.addedfooddelivery_user.common.GlobalData.rest_type;

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
    boolean recevierCall = true;
    boolean getDefaultAddress = false;
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (SavedAddress == null) {
                if (CurrentAddress != null) {
                    String address = CurrentAddress.get(0).getAddressLine(0);
                    txtAddress.setText(address.trim());
                    String city = CurrentAddress.get(0).getLocality();
                    if (recevierCall) {
                        homePresenter.requestAPIRestaurant(getActivity(), city);
                        recevierCall = false;
                    }
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
        homePresenter.requestForAddress(getActivity());

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
    public void onDestroyView() {
        super.onDestroyView();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mMessageReceiver);
    }

    @Override
    public void onResume() {
        super.onResume();
        String selectCity = "";
        String SelectAddress = "";

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMessageReceiver,
                new IntentFilter("location"));

            //get default address after call get restaurant data
            if (SavedAddress != null) {
                SelectAddress = SavedAddress.get(0).getAddressLine(0);
                txtAddress.setText(SelectAddress.trim());
                selectCity = SavedAddress.get(0).getLocality().toString();
            } else {
                if (CurrentAddress != null) {
                    selectCity = CurrentAddress.get(0).getLocality();
                }
            }

            homePresenter.requestAPIRestaurant(getActivity(), selectCity);

    }


    @OnClick({R.id.ll_adddress, R.id.txtViewAllPopular, R.id.txtViewAllTrending})
    public void OnViewClicked(View view) {
        switch (view.getId()) {

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
                startActivity(new Intent(getContext(), RestaurantListActivity.class).putExtra("restType","Popular"));
                getActivity().overridePendingTransition(R.anim.rightto, R.anim.left);
                rest_type="Popular";
                break;
            case R.id.txtViewAllTrending:
                startActivity(new Intent(getContext(), RestaurantListActivity.class).putExtra("restType","Trending"));
                getActivity().overridePendingTransition(R.anim.rightto, R.anim.left);
                rest_type="Trending";

                break;

        }

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
            if (trendingRestaurantList.size() > 0) {
                trendingRestaurantList.clear();
            }
            trendingRestaurantList.addAll(response.getData().getTrending());
            if (popularRestaurantList.size() > 0) {
                popularRestaurantList.clear();
            }
            popularRestaurantList.addAll(response.getData().getPopular());

        } else {
            llHomeNoData.setVisibility(View.VISIBLE);
            llHome.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDefaultAddResponseFailure(Throwable throwable) {
        displayMessage(throwable.getMessage());
        getDefaultAddress = false;
    }

    @Override
    public void onDefaultAddResponseSuccess(DefaultAddResponse response) {
        double wayLatitude = Double.parseDouble(response.getData().getAdderessLatitude());
        double wayLongitude = Double.parseDouble(response.getData().getAdderessLongitude());

        geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(wayLatitude, wayLongitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }

        GlobalData.SavedAddress = addresses;

        txtAddress.setText(SavedAddress.get(0).getAddressLine(00));
        homePresenter.requestAPIRestaurant(getActivity(), SavedAddress.get(0).getLocality().toString());

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
        if (!(message.equalsIgnoreCase("CategoryDataResponce get Successfully.")||message.equalsIgnoreCase("CartData get Successfully."))) {
            CustomeToast.showToast(
                    getActivity(),
                    message,
                    true,
                    getResources().getColor(R.color.white),
                    getResources().getColor(R.color.colorPrimary),
                    true);
        }
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

