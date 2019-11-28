package com.addedfooddelivery_user.RestaurantDetails;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.RatingBar;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.RestaurantDetails.adpter.productListAdapter;
import com.addedfooddelivery_user.RestaurantDetails.adpter.ReviewListAdpter;
import com.addedfooddelivery_user.RestaurantDetails.adpter.viewPagerAdapter;
import com.addedfooddelivery_user.RestaurantDetails.api.RestDetailsConstructor;
import com.addedfooddelivery_user.RestaurantDetails.api.RestDetailsPresenter;
import com.addedfooddelivery_user.RestaurantDetails.model.Categorydetail;
import com.addedfooddelivery_user.RestaurantDetails.model.ParentData;
import com.addedfooddelivery_user.RestaurantDetails.model.RestDetailsResponse;
import com.addedfooddelivery_user.RestaurantDetails.model.RestaurantDetails;
import com.addedfooddelivery_user.RestaurantDetails.model.RestaurantImage;
import com.addedfooddelivery_user.RestaurantDetails.model.RestaurantReview;
import com.addedfooddelivery_user.RestaurantDetails.model.addQTYResponce.QtyAddResponce;
import com.addedfooddelivery_user.common.CustomeToast;
import com.addedfooddelivery_user.common.SimpleDividerItemDecoration;
import com.addedfooddelivery_user.common.views.CustomTextView;
import com.addedfooddelivery_user.cart.CartActivity;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

public class RestDetailsActivity extends AppCompatActivity implements RestDetailsConstructor.View {
    @BindView(R.id.txtRestName)
    CustomTextView txtRestName;
    @BindView(R.id.txtRestItems)
    CustomTextView txtRestItems;
    @BindView(R.id.restaurantRating)
    RatingBar restaurantRating;
    @BindView(R.id.txtRating)
    CustomTextView txtRating;
    @BindView(R.id.txtdeliveryStatus)
    CustomTextView txtdeliveryStatus;
    @BindView(R.id.switchVeg)
    SwitchMaterial switchVeg;
    @BindView(R.id.txtLabelReview)
    CustomTextView txtLabelReview;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collapseToolBarEvent;
    @BindView(R.id.pager)
    ViewPager mPager;
    @BindView(R.id.rcyProductList)
    RecyclerView rcyProductList;
    @BindView(R.id.rcyReviewList)
    RecyclerView rcyReviewList;
    @BindView(R.id.txtItemTotal)
    public CustomTextView txtItemTotal;
    @BindView(R.id.txtViewCart)
    public CustomTextView txtViewCart;
    public static CustomTextView txtItemCount;
    public static RelativeLayout rlCartFooter;
    LinearLayoutManager mLayoutManagerReview;


    ReviewListAdpter reviewListAdpter;
    productListAdapter productListAdapter;
    viewPagerAdapter adapter;

    private static int currentPage = 0;
    private ArrayList<RestaurantDetails> restDetails;
    private ArrayList<RestaurantImage> restDetailsImages;
    private ArrayList<RestaurantReview> restReview;
    RestDetailsPresenter restDetailsPresenter;
    Dialog dialog;
    List<ParentData> list;
    String restId = "";
    boolean veg = false;


