package com.addedfooddelivery_user.splash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.home.MainActivity;
import com.addedfooddelivery_user.login.SocialLoginActivity;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsResult;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import butterknife.ButterKnife;

import static com.addedfooddelivery_user._common.AppConstants.PERMISSIONS_REQUEST_CODE;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);
        redirectToIntro();
    }





    void redirectToIntro() {
        Runnable r = () -> {

//            boolean isSkipIntro = SharedPreferenceManager.getBoolean(SKIP_INTRO, false);
//            boolean isLogin = SharedPreferenceManager.getBoolean(ISLOGIN, false);
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
//            if (!isSkipIntro) {
//                i = new Intent(getApplicationContext(), IntroScreenActivity.class);
//            } else {
//                if (isLogin) {
//                    boolean isProvider = SharedPreferenceManager.getBoolean(IS_PROVIDER, false);
//
//                    if (isProvider) {
//                        i = new Intent(getApplicationContext(), ProviderHomeActivity.class);
//                    } else {
//                        i = new Intent(getApplicationContext(), HomeActivity.class);
//                    }
//                } else {
//                    i = new Intent(getApplicationContext(), UserSelectionActivity.class);
//                }
//            }
            startActivity(i);
            overridePendingTransition(R.anim.rightto, R.anim.left);
            finish();
        };
        Handler h = new Handler();
        h.postDelayed(r, 1500); // will be delayed for ic_placeholder.5 seconds
    }
}
