package com.addedfooddelivery_user.cart.api;

import android.app.Activity;

import com.addedfooddelivery_user.RestaurantDetails.model.addQTYResponce.QtyAddResponce;
import com.addedfooddelivery_user.cart.model.CartDataResponce;
import com.addedfooddelivery_user.cart.model.CouponList;
import com.addedfooddelivery_user.common.AppConstants;
import com.addedfooddelivery_user.common.model.CommonResponce;

public class CartPresenter implements CartConstructor.Presenter, CartConstructor.Model.OnFinishedListener, AppConstants {

    String TAG = "CartPresenter";
    private CartConstructor.View cartView;
    private CartConstructor.Model cartModel;

    public CartPresenter(CartConstructor.View loginView) {
        this.cartView = loginView;
        this.cartModel = new CartApiModel();
    }


    @Override
    public void onCartFinished(CartDataResponce response) {
        cartView.onCartResponseSuccess(response);
        cartView.showLoadingIndicator(false);
    }

    @Override
    public void onCartFailure(String t) {
        if (cartView != null) {
            cartView.showLoadingIndicator(false);
            cartView.displayMessage(t);
        }

    }

    @Override
    public void onCartUpdateFinished(QtyAddResponce response) {
        cartView.onCartUpdateResponseSuccess(response);
        cartView.showLoadingIndicator(false);

    }

    @Override
    public void onCartUpdateFailure(String t) {
        if (cartView != null) {
            cartView.showLoadingIndicator(false);
            cartView.displayMessage(t);
        }
    }

    @Override
    public void onCartAddFinished(QtyAddResponce response) {
        cartView.onCartAddResponseSuccess(response);
        cartView.showLoadingIndicator(false);

    }

    @Override
    public void onCartAddFailure(String t) {
        if (cartView != null) {
            cartView.showLoadingIndicator(false);
            cartView.displayMessage(t);
        }
    }

    @Override
    public void onCouponListFinished(CouponList response) {
        cartView.onCouponListSuccess(response);
        cartView.showLoadingIndicator(false);
    }

    @Override
    public void onCouponListFailure(String t) {
        if (cartView != null) {
            cartView.showLoadingIndicator(false);
            cartView.displayMessage(t);
        }
    }

    @Override
    public void onApplyCouponFinished(CommonResponce response) {
        cartView.onApplyCouponSuccess(response);
        cartView.showLoadingIndicator(false);
    }

    @Override
    public void onApplyCouponListFailure(String t) {
        if (cartView != null) {
            cartView.showLoadingIndicator(false);
            cartView.displayMessage(t);
        }
    }

    @Override
    public void onRemoveCouponFinished(CommonResponce response) {
        cartView.onRemoveCouponSuccess(response);
        cartView.showLoadingIndicator(false);
    }

    @Override
    public void onRemoveCouponListFailure(String t) {
        if (cartView != null) {
            cartView.showLoadingIndicator(false);
            cartView.displayMessage(t);
        }
    }

    @Override
    public void onDestroy() {
        cartView = null;
        cartModel = null;
    }

    @Override
    public void requestCartData(Activity activity) {
        if (cartView != null) {
            cartView.showLoadingIndicator(true);
        }
        cartModel.getCartData(this, activity);
    }

    @Override
    public void requestUpdateCartQTY(Activity activity, String restaurantID, int itemID, int count) {
        if (cartView != null) {
            cartView.showLoadingIndicator(true);
        }
        cartModel.updateCart(this, activity, restaurantID, itemID, count);
    }

    public void requestAddQTY(Activity activity, String restaurantID, int itemID, String itemPrice, int count) {
        if (cartView != null) {
            cartView.showLoadingIndicator(true);
        }
        cartModel.addItemQTY(this, activity, restaurantID, itemID, itemPrice, count);
    }

    @Override
    public void requestCouponData(Activity activity) {
        if (cartView != null) {
            cartView.showLoadingIndicator(true);
        }
        cartModel.getCouponData(this, activity);
    }

    @Override
    public void requestCoupon(Activity activity, String couponCode) {
        if (cartView != null) {
            cartView.showLoadingIndicator(true);
        }
        cartModel.getApplyCoupon(this, activity, couponCode);
    }

    @Override
    public void requestRemoveCoupon(Activity activity) {
        if (cartView != null) {
            cartView.showLoadingIndicator(true);
        }
        cartModel.removeCouponData(this, activity);
    }
}
