package com.addedfooddelivery_user.restaurantList.api;

import android.app.Activity;

import com.addedfooddelivery_user.restaurantList.model.AllRestCategoryResponse;
import com.addedfooddelivery_user.restaurantList.model.AllRestaurantResponse;
import com.addedfooddelivery_user.common.api.BaseView;

public interface RestaurantConstructor {

    interface Model {

        void getAllRestaurant(OnFinishedListener onFinishedListener, Activity activity, String restaurantType, String sort_by, String direction, String category, String price);

        void getAllRestaurantCategory(OnFinishedListener onFinishedListener, Activity activity, String categoryName, String sort_by, String direction, String price);


        interface OnFinishedListener {
            void onRestaurantFinished(AllRestaurantResponse response);

            void onRestaurantFailure(String response);

            void onRestCategoryFinished(AllRestCategoryResponse response);

            void onRestCategoryFailure(String response);

        }
    }

    interface View extends BaseView {

        void onRestaurantResponseFailure(String throwable);

        void onRestaurantResponseSuccess(AllRestaurantResponse response);

        void onRestCategoryFailure(String throwable);

        void onRestCategorySuccess(AllRestCategoryResponse response);

    }

    interface Presenter {
        void onDestroy();

        void requestAPIAllRestaurant(Activity activity, String restaurantType, String sort_by, String direction, String category, String price);

        void requestAPIAllRestaurantCategory(Activity activity, String categoryName, String sort_by, String direction, String price);

    }
}
