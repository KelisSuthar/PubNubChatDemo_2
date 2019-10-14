package com.addedfooddelivery_user.login.loginEmail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user._common.IntegratorImpl;
import com.addedfooddelivery_user._common.LoginImaplementView;
import com.addedfooddelivery_user._common.ReusedMethod;
import com.addedfooddelivery_user._common.views.CustomButton;
import com.addedfooddelivery_user._common.views.CustomEditText;
import com.addedfooddelivery_user._common.views.CustomTextView;
import com.addedfooddelivery_user.forgottPassword.ForgotPasswordActivity;
import com.addedfooddelivery_user.signup.SignupActivity;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmailLoginActivity extends AppCompatActivity {
    @BindView(R.id.ll_email_login)
    LinearLayout llMainView;
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
    @BindView(R.id.img_back)
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_email_login);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btLogin, R.id.tvForgotPassword, R.id.txtSignup})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btLogin:
                IntegratorImpl.LoginIntegrator(edEmail.getText().toString().trim(), edPassword.getText().toString().trim(), new LoginImaplementView() {
                    @Override
                    public void email() {
                        ReusedMethod.showSnackBar(EmailLoginActivity.this, getResources().getString(R.string.val_email), 1);
                    }

                    @Override
                    public void emailValidation() {
                        ReusedMethod.showSnackBar(EmailLoginActivity.this, getResources().getString(R.string.val_validate_email), 1);
                    }

                    @Override
                    public void passwordValidation() {
                        ReusedMethod.showSnackBar(EmailLoginActivity.this, getResources().getString(R.string.val_password), 1);
                    }

                    @Override
                    public void passwordMinValidation() {

                    }

                    @Override
                    public void success() {

                    }
                });
                break;
            case R.id.tvForgotPassword:
                startActivity(new Intent(EmailLoginActivity.this, ForgotPasswordActivity.class));
                break;
            case R.id.txtSignup:
                startActivity(new Intent(EmailLoginActivity.this, SignupActivity.class));
                break;

        }
    }

}
