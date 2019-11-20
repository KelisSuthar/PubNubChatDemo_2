package com.addedfooddelivery_user.verifyPhoneOtp.api;

import android.app.Activity;

import com.addedfooddelivery_user.common.AppConstants;
import com.addedfooddelivery_user.verifyPhoneOtp.OtpActivity;
import com.addedfooddelivery_user.verifyPhoneOtp.model.PhoneOtpResponse;

public class VerifyOtpPresenter implements VerifyOtpConstructor.Presenter, VerifyOtpConstructor.Model.OnFinishedListener, AppConstants {

    String TAG = "SignupPresenter";
    private VerifyOtpConstructor.View phoneOtpView;
    private VerifyOtpConstructor.Model phoneOtpModel;

    public VerifyOtpPresenter(VerifyOtpConstructor.View loginView) {
        this.phoneOtpView = loginView;
        this.phoneOtpModel = new VerifyOtpApiModel();
    }


    @Override
    public void onFinished(PhoneOtpResponse response) {

        phoneOtpView.onResponseSuccess(response);
        phoneOtpView.showLoadingIndicator(false);
    }


    @Override
    public void onFailure(String t) {
        if (phoneOtpView != null) {
            phoneOtpView.showLoadingIndicator(false);
            phoneOtpView.displayMessage(t);
        }

    }

    @Override
    public void onOtpFinished(PhoneOtpResponse response) {

    }

    @Override
    public void onOtpFailure(String response) {

    }

    @Override
    public void onDestroy() {
        phoneOtpView = null;
        phoneOtpModel = null;
    }

    public void requestAPIKey(Activity otpActivity,String otp) {
            if (phoneOtpView != null) {
                phoneOtpView.showLoadingIndicator(true);
            }
            phoneOtpModel.getForgotData(this, otpActivity,otp);

    }

    @Override
    public void getResetOtp(Activity activity) {
        if (phoneOtpView != null) {
            phoneOtpView.showLoadingIndicator(true);
        }
        phoneOtpModel.getResetOtp(this, activity);
    }


}
