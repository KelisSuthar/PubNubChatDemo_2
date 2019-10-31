package com.addedfooddelivery_user.RestaurantList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.home.fragement.adpter.PopularRestaurantListAdpter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RestaurantListActivity extends AppCompatActivity {
    PopularRestaurantListAdpter mAdpter;
    private ArrayList<String> popularRestaurantList;
    GridLayoutManager mLayoutManagerPopular;
    @BindView(R.id.imgBackRestList)
    ImageView imgBackRestList;
    @BindView(R.id.rcyRestaurantList)
    RecyclerView rcyRestList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);
        ButterKnife.bind(this);

        popularRestaurantList = new ArrayList<>();
        fillRecords();
        setRestaurantData();
    }

    @OnClick(R.id.imgBackRestList)
    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.imgBackRestList:
                onBackPressed();
                break;
        }
    }

    private void setRestaurantData() {
        mAdpter = new PopularRestaurantListAdpter(RestaurantListActivity.this, popularRestaurantList);
        mLayoutManagerPopular = new GridLayoutManager(RestaurantListActivity.this, 2, GridLayoutManager.VERTICAL, false);
        rcyRestList.setLayoutManager(mLayoutManagerPopular);

        rcyRestList.setItemAnimator(new DefaultItemAnimator());
        rcyRestList.setAdapter(mAdpter);
    }

    private void fillRecords() {
        popularRestaurantList.add("1");
        popularRestaurantList.add("2");
        popularRestaurantList.add("3");
        popularRestaurantList.add("4");
        popularRestaurantList.add("5");
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.leftto, R.anim.right);
        finish();
    }
}
