package com.addedfooddelivery_user.help;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user._common.views.CustomButton;
import com.addedfooddelivery_user._common.views.CustomEditText;
import com.addedfooddelivery_user.chat.ChatActivity;
import com.addedfooddelivery_user.login.SocialLoginActivity;
import com.addedfooddelivery_user.login.loginEmail.LoginEmailActivity;
import com.bumptech.glide.Glide;
import com.fxn.pix.Options;
import com.fxn.pix.Pix;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.addedfooddelivery_user._common.AppConstants.REQUEST_ENABLE_CAMERA;

public class HelpActivity extends AppCompatActivity {
    @BindView(R.id.imgBackProfile)
    ImageView imgBackProfile;
    @BindView(R.id.imgComment)
    ImageView imgComment;
    @BindView(R.id.edProComment)
    CustomEditText edProComment;
    @BindView(R.id.img_1)
    MaterialCardView img1;
    @BindView(R.id.imgAdd1)
    ImageView imgAdd1;
    @BindView(R.id.imgScreenShot1)
    ImageView imgScreenShot1;
    @BindView(R.id.img_2)
    MaterialCardView img2;
    @BindView(R.id.imgAdd2)
    ImageView imgAdd2;
    @BindView(R.id.imgScreenShot2)
    ImageView imgScreenShot2;
    @BindView(R.id.img_3)
    MaterialCardView img3;
    @BindView(R.id.imgAdd3)
    ImageView imgAdd3;
    @BindView(R.id.imgScreenShot3)
    ImageView imgScreenShot3;
    @BindView(R.id.btSend)
    CustomButton btSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_1, R.id.img_2, R.id.img_3,R.id.imgBackProfile,R.id.imgComment})
    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.img_1:
                if (getPermission()) {
                    Pix.start(HelpActivity.this, Options.init().setRequestCode(101).setCount(1));
                } else {
                    ActivityCompat.requestPermissions(HelpActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_ENABLE_CAMERA);
                }
                break;
            case R.id.img_2:
                if (getPermission()) {
                    Pix.start(HelpActivity.this, Options.init().setRequestCode(102).setCount(1));
                } else {
                    ActivityCompat.requestPermissions(HelpActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_ENABLE_CAMERA);
                }
                break;
            case R.id.img_3:
                if (getPermission()) {
                    Pix.start(HelpActivity.this, Options.init().setRequestCode(103).setCount(1));
                } else {
                    ActivityCompat.requestPermissions(HelpActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_ENABLE_CAMERA);
                }
                break;
            case R.id.imgComment:
                startActivity(new Intent(HelpActivity.this, ChatActivity.class));
                overridePendingTransition(R.anim.rightto, R.anim.left);

                break;
            case R.id.imgBackProfile:
                onBackPressed();
                break;
        }
    }
    private boolean getPermission() {
        if (ContextCompat.checkSelfPermission(HelpActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            return false;
        } else
            return true;

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 101) {
            ArrayList<String> returnValue = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
            Glide.with(HelpActivity.this)
                    .load(returnValue.get(0).toString())
                    .into(imgScreenShot1);
            imgAdd1.setVisibility(View.GONE);
        }
        else  if (resultCode == Activity.RESULT_OK && requestCode == 102) {
            ArrayList<String> returnValue = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
            Glide.with(HelpActivity.this)
                    .load(returnValue.get(0).toString())
                    .into(imgScreenShot2);
            imgAdd2.setVisibility(View.GONE);
        }
        else  if (resultCode == Activity.RESULT_OK && requestCode == 103) {
            ArrayList<String> returnValue = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
            Glide.with(HelpActivity.this)
                    .load(returnValue.get(0).toString())
                    .into(imgScreenShot3);
            imgAdd3.setVisibility(View.GONE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_ENABLE_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Pix.start(HelpActivity.this, Options.init().setRequestCode(100).setCount(1));
                } else {
                    Toast.makeText(HelpActivity.this, "Approve permissions to set profile image", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.leftto, R.anim.right);
        finish();
    }
}
