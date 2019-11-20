package com.addedfooddelivery_user.home_deliverylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.common.views.CustomEditText;
import com.addedfooddelivery_user.home_deliverylist.adpter.AddressListAdpter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DeliveryListActivity extends AppCompatActivity {
    @BindView(R.id.imgBackAddress)
    ImageView imgBackAddress;
    @BindView(R.id.edtSearch)
    CustomEditText edtSearch;
    @BindView(R.id.ll_current_location)
    LinearLayout llCurrentLocation;
    @BindView(R.id.ll_address_list)
    LinearLayout llAddressList;
    @BindView(R.id.rcyAddressList)
    RecyclerView rcyAddressList;
    LinearLayoutManager mLayoutManagerDelivery;
    private ArrayList<String> addresssList;
    AddressListAdpter adpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_delivery);

        ButterKnife.bind(this);
        addresssList = new ArrayList<>();
        fillRecords();
        setAddressData();
    }

    @OnClick({R.id.imgBackAddress})
    public void EventClick(View view) {
        switch (view.getId()) {
            case R.id.imgBackAddress:
                onBackPressed();
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
