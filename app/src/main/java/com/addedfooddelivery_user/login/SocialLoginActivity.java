package com.addedfooddelivery_user.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.login.loginEmail.LoginEmailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
}
