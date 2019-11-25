package com.addedfooddelivery_user.RestaurantList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.RestaurantList.adpter.AllRestaurantListAdpter;
import com.addedfooddelivery_user.RestaurantList.api.RestaurantConstructor;
import com.addedfooddelivery_user.RestaurantList.api.RestaurantPresenter;
import com.addedfooddelivery_user.RestaurantList.model.AllRestaurantData;
import com.addedfooddelivery_user.RestaurantList.model.AllRestaurantResponse;
import com.addedfooddelivery_user.common.CustomeToast;
import com.addedfooddelivery_user.home_filter.FiltersActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.addedfooddelivery_user.common.GlobalData.category;
import static com.addedfooddelivery_user.common.GlobalData.direction;
import static com.addedfooddelivery_user.common.GlobalData.price;
import static com.addedfooddelivery_user.common.GlobalData.rest_type;
import static com.addedfooddelivery_user.common.GlobalData.sort_by;

public class RestaurantListActivity extends AppCompatActivity implements RestaurantConstructor.View {

    AllRestaurantListAdpter mAdpter;
    private ArrayList<AllRestaurantData> allRestaurantList;
    GridLayoutManager mLayoutManagerPopular;
    @BindView(R.id.imgBackRestList)
    ImageView imgBackRestList;
    @BindView(R.id.rcyRestaurantList)
    RecyclerView rcyRestList;
    @BindView(R.id.imgRestListFilter)
    ImageView imgRestListFilter;
    Dialog dialog;
    RestaurantPresenter restaurantPresenter;
    @BindView(R.id.ll_home_no_data)
    LinearLayout llHomeNoData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);
        ButterKnife.bind(this);

        allRestaurantList = new ArrayList<>();

        setRestaurantData();
        initProgressBar();
        restaurantPresenter = new RestaurantPresenter(this);
    }

    @OnClick({R.id.imgBackRestList, R.id.imgRestListFilter})
    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.imgBackRestList:
                onBackPressed();
                break;
            case R.id.imgRestListFilter:
                startActivityForResult(new Intent(getContext(), FiltersActivity.class),1);
                overridePendingTransition(R.anim.rightto, R.anim.left);
                break;
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                restaurantPresenter.requestAPIAllRestaurant(RestaurantListActivity.this, rest_type, sort_by, direction, category, price);
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
            restaurantPresenter.requestAPIAllRestaurant(RestaurantListActivity.this, rest_type, sort_by, direction, category, price);

    }

    private void setRestaurantData() {
        mAdpter = new AllRestaurantListAdpter(RestaurantListActivity.this, allRestaurantList);
        mLayoutManagerPopular = new GridLayoutManager(RestaurantListActivity.this, 2, GridLayoutManager.VERTICAL, false);
        rcyRestList.setLayoutManager(mLayoutManagerPopular);

        rcyRestList.setItemAnimator(new DefaultItemAnimator());
        rcyRestList.setAdapter(mAdpter);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.leftto, R.anim.right);
        finish();
    }

    @Override
    public void onRestaurantResponseFailure(String t) {
        displayMessage(t);
        rcyRestList.setVisibility(View.GONE);
        llHomeNoData.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRestaurantResponseSuccess(AllRestaurantResponse response) {
        if (response.getStatus() == 1) {
            rcyRestList.setVisibility(View.VISIBLE);
            llHomeNoData.setVisibility(View.GONE);
            if (allRestaurantList.size() > 0) {
                allRestaurantList.clear();
            }

            allRestaurantList.addAll(response.getData());

        } else {
            rcyRestList.setVisibility(View.GONE);
            llHomeNoData.setVisibility(View.VISIBLE);
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
