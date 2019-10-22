package com.addedfooddelivery_user.orderHistory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.orderHistory.adpter.OrderHistoryListAdpter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderHistoryActivity extends AppCompatActivity {
    @BindView(R.id.rcyOrderHistory)
    RecyclerView rcyOHistory;

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
    private void setAddressData() {
        adpter = new OrderHistoryListAdpter(OrderHistoryActivity.this, orderHistoryList, new OrderHistoryListAdpter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {

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
