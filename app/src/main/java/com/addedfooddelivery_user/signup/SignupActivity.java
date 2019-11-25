package com.addedfooddelivery_user.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.common.CustomeToast;
import com.addedfooddelivery_user.common.IntegratorImpl;
import com.addedfooddelivery_user.common.LoginImaplementView;
import com.addedfooddelivery_user.common.ReusedMethod;
import com.addedfooddelivery_user.common.SharedPreferenceManager;
import com.addedfooddelivery_user.common.views.CustomButton;
import com.addedfooddelivery_user.common.views.CustomEditText;
import com.addedfooddelivery_user.common.views.CustomTextView;
import com.addedfooddelivery_user.signup.api.SignupConstructor;
import com.addedfooddelivery_user.signup.api.SignupPresenter;
import com.addedfooddelivery_user.signup.model.SignupResponse;
import com.addedfooddelivery_user.verificationPhone.VerifyPhoneActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.addedfooddelivery_user.common.AppConstants.ACCESS_TOKEN;
import static com.addedfooddelivery_user.common.AppConstants.LOGGED_IN_USER_ID;

public class SignupActivity extends AppCompatActivity implements SignupConstructor.View {
    @BindView(R.id.img_back_signup)
    ImageView imgBack;
    @BindView(R.id.etUserName)
    CustomEditText edUserName;
    @BindView(R.id.edSignupEmail)
    CustomEditText edSignupEmail;
    @BindView(R.id.edSignupPassword)
    CustomEditText edSignupPassword;
    @BindView(R.id.edConfirmPassword)
    CustomEditText edConfirmPassword;
    @BindView(R.id.btSignup)
    CustomButton btSignup;
    @BindView(R.id.txtSignup)
    CustomTextView txtSignup;
    SignupPresenter signupPresenter;
    Dialog dialog;
    String loginType="manual";
    String socialId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        signupPresenter = new SignupPresenter(SignupActivity.this);
        initProgressBar();
    }

    @OnClick({R.id.btSignup, R.id.img_back_signup})
    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.img_back_signup:
                onBackPressed();
                break;
            case R.id.btSignup:
                IntegratorImpl.SignUpIntegrator(edUserName.getText().toString().trim(),
                        edSignupEmail.getText().toString().trim(),
                        edSignupPassword.getText().toString().trim(),
                        edConfirmPassword.getText().toString().trim(), new LoginImaplementView.SignUpImaplementView() {
                            @Override
                            public void firstName() {
                                ReusedMethod.showSnackBar(SignupActivity.this, getResources().getString(R.string.val_name), 1);
                            }

                            @Override
                            public void email() {
                                ReusedMethod.showSnackBar(SignupActivity.this, getResources().getString(R.string.val_email), 1);
                            }

                            @Override
                            public void emailValidation() {
                                ReusedMethod.showSnackBar(SignupActivity.this, getResources().getString(R.string.val_validate_email), 1);
                            }

                            @Override
                            public void passwordValidation() {
                                ReusedMethod.showSnackBar(SignupActivity.this, getResources().getString(R.string.val_password), 1);
                            }

                            @Override
                            public void passwordMinValidation() {
                                ReusedMethod.showSnackBar(SignupActivity.this, getResources().getString(R.string.val_min_password), 1);
                            }

                            @Override
                            public void confirmPasswordValidation() {
                                ReusedMethod.showSnackBar(SignupActivity.this, getResources().getString(R.string.val_password), 1);
                            }

                            @Override
                            public void passwordCompare() {
                                ReusedMethod.showSnackBar(SignupActivity.this, getResources().getString(R.string.val_conf_password), 1);
                            }

                            @Override
                            public void success() {
                                signupPresenter.requestAPIKey(SignupActivity.this,edUserName.getText().toString().trim(),
                                        edSignupPassword.getText().toString().trim(),
                                        edSignupEmail.getText().toString().trim(),
                                        loginType,
                                        socialId);
                               /* startActivity(new Intent(SignupActivity.this, VerifyPhoneActivity.class));
                                overridePendingTransition(R.anim.leftto, R.anim.right);
                                finish();*/
                            }


                        });

                break;
        }
    }

    @Override
    public void onBackPressed() {
        //startActivity(new Intent(SignupActivity.this, LoginEmailActivity.class));
        overridePendingTransition(R.anim.leftto, R.anim.right);
        finish();
    }


    @Override
    public void onResponseFailure(Throwable throwable) {
        displayMessage(throwable.getMessage());
    }

    @Override
    public void onResponseSuccess(SignupResponse response) {
        if (response.getStatus() == 1) {
            SharedPreferenceManager.putString(ACCESS_TOKEN, response.getData().getUserDetail().getAccessToken());
            SharedPreferenceManager.putInt(LOGGED_IN_USER_ID, response.getData().getUserDetail().getCustomerID());
            startActivity(new Intent(SignupActivity.this, VerifyPhoneActivity.class));
            overridePendingTransition(R.anim.leftto, R.anim.right);
            finish();
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
