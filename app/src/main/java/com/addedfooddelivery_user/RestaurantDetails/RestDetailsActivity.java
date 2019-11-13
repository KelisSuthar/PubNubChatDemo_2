package com.addedfooddelivery_user.RestaurantDetails;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
import com.addedfooddelivery_user.RestaurantDetails.model.ChildData;
import com.addedfooddelivery_user.RestaurantDetails.model.ParentData;
import com.addedfooddelivery_user.common.SimpleDividerItemDecoration;
import com.addedfooddelivery_user.common.views.CustomTextView;
import com.addedfooddelivery_user.cart.CartActivity;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

public class RestDetailsActivity extends AppCompatActivity {
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
    private ArrayList<String> reviewList;
    ReviewListAdpter reviewListAdpter;

    private static int currentPage = 0;
    private static final Integer[] XMEN = {R.drawable.person02, R.drawable.person08, R.drawable.food01, R.drawable.food09};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();

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

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        txtItemCount = findViewById(R.id.txtItemCount);
        rlCartFooter = findViewById(R.id.cart_footer);

        init();
        setupItemRecycleview();
        reviewList = new ArrayList<>();
        fillRecords();
        setRestaurantData();
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
        reviewList.add("1");
        reviewList.add("2");
        reviewList.add("3");
        reviewList.add("4");
        reviewList.add("5");
    }

    private void setRestaurantData() {
        reviewListAdpter = new ReviewListAdpter(RestDetailsActivity.this, reviewList);

        mLayoutManagerReview = new LinearLayoutManager(RestDetailsActivity.this, RecyclerView.VERTICAL, false);
        rcyReviewList.setLayoutManager(mLayoutManagerReview);
        rcyReviewList.addItemDecoration(new SimpleDividerItemDecoration(RestDetailsActivity.this));

        rcyReviewList.setItemAnimator(new DefaultItemAnimator());
        rcyReviewList.setAdapter(reviewListAdpter);
    }

    private void setupItemRecycleview() {
        List<ParentData> list = getList();

        rcyProductList.setLayoutManager(new LinearLayoutManager(this));

        productListAdapter myAdapter = new productListAdapter(RestDetailsActivity.this, list);
        rcyProductList.setAdapter(myAdapter);
        rcyProductList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rcyProductList.setAdapter(myAdapter);

    }

    private List<ParentData> getList() {

        List<ParentData> list_parent = new ArrayList<>();
        List<ChildData> list_data_child = new ArrayList<>();
        List<ChildData> list_data_child1 = new ArrayList<>();

        list_data_child.add(new ChildData("Manchurian dry"));
        list_data_child.add(new ChildData("Vegetable Hakka Noodles"));

        list_data_child1.add(new ChildData("Sweet potato egg casserole"));
        list_data_child1.add(new ChildData("Almond breakfast smoothie"));

        list_parent.add(new ParentData("Chinese", list_data_child));
        list_parent.add(new ParentData("Breakfast", list_data_child1));


        return list_parent;
    }

    private void init() {
        for (int i = 0; i < XMEN.length; i++)
            XMENArray.add(XMEN[i]);

        mPager.setAdapter(new viewPagerAdapter(RestDetailsActivity.this, XMENArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_rest_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.leftto, R.anim.right);
        finish();
    }
}
