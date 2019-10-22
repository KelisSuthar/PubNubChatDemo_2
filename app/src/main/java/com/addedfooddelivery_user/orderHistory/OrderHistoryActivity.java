package com.addedfooddelivery_user.orderHistory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.orderHistory.adpter.OrderHistoryListAdpter;
import com.addedfooddelivery_user.order_history.OrderSummaryActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderHistoryActivity extends AppCompatActivity {
    @BindView(R.id.rcyOrderHistory)
    RecyclerView rcyOHistory;
    @BindView(R.id.img_back_oHistory)
    ImageView imgBackHistory;

    LinearLayoutManager mLayoutManagerHistory;
    private ArrayList<String> orderHistoryList;
    OrderHistoryListAdpter adpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        ButterKnife.bind(this);
        orderHistoryList = new ArrayList<>();
        fillRecords();
        setAddressData();
    }
    @OnClick(R.id.img_back_oHistory)
    public  void eventClick(View view){
        switch (view.getId()){
            case R.id.img_back_oHistory:
                onBackPressed();
                break;
        }
    }

    private void setAddressData() {
        adpter = new OrderHistoryListAdpter(OrderHistoryActivity.this, orderHistoryList, new OrderHistoryListAdpter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                startActivity(new Intent(OrderHistoryActivity.this, OrderSummaryActivity.class));
                overridePendingTransition(R.anim.rightto, R.anim.left);
            }
        });

        mLayoutManagerHistory = new LinearLayoutManager(OrderHistoryActivity.this, RecyclerView.VERTICAL, false);
        rcyOHistory.setLayoutManager(mLayoutManagerHistory);

        rcyOHistory.setItemAnimator(new DefaultItemAnimator());
        rcyOHistory.setAdapter(adpter);
    }

    private void fillRecords() {
        orderHistoryList.add("1");
        orderHistoryList.add("2");
        orderHistoryList.add("3");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.leftto, R.anim.right);
        finish();
    }
}
