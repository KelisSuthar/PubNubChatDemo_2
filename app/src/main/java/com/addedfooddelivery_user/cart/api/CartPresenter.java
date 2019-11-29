package com.addedfooddelivery_user.cart.api;

import android.app.Activity;

import com.addedfooddelivery_user.RestaurantDetails.model.addQTYResponce.QtyAddResponce;
import com.addedfooddelivery_user.cart.model.CartDataResponce;
import com.addedfooddelivery_user.common.AppConstants;

public class CartPresenter implements CartConstructor.Presenter, CartConstructor.Model.OnFinishedListener, AppConstants {

    String TAG = "SignupPresenter";
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
        cartView.onCartUpdateResponseFailure(response.getMessage());
    }

    @Override
    public void onCartUpdateFailure(String t) {
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
        cartModel.updateCart(this, activity, restaurantID, itemID,count);
    }

}
