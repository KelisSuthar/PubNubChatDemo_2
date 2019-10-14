package com.addedfooddelivery_user.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user._common.IntegratorImpl;
import com.addedfooddelivery_user._common.LoginImaplementView;
import com.addedfooddelivery_user._common.ReusedMethod;
import com.addedfooddelivery_user._common.views.CustomButton;
import com.addedfooddelivery_user._common.views.CustomEditText;
import com.addedfooddelivery_user._common.views.CustomTextView;
import com.addedfooddelivery_user.login.loginEmail.EmailLoginActivity;

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
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btSignup)
    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.img_back_signup:
                finish();
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

                            }

                            @Override
                            public void passwordCompare() {
                                ReusedMethod.showSnackBar(SignupActivity.this, getResources().getString(R.string.val_conf_password), 1);
                            }

                            @Override
                            public void success() {
                                startActivity(new Intent(SignupActivity.this,EmailLoginActivity.class));
                                finish();
                            }


                        });
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
