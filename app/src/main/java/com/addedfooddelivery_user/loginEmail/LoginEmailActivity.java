package com.addedfooddelivery_user.loginEmail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.common.CustomeToast;
import com.addedfooddelivery_user.common.IntegratorImpl;
import com.addedfooddelivery_user.common.LoginImaplementView;
import com.addedfooddelivery_user.common.ReusedMethod;
import com.addedfooddelivery_user.common.SharedPreferenceManager;
import com.addedfooddelivery_user.common.views.CustomButton;
import com.addedfooddelivery_user.common.views.CustomEditText;
import com.addedfooddelivery_user.common.views.CustomTextView;
import com.addedfooddelivery_user.forgottPassword.ForgotPasswordActivity;
import com.addedfooddelivery_user.home.MainActivity;
import com.addedfooddelivery_user.login.SocialLoginActivity;
import com.addedfooddelivery_user.loginEmail.api.LoginConstructor;
import com.addedfooddelivery_user.loginEmail.api.LoginPresenter;
import com.addedfooddelivery_user.loginEmail.model.LoginResponse;
import com.addedfooddelivery_user.signup.SignupActivity;
import com.addedfooddelivery_user.verificationPhone.VerifyPhoneActivity;
import com.addedfooddelivery_user.verifyPhoneOtp.OtpActivity;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.addedfooddelivery_user.common.AppConstants.ACCESS_TOKEN;
import static com.addedfooddelivery_user.common.AppConstants.IS_LOGIN;
import static com.addedfooddelivery_user.common.AppConstants.LOGGED_IN_USER_ID;
import static com.addedfooddelivery_user.common.AppConstants.USER_DETAIL_LOGIN;

public class LoginEmailActivity extends AppCompatActivity implements LoginConstructor.View {
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

    LoginPresenter loginPresenter;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_email_login);
        ButterKnife.bind(this);
        //first istep
        loginPresenter = new LoginPresenter(LoginEmailActivity.this);
        initProgressBar();
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

                        loginPresenter.requestAPIKey(LoginEmailActivity.this, edEmail.getText().toString().trim(), edPassword.getText().toString().trim());

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
                finish();
                break;
            case R.id.img_back_login:
                onBackPressed();
                break;

        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(LoginEmailActivity.this, SocialLoginActivity.class));
        overridePendingTransition(R.anim.leftto, R.anim.right);
        finish();
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        displayMessage(throwable.getMessage());
    }

    @Override
    public void onResponseSuccess(LoginResponse response) {
        if (response.getStatus() == 1) {
            SharedPreferenceManager.putBoolean(IS_LOGIN, true);
            Gson gson = new Gson();
            String json = gson.toJson(response.getData().getUserDetail());

            SharedPreferenceManager.putString(USER_DETAIL_LOGIN, json);
            SharedPreferenceManager.putString(ACCESS_TOKEN, response.getData().getUserDetail().getAccessToken());
            SharedPreferenceManager.putInt(LOGGED_IN_USER_ID, response.getData().getUserDetail().getCustomerID());

            if (response.getData().getUserDetail().getIsVerify() == 0) {
                if (response.getData().getUserDetail().getPhoneNumber().equalsIgnoreCase("0")) {
                    startActivity(new Intent(LoginEmailActivity.this, VerifyPhoneActivity.class));
                    overridePendingTransition(R.anim.rightto, R.anim.left);
                    finish();
                } else {
                    startActivity(new Intent(LoginEmailActivity.this, OtpActivity.class).putExtra("screen","login"));
                    overridePendingTransition(R.anim.rightto, R.anim.left);
                    finish();
                }
            } else {
                startActivity(new Intent(LoginEmailActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.rightto, R.anim.left);
                finish();
            }
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
