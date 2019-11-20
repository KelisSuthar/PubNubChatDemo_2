package com.addedfooddelivery_user.signup.api;

import android.app.Activity;

import com.addedfooddelivery_user.common.AppConstants;
import com.addedfooddelivery_user.signup.model.SignupResponse;

public class SignupPresenter implements SignupConstructor.Presenter, SignupConstructor.Model.OnFinishedListener, AppConstants {

    String TAG = "SignupPresenter";
    private SignupConstructor.View signupView;
    private SignupConstructor.Model signupModel;

    public SignupPresenter(SignupConstructor.View loginView) {
        this.signupView = loginView;
        this.signupModel = new SignupApiModel();
    }


    @Override
    public void onFinished(SignupResponse response) {

        signupView.onResponseSuccess(response);
        signupView.showLoadingIndicator(false);
    }


    @Override
    public void onFailure(String t) {
        if (signupView != null) {
            signupView.showLoadingIndicator(false);
            signupView.displayMessage(t);
        }

    }

    @Override
    public void onDestroy() {
        signupView = null;
        signupModel = null;
    }

    public void requestAPIKey(Activity loginEmailActivity, String username,String password,String email,String logintype,String socialid) {
            if (signupView != null) {
                signupView.showLoadingIndicator(true);
            }
            signupModel.getSignupData(this, loginEmailActivity,username, password, email, logintype, socialid);

    }
}
