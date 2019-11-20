package com.addedfooddelivery_user.apiKey;

import android.app.Activity;

import com.addedfooddelivery_user.apiKey.model.Data;
import com.addedfooddelivery_user.apiKey.model.GetAPIKeyResponse;
import com.addedfooddelivery_user.common.AppConstants;
import com.addedfooddelivery_user.common.SharedPreferenceManager;

public class GetAPIKeyPresenter implements GetAPIKeyConstructor.Presenter, GetAPIKeyConstructor.Model.OnFinishedListener, AppConstants {

    String TAG = "GetAPIKeyPresenter";
    private GetAPIKeyConstructor.View movieDetailView;
    private GetAPIKeyConstructor.Model movieDetailsModel;

    public GetAPIKeyPresenter(GetAPIKeyConstructor.View movieDetailView) {
        this.movieDetailView = movieDetailView;
        this.movieDetailsModel = new GetAPIKeyModel();
    }


    @Override
    public void onFinished(GetAPIKeyResponse response) {
       // HomeRestaurantData data = response.getData();
        //SharedPreferenceManager.putString(API_KEY_VALUE, data.getApiKey());
        movieDetailView.onResponseSuccess(response);
        movieDetailView.showLoadingIndicator(false);
    }


    @Override
    public void onFailure(String t) {
        if (movieDetailView != null) {
            movieDetailView.showLoadingIndicator(false);
            movieDetailView.displayMessage(t);

        }

    }

    @Override
    public void onDestroy() {
        movieDetailView = null;
        movieDetailsModel = null;
    }

    @Override
    public void requestAPIKey(Activity activity) {
        if (movieDetailView != null) {
            movieDetailView.showLoadingIndicator(true);
        }
        movieDetailsModel.getAPIKeyDetail(this, activity);
    }

}
