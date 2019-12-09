package com.addedfooddelivery_user.cart;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
import com.addedfooddelivery_user.cart.model.BillingDetail;
import com.addedfooddelivery_user.cart.model.CartDataResponce;
import com.addedfooddelivery_user.cart.model.CartDetail;
import com.addedfooddelivery_user.cart.model.CouponList;
import com.addedfooddelivery_user.cart.model.MayLike;
import com.addedfooddelivery_user.cart.model.ParentCartData;
import com.addedfooddelivery_user.common.CustomeToast;
import com.addedfooddelivery_user.common.model.CommonResponce;
import com.addedfooddelivery_user.common.views.CustomButton;
import com.addedfooddelivery_user.common.views.CustomTextView;
import com.addedfooddelivery_user.home.MainActivity;
import com.addedfooddelivery_user.home_deliverylist.DeliveryListActivity;
import com.addedfooddelivery_user.home_deliverylist.SaveAddressActivity;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.addedfooddelivery_user.common.AppConstants.AUTOCOMPLETE_REQUEST_CODE;
import static com.addedfooddelivery_user.common.AppConstants.COUPON_ACTIVITY_REQUEST_CODE;
import static com.addedfooddelivery_user.common.GlobalData.SavedAddress;
import static com.addedfooddelivery_user.common.GlobalData.addressType;
import static com.addedfooddelivery_user.common.ReusedMethod.fields;

public class CartActivity extends AppCompatActivity implements CartConstructor.View {
    private static String currency = "$";
    @BindView(R.id.txtEmptyCart)
    CustomTextView txtEmptyCart;
    @BindView(R.id.txtItemTotla)
    CustomTextView txtItemTotla;
    @BindView(R.id.txtRestCharge)
    CustomTextView txtRestCharge;
    @BindView(R.id.txtDiscount)
    CustomTextView txtDiscount;
    @BindView(R.id.txtdeliveryStatus)
    CustomTextView txtdeliveryStatus;
    @BindView(R.id.txtTopay)
    CustomTextView txtTopay;
    @BindView(R.id.rl_discount)
    RelativeLayout rl_discount;
    @BindView(R.id.txtApplyCoupon)
    CustomTextView txtApplyCoupon;
    @BindView(R.id.ll_like)
    LinearLayout llLike;
    @BindView(R.id.img_back_cart)
    ImageView imgBackCart;
    @BindView(R.id.rcyCartProductList)
    RecyclerView rcyProductCart;
    @BindView(R.id.rcyLikeItem)
    RecyclerView rcyItemLike;
    @BindView(R.id.txtAddAddress)
    CustomButton txtAddAddress;
    @BindView(R.id.txtSelectAddress)
    CustomButton txtSelectAddress;
    @BindView(R.id.rl_selected_add)
    RelativeLayout rlSelectedAdd;
    @BindView(R.id.rl_no_add)
    LinearLayout rladdAddress;
    @BindView(R.id.txtAddLabel)
    CustomTextView txtAddLabel;
    @BindView(R.id.txtAddChange)
    CustomTextView txtAddChange;
    @BindView(R.id.txtFooterAdd)
    CustomTextView txtFooterAdd;

