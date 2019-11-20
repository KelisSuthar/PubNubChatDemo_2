package com.addedfooddelivery_user.forgottPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.common.CustomeToast;
import com.addedfooddelivery_user.common.ReusedMethod;
import com.addedfooddelivery_user.common.SmartUtils;
import com.addedfooddelivery_user.common.views.CustomButton;
import com.addedfooddelivery_user.common.views.CustomEditText;
import com.addedfooddelivery_user.forgottPassword.api.ForgotPassConstructor;
import com.addedfooddelivery_user.forgottPassword.api.ForgotPassPresenter;
import com.addedfooddelivery_user.forgottPassword.model.ForgotPassResponse;
import com.addedfooddelivery_user.loginEmail.LoginEmailActivity;
import com.addedfooddelivery_user.loginEmail.api.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPasswordActivity extends AppCompatActivity implements ForgotPassConstructor.View {
    @BindView(R.id.img_back_forgot)
    ImageView imgBackForgot;
    @BindView(R.id.edForgotEmail)
    CustomEditText edForgotEmail;
    @BindView(R.id.btSend)
    CustomButton btSend;
    ForgotPassPresenter forgotPassPresenter;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
        forgotPassPresenter = new ForgotPassPresenter(ForgotPasswordActivity.this);
        initProgressBar();
    }

    @OnClick({R.id.btSend, R.id.img_back_forgot})
    public void clickEvent(View view) {
        switch (view.getId()) {
            case R.id.btSend:
                if (edForgotEmail.getText().toString().trim().equalsIgnoreCase("")) {
                    ReusedMethod.showSnackBar(ForgotPasswordActivity.this, getResources().getString(R.string.val_email), 1);
                } else if (!SmartUtils.emailValidator(edForgotEmail.getText().toString().trim())) {
                    ReusedMethod.showSnackBar(ForgotPasswordActivity.this, getResources().getString(R.string.val_validate_email), 1);
                } else {
                    forgotPassPresenter.requestAPIKey(ForgotPasswordActivity.this, edForgotEmail.getText().toString().trim());
                }
                break;
            case R.id.img_back_forgot:
                onBackPressed();
                break;
        }
    }

    private void PasswordResetDialog() {

            try {
                AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPasswordActivity.this, R.style.MyDialogTheme_1);

                final FrameLayout frameView = new FrameLayout(ForgotPasswordActivity.this);
                //frameView.setBackground(activity.getResources().getDrawable(R.drawable.dialog_bg));
                builder.setView(frameView);

                AlertDialog alertDialog = builder.create();
                LayoutInflater inflater = alertDialog.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custome_popup_forgot, frameView);

                alertDialog.show();
                alertDialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.dialog_bg));


                CustomButton customButton = dialogView.findViewById(R.id.btdone);
                customButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });


            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.leftto, R.anim.right);
        finish();
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        displayMessage(throwable.getMessage());
    }

    @Override
    public void onResponseSuccess(ForgotPassResponse response) {
        if (response.getStatus() == 1) {
            PasswordResetDialog();
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
