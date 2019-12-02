package com.addedfooddelivery_user.cart.api;

import android.app.Activity;

import com.addedfooddelivery_user.RestaurantDetails.model.addQTYResponce.QtyAddResponce;
import com.addedfooddelivery_user.cart.model.CartDataResponce;
import com.addedfooddelivery_user.cart.model.CouponList;
import com.addedfooddelivery_user.common.api.BaseView;
import com.addedfooddelivery_user.common.model.CommonResponce;

public interface CartConstructor {

    interface Model {

        void getCartData(OnFinishedListener onFinishedListener, Activity activity);

        void updateCart(OnFinishedListener onFinishedListener, Activity activity, String restaurantID, int itemID, int count);

        void addItemQTY(OnFinishedListener onFinishedListener, Activity activity, String restaurantID, int itemID, String itemPrice, int count);

        void getCouponData(OnFinishedListener onFinishedListener, Activity activity);

        void getApplyCoupon(OnFinishedListener onFinishedListener, Activity activity,String code);

        void removeCouponData(OnFinishedListener onFinishedListener, Activity activity);



        interface OnFinishedListener {
            void onCartFinished(CartDataResponce response);

            void onCartFailure(String response);

            void onCartUpdateFinished(QtyAddResponce response);

            void onCartUpdateFailure(String response);

            void onCartAddFinished(QtyAddResponce response);

            void onCartAddFailure(String response);

            void onCouponListFinished(CouponList response);

            void onCouponListFailure(String response);

            void onApplyCouponFinished(CommonResponce response);

            void onApplyCouponListFailure(String response);

            void onRemoveCouponFinished(CommonResponce response);

            void onRemoveCouponListFailure(String response);

        }
    }

    interface View extends BaseView {

        void onCartResponseFailure(Throwable throwable);

        void onCartResponseSuccess(CartDataResponce response);

        void onCartUpdateResponseFailure(String throwable);

        void onCartUpdateResponseSuccess(QtyAddResponce response);

        void onCartAddResponseFailure(String throwable);

        void onCartAddResponseSuccess(QtyAddResponce response);

        void onCouponListFailure(String throwable);

        void onCouponListSuccess(CouponList response);

        void onApplyCouponFailure(String throwable);

        void onApplyCouponSuccess(CommonResponce response);

        void onRemoveCouponFailure(String throwable);

        void onRemoveCouponSuccess(CommonResponce response);



    }

    interface Presenter {
        void onDestroy();

        void requestCartData(Activity activity);

        void requestUpdateCartQTY(Activity activity, String restaurantID, int itemID, int count);

        void requestAddQTY(Activity activity, String restaurantID, int itemID, String itemPrice, int count);

        void requestCouponData(Activity activity);

        void requestCoupon(Activity activity,String couponCode);

        void requestRemoveCoupon(Activity activity);
    }
}
