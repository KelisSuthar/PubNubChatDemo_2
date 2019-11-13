package com.addedfooddelivery_user.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.login.loginEmail.LoginEmailActivity;
import com.google.android.material.card.MaterialCardView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity {
    @BindView(R.id.img_back_settings)
    ImageView imgBackSettings;
    @BindView(R.id.rl_notification)
    RelativeLayout rlNotification;
    @BindView(R.id.rl_changePassword)
    RelativeLayout rlChangePassword;
    @BindView(R.id.profile_item_arrow)
    ImageView profileItemArrow;
    @BindView(R.id.rl_sendFeedback)
    RelativeLayout rlSendFeedback;
    @BindView(R.id.rl_shareApp)
    RelativeLayout rlShareApp;
    @BindView(R.id.rl_PrivacyPolice)
    RelativeLayout rlPrivacyPolice;
    @BindView(R.id.rl_AboutAdded)
    RelativeLayout rlAboutAdded;
    @BindView(R.id.rl_deleteAccount)
    RelativeLayout rlDeleteAccount;
    @BindView(R.id.btnLogout)
    MaterialCardView btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_back_settings, R.id.btnLogout, R.id.rl_changePassword, R.id.rl_sendFeedback, R.id.rl_shareApp, R.id.rl_PrivacyPolice, R.id.rl_AboutAdded, R.id.rl_deleteAccount})
    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.img_back_settings:
                onBackPressed();
                break;
            case R.id.btnLogout:
                Intent intent=new Intent(SettingsActivity.this, LoginEmailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.leftto, R.anim.right);
                break;
            case R.id.rl_changePassword:
                break;
            case R.id.rl_sendFeedback:
                break;
            case R.id.rl_shareApp:
                break;
            case R.id.rl_PrivacyPolice:
                break;
            case R.id.rl_AboutAdded:
                break;
            case R.id.rl_deleteAccount:
                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.leftto, R.anim.right);
        finish();
    }
}
