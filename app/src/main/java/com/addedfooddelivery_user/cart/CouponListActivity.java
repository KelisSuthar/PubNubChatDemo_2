package com.addedfooddelivery_user.cart;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.RestaurantDetails.model.addQTYResponce.QtyAddResponce;
import com.addedfooddelivery_user.cart.adpter.CouponListAdpter;
import com.addedfooddelivery_user.cart.api.CartConstructor;
import com.addedfooddelivery_user.cart.api.CartPresenter;
import com.addedfooddelivery_user.cart.model.CartDataResponce;
import com.addedfooddelivery_user.cart.model.CouponList;
import com.addedfooddelivery_user.cart.model.CouponListData;
import com.addedfooddelivery_user.common.CustomeToast;
import com.addedfooddelivery_user.common.model.CommonResponce;
import com.addedfooddelivery_user.common.views.CustomEditText;
import com.addedfooddelivery_user.common.views.CustomTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CouponListActivity extends AppCompatActivity implements CartConstructor.View {
    @BindView(R.id.imgBackCoupon)
    ImageView imgBackCoupon;
    @BindView(R.id.edCoupon)
    CustomEditText edCoupon;
    @BindView(R.id.txtApply)
    CustomTextView txtApply;
    @BindView(R.id.txtLabelAvilable)
    CustomTextView txtLabelAvilable;
    @BindView(R.id.rcyCouponList)
    RecyclerView rcyCouponList;
    Dialog dialog;
    CartPresenter cartPresenter;
    ArrayList<CouponListData> couponLists;
    CouponListAdpter couponListAdpter;
    LinearLayoutManager mLayoutManagerCoupon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_list);
        ButterKnife.bind(this);
        initProgressBar();
        cartPresenter = new CartPresenter(this);
        couponLists = new ArrayList<>();
        setCouponData();
    }

    @OnClick(R.id.txtApply)
    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.txtApply:
                Intent intent = new Intent();
                intent.putExtra("coupon", edCoupon.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }

    private void setCouponData() {
        couponListAdpter = new CouponListAdpter(CouponListActivity.this, couponLists, new CouponListAdpter.OnItemClickListener() {
            @Override
            public void onCouponItemClick(int position, View view) {
                Intent intent = new Intent();
                intent.putExtra("coupon", couponLists.get(position).getCouponCode());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        mLayoutManagerCoupon = new LinearLayoutManager(CouponListActivity.this, RecyclerView.VERTICAL, false);
        rcyCouponList.setLayoutManager(mLayoutManagerCoupon);

        rcyCouponList.setItemAnimator(new DefaultItemAnimator());
        rcyCouponList.setAdapter(couponListAdpter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cartPresenter.requestCouponData(CouponListActivity.this);
    }

    @Override
    public void onCartResponseFailure(Throwable throwable) {
//for cart
    }

    @Override
    public void onCartResponseSuccess(CartDataResponce response) {
//for cart
    }

    @Override
    public void onCartUpdateResponseFailure(String throwable) {
//for cart
    }

    @Override
    public void onCartUpdateResponseSuccess(QtyAddResponce response) {
//for cart
    }

    @Override
    public void onCartAddResponseFailure(String throwable) {
//for cart
    }

    @Override
    public void onCartAddResponseSuccess(QtyAddResponce response) {
//for cart
    }

    @Override
    public void onCouponListFailure(String throwable) {
        displayMessage(throwable);
    }

    @Override
    public void onCouponListSuccess(CouponList response) {
        if (response.getStatus() == 1) {
            if (couponLists.size() > 0) {
                couponLists.clear();
            }
            couponLists.addAll(response.getData());
            couponListAdpter.notifyDataSetChanged();
        }
    }

    @Override
    public void onApplyCouponFailure(String throwable) {
        //apply coupon code
    }

    @Override
    public void onApplyCouponSuccess(CommonResponce response) {
        //apply coupon code
    }

    @Override
    public void onRemoveCouponFailure(String throwable) {
        //remove coupon code
    }

    @Override
    public void onRemoveCouponSuccess(CommonResponce response) {
        //remove coupon code
    }

    @Override
    public void onDeleteCartFailure(String throwable) {
        //delete Cart
    }

    @Override
    public void onDeleteCartSuccess(CommonResponce response) {
//delete Cart
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
