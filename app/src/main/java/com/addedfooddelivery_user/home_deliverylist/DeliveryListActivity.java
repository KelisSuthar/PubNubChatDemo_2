package com.addedfooddelivery_user.home_deliverylist;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.common.CommonGps;
import com.addedfooddelivery_user.common.CustomeToast;
import com.addedfooddelivery_user.common.ReusedMethod;
import com.addedfooddelivery_user.common.views.CustomButton;
import com.addedfooddelivery_user.common.views.CustomEditText;
import com.addedfooddelivery_user.common.views.CustomTextView;
import com.addedfooddelivery_user.home.MainActivity;
import com.addedfooddelivery_user.home_deliverylist.adpter.AddressListAdpter;
import com.addedfooddelivery_user.home_deliverylist.api.AddAddressConstructor;
import com.addedfooddelivery_user.home_deliverylist.api.AddAddressPresenter;
import com.addedfooddelivery_user.home_deliverylist.model.ListAddData;
import com.addedfooddelivery_user.home_deliverylist.model.ListAddResponse;
import com.addedfooddelivery_user.home_deliverylist.model.SaveAddResponse;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.mateware.snacky.Snacky;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static com.addedfooddelivery_user.common.AppConstants.AUTOCOMPLETE_REQUEST_CODE;
import static com.addedfooddelivery_user.common.AppConstants.PERMISSION_LOCATION_REQUEST_CODE;
import static com.addedfooddelivery_user.common.CommonGps.openGpsEnableSetting;

public class DeliveryListActivity extends AppCompatActivity implements AddAddressConstructor.View {
    @BindView(R.id.imgBackAddress)
    ImageView imgBackAddress;
    @BindView(R.id.edtSearch)
    CustomTextView edtSearch;
    @BindView(R.id.ll_current_location)
    LinearLayout llCurrentLocation;
    @BindView(R.id.ll_address_list)
    LinearLayout llAddressList;
    @BindView(R.id.rcyAddressList)
    RecyclerView rcyAddressList;
    LinearLayoutManager mLayoutManagerDelivery;
    private ArrayList<ListAddData> addresssList;
    AddressListAdpter adpter;
    AlertDialog alertDialog;
    PlacesClient placesClient;

    FusedLocationProviderClient mFusedLocationClient;
    AddAddressPresenter addAddressPresenter;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_delivery);

        ButterKnife.bind(this);
        addresssList = new ArrayList<>();

        setAddressData();
        initPlaces();
        getAddressList();
        if (ReusedMethod.checkPermission(DeliveryListActivity.this)) {
            checkGPS();
        }

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

    }

    private void getAddressList() {
        addAddressPresenter = new AddAddressPresenter(this);
        addAddressPresenter.listAddAddress(DeliveryListActivity.this);
    }

    private void setAddressData() {
        adpter = new AddressListAdpter(DeliveryListActivity.this, addresssList, new AddressListAdpter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {

            }
        });

        mLayoutManagerDelivery = new LinearLayoutManager(DeliveryListActivity.this, RecyclerView.VERTICAL, false);
        rcyAddressList.setLayoutManager(mLayoutManagerDelivery);

        rcyAddressList.setItemAnimator(new DefaultItemAnimator());
        rcyAddressList.setAdapter(adpter);
    }


    private void checkGPS() {
        new CommonGps(this).turnGPSOn(new CommonGps.onGpsListener() {
            @Override
            public void gpsStatus(boolean isGPSEnable) {
                if (isGPSEnable) {
                    if (alertDialog != null) {
                        alertDialog.dismiss();
                    }
                } else {
                    ReusedMethod.CustomeDialog(DeliveryListActivity.this, alertDialog);
                }
            }
        });
    }

    private void initPlaces() {
        Places.initialize(this, getString(R.string.google_maps_key));
        placesClient = Places.createClient(this);
    }

    private void setUpPlacesAutocomplete() {


        Intent intent = new Autocomplete.IntentBuilder(
                AutocompleteActivityMode.OVERLAY, ReusedMethod.fields)
                .build(this);
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                if (place != null) {
                    startActivity(new Intent(DeliveryListActivity.this, SaveAddressActivity.class).putExtra("placeId", place.getId()));
                    overridePendingTransition(R.anim.rightto, R.anim.left);
                }
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i("AutoComplete", status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAddressList();
    }

    @OnClick({R.id.imgBackAddress, R.id.edtSearch, R.id.ll_current_location})
    public void EventClick(View view) {
        switch (view.getId()) {
            case R.id.imgBackAddress:
                onBackPressed();
                break;
            case R.id.edtSearch:
                setUpPlacesAutocomplete();
                break;
            case R.id.ll_current_location:
                getLocation();
                break;
        }
    }

    private void getLocation() {
        // Use the builder to create a FindCurrentPlaceRequest.
                FindCurrentPlaceRequest request =
                        FindCurrentPlaceRequest.newInstance(ReusedMethod.CurrentPlacefields);

        // Call findCurrentPlace and handle the response (first check that the user has granted permission).
        if (ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Task<FindCurrentPlaceResponse> placeResponse = placesClient.findCurrentPlace(request);
            placeResponse.addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    FindCurrentPlaceResponse response = task.getResult();
                    for (PlaceLikelihood placeLikelihood : response.getPlaceLikelihoods()) {
                        startActivity(new Intent(DeliveryListActivity.this, SaveAddressActivity.class).putExtra("placeId", placeLikelihood.getPlace().getId()));
                        overridePendingTransition(R.anim.rightto, R.anim.left);
                    }
                } else {
                    Exception exception = task.getException();
                    if (exception instanceof ApiException) {
                        ApiException apiException = (ApiException) exception;
                        Log.e("Place not found: ", String.valueOf(apiException.getStatusCode()));
                    }
                }
            });
        } else {

            checkSelfPermission();
        }

    }

    private void checkSelfPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSION_LOCATION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_LOCATION_REQUEST_CODE:
                if (grantResults.length > 0) {

                    boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (locationAccepted)
                        checkGPS();
                    else {
                        ReusedMethod.locationPermission(DeliveryListActivity.this);
                    }
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
        finish();
    }

    @Override
    public void onSaveAddressResponseFailure(Throwable throwable) {
// for add address
    }

    @Override
    public void onSaveAddressResponseSuccess(SaveAddResponse response) {
// for add address
    }

    @Override
    public void onListAddressResponseFailure(Throwable throwable) {
        displayMessage(throwable.getMessage());
    }

    @Override
    public void onListAddressResponseSuccess(ListAddResponse response) {
        if (response.getStatus() == 1) {
            if (addresssList.size() > 0) {
                addresssList.clear();

            }
            addresssList.addAll(response.getData());
            adpter.notifyDataSetChanged();

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
                this,
                message,
                true,
                getResources().getColor(R.color.white),
                getResources().getColor(R.color.colorPrimary),
                true);
    }

    @Override
    public void initProgressBar() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progress_dialog);

        dialog.setCancelable(false);
    }

    @Override
    public Activity getContext() {
        return this;
    }
}
