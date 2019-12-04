package com.addedfooddelivery_user.home_deliverylist;

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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.cart.CartActivity;
import com.addedfooddelivery_user.common.CommonGps;
import com.addedfooddelivery_user.common.CustomeToast;
import com.addedfooddelivery_user.common.views.CustomButton;
import com.addedfooddelivery_user.common.views.CustomEditText;
import com.addedfooddelivery_user.home_deliverylist.api.AddAddressConstructor;
import com.addedfooddelivery_user.home_deliverylist.api.AddAddressPresenter;
import com.addedfooddelivery_user.home_deliverylist.model.ListAddResponse;
import com.addedfooddelivery_user.home_deliverylist.model.SaveAddResponse;
import com.addedfooddelivery_user.home_deliverylist.model.SetDefaultAddResponse;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.io.IOException;
import java.util.Arrays;
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

public class SaveAddressActivity extends AppCompatActivity implements OnMapReadyCallback, AddAddressConstructor.View, GoogleMap.OnCameraMoveListener, GoogleMap.OnCameraIdleListener {
    @BindView(R.id.backArrow)
    ImageView backArrow;
    @BindView(R.id.ll_map)
    RelativeLayout llMap;
    @BindView(R.id.imgCurrentLoc)
    TextView imgCurrentLocation;
    @BindView(R.id.current_loc_img)
    ImageView currentLocImg;
    @BindView(R.id.animation_line_cart_add)
    ImageView animationLineCartAdd;
    @BindView(R.id.edLocation)
    CustomEditText edaddress;
    @BindView(R.id.landmark)
    CustomEditText landmark;
    @BindView(R.id.type_radiogroup)
    RadioGroup typeRadiogroup;
    @BindView(R.id.home_radio)
    RadioButton homeRadio;
    @BindView(R.id.work_radio)
    RadioButton workRadio;
    @BindView(R.id.other_radio)
    RadioButton otherRadio;
    @BindView(R.id.other_address_title_layout)
    RelativeLayout otherAddressTitleLayout;
    @BindView(R.id.other_address_header_et)
    CustomEditText otherAddressHeaderEt;
    @BindView(R.id.cancel_txt)
    TextView cancelTxt;
    @BindView(R.id.saveAdd)
    CustomButton saveAdd;
    FusedLocationProviderClient mFusedLocationClient;
    AlertDialog alertDialog;
    private double wayLatitude = 0.0, wayLongitude = 0.0;
    Geocoder geocoder;
    List<Address> addresses;
    SupportMapFragment mapFragment;
    private GoogleMap mMap;
    PlacesClient placesClient;
    String addressType;

