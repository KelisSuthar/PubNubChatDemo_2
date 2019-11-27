package com.addedfooddelivery_user.restaurantList;

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
import com.addedfooddelivery_user.restaurantList.adpter.AllResCategoryListAdpter;
import com.addedfooddelivery_user.restaurantList.adpter.AllRestaurantListAdpter;
import com.addedfooddelivery_user.restaurantList.api.RestaurantConstructor;
import com.addedfooddelivery_user.restaurantList.api.RestaurantPresenter;
import com.addedfooddelivery_user.restaurantList.model.AllRestCategoryData;
import com.addedfooddelivery_user.restaurantList.model.AllRestCategoryResponse;
import com.addedfooddelivery_user.restaurantList.model.AllRestaurantData;
import com.addedfooddelivery_user.restaurantList.model.AllRestaurantResponse;
import com.addedfooddelivery_user.common.CustomeToast;

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
    AllResCategoryListAdpter mCategoryAdpter;
    private ArrayList<AllRestCategoryData> allResCategoryList;

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
        allResCategoryList = new ArrayList<>();

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
                startActivityForResult(new Intent(getContext(), FiltersActivity.class), 1);
                overridePendingTransition(R.anim.rightto, R.anim.left);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getIntent().hasExtra("foodCategoryName")) {
            String categoryName = getIntent().getStringExtra("foodCategoryName");
            restaurantPresenter.requestAPIAllRestaurantCategory(RestaurantListActivity.this, categoryName, sort_by, direction, price);
        } else
            restaurantPresenter.requestAPIAllRestaurant(RestaurantListActivity.this, rest_type, sort_by, direction, category, price);

    }

    private void setRestaurantData() {
        mAdpter = new AllRestaurantListAdpter(RestaurantListActivity.this, allRestaurantList);
        mCategoryAdpter = new AllResCategoryListAdpter(RestaurantListActivity.this, allResCategoryList);
        mLayoutManagerPopular = new GridLayoutManager(RestaurantListActivity.this, 2, GridLayoutManager.VERTICAL, false);
        rcyRestList.setLayoutManager(mLayoutManagerPopular);

        rcyRestList.setItemAnimator(new DefaultItemAnimator());





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
            rcyRestList.setAdapter(mAdpter);
            allRestaurantList.addAll(response.getData());
            mAdpter.notifyDataSetChanged();
        } else {
            rcyRestList.setVisibility(View.GONE);
            llHomeNoData.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRestCategoryFailure(String t) {
        displayMessage(t);
        rcyRestList.setVisibility(View.GONE);
        llHomeNoData.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRestCategorySuccess(AllRestCategoryResponse response) {
        if (response.getStatus() == 1) {
            rcyRestList.setVisibility(View.VISIBLE);
            llHomeNoData.setVisibility(View.GONE);
            if (allResCategoryList.size() > 0) {
                allResCategoryList.clear();
            }
            rcyRestList.setAdapter(mCategoryAdpter);
            allResCategoryList.addAll(response.getData());
            mCategoryAdpter.notifyDataSetChanged();
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
