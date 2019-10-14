package com.addedfooddelivery_user.forgottPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user._common.ReusedMethod;
import com.addedfooddelivery_user._common.SmartUtils;
import com.addedfooddelivery_user._common.views.CustomButton;
import com.addedfooddelivery_user._common.views.CustomEditText;
import com.addedfooddelivery_user.login.loginEmail.EmailLoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPasswordActivity extends AppCompatActivity {
    @BindView(R.id.img_back_forgot)
    ImageView imgBackForgot;
    @BindView(R.id.edForgotEmail)
    CustomEditText edForgotEmail;
    @BindView(R.id.btSend)
    CustomButton btSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.btSend)
    public  void clickEvent(View view){
        switch (view.getId()){
            case R.id.btSend:
                if(edForgotEmail.getText().toString().trim().equalsIgnoreCase("")){
                    ReusedMethod.showSnackBar(ForgotPasswordActivity.this, getResources().getString(R.string.val_email), 1);
                }
                else if(!SmartUtils.emailValidator(edForgotEmail.getText().toString().trim())){
                    ReusedMethod.showSnackBar(ForgotPasswordActivity.this, getResources().getString(R.string.val_validate_email), 1);
                }else {
                    finish();
                }
                break;
        }
    }
}
