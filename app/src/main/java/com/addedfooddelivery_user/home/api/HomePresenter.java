package com.addedfooddelivery_user.home.api;

import android.app.Activity;

import androidx.fragment.app.FragmentActivity;

import com.addedfooddelivery_user.common.AppConstants;
import com.addedfooddelivery_user.home.model.HomeRestaurantResponse;

public class HomePresenter implements HomeConstructor.Presenter, HomeConstructor.Model.OnFinishedListener {

    String TAG = "SignupPresenter";
    private HomeConstructor.View homeView;
    private HomeConstructor.Model homeModel;

    public HomePresenter(HomeConstructor.View loginView) {
        this.homeView = loginView;
        this.homeModel = new HomeApiModel();
    }


    @Override
    public void onHomeFinished(HomeRestaurantResponse response) {

        homeView.onHomeResponseSuccess(response);
        homeView.showLoadingIndicator(false);
    }


    @Override
    public void onHomeFailure(String t) {
        if (homeView != null) {
            homeView.showLoadingIndicator(false);
            homeView.displayMessage(t);
        }

    }

    @Override
    public void onDestroy() {
        homeView = null;
        homeModel = null;
    }



    @Override
    public void requestAPIRestaurant(Activity activity,String restaurantCity ) {
        if (homeView != null) {
            homeView.showLoadingIndicator(true);
        }
        homeModel.getHomeData(this,activity, restaurantCity);
    }


}