    AddAddressPresenter addAddressPresenter;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_save_address);
        ButterKnife.bind(this);

        checkPermission();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        initPlace();
        addAddressPresenter = new AddAddressPresenter(this);
        addressType = getResources().getString(R.string.other);
        otherRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentLocImg.setBackgroundResource(R.drawable.ic_map_dropuppin);
                otherAddressTitleLayout.setVisibility(View.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(SaveAddressActivity.this, R.anim.slide_in_right);
                animation.setDuration(500);
                Animation animation2 = AnimationUtils.loadAnimation(SaveAddressActivity.this, R.anim.slide_out_left);
                animation2.setDuration(500);
                typeRadiogroup.startAnimation(animation2);
                otherAddressTitleLayout.startAnimation(animation);
                typeRadiogroup.setVisibility(View.GONE);
            }
        });

        typeRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = radioGroup.findViewById(i);
                if (radioButton.getText().toString().toLowerCase().equals("home")) {
                    addressType = getResources().getString(R.string.home);

                    currentLocImg.setBackgroundResource(R.drawable.ic_location_homegray_midum);
                    currentLocImg.setColorFilter(getResources().getColor(R.color.colorPrimary));

                    if (homeRadio.isChecked()) {
                        homeRadio.setTextColor(getResources().getColor(R.color.colorPrimary));
                        homeRadio.setBackground(getResources().getDrawable(R.drawable.select_border_save_add));

                        workRadio.setTextColor(getResources().getColor(R.color.text_gray_1));
                        workRadio.setBackground(getResources().getDrawable(R.drawable.border_save_address));
                        otherRadio.setTextColor(getResources().getColor(R.color.text_gray_1));
                        otherRadio.setBackground(getResources().getDrawable(R.drawable.border_save_address));
                    } else {
                        homeRadio.setTextColor(getResources().getColor(R.color.text_gray_1));
                        homeRadio.setBackground(getResources().getDrawable(R.drawable.border_save_address));
                    }
                } else if (radioButton.getText().toString().toLowerCase().equals("work")) {
                    addressType = getResources().getString(R.string.work);

                    currentLocImg.setBackgroundResource(R.drawable.ic_location_workgray_midum);
                    currentLocImg.setColorFilter(getResources().getColor(R.color.colorPrimary));
                    if (workRadio.isChecked()) {
                        workRadio.setTextColor(getResources().getColor(R.color.colorPrimary));
                        workRadio.setBackground(getResources().getDrawable(R.drawable.select_border_save_add));

                        homeRadio.setTextColor(getResources().getColor(R.color.text_gray_1));
                        homeRadio.setBackground(getResources().getDrawable(R.drawable.border_save_address));
                        otherRadio.setTextColor(getResources().getColor(R.color.text_gray_1));
                        otherRadio.setBackground(getResources().getDrawable(R.drawable.border_save_address));

                    } else {
                        workRadio.setTextColor(getResources().getColor(R.color.text_gray_1));
                        workRadio.setBackground(getResources().getDrawable(R.drawable.border_save_address));


                    }
                } else if (radioButton.getText().toString().equalsIgnoreCase(getResources().getString(R.string.other))) {
                    if (otherRadio.isChecked()) {
                        addressType = getResources().getString(R.string.other);

                        //otherAddressHeaderEt.setText(edaddress.getType());
                        otherRadio.setTextColor(getResources().getColor(R.color.colorPrimary));
                        otherRadio.setBackground(getResources().getDrawable(R.drawable.select_border_save_add));

                        homeRadio.setTextColor(getResources().getColor(R.color.text_gray_1));
                        homeRadio.setBackground(getResources().getDrawable(R.drawable.border_save_address));
                        workRadio.setTextColor(getResources().getColor(R.color.text_gray_1));
                        workRadio.setBackground(getResources().getDrawable(R.drawable.border_save_address));
                    } else {
                        otherRadio.setTextColor(getResources().getColor(R.color.text_gray_1));
                        otherRadio.setBackground(getResources().getDrawable(R.drawable.border_save_address));
                    }
                    currentLocImg.setBackgroundResource(R.drawable.ic_location_othergray_midum);

                    currentLocImg.setColorFilter(getResources().getColor(R.color.colorPrimary));


                    otherAddressTitleLayout.setVisibility(View.VISIBLE);
                    Animation animation = AnimationUtils.loadAnimation(SaveAddressActivity.this, R.anim.slide_in_right);
                    animation.setDuration(500);
                    Animation animation2 = AnimationUtils.loadAnimation(SaveAddressActivity.this, R.anim.slide_out_left);
                    animation2.setDuration(500);
                    typeRadiogroup.startAnimation(animation2);
                    otherAddressTitleLayout.startAnimation(animation);
                    typeRadiogroup.setVisibility(View.GONE);
                }

                System.out.println("typeRadiogroup " + radioButton.getText().toString().toLowerCase());
                //edaddress.setType(radioButton.getText().toString().toLowerCase());

            }
        });
        //pass by autocomplete place api
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String id = extras.getString("placeId");
            if (id != null) {
                getAndSetBundleData(id);
            }
        }


    }

    private void initPlace() {
        // Initialize the SDK
        Places.initialize(getApplicationContext(), getResources().getString(R.string.google_maps_key));
        // Create a new Places client instance
        placesClient = Places.createClient(this);
    }

    private void getAndSetBundleData(String id) {
        List<Place.Field> placeFields = Arrays.asList(
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
        FetchPlaceRequest request = FetchPlaceRequest.builder(id, placeFields)
                .build();
        placesClient.fetchPlace(request).addOnSuccessListener((response) -> {
            Place place = response.getPlace();


            geocoder = new Geocoder(SaveAddressActivity.this, Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(place.getLatLng().latitude, place.getLatLng().longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            } catch (IOException e) {
                e.printStackTrace();
            }
            edaddress.setText(addresses.get(0).getAddressLine(0));
            LatLng latLng = place.getLatLng();
            CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(16).build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        }).addOnFailureListener((exception) -> {
            if (exception instanceof ApiException) {
                ApiException apiException = (ApiException) exception;
                int statusCode = apiException.getStatusCode();
                // Handle error with given status code.
                Log.e("FoundPlace", "Place not found: " + exception.getMessage());
            }
        });

    }

    @OnClick({R.id.cancel_txt, R.id.backArrow, R.id.imgCurrentLoc, R.id.edLocation, R.id.saveAdd})
    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.cancel_txt:
                otherAddressHeaderEt.setVisibility(View.VISIBLE);
                Animation animation2 = AnimationUtils.loadAnimation(SaveAddressActivity.this, R.anim.slide_out_right);
                animation2.setDuration(500);
                otherAddressHeaderEt.startAnimation(animation2);
                Animation animation = AnimationUtils.loadAnimation(SaveAddressActivity.this, R.anim.slide_in_left);
                animation.setDuration(500);
                typeRadiogroup.startAnimation(animation);
                typeRadiogroup.setVisibility(View.VISIBLE);
                otherRadio.setChecked(true);
                break;
            case R.id.backArrow:
                onBackPressed();
                break;
            case R.id.imgCurrentLoc:
                getLocation();
                break;
            case R.id.edLocation:
                setUpPlacesAutocomplete();
                break;
            case R.id.saveAdd:
                if (addresses != null) {
                    addAddressPresenter.requestAddAddress(SaveAddressActivity.this,
                            addressType,
                            addresses.get(0).getAddressLine(0),
                            addresses.get(0).getLatitude(),
                            addresses.get(0).getLongitude(),
                            landmark.getText().toString().trim(),
                            addresses.get(0).getLocality());
                }
                break;
        }
    }

    private void setUpPlacesAutocomplete() {
        List<Place.Field> fields = Arrays.asList(
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

        Intent intent = new Autocomplete.IntentBuilder(
                AutocompleteActivityMode.OVERLAY, fields)
                .build(this);
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
    }

    private void checkPermission() {
        boolean coarsePermissionCheck = (ContextCompat.checkSelfPermission(SaveAddressActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED);
        boolean finePermissionCheck = (ContextCompat.checkSelfPermission(SaveAddressActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
        if (coarsePermissionCheck && finePermissionCheck) {
            checkGPS();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        PERMISSION_LOCATION_REQUEST_CODE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                if (place != null) {
                    if (place.getId() != null) {
                        getAndSetBundleData(place.getId());
                    }
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
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
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

    private void checkGPS() {
        new CommonGps(this).turnGPSOn(new CommonGps.onGpsListener() {
            @Override
            public void gpsStatus(boolean isGPSEnable) {
                if (isGPSEnable) {
                    if (alertDialog != null) {
                        alertDialog.dismiss();
                    }

                } else {
                    CustomeDialog(SaveAddressActivity.this);
                }
            }
        });
    }

    private void getLocation() {
        if (mFusedLocationClient == null) {
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(SaveAddressActivity.this);
        }

        mFusedLocationClient.getLastLocation().addOnSuccessListener(SaveAddressActivity.this, new OnSuccessListener<Location>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    wayLatitude = location.getLatitude();
                    wayLongitude = location.getLongitude();

                    geocoder = new Geocoder(SaveAddressActivity.this, Locale.getDefault());

                    try {
                        addresses = geocoder.getFromLocation(wayLatitude, wayLongitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                            new LatLng(wayLatitude,
                                    wayLongitude), 16));

                    edaddress.setText(addresses.get(0).getAddressLine(0));
                    /*Toast.makeText(SaveAddressActivity.this, "Success"+String.valueOf(edaddress.trim()), Toast.LENGTH_SHORT).show();*/
                }
            }
        });
    }

    public void locationPermission() {
        Snacky.builder()
                .setActivity(SaveAddressActivity.this)
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
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (mMap != null) {
            mMap.getUiSettings().setCompassEnabled(false);
            mMap.setBuildingsEnabled(true);
            mMap.setOnCameraMoveListener(this);
            mMap.setOnCameraIdleListener(this);
            mMap.getUiSettings().setRotateGesturesEnabled(false);
            mMap.getUiSettings().setTiltGesturesEnabled(false);
        }
      /*  if(mMap!=null) {
            mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                @Override
                public void onMarkerDragStart(Marker marker) {

                }

                @Override
                public void onMarkerDrag(Marker marker) {

                }

                @Override
                public void onMarkerDragEnd(Marker marker) {
// for move marker get latlong
                    LatLng position = marker.getPosition();
                    geocoder = new Geocoder(SaveAddressActivity.this, Locale.getDefault());

                    try {
                        addresses = geocoder.getFromLocation(position.latitude, position.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    edaddress.setText(addresses.get(0).getAddressLine(0));
                }
            });
        }*/

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //startActivity(new Intent(SignupActivity.this, LoginEmailActivity.class));
        overridePendingTransition(R.anim.leftto, R.anim.right);
        finish();
    }

    @Override
    public void onSaveAddressResponseFailure(Throwable throwable) {
        displayMessage(throwable.getMessage());
    }

    @Override
    public void onSaveAddressResponseSuccess(SaveAddResponse response) {
        if (response.getStatus() == 1) {
            displayMessage(response.getMessage());
           /* if(getIntent().hasExtra("screen")){
                if(getIntent().getStringExtra("screen").equalsIgnoreCase("cart")){
                    startActivity(new Intent(SaveAddressActivity.this, CartActivity.class));
                    overridePendingTransition(R.anim.leftto, R.anim.right);
                    finish();
                }else {
                    startActivity(new Intent(SaveAddressActivity.this, DeliveryListActivity.class));
                    overridePendingTransition(R.anim.leftto, R.anim.right);
                    finish();
                }
            }*/
            startActivity(new Intent(SaveAddressActivity.this, DeliveryListActivity.class));
            overridePendingTransition(R.anim.leftto, R.anim.right);
            finish();

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

    @Override
    public void onListAddressResponseFailure(Throwable throwable) {
// getting address list
    }

    @Override
    public void onListAddressResponseSuccess(ListAddResponse response) {
// getting address list
    }

    @Override
    public void onsetDefaultAddailure(String throwable) {
        // for set default address
    }

    @Override
    public void onsetDefaultAddSuccess(SetDefaultAddResponse response) {
// for set default address
    }

    @Override
    public void onCameraMove() {

    }

    @Override
    public void onCameraIdle() {
        CameraPosition cameraPosition = mMap.getCameraPosition();

        geocoder = new Geocoder(SaveAddressActivity.this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(cameraPosition.target.latitude, cameraPosition.target.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (addresses.size() != 0) {
            edaddress.setText(addresses.get(0).getAddressLine(0));
        }
    }
}
