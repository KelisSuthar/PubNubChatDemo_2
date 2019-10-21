package com.addedfooddelivery_user.login;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.login.loginEmail.LoginEmailActivity;
import com.addedfooddelivery_user.splash.SplashActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.addedfooddelivery_user._common.AppConstants.REQUEST_ENABLE_MULTIPLE;

public class SocialLoginActivity extends AppCompatActivity {
    @BindView(R.id.ll_fb)
    LinearLayout llFb;
    @BindView(R.id.ll_twitter)
    LinearLayout llTwitter;
    @BindView(R.id.ll_email)
    LinearLayout llEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getPermission();
        setContentView(R.layout.activity_social_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ll_fb, R.id.ll_email, R.id.ll_twitter})
    public void clicEvent(View view) {
        switch (view.getId()) {
            case R.id.ll_fb:
                break;
            case R.id.ll_email:
                startActivity(new Intent(SocialLoginActivity.this, LoginEmailActivity.class));
                overridePendingTransition(R.anim.rightto, R.anim.left);
                finish();
                break;
            case R.id.ll_twitter:
                break;
        }
    }
    private void getPermission() {
        ActivityCompat.requestPermissions(SocialLoginActivity.this, new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.CALL_PHONE}, REQUEST_ENABLE_MULTIPLE);
    }
}
