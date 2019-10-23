package com.addedfooddelivery_user.orderTracking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user._common.views.CustomTextView;
import com.addedfooddelivery_user.orderSummary.OrderSummaryActivity;
import com.addedfooddelivery_user.orderSummary.adpter.OrderSummeryItemListAdpter;
import com.addedfooddelivery_user.orderTracking.adpter.OrderTrackingItemListAdpter;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderTrackingActivity extends AppCompatActivity implements OnMapReadyCallback {

    @BindView(R.id.fram_order)
    FrameLayout framOrder;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.CardOrderDetails)
    MaterialCardView CardOrderDetails;
    @BindView(R.id.orderId)
    CustomTextView orderId;
    @BindView(R.id.orderTime)
    CustomTextView orderTime;
    @BindView(R.id.orderEstimatedLabel)
    CustomTextView orderEstimatedLabel;
    @BindView(R.id.orderArrivalTime)
    CustomTextView orderArrivalTime;
    @BindView(R.id.orderPrise)
    CustomTextView orderPrise;
    @BindView(R.id.orderItemCount)
    CustomTextView orderItemCount;
    @BindView(R.id.imgOrderStatus)
    ImageView imgOrderStatus;
    @BindView(R.id.orderStatusLabel)
    CustomTextView orderStatusLabel;
    @BindView(R.id.orderStatus)
    CustomTextView orderStatus;
    @BindView(R.id.driver_info)
    RelativeLayout driverInfo;
    @BindView(R.id.imgDriver)
    CircularImageView imgDriver;
    @BindView(R.id.txtDriverName)
    CustomTextView txtDriverName;
    @BindView(R.id.driver_desc)
    CustomTextView driverDesc;
    @BindView(R.id.rcyOrderItem)
    RecyclerView rcyOrderItem;
    @BindView(R.id.imgChatBoat)
    ImageView imgChatBoat;
    @BindView(R.id.txtHaveIssue)
    CustomTextView txtHaveIssue;
    SupportMapFragment map;
    LinearLayoutManager mLayoutManagerTracking;
    private ArrayList<String> orderTrackingItemList;
    OrderTrackingItemListAdpter adpter;
    GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_tracking);
        ButterKnife.bind(this);

        orderTrackingItemList = new ArrayList<>();
        fillRecords();
        setAddressData();
        setupMap();
    }

    private void setAddressData() {
        adpter = new OrderTrackingItemListAdpter(OrderTrackingActivity.this, orderTrackingItemList);

        mLayoutManagerTracking = new LinearLayoutManager(OrderTrackingActivity.this, RecyclerView.VERTICAL, false);
        rcyOrderItem.setLayoutManager(mLayoutManagerTracking);

        rcyOrderItem.setItemAnimator(new DefaultItemAnimator());
        rcyOrderItem.setAdapter(adpter);
    }

    private void fillRecords() {
        orderTrackingItemList.add("1");
        orderTrackingItemList.add("2");
        orderTrackingItemList.add("3");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.leftto, R.anim.right);
        finish();
    }

    private void setupMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (map != null) {
            map.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
       /* googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

        CameraUpdate center = CameraUpdateFactory.newLatLng(sydney);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(5);

        googleMap.moveCamera(center);
        googleMap.animateCamera(zoom);
    }
}
