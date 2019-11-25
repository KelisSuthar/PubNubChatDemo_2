package com.addedfooddelivery_user.home.api;

import android.app.Activity;

import com.addedfooddelivery_user.common.api.BaseView;
import com.addedfooddelivery_user.home.model.DefaultAddResponse;
import com.addedfooddelivery_user.home.model.HomeRestaurantResponse;
import com.addedfooddelivery_user.home_deliverylist.model.ListAddResponse;

public interface HomeConstructor {

    interface Model {

        void getHomeData(OnFinishedListener onFinishedListener, Activity activity, String restaurantCity);

        void getDefaultAddress(OnFinishedListener onFinishedListener, Activity activity);

        interface OnFinishedListener {
            void onHomeFinished(HomeRestaurantResponse response);

            void onHomeFailure(String response);

            void onDefaultAddFinished(DefaultAddResponse response);

            void onDefaultAddFailure(String response);
        }
    }

    interface View extends BaseView {

        void onHomeResponseFailure(Throwable throwable);

        void onHomeResponseSuccess(HomeRestaurantResponse response);

        void onDefaultAddResponseFailure(Throwable throwable);

        void onDefaultAddResponseSuccess(DefaultAddResponse response);

    }

    interface Presenter {
        void onDestroy();

        void requestAPIRestaurant(Activity activity, String restaurantCity);

    }
}
