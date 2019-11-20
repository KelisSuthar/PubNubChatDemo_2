package com.addedfooddelivery_user.verificationPhone.api;

import android.app.Activity;

import com.addedfooddelivery_user.common.AppConstants;
import com.addedfooddelivery_user.verificationPhone.model.PhoneVerifyResponse;

public class VerifyPhonePresenter implements VerifyPhoneConstructor.Presenter, VerifyPhoneConstructor.Model.OnFinishedListener, AppConstants {

    String TAG = "SignupPresenter";
    private VerifyPhoneConstructor.View phoneVerifyView;
    private VerifyPhoneConstructor.Model phoneVerifyModel;

    public VerifyPhonePresenter(VerifyPhoneConstructor.View loginView) {
        this.phoneVerifyView = loginView;
        this.phoneVerifyModel = new VerifyPhoneApiModel();
    }


    @Override
    public void onFinished(PhoneVerifyResponse response) {

        phoneVerifyView.onResponseSuccess(response);
        phoneVerifyView.showLoadingIndicator(false);
    }


    @Override
    public void onFailure(String t) {
        if (phoneVerifyView != null) {
            phoneVerifyView.showLoadingIndicator(false);
            phoneVerifyView.displayMessage(t);
        }

    }

    @Override
    public void onDestroy() {
        phoneVerifyView = null;
        phoneVerifyModel = null;
    }

    public void requestAPIKey(Activity loginEmailActivity,String countryCode,String phoneNumber) {
            if (phoneVerifyView != null) {
                phoneVerifyView.showLoadingIndicator(true);
            }
            phoneVerifyModel.getForgotData(this, loginEmailActivity,countryCode,phoneNumber);

    }
}
