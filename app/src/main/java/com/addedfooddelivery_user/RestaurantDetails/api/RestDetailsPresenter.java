package com.addedfooddelivery_user.RestaurantDetails.api;

import android.app.Activity;

import com.addedfooddelivery_user.RestaurantDetails.model.RestDetailsResponse;
import com.addedfooddelivery_user.RestaurantDetails.model.addQTYResponce.QtyAddResponce;

public class RestDetailsPresenter implements RestDetailsConstructor.Presenter, RestDetailsConstructor.Model.OnFinishedListener {

    String TAG = "restDetailsPresenter";
    private RestDetailsConstructor.View restDetailsView;
    private RestDetailsConstructor.Model restDetailsModel;

    public RestDetailsPresenter(RestDetailsConstructor.View loginView) {
        this.restDetailsView = loginView;
        this.restDetailsModel = new RestDetailsApiModel();
    }

    @Override
    public void onResDetailsFinished(RestDetailsResponse response) {
        restDetailsView.onRestDetailsResponseSuccess(response);
        restDetailsView.showLoadingIndicator(false);
    }

    @Override
    public void onRestDetailsFailure(String t) {
        if (restDetailsView != null) {
            restDetailsView.showLoadingIndicator(false);
            restDetailsView.displayMessage(t);
            restDetailsView.onRestDetailsResponseFailure(t);
        }
    }

    @Override
    public void onUpdateQTYFinished(QtyAddResponce response) {
        restDetailsView.onUpdateQTYSuccess(response);
        restDetailsView.showLoadingIndicator(false);
    }

    @Override
    public void onUpdateQTYFailure(String t) {
        if (restDetailsView != null) {
            restDetailsView.showLoadingIndicator(false);
            restDetailsView.displayMessage(t);
            restDetailsView.onUpdateQTYFailure(t);
        }
    }

    @Override
    public void onAddQTYFinished(QtyAddResponce response) {
        restDetailsView.onAddQTYSuccess(response);
        restDetailsView.showLoadingIndicator(false);
    }

    @Override
    public void onAddQTYFailure(String t) {
        if (restDetailsView != null) {
            restDetailsView.showLoadingIndicator(false);
            restDetailsView.displayMessage(t);
            restDetailsView.onAddQTYFailure(t);
        }
    }

    @Override
    public void onDestroy() {
        restDetailsView = null;
        restDetailsModel = null;
    }

    @Override
    public void requestAPIRestDetails(Activity activity, String restaurantID, String vegType) {
        if (restDetailsView != null) {
            restDetailsView.showLoadingIndicator(true);
        }
        restDetailsModel.getRestaurantDetails(this, activity, restaurantID, vegType);
    }

    @Override
    public void requestUpdateQTY(Activity activity, String restaurantID, int itemID, int count) {
        if (restDetailsView != null) {
            restDetailsView.showLoadingIndicator(true);
        }
        restDetailsModel.updateItemQTY(this, activity, restaurantID, itemID,count);
    }

    public void requestAddQTY(Activity activity, String restaurantID, int itemID, String itemPrice, int count) {
        if (restDetailsView != null) {
            restDetailsView.showLoadingIndicator(true);
        }
        restDetailsModel.addItemQTY(this, activity, restaurantID, itemID,itemPrice,count);
    }
}
