package com.addedfooddelivery_user.verification.verifyOtp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.common.views.CustomButton;
import com.addedfooddelivery_user.common.views.CustomTextView;
import com.addedfooddelivery_user.login.loginEmail.LoginEmailActivity;
import com.addedfooddelivery_user.verification.VerifyPhoneActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.aabhasjindal.otptextview.OtpTextView;

public class OtpActivity extends AppCompatActivity {
    @BindView(R.id.otpTextView)
    OtpTextView otpTextView;
    @BindView(R.id.btDone)
    CustomButton btDone;
    @BindView(R.id.txtResendOtp)
    CustomTextView txtResendOtp;
    @BindView(R.id.txtChangeNo)
    CustomTextView txtChangeNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.btDone,R.id.txtResendOtp, R.id.txtChangeNo})
    public void clickEvent(View view) {
        switch (view.getId()) {
            case R.id.btDone:
                startActivity(new Intent(OtpActivity.this, LoginEmailActivity.class));
                overridePendingTransition(R.anim.leftto, R.anim.right);
                finish();
                break;
            case R.id.txtResendOtp:

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
}
