package com.addedfooddelivery_user.RestaurantList.api;

import android.app.Activity;

import com.addedfooddelivery_user.RestaurantList.model.AllRestaurantData;
import com.addedfooddelivery_user.RestaurantList.model.AllRestaurantResponse;
import com.addedfooddelivery_user.common.api.BaseView;

public interface RestaurantConstructor {

    interface Model {

        void getHomeData(OnFinishedListener onFinishedListener, Activity activity, String restaurantType, String sort_by, String direction, String category, String price);


        interface OnFinishedListener {
            void onRestaurantFinished(AllRestaurantResponse response);

            void onRestaurantFailure(String response);

        }
    }

    interface View extends BaseView {

        void onRestaurantResponseFailure(Throwable throwable);

        void onRestaurantResponseSuccess(AllRestaurantResponse response);

    }

    interface Presenter {
        void onDestroy();

        void requestAPIAllRestaurant(Activity activity, String restaurantType, String sort_by, String direction, String category, String price);

    }
}