    private AppBarLayout.OnOffsetChangedListener onOffsetChangedListener = new AppBarLayout.OnOffsetChangedListener() {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            //  Vertical offset == 0 indicates appBar is fully expanded.
            if (Math.abs(verticalOffset) > 450) {
                toolbar.setTitle(getResources().getString(R.string.title_activity_rest_details));
                collapseToolBarEvent.setTitle(getResources().getString(R.string.title_activity_rest_details));
            } else {
                collapseToolBarEvent.setTitle("");
                toolbar.setTitle("");
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_rest_details);
        ButterKnife.bind(this);
        appBarLayout.addOnOffsetChangedListener(onOffsetChangedListener);
        setSupportActionBar(toolbar);
        restId = String.valueOf(getIntent().getIntExtra("restaurantID", 0));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        restDetails = new ArrayList<>();
        restDetailsImages = new ArrayList<>();
        restReview = new ArrayList<>();

        restDetailsPresenter = new RestDetailsPresenter(this);

        txtItemCount = findViewById(R.id.txtItemCount);
        rlCartFooter = findViewById(R.id.cart_footer);
        fillRecords();
        init();
        setReviewData();
        switchVeg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    veg = true;
                    restDetailsPresenter.requestAPIRestDetails(RestDetailsActivity.this, restId, "on");
                } else {
                    veg = false;
                    restDetailsPresenter.requestAPIRestDetails(RestDetailsActivity.this, restId, "off");
                }
            }
        });

    }

    @OnClick(R.id.txtViewCart)
    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.txtViewCart:
                startActivity(new Intent(RestDetailsActivity.this, CartActivity.class));
                overridePendingTransition(R.anim.rightto, R.anim.left);
                break;
        }
    }

    private void fillRecords() {
        veg = false;
        restDetailsPresenter.requestAPIRestDetails(RestDetailsActivity.this, restId, "off");
    }

    private void setReviewData() {
        reviewListAdpter = new ReviewListAdpter(RestDetailsActivity.this, restReview);

        mLayoutManagerReview = new LinearLayoutManager(RestDetailsActivity.this, RecyclerView.VERTICAL, false);
        rcyReviewList.setLayoutManager(mLayoutManagerReview);
        rcyReviewList.addItemDecoration(new SimpleDividerItemDecoration(RestDetailsActivity.this));

        rcyReviewList.setItemAnimator(new DefaultItemAnimator());
        rcyReviewList.setAdapter(reviewListAdpter);
    }

    private void restaurantCategory() {
        rcyProductList.setLayoutManager(new LinearLayoutManager(this));
        productListAdapter = new productListAdapter(RestDetailsActivity.this, list, new productListAdapter.OnItemClickListener() {
            @Override
            public void onUpdateItemClick(int position, View view, int count, int itemID) {
                restDetailsPresenter.requestUpdateQTY(RestDetailsActivity.this, restId, itemID, count);
            }

            @Override
            public void onAddItemClick(int position, View view, int count, String itemPrice, int itemID) {
                restDetailsPresenter.requestAddQTY(RestDetailsActivity.this, restId, itemID,itemPrice, count);
            }
        });
        rcyProductList.setAdapter(productListAdapter);
        rcyProductList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rcyProductList.setAdapter(productListAdapter);

    }

    private List<ParentData> getList(List<Categorydetail> categorydetails) {

        List<ParentData> list_parent = new ArrayList<>();
        for (int i = 0; i < categorydetails.size(); i++) {
            list_parent.add(new ParentData(categorydetails.get(i).getFoodCategoryName(), categorydetails.get(i).getCategoryList()));
        }

        return list_parent;
    }

    private void init() {

        adapter = new viewPagerAdapter(RestDetailsActivity.this, restDetailsImages);
        mPager.setAdapter(adapter);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == restDetailsImages.size()) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.leftto, R.anim.right);
        finish();
    }

    @Override
    public void onRestDetailsResponseFailure(String throwable) {
        displayMessage(throwable);
    }

    @Override
    public void onRestDetailsResponseSuccess(RestDetailsResponse response) {
        initProgressBar();
        if (response.getStatus() == 1) {
            if (restDetails.size() > 0) {
                restDetails.clear();
            }
            if (restDetailsImages.size() > 0) {
                restDetailsImages.clear();
            }
            if (restReview.size() > 0) {
                restReview.clear();
            }
            restDetails.add(response.getData().getRestaurantDetails());
            setDataRestaurantData(restDetails);
//for image slider
            restDetailsImages.addAll(response.getData().getRestaurantImages());
            adapter.notifyDataSetChanged();
//for review list
            restReview.addAll(response.getData().getReview());
            if (restReview.size() > 0) {
                txtLabelReview.setVisibility(View.VISIBLE);
            } else
                txtLabelReview.setVisibility(View.GONE);
            reviewListAdpter.notifyDataSetChanged();
//for restaurant category
            list = getList(response.getData().getCategorydetails());
            restaurantCategory();
            productListAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void onUpdateQTYFailure(String throwable) {
        displayMessage(throwable);
    }

    @Override
    public void onUpdateQTYSuccess(QtyAddResponce response) {
        if (response.getStatus() == 1) {
            if (veg) {
                restDetailsPresenter.requestAPIRestDetails(RestDetailsActivity.this, restId, "on");
            } else
                restDetailsPresenter.requestAPIRestDetails(RestDetailsActivity.this, restId, "off");
        }
    }

    @Override
    public void onAddQTYFailure(String throwable) {
        displayMessage(throwable);
    }

    @Override
    public void onAddQTYSuccess(QtyAddResponce response) {
        if (response.getStatus() == 1) {
            if (veg) {
                restDetailsPresenter.requestAPIRestDetails(RestDetailsActivity.this, restId, "on");
            } else
                restDetailsPresenter.requestAPIRestDetails(RestDetailsActivity.this, restId, "off");
        }
    }

    private void setDataRestaurantData(ArrayList<RestaurantDetails> restDetails) {
        if (restDetails != null && restDetails.size() != 0) {
            txtRestName.setText(TextUtils.isEmpty(restDetails.get(0).getRestaurantName().toString()) ? "" : restDetails.get(0).getRestaurantName().toString());
            txtRestItems.setText(TextUtils.isEmpty(restDetails.get(0).getRestaurantAddress().toString()) ? "" : restDetails.get(0).getRestaurantAddress().toString());
            txtRating.setText(TextUtils.isEmpty(restDetails.get(0).getRestaurantRatingAVG().toString()) ? "" : restDetails.get(0).getRestaurantRatingAVG().toString());
            if (restDetails.get(0).getRestaurantRatingAVG() != null) {
                if (restDetails.get(0).getRestaurantRatingAVG() == 0) {
                    restaurantRating.setRating((float) 0.0);
                } else
                    restaurantRating.setRating(restDetails.get(0).getRestaurantRatingAVG());
            }
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
