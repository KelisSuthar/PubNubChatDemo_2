package com.addedfooddelivery_user.verifyPhoneOtp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.common.CustomeToast;
import com.addedfooddelivery_user.common.SharedPreferenceManager;
import com.addedfooddelivery_user.common.views.CustomButton;
import com.addedfooddelivery_user.common.views.CustomTextView;
import com.addedfooddelivery_user.home.MainActivity;
import com.addedfooddelivery_user.loginEmail.LoginEmailActivity;
import com.addedfooddelivery_user.verificationPhone.VerifyPhoneActivity;
import com.addedfooddelivery_user.verificationPhone.api.VerifyPhoneConstructor;
import com.addedfooddelivery_user.verificationPhone.api.VerifyPhonePresenter;
import com.addedfooddelivery_user.verificationPhone.model.PhoneVerifyResponse;
import com.addedfooddelivery_user.verifyPhoneOtp.api.VerifyOtpConstructor;
import com.addedfooddelivery_user.verifyPhoneOtp.api.VerifyOtpPresenter;
import com.addedfooddelivery_user.verifyPhoneOtp.model.PhoneOtpResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.aabhasjindal.otptextview.OTPListener;
import in.aabhasjindal.otptextview.OtpTextView;

import static com.addedfooddelivery_user.common.AppConstants.API_KEY_VALUE;
import static com.addedfooddelivery_user.common.AppConstants.IS_LOGIN;
import static com.addedfooddelivery_user.common.AppConstants.LOGGED_IN_USER_ID;

public class OtpActivity extends AppCompatActivity implements VerifyOtpConstructor.View {
    @BindView(R.id.otpTextView)
    OtpTextView otpTextView;
    @BindView(R.id.btDone)
    CustomButton btDone;
    @BindView(R.id.txtResendOtp)
    CustomTextView txtResendOtp;
    @BindView(R.id.txtChangeNo)
    CustomTextView txtChangeNo;
    String otpData = " ";
    Dialog dialog;
    VerifyOtpPresenter verifyOtpPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);
        verifyOtpPresenter = new VerifyOtpPresenter(this);
        initProgressBar();

        otpTextView.setOtpListener(new OTPListener() {
            @Override
            public void onInteractionListener() {
                // fired when user types something in the Otpbox
            }

            @Override
            public void onOTPComplete(String otp) {
                // fired when user has entered the OTP fully.
                // Toast.makeText(MainActivity.this, "The OTP is " + otp, Toast.LENGTH_SHORT).show();
                otpData = otp;
            }
        });
    }

    @OnClick({R.id.btDone, R.id.txtResendOtp, R.id.txtChangeNo})
    public void clickEvent(View view) {
        switch (view.getId()) {
            case R.id.btDone:
                if (otpData.equalsIgnoreCase("")) {
                    verifyOtpPresenter.requestAPIKey(OtpActivity.this, otpData);
                }
                break;
            case R.id.txtResendOtp:
                verifyOtpPresenter.getResetOtp(OtpActivity.this);
                break;
            case R.id.txtChangeNo:
                startActivity(new Intent(OtpActivity.this, VerifyPhoneActivity.class));
                overridePendingTransition(R.anim.leftto, R.anim.right);
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(OtpActivity.this, LoginEmailActivity.class));
        overridePendingTransition(R.anim.leftto, R.anim.right);
        finish();
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        displayMessage(throwable.getMessage());
    }


    @Override
    public void onResponseSuccess(PhoneOtpResponse response) {
        if (response.getStatus() == 1) {
            boolean login = SharedPreferenceManager.getBoolean(IS_LOGIN, false);
            if (login) {
                startActivity(new Intent(OtpActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.rightto, R.anim.left);
                finish();
            } else {
                startActivity(new Intent(OtpActivity.this, LoginEmailActivity.class));
                overridePendingTransition(R.anim.leftto, R.anim.right);
                finish();
            }
        }

    }

    @Override
    public void onOtpResponseFailure(Throwable throwable) {

    }

    @Override
    public void onOtpResponseSuccess(PhoneOtpResponse response) {

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
