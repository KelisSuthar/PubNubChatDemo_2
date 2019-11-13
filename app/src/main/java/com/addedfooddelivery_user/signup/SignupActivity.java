package com.addedfooddelivery_user.signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.common.IntegratorImpl;
import com.addedfooddelivery_user.common.LoginImaplementView;
import com.addedfooddelivery_user.common.ReusedMethod;
import com.addedfooddelivery_user.common.views.CustomButton;
import com.addedfooddelivery_user.common.views.CustomEditText;
import com.addedfooddelivery_user.common.views.CustomTextView;
import com.addedfooddelivery_user.login.loginEmail.LoginEmailActivity;
import com.addedfooddelivery_user.verification.VerifyPhoneActivity;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupActivity extends AppCompatActivity {
    @BindView(R.id.img_back_signup)
    ImageView imgBack;
    @BindView(R.id.etUserName)
    CustomEditText etUserName;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btSignup,R.id.img_back_signup})
    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.img_back_signup:
               onBackPressed();
                break;
            case R.id.btSignup:
                IntegratorImpl.SignUpIntegrator(etUserName.getText().toString().trim(),
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
                                startActivity(new Intent(SignupActivity.this, VerifyPhoneActivity.class));
                                overridePendingTransition(R.anim.leftto, R.anim.right);
                                finish();
                            }


                        });

                break;
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SignupActivity.this, LoginEmailActivity.class));
        overridePendingTransition(R.anim.leftto, R.anim.right);
        finish();
    }

    static public void showSnackBar(Activity context, String message, int length) {
        View contextView = context.findViewById(android.R.id.content);

        Snackbar snackbar = Snackbar.make(contextView, message, Snackbar.LENGTH_SHORT);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorBlack));
        TextView tv = snackBarView.findViewById(R.id.snackbar_text);
        tv.setTextSize(12);
        tv.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));
        snackbar.show();

    }
}