    ItemLikeListAdpter itemLikeAdpter;
    CartProductListAdapter myAdapter;
    LinearLayoutManager mLayoutManagerLike;
    Dialog dialog;
    CartPresenter cartPresenter;
    List<ParentCartData> list;
    List<CartDetail> restaurantDetails;
    private ArrayList<MayLike> itemLikeList;
    String cartId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);

        initProgressBar();
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
        setAddressBottom();
    }

    private void setAddressBottom() {
        if (SavedAddress != null) {
            if (SavedAddress.size() != 0) {
                rlSelectedAdd.setVisibility(View.VISIBLE);
                rladdAddress.setVisibility(View.GONE);
                txtFooterAdd.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_checked, 0, 0, 0);
                if (!(addressType.equalsIgnoreCase("")) || !(SavedAddress.get(0).getAddressLine(0).toString().equalsIgnoreCase(""))) {
                    txtFooterAdd.setText(addressType + " (" + SavedAddress.get(0).getAddressLine(0).toString() + ")");
                }
            }
        } else {
            rlSelectedAdd.setVisibility(View.GONE);
            rladdAddress.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.img_back_cart, R.id.txtApplyCoupon, R.id.txtAddChange, R.id.txtEmptyCart})
    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.img_back_cart:
                onBackPressed();
                break;
            case R.id.txtApplyCoupon:
                if (txtApplyCoupon.getText().toString().equalsIgnoreCase(getString(R.string.apply_coupon))) {
                    //apply promo code
                    Intent intent = new Intent(this, CouponListActivity.class);
                    overridePendingTransition(R.anim.rightto, R.anim.left);
                    startActivityForResult(intent, COUPON_ACTIVITY_REQUEST_CODE);
                } else {
                    //remove promo code
                    cartPresenter.requestRemoveCoupon(CartActivity.this);

                }
                break;
            case R.id.txtAddChange:
                startActivity(new Intent(CartActivity.this, DeliveryListActivity.class));
                overridePendingTransition(R.anim.leftto, R.anim.right);
                break;
            case R.id.txtEmptyCart:
                if (!cartId.equalsIgnoreCase("")) {
                    cartPresenter.requestDeletCart(CartActivity.this, cartId);
                }
                break;
        }
    }

    private void setRestaurantData() {
        itemLikeAdpter = new ItemLikeListAdpter(CartActivity.this, itemLikeList, new ItemLikeListAdpter.OnItemClickListener() {
            @Override
            public void onAddItemClick(int position, View view) {
                cartPresenter.requestAddQTY(CartActivity.this,
                        String.valueOf(itemLikeList.get(position).getRestaurantID()),
                        itemLikeList.get(position).getItemID(),
                        itemLikeList.get(position).getItemPrice(),
                        1);
            }
        });

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
            cartId = String.valueOf(restaurantDetails.get(0).getRestaurantItem().get(0).getCartID());
            list = getList(response.getData().getCartDetails());
            itemLikeList.addAll(response.getData().getMayLike());
            setupItemRecycleview();
            if (itemLikeList.size() > 0) {
                // tempery hid functionlity
                llLike.setVisibility(View.GONE);
            } else {
                llLike.setVisibility(View.GONE);
            }

            //set other details
            setCartDetails(response);
            itemLikeAdpter.notifyDataSetChanged();

            if (dialog != null) {
                dialog.hide();
            }
        }
    }

    private void setCartDetails(CartDataResponce response) {
        BillingDetail billingDetail = response.getData().getBillingDetail();
        txtItemTotla.setText(TextUtils.isEmpty(String.valueOf(billingDetail.getTotalAmount())) ? "" : (String.format(Locale.getDefault(), "%s %.2f", currency, Double.valueOf(billingDetail.getTotalAmount()))));

        txtRestCharge.setText(TextUtils.isEmpty(String.valueOf(billingDetail.getTax())) ? "" : (String.format(Locale.getDefault(), "%s %.2f", currency, Double.valueOf(billingDetail.getTax()))));
        if (billingDetail.getDiscount() != null) {
            rl_discount.setVisibility(View.VISIBLE);
            txtDiscount.setText(TextUtils.isEmpty(String.valueOf(billingDetail.getDiscount())) ? "" : (String.format(Locale.getDefault(), "%s %.2f", currency, Double.valueOf(billingDetail.getDiscount()))));
        } else
            rl_discount.setVisibility(View.GONE);
        txtdeliveryStatus.setText(TextUtils.isEmpty(String.valueOf(billingDetail.getDeliveryfee())) ? "" : String.valueOf(billingDetail.getDeliveryfee()));

        txtTopay.setText(TextUtils.isEmpty(String.valueOf(billingDetail.getToPay())) ? "" : (String.format(Locale.getDefault(), "%s %.2f", currency, Double.valueOf(billingDetail.getToPay()))));

    }


    @Override
    public void onCartUpdateResponseFailure(String throwable) {
        // fot update cart quantity
        displayMessage(throwable);
    }

    @Override
    public void onCartUpdateResponseSuccess(QtyAddResponce response) {
        // fot update cart quantity
        if (response.getStatus() == 1) {
            cartPresenter.requestCartData(CartActivity.this);
        }
    }

    @Override
    public void onCartAddResponseFailure(String throwable) {
        // fot may like add
        displayMessage(throwable);
    }

    @Override
    public void onCartAddResponseSuccess(QtyAddResponce response) {
        // fot may like add
        if (response.getStatus() == 1) {
            cartPresenter.requestCartData(CartActivity.this);
        }
    }

    @Override
    public void onCouponListFailure(String throwable) {
        //for coupon list data
    }

    @Override
    public void onCouponListSuccess(CouponList response) {
//for coupon list data
    }

    @Override
    public void onApplyCouponFailure(String throwable) {
        //apply Coupon
        displayMessage(throwable);
    }

    @Override
    public void onApplyCouponSuccess(CommonResponce response) {
        //apply Coupon
        if (response.getStatus() == 1) {
            if (dialog != null) {
                dialog.hide();
            }
            txtApplyCoupon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_discount, 0, R.drawable.tw__composer_close, 0);
            cartPresenter.requestCartData(CartActivity.this);
        }
    }

    @Override
    public void onRemoveCouponFailure(String throwable) {
        displayMessage(throwable);
    }

    @Override
    public void onRemoveCouponSuccess(CommonResponce response) {
        if (response.getStatus() == 1) {
            txtApplyCoupon.setText(getString(R.string.apply_coupon));
            txtApplyCoupon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_discount, 0, 0, 0);
            cartPresenter.requestCartData(CartActivity.this);
        }
    }

    @Override
    public void onDeleteCartFailure(String throwable) {
        displayMessage(throwable);
    }

    @Override
    public void onDeleteCartSuccess(CommonResponce response) {
        if (response.getStatus() == 1) {
            displayMessage(response.getMessage());
            startActivity(new Intent(CartActivity.this, MainActivity.class));
            overridePendingTransition(R.anim.leftto, R.anim.right);
            finish();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == COUPON_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK

                // get String data from Intent
                String couponCode = data.getStringExtra("coupon");
                cartPresenter.requestCoupon(CartActivity.this, couponCode);
                txtApplyCoupon.setText(couponCode);
            }
        }
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                if (place != null) {
                    if (place.getId() != null) {
                        startActivity(new Intent(CartActivity.this, SaveAddressActivity.class)
                                .putExtra("placeId", place.getId())
                                .putExtra("screen", "cart"));
                        overridePendingTransition(R.anim.rightto, R.anim.left);
                    }
                }
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i("AutoComplete", status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.leftto, R.anim.right);
        finish();
    }

}
