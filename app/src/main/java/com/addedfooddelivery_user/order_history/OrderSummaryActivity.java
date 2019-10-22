package com.addedfooddelivery_user.order_history;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user._common.views.CustomTextView;
import com.addedfooddelivery_user.orderHistory.OrderHistoryActivity;
import com.addedfooddelivery_user.orderHistory.adpter.OrderHistoryListAdpter;
import com.addedfooddelivery_user.order_history.adpter.OrderSummeryItemListAdpter;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderSummaryActivity extends AppCompatActivity {
    @BindView(R.id.img_back_oHistory)
    ImageView imgBack;
    @BindView(R.id.imgRestaurantSummery)
    PorterShapeImageView imgRestaurantSummery;
    @BindView(R.id.txtResNameSummery)
    CustomTextView txtResNameSummery;
    @BindView(R.id.txtRestAddressSummery)
    CustomTextView txtRestAddressSummery;
    @BindView(R.id.rcyOrderItemSummery)
    RecyclerView rcyOrderItemSummery;
    @BindView(R.id.txtItemTotal)
    CustomTextView txtItemTotal;
    @BindView(R.id.txtTaxes)
    CustomTextView txtTaxes;
    @BindView(R.id.txtDeliveryCharge)
    CustomTextView txtDeliveryCharge;
    @BindView(R.id.txtOrderTotal)
    CustomTextView txtOrderTotal;
    @BindView(R.id.txtOrderId)
    CustomTextView txtOrderId;
    @BindView(R.id.txtPaymentStatus)
    CustomTextView txtPaymentStatus;
    @BindView(R.id.txtPhoneNumber)
    CustomTextView txtPhoneNumber;
    @BindView(R.id.txtDeliverAddress)
    CustomTextView txtDeliverAddress;
    @BindView(R.id.ll_repeatOrder)
    LinearLayout llRepeatOrder;


    LinearLayoutManager mLayoutManagerSummaryItem;
    private ArrayList<String> orderSummaryItemList;
    OrderSummeryItemListAdpter adpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);
        ButterKnife.bind(this);

        ButterKnife.bind(this);
        orderSummaryItemList = new ArrayList<>();
        fillRecords();
        setAddressData();
    }
    private void setAddressData() {
        adpter = new OrderSummeryItemListAdpter(OrderSummaryActivity.this, orderSummaryItemList);

        mLayoutManagerSummaryItem = new LinearLayoutManager(OrderSummaryActivity.this, RecyclerView.VERTICAL, false);
        rcyOrderItemSummery.setLayoutManager(mLayoutManagerSummaryItem);

        rcyOrderItemSummery.setItemAnimator(new DefaultItemAnimator());
        rcyOrderItemSummery.setAdapter(adpter);
    }

    private void fillRecords() {
        orderSummaryItemList.add("1");
        orderSummaryItemList.add("2");
        orderSummaryItemList.add("3");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.leftto, R.anim.right);
        finish();
    }
}
