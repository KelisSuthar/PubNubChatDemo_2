package com.addedfooddelivery_user.cart.api;

import android.app.Activity;

import com.addedfooddelivery_user.RestaurantDetails.model.addQTYResponce.QtyAddResponce;
import com.addedfooddelivery_user.cart.model.CartDataResponce;
import com.addedfooddelivery_user.common.api.BaseView;
import com.addedfooddelivery_user.forgottPassword.model.ForgotPassResponse;

public interface CartConstructor {

    interface Model {

        void getCartData(OnFinishedListener onFinishedListener, Activity activity);

        void updateCart(OnFinishedListener onFinishedListener, Activity activity, String restaurantID, int itemID, int count);


        interface OnFinishedListener {
            void onCartFinished(CartDataResponce response);

            void onCartFailure(String response);

            void onCartUpdateFinished(QtyAddResponce response);

            void onCartUpdateFailure(String response);

        }
    }

    interface View extends BaseView {

        void onCartResponseFailure(Throwable throwable);

        void onCartResponseSuccess(CartDataResponce response);

        void onCartUpdateResponseFailure(String throwable);

        void onCartUpdateResponseSuccess(QtyAddResponce response);

    }

    interface Presenter {
        void onDestroy();

        void requestCartData(Activity activity);

        void requestUpdateCartQTY(Activity activity, String restaurantID, int itemID, int count);

    }
}
