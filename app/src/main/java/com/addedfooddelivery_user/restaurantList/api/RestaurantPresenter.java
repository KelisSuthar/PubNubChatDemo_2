package com.addedfooddelivery_user.restaurantList.api;

import android.app.Activity;

import com.addedfooddelivery_user.restaurantList.RestaurantListActivity;
import com.addedfooddelivery_user.restaurantList.model.AllRestCategoryResponse;
import com.addedfooddelivery_user.restaurantList.model.AllRestaurantResponse;

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
    public void onRestCategoryFinished(AllRestCategoryResponse response) {
        restaurantView.onRestCategorySuccess(response);
        restaurantView.showLoadingIndicator(false);
    }

    @Override
    public void onRestCategoryFailure(String t) {
        if (restaurantView != null) {
            restaurantView.showLoadingIndicator(false);
            restaurantView.displayMessage(t);
            restaurantView.onRestCategoryFailure(t);
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

    @Override
    public void requestAPIAllRestaurantCategory(Activity activity, String categoryName, String sort_by, String direction, String price) {
        if (restaurantView != null) {
            restaurantView.showLoadingIndicator(true);
        }
        restaurantModel.getAllRestaurantCategory(this, activity, categoryName, sort_by, direction, price);
    }



}
