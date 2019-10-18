package com.addedfooddelivery_user.Added;

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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user._common.views.CustomButton;
import com.addedfooddelivery_user._common.views.CustomEditText;
import com.bumptech.glide.Glide;
import com.fxn.pix.Options;
import com.fxn.pix.Pix;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.addedfooddelivery_user._common.AppConstants.REQUEST_ENABLE_CAMERA;

public class MyProfileActivity extends AppCompatActivity {
    @BindView(R.id.imgBackProfile)
    ImageView imgBackAddress;
    @BindView(R.id.llPhotoEdit)
    LinearLayout llPhotoEdit;
    @BindView(R.id.etProUserName)
    CustomEditText etProUserName;
    @BindView(R.id.edProSignupEmail)
    CustomEditText edProSignupEmail;
    @BindView(R.id.etProPhone)
    CustomEditText etProPhone;
    @BindView(R.id.edProLocation)
    CustomEditText edProLocation;
    @BindView(R.id.edProComment)
    CustomEditText edProComment;
    @BindView(R.id.btSaveChanges)
    CustomButton btSaveChanges;
    @BindView(R.id.imgProfile)
    ImageView imgProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.llPhotoEdit, R.id.btSaveChanges,R.id.imgBackProfile})
    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.llPhotoEdit:
                if (getPermission()) {
                    Pix.start(MyProfileActivity.this, Options.init().setRequestCode(100).setCount(1));
                } else {
                    ActivityCompat.requestPermissions(MyProfileActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_ENABLE_CAMERA);
                }
                break;
            case R.id.btSaveChanges:
                break;
            case R.id.imgBackProfile:
                onBackPressed();
                break;
        }
    }

    private boolean getPermission() {
        if (ContextCompat.checkSelfPermission(MyProfileActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            return false;
        } else
            return true;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            ArrayList<String> returnValue = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
            Glide.with(MyProfileActivity.this)
                    .load(returnValue.get(0).toString())
                    .into(imgProfile);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_ENABLE_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Pix.start(MyProfileActivity.this, Options.init().setRequestCode(100).setCount(1));
                } else {
                    Toast.makeText(MyProfileActivity.this, "Approve permissions to set profile image", Toast.LENGTH_LONG).show();
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
