package com.addedfooddelivery_user.apiKey;

import android.app.Activity;

import com.addedfooddelivery_user.apiKey.model.Data;
import com.addedfooddelivery_user.apiKey.model.GetAPIKeyResponse;
import com.addedfooddelivery_user.common.AppConstants;
import com.addedfooddelivery_user.common.api.GetApiModel;
import com.google.gson.Gson;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class GetAPIKeyPresenter implements GetAPIKeyConstructor.Presenter, GetAPIKeyConstructor.Model.OnFinishedListener, AppConstants {

    String TAG = "GetAPIKeyPresenter";
    private GetAPIKeyConstructor.View movieDetailView;
    private GetAPIKeyConstructor.Model movieDetailsModel;
    Data data=new Data();


    public GetAPIKeyPresenter(GetAPIKeyConstructor.View movieDetailView) {
        this.movieDetailView = movieDetailView;
        this.movieDetailsModel = new GetApiModel();
    }


    @Override
    public void onFinished(Response<ResponseBody> rawResponse) {


        //SharedPreferenceManager.putString(API_KEY_VALUE, data.getApiKey());
       // movieDetailView.onResponseSuccess(response);
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
