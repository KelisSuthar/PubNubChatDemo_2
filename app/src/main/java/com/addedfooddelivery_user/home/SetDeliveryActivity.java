package com.addedfooddelivery_user.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user._common.views.CustomEditText;
import com.addedfooddelivery_user.home.fragement.adpter.AddressListAdpter;
import com.addedfooddelivery_user.home.fragement.adpter.TrendingRestaurantListAdpter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SetDeliveryActivity extends AppCompatActivity {
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

    private void setAddressData() {
        adpter = new AddressListAdpter(SetDeliveryActivity.this, addresssList, new AddressListAdpter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                switch (view.getId()) {

                }
            }
        });

        mLayoutManagerDelivery = new LinearLayoutManager(SetDeliveryActivity.this, RecyclerView.VERTICAL, false);
        rcyAddressList.setLayoutManager(mLayoutManagerDelivery);

        rcyAddressList.setItemAnimator(new DefaultItemAnimator());
        rcyAddressList.setAdapter(adpter);
    }

    private void fillRecords() {
        addresssList.add("1");
        addresssList.add("2");
        addresssList.add("3");
    }
}
