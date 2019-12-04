package com.addedfooddelivery_user.settings.api;

import android.app.Activity;

import com.addedfooddelivery_user.common.model.CommonResponce;
import com.addedfooddelivery_user.settings.SettingsActivity;

public class SettingPresenter implements SettingConstructor.Presenter, SettingConstructor.Model.OnFinishedListener {

    String TAG = "SignupPresenter";
    private SettingConstructor.View settingView;
    private SettingConstructor.Model settingModel;

    public SettingPresenter(SettingConstructor.View settingView) {
        this.settingView = settingView;
        this.settingModel = new SettingApiModel();
    }
    @Override
    public void onLogoutFinished(CommonResponce response) {
        settingView.onLogoutSuccess(response);
        settingView.showLoadingIndicator(false);

    }
    @Override
    public void onLogoutFailure(String t) {
        if (settingView != null) {
            settingView.showLoadingIndicator(false);
            settingView.displayMessage(t);
        }
    }

    @Override
    public void onDeleteAccFinished(CommonResponce response) {
        settingView.onDeleteAccSuccess(response);
        settingView.showLoadingIndicator(false);
    }

    @Override
    public void onDeleteAccFailure(String t) {
        if (settingView != null) {
            settingView.showLoadingIndicator(false);
            settingView.displayMessage(t);
        }
    }

    @Override
    public void onDestroy() {
        settingView = null;
        settingModel = null;
    }

    public void requestLogout(Activity activity) {
        if (settingView != null) {
            settingView.showLoadingIndicator(true);
        }
        settingModel.logout(this,activity);
    }

    @Override
    public void requestDelete(Activity activity) {
        if (settingView != null) {
            settingView.showLoadingIndicator(true);
        }
        settingModel.deleteAccount(this,activity);
    }


}
