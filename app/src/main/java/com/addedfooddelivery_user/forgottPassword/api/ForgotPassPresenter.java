package com.addedfooddelivery_user.forgottPassword.api;

import android.app.Activity;

import com.addedfooddelivery_user.common.AppConstants;
import com.addedfooddelivery_user.forgottPassword.model.ForgotPassResponse;

public class ForgotPassPresenter implements ForgotPassConstructor.Presenter, ForgotPassConstructor.Model.OnFinishedListener, AppConstants {

    String TAG = "SignupPresenter";
    private ForgotPassConstructor.View forgotView;
    private ForgotPassConstructor.Model forgotModel;

    public ForgotPassPresenter(ForgotPassConstructor.View loginView) {
        this.forgotView = loginView;
        this.forgotModel = new ForgotPassApiModel();
    }


    @Override
    public void onFinished(ForgotPassResponse response) {

        forgotView.onResponseSuccess(response);
        forgotView.showLoadingIndicator(false);
    }


    @Override
    public void onFailure(String t) {
        if (forgotView != null) {
            forgotView.showLoadingIndicator(false);
            forgotView.displayMessage(t);
        }

    }

    @Override
    public void onDestroy() {
        forgotView = null;
        forgotModel = null;
    }

    public void requestAPIKey(Activity loginEmailActivity, String email) {
            if (forgotView != null) {
                forgotView.showLoadingIndicator(true);
            }
            forgotModel.getForgotData(this, loginEmailActivity,email);

    }
}
