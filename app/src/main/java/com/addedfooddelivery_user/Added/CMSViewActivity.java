package com.addedfooddelivery_user.Added;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user.common.views.CustomTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CMSViewActivity extends AppCompatActivity {
    @BindView(R.id.txtTitleCMS)
    CustomTextView txtTitleCMS;
    @BindView(R.id.imgBackCMS)
    ImageView imgBackCMS;
    @BindView(R.id.webviewCms)
    WebView webviewCms;
    String terms = "http://webapps.iqlance-demo.com/added_food/term";
    String privacy = "http://webapps.iqlance-demo.com/added_food/privacy";
    String about = "http://webapps.iqlance-demo.com/added_food/added";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmsview);
        ButterKnife.bind(this);

        WebSettings webSettings = webviewCms.getSettings();
        webSettings.setJavaScriptEnabled(true);

        if (getIntent() != null) {
            if (getIntent().hasExtra(getString(R.string.screen))) {
                if (getIntent().getStringExtra(getString(R.string.screen)).equalsIgnoreCase("signup")) {
                    txtTitleCMS.setText(getString(R.string.title_terms));
                    webviewCms.loadUrl(terms);
                } else if (getIntent().getStringExtra(getString(R.string.screen)).equalsIgnoreCase("setting")) {
                    if (getIntent().hasExtra(getString(R.string.screen))) {
                        if (getIntent().getStringExtra("url").equalsIgnoreCase("about")) {
                            txtTitleCMS.setText(getString(R.string.title_about));
                            webviewCms.loadUrl(about);
                        }else if(getIntent().getStringExtra("url").equalsIgnoreCase("privacy")){
                            txtTitleCMS.setText(getString(R.string.title_privacy));
                            webviewCms.loadUrl(privacy);
                        }
                    }
                }
            }
        }
    }

    @OnClick(R.id.imgBackCMS)
    public void evenClick(View view) {
        switch (view.getId()) {
            case R.id.imgBackCMS:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //startActivity(new Intent(SignupActivity.this, LoginEmailActivity.class));
        overridePendingTransition(R.anim.leftto, R.anim.right);
        finish();
    }
}
