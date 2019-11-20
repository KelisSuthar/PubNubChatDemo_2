package com.addedfooddelivery_user.home.api;

import android.app.Activity;

import com.addedfooddelivery_user.common.api.BaseView;
import com.addedfooddelivery_user.home.model.HomeRestaurantResponse;

public interface HomeConstructor {

    interface Model {

        void getHomeData(OnFinishedListener onFinishedListener, Activity activity, String restaurantCity);


        interface OnFinishedListener {
            void onHomeFinished(HomeRestaurantResponse response);

            void onHomeFailure(String response);

        }
    }

    interface View extends BaseView {

        void onHomeResponseFailure(Throwable throwable);

        void onHomeResponseSuccess(HomeRestaurantResponse response);

    }

    interface Presenter {
        void onDestroy();

        void requestAPIRestaurant(Activity activity,String restaurantCity);

    }
}
