package com.addedfooddelivery_user.cart;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.RestaurantDetails.model.addQTYResponce.QtyAddResponce;
import com.addedfooddelivery_user.cart.adpter.CartProductListAdapter;
import com.addedfooddelivery_user.cart.adpter.ItemLikeListAdpter;
import com.addedfooddelivery_user.cart.api.CartConstructor;
import com.addedfooddelivery_user.cart.api.CartPresenter;
import com.addedfooddelivery_user.cart.model.CartDataResponce;
import com.addedfooddelivery_user.cart.model.CartDetail;
import com.addedfooddelivery_user.cart.model.MayLike;
import com.addedfooddelivery_user.cart.model.ParentCartData;
import com.addedfooddelivery_user.common.CustomeToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CartActivity extends AppCompatActivity implements CartConstructor.View {
    @BindView(R.id.ll_like)
    LinearLayout llLike;
    @BindView(R.id.rcyLikeItem)
    RecyclerView rcyLikeItem;

    @BindView(R.id.img_back_cart)
    ImageView imgBackCart;
    @BindView(R.id.rcyCartProductList)
    RecyclerView rcyProductCart;
    @BindView(R.id.rcyLikeItem)
    RecyclerView rcyItemLike;
    ItemLikeListAdpter itemLikeAdpter;
    CartProductListAdapter myAdapter;
    LinearLayoutManager mLayoutManagerLike;
    private ArrayList<MayLike> itemLikeList;
    Dialog dialog;
    CartPresenter cartPresenter;
    List<ParentCartData> list;
    List<CartDetail> restaurantDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);

        cartPresenter = new CartPresenter(CartActivity.this);
        itemLikeList = new ArrayList<>();
        restaurantDetails = new ArrayList<>();
        setRestaurantData();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initProgressBar();
        cartPresenter.requestCartData(CartActivity.this);
    }

    @OnClick(R.id.img_back_cart)
    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.img_back_cart:
                onBackPressed();
                break;
        }
    }

    private void setRestaurantData() {
        itemLikeAdpter = new ItemLikeListAdpter(CartActivity.this, itemLikeList);

        mLayoutManagerLike = new LinearLayoutManager(CartActivity.this, RecyclerView.HORIZONTAL, false);
        rcyItemLike.setLayoutManager(mLayoutManagerLike);

        rcyItemLike.setItemAnimator(new DefaultItemAnimator());
        rcyItemLike.setAdapter(itemLikeAdpter);
    }


    private void setupItemRecycleview() {

        rcyProductCart.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new CartProductListAdapter(CartActivity.this, list, (ArrayList<CartDetail>) restaurantDetails, new CartProductListAdapter.OnItemClickListener() {
            @Override
            public void onUpdateItemClick(int position, View view, String restId, int count, int itemID) {

                cartPresenter.requestUpdateCartQTY(CartActivity.this, restId, itemID, count);
            }
        });
        rcyProductCart.setAdapter(myAdapter);
        rcyProductCart.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rcyProductCart.setAdapter(myAdapter);
        myAdapter.expandAllGroups();

    }

    private List<ParentCartData> getList(List<CartDetail> cartDetails) {
        List<ParentCartData> list_parent = new ArrayList<>();
        for (int i = 0; i < cartDetails.size(); i++) {
            list_parent.add(new ParentCartData(String.valueOf(cartDetails.get(i).getRestaurantID()), cartDetails.get(i).getRestaurantItem()));
        }

        return list_parent;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.leftto, R.anim.right);
        finish();
    }

    @Override
    public void onCartResponseFailure(Throwable throwable) {
        displayMessage(throwable.getMessage());
    }

    @Override
    public void onCartResponseSuccess(CartDataResponce response) {
        if (response.getStatus() == 1) {
            if (restaurantDetails.size() > 0) {
                restaurantDetails.clear();
            }
            if (itemLikeList.size() > 0) {
                itemLikeList.clear();
            }
            restaurantDetails = response.getData().getCartDetails();
            list = getList(response.getData().getCartDetails());
            itemLikeList.addAll(response.getData().getMayLike());
            setupItemRecycleview();
            if (itemLikeList.size() > 0) {
                llLike.setVisibility(View.VISIBLE);
            } else {
                llLike.setVisibility(View.GONE);
            }
            itemLikeAdpter.notifyDataSetChanged();

        }
    }


    @Override
    public void onCartUpdateResponseFailure(String throwable) {
        displayMessage(throwable);
    }

    @Override
    public void onCartUpdateResponseSuccess(QtyAddResponce response) {
        if (response.getStatus() == 1) {

            cartPresenter.requestCartData(CartActivity.this);
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
