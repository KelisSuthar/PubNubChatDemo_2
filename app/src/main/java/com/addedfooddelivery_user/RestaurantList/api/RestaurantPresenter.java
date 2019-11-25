package com.addedfooddelivery_user.RestaurantList.api;

import android.app.Activity;

import com.addedfooddelivery_user.RestaurantList.model.AllRestaurantResponse;

public class RestaurantPresenter implements RestaurantConstructor.Presenter, RestaurantConstructor.Model.OnFinishedListener {

    String TAG = "SignupPresenter";
    private RestaurantConstructor.View restaurantView;
    private RestaurantConstructor.Model restaurantModel;

    public RestaurantPresenter(RestaurantConstructor.View loginView) {
        this.restaurantView = loginView;
        this.restaurantModel = new RestaurantApiModel();
    }


    @Override
    public void onRestaurantFinished(AllRestaurantResponse response) {

        restaurantView.onRestaurantResponseSuccess(response);
        restaurantView.showLoadingIndicator(false);
    }


    @Override
    public void onRestaurantFailure(String t) {
        if (restaurantView != null) {
            restaurantView.showLoadingIndicator(false);
            restaurantView.displayMessage(t);
            restaurantView.onRestaurantResponseFailure(t);
        }

    }

    @Override
    public void onDestroy() {
        restaurantView = null;
        restaurantModel = null;
    }

    @Override
    public void requestAPIAllRestaurant(Activity activity, String restaurantType, String sort_by, String direction, String category, String price) {
        if (restaurantView != null) {
            restaurantView.showLoadingIndicator(true);
        }
        restaurantModel.getAllRestaurant(this, activity, restaurantType, sort_by, direction, category, price);
    }


}
