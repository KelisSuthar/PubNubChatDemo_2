package com.addedfooddelivery_user.home_deliverylist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.common.views.CustomEditText;
import com.addedfooddelivery_user.common.views.CustomTextView;
import com.addedfooddelivery_user.home.MainActivity;
import com.addedfooddelivery_user.home_deliverylist.adpter.AddressListAdpter;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.addedfooddelivery_user.common.AppConstants.AUTOCOMPLETE_REQUEST_CODE;

public class DeliveryListActivity extends AppCompatActivity {
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
    private ArrayList<String> addresssList;
    AddressListAdpter adpter;

    PlacesClient placesClient;
    AutocompleteSupportFragment places_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_delivery);

        ButterKnife.bind(this);
        addresssList = new ArrayList<>();
        fillRecords();
        setAddressData();
        initPlaces();

    }

    private void initPlaces() {
        Places.initialize(this, getString(R.string.google_maps_key));
        placesClient = Places.createClient(this);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                if (place != null) {
                    startActivity(new Intent(DeliveryListActivity.this, SaveAddressActivity.class).putExtra("placeId",place.getId()));
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

    @OnClick({R.id.imgBackAddress, R.id.edtSearch})
    public void EventClick(View view) {
        switch (view.getId()) {
            case R.id.imgBackAddress:
                onBackPressed();
                break;
            case R.id.edtSearch:
                setUpPlacesAutocomplete();
                break;
        }
    }

    private void setAddressData() {
        adpter = new AddressListAdpter(DeliveryListActivity.this, addresssList, new AddressListAdpter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                switch (view.getId()) {

                }
            }
        });

        mLayoutManagerDelivery = new LinearLayoutManager(DeliveryListActivity.this, RecyclerView.VERTICAL, false);
        rcyAddressList.setLayoutManager(mLayoutManagerDelivery);

        rcyAddressList.setItemAnimator(new DefaultItemAnimator());
        rcyAddressList.setAdapter(adpter);
    }

    private void fillRecords() {
        addresssList.add("1");
        addresssList.add("2");
        addresssList.add("3");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
        finish();
    }
}
