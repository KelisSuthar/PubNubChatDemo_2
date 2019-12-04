package com.addedfooddelivery_user.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.addedfooddelivery_user.Added.CMSViewActivity;
import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.common.CustomeToast;
import com.addedfooddelivery_user.common.SharedPreferenceManager;
import com.addedfooddelivery_user.common.model.CommonResponce;
import com.addedfooddelivery_user.loginEmail.LoginEmailActivity;
import com.addedfooddelivery_user.settings.api.SettingConstructor;
import com.addedfooddelivery_user.settings.api.SettingPresenter;
import com.addedfooddelivery_user.signup.SignupActivity;
import com.google.android.material.card.MaterialCardView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.addedfooddelivery_user.common.AppConstants.ACCESS_TOKEN;
import static com.addedfooddelivery_user.common.AppConstants.LOGGED_IN_USER_ID;
import static com.addedfooddelivery_user.common.AppConstants.USER_DETAIL_LOGIN;

public class SettingsActivity extends AppCompatActivity implements SettingConstructor.View {
    @BindView(R.id.img_back_settings)
    ImageView imgBackSettings;
    @BindView(R.id.rl_notification)
    RelativeLayout rlNotification;
    @BindView(R.id.rl_changePassword)
    RelativeLayout rlChangePassword;
    @BindView(R.id.profile_item_arrow)
    ImageView profileItemArrow;
    @BindView(R.id.rl_sendFeedback)
    RelativeLayout rlSendFeedback;
    @BindView(R.id.rl_shareApp)
    RelativeLayout rlShareApp;
    @BindView(R.id.rl_PrivacyPolice)
    RelativeLayout rlPrivacyPolice;
    @BindView(R.id.rl_AboutAdded)
    RelativeLayout rlAboutAdded;
    @BindView(R.id.rl_deleteAccount)
    RelativeLayout rlDeleteAccount;
    @BindView(R.id.btnLogout)
    MaterialCardView btnLogout;

    Dialog dialog;
    SettingPresenter settingPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        initProgressBar();
        settingPresenter = new SettingPresenter(this);
    }

    @OnClick({R.id.img_back_settings, R.id.btnLogout, R.id.rl_changePassword, R.id.rl_sendFeedback, R.id.rl_shareApp, R.id.rl_PrivacyPolice, R.id.rl_AboutAdded, R.id.rl_deleteAccount})
    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.img_back_settings:
                onBackPressed();
                break;
            case R.id.btnLogout:
                settingPresenter.requestLogout(SettingsActivity.this);
                break;
            case R.id.rl_changePassword:
                break;
            case R.id.rl_sendFeedback:
                break;
            case R.id.rl_shareApp:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Added Food Delivery");
                startActivity(Intent.createChooser(intent, "choose one"));
                break;
            case R.id.rl_PrivacyPolice:
                startActivity(new Intent(SettingsActivity.this, CMSViewActivity.class)
                        .putExtra(getString(R.string.screen),"setting")
                        .putExtra("url","privacy"));
                overridePendingTransition(R.anim.rightto, R.anim.left);
                break;
            case R.id.rl_AboutAdded:
                startActivity(new Intent(SettingsActivity.this, CMSViewActivity.class)
                        .putExtra(getString(R.string.screen),"setting")
                        .putExtra("url","about"));
                overridePendingTransition(R.anim.rightto, R.anim.left);
                break;
            case R.id.rl_deleteAccount:
                settingPresenter.requestDelete(SettingsActivity.this);
                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.leftto, R.anim.right);
        finish();
    }

    @Override
    public void onLogoutFailure(Throwable throwable) {
        displayMessage(throwable.getMessage());
    }

    @Override
    public void onLogoutSuccess(CommonResponce response) {
        if (response.getStatus() == 1) {
            displayMessage(response.getMessage());
            SharedPreferenceManager.removeAllData();
            Intent intent = new Intent(SettingsActivity.this, LoginEmailActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            overridePendingTransition(R.anim.leftto, R.anim.right);
        }
    }

    @Override
    public void onDeleteAccFailure(Throwable throwable) {
        displayMessage(throwable.getMessage());
    }

    @Override
    public void onDeleteAccSuccess(CommonResponce response) {
        if (response.getStatus() == 1) {
            displayMessage(response.getMessage());
            SharedPreferenceManager.removeAllData();
            Intent intent = new Intent(SettingsActivity.this, LoginEmailActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            overridePendingTransition(R.anim.leftto, R.anim.right);
        }
    }

    @Override
    public void showLoadingIndicator(boolean isShow) {
        if (dialog != null) {
            if (isShow) {
                dialog.show();
            } else {
                dialog.dismiss();
                dialog.cancel();
            }
        }
    }

    @Override
    public void displayMessage(String message) {
        CustomeToast.showToast(
                this,
                message,
                true,
                getResources().getColor(R.color.white),
                getResources().getColor(R.color.colorPrimary),
                true);
    }

    @Override
    public void initProgressBar() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progress_dialog);

        dialog.setCancelable(false);
    }

    @Override
    public Activity getContext() {
        return this;
    }
}
