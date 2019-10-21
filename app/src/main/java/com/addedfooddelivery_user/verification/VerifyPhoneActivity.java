package com.addedfooddelivery_user.verification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user._CountryPicker.Country;
import com.addedfooddelivery_user._CountryPicker.CountryPicker;
import com.addedfooddelivery_user._common.ReusedMethod;
import com.addedfooddelivery_user._common.views.CustomButton;
import com.addedfooddelivery_user._common.views.CustomEditText;
import com.addedfooddelivery_user._common.views.CustomTextView;
import com.addedfooddelivery_user.login.loginEmail.LoginEmailActivity;
import com.addedfooddelivery_user.signup.SignupActivity;
import com.addedfooddelivery_user.verification.verifyOtp.OtpActivity;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VerifyPhoneActivity extends AppCompatActivity {
    @BindView(R.id.ll_country)
    LinearLayout ll_country;
    @BindView(R.id.img_back_verify)
    ImageView imgBackVerify;
    @BindView(R.id.txtContryCode)
    CustomTextView txtContryCode;
    @BindView(R.id.imfFlag)
    ImageView imgFlag;
    @BindView(R.id.edPhone)
    CustomEditText edPhone;
    @BindView(R.id.btNext)
    CustomButton btNext;
    private CountryPicker mCountryPicker;
    String country_code = "+91";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_verify_phone);
        ButterKnife.bind(this);
        mCountryPicker = CountryPicker.newInstance("Select Country");
        setupCountry();
    }

    @OnClick({R.id.ll_country, R.id.btNext, R.id.img_back_verify})
    public void eventClicK(View view) {
        switch (view.getId()) {
            case R.id.ll_country:
                mCountryPicker.show(VerifyPhoneActivity.this.getSupportFragmentManager(), "COUNTRY_PICKER");
                break;
            case R.id.btNext:
                if(edPhone.getText().toString().trim().equalsIgnoreCase("")){
                    ReusedMethod.showSnackBar(VerifyPhoneActivity.this, getResources().getString(R.string.val_validate_phone), 1);
                }else {
                    startActivity(new Intent(VerifyPhoneActivity.this, OtpActivity.class));
                    overridePendingTransition(R.anim.rightto, R.anim.left);
                    finish();
                }
                break;
            case R.id.img_back_verify:
                onBackPressed();
                break;
        }
    }

    private void setupCountry() {

        // You can limit the displayed countries
        List<Country> countryList = Country.getAllCountries();
        Collections.sort(countryList, new Comparator<Country>() {
            @Override
            public int compare(Country s1, Country s2) {
                return s1.getName().compareToIgnoreCase(s2.getName());
            }
        });
        mCountryPicker.setCountriesList(countryList);
        setListener();
    }

    private void setListener() {
        mCountryPicker.setListener((name, code, dialCode, flagDrawableResID) -> {
            txtContryCode.setText(dialCode);
            country_code = dialCode;
            imgFlag.setImageResource(flagDrawableResID);
            //txtContryCode.setCompoundDrawablesWithIntrinsicBounds(flagDrawableResID, 0, 0, 0);

            mCountryPicker.dismiss();
        });


        getUserCountryInfo();
    }

    private void getUserCountryInfo() {
        Locale current = getResources().getConfiguration().locale;
        Country country = Country.getCountryFromSIM(VerifyPhoneActivity.this);
        if (txtContryCode != null) {
            if (country != null) {
                txtContryCode.setText(country.getDialCode());
                //txtContryCode.setCompoundDrawablesWithIntrinsicBounds(country.getFlag(), 0, 0, 0);
                imgFlag.setImageResource(country.getFlag());
                country_code = country.getDialCode();
            } else {
                Country us = new Country("US", "United States", "+1", R.drawable.flag_us);
                txtContryCode.setText(us.getDialCode());
               // txtContryCode.setCompoundDrawablesWithIntrinsicBounds(us.getFlag(), 0, 0, 0);
                imgFlag.setImageResource(us.getFlag());
                country_code = us.getDialCode();
            }
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(VerifyPhoneActivity.this, LoginEmailActivity.class));
        overridePendingTransition(R.anim.leftto, R.anim.right);
        finish();
    }
}
