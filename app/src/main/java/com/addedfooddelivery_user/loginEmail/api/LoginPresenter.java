package com.addedfooddelivery_user.loginEmail.api;

import android.app.Activity;

import com.addedfooddelivery_user.common.AppConstants;
import com.addedfooddelivery_user.common.LoginImaplementView;
import com.addedfooddelivery_user.loginEmail.model.LoginResponse;

public class LoginPresenter implements LoginConstructor.Presenter, LoginConstructor.Model.OnFinishedListener, AppConstants {

    String TAG = "SignupPresenter";
    private LoginConstructor.View loginView;
    private LoginConstructor.Model loginModel;

    public LoginPresenter(LoginConstructor.View loginView) {
        this.loginView = loginView;
        this.loginModel = new LoginApiModel();
    }


    @Override
    public void onFinished(LoginResponse response) {

        loginView.onResponseSuccess(response);
        loginView.showLoadingIndicator(false);
    }


    @Override
    public void onFailure(String t) {
        if (loginView != null) {
            loginView.showLoadingIndicator(false);
            loginView.displayMessage(t);
        }

    }

    @Override
    public void onDestroy() {
        loginView = null;
        loginModel = null;
    }

    @Override
    public void requestAPIKey(Activity activity) {

    }


    public void requestAPIKey(Activity loginEmailActivity, String email, String password) {
            if (loginView != null) {
                loginView.showLoadingIndicator(true);
            }
            loginModel.getLoginData(this, loginEmailActivity,email,password);

    }
}
