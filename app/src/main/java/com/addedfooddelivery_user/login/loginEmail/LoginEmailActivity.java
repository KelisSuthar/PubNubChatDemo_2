package com.addedfooddelivery_user.login.loginEmail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.common.IntegratorImpl;
import com.addedfooddelivery_user.common.LoginImaplementView;
import com.addedfooddelivery_user.common.ReusedMethod;
import com.addedfooddelivery_user.common.views.CustomButton;
import com.addedfooddelivery_user.common.views.CustomEditText;
import com.addedfooddelivery_user.common.views.CustomTextView;
import com.addedfooddelivery_user.forgottPassword.ForgotPasswordActivity;
import com.addedfooddelivery_user.home.MainActivity;
import com.addedfooddelivery_user.signup.SignupActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginEmailActivity extends AppCompatActivity {
    @BindView(R.id.ll_email_login)
    CoordinatorLayout llMainView;
    @BindView(R.id.img_login_banner)
    ImageView imgLoginBanner;
    @BindView(R.id.llLogin)
    LinearLayout llLogin;
    @BindView(R.id.edEmail)
    CustomEditText edEmail;
    @BindView(R.id.edPassword)
    CustomEditText edPassword;
    @BindView(R.id.btLogin)
    CustomButton btLogin;
    @BindView(R.id.tvForgotPassword)
    CustomTextView tvForgotPassword;
    @BindView(R.id.txtSignup)
    CustomTextView txtSignup;
    @BindView(R.id.img_back_login)
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_email_login);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btLogin, R.id.tvForgotPassword, R.id.txtSignup, R.id.img_back_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btLogin:
                IntegratorImpl.LoginIntegrator(edEmail.getText().toString().trim(), edPassword.getText().toString().trim(), new LoginImaplementView() {
                    @Override
                    public void email() {
                        ReusedMethod.showSnackBar(LoginEmailActivity.this, getResources().getString(R.string.val_email), 1);
                    }

                    @Override
                    public void emailValidation() {
                        ReusedMethod.showSnackBar(LoginEmailActivity.this, getResources().getString(R.string.val_validate_email), 1);
                    }

                    @Override
                    public void passwordValidation() {
                        ReusedMethod.showSnackBar(LoginEmailActivity.this, getResources().getString(R.string.val_password), 1);
                    }

                    @Override
                    public void passwordMinValidation() {
                        ReusedMethod.showSnackBar(LoginEmailActivity.this, getResources().getString(R.string.val_min_password), 1);
                    }

                    @Override
                    public void success() {
                        startActivity(new Intent(LoginEmailActivity.this, MainActivity.class));
                        overridePendingTransition(R.anim.rightto, R.anim.left);
                        finish();
                    }
                });

                break;
            case R.id.tvForgotPassword:
                startActivity(new Intent(LoginEmailActivity.this, ForgotPasswordActivity.class));
                overridePendingTransition(R.anim.rightto, R.anim.left);
                break;
            case R.id.txtSignup:
                startActivity(new Intent(LoginEmailActivity.this, SignupActivity.class));
                overridePendingTransition(R.anim.rightto, R.anim.left);
                break;
            case R.id.img_back_login:
                finish();
                break;

        }
    }


    @Override
    public void onBackPressed() {
        finish();
    }
}
