package com.addedfooddelivery_user.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.apiKey.GetAPIKeyConstructor;
import com.addedfooddelivery_user.apiKey.GetAPIKeyPresenter;
import com.addedfooddelivery_user.apiKey.model.GetAPIKeyResponse;
import com.addedfooddelivery_user.common.ReusedMethod;
import com.addedfooddelivery_user.common.SharedPreferenceManager;
import com.addedfooddelivery_user.home.MainActivity;
import com.addedfooddelivery_user.login.SocialLoginActivity;

import butterknife.ButterKnife;

import static com.addedfooddelivery_user.common.AppConstants.API_KEY_VALUE;
import static com.addedfooddelivery_user.common.AppConstants.IS_LOGIN;

public class SplashActivity extends AppCompatActivity implements GetAPIKeyConstructor.View {
    GetAPIKeyPresenter apiKeyPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);
        initPresenter();
        //redirectToIntro();

    }

    void initPresenter() {
        String apiKey = SharedPreferenceManager.getString(API_KEY_VALUE, "");
        apiKeyPresenter = new GetAPIKeyPresenter(this);
        if (apiKey.equals("")) {
            if (ReusedMethod.isNetworkConnected(SplashActivity.this)) {
                apiKeyPresenter.requestAPIKey(SplashActivity.this);
            } else {
                displayMessage(getResources().getString(R.string.internet_message_alert));
            }
        } else {
            redirectToIntro();
        }
    }

    void redirectToIntro() {
        Runnable r = () -> {
            boolean isLogin = SharedPreferenceManager.getBoolean(IS_LOGIN, false);
            if (isLogin) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.rightto, R.anim.left);
                finish();
            } else {
                Intent i = new Intent(getApplicationContext(), SocialLoginActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.rightto, R.anim.left);
                finish();
            }


        };
        Handler h = new Handler();
        h.postDelayed(r, 1500); // will be delayed for ic_placeholder.5 seconds
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        displayMessage(throwable.getMessage());
    }

    @Override
    public void onResponseSuccess(GetAPIKeyResponse response) {
        if (response.getStatus() == 1) {
            SharedPreferenceManager.putString(API_KEY_VALUE, response.getData().getApiKey());
            //displayMessage(response.getData().getApiKey().toString());
            redirectToIntro();
        }
    }

    @Override
    public void showLoadingIndicator(boolean isShow) {

    }

    @Override
    public void displayMessage(String message) {
        /*CustomeToast.showToast(
                this,
                message,
                true,
                getResources().getColor(R.color.white),
                getResources().getColor(R.color.green),
                true);*/
    }

    @Override
    public void initProgressBar() {

    }

    @Override
    public Activity getContext() {
        return null;
    }
}
