package com.addedfooddelivery_user.verificationPhone;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.addedfooddelivery_user.R;
import com.addedfooddelivery_user._CountryPicker.Country;
import com.addedfooddelivery_user._CountryPicker.CountryPicker;
import com.addedfooddelivery_user.common.CustomeToast;
import com.addedfooddelivery_user.common.ReusedMethod;
import com.addedfooddelivery_user.common.views.CustomButton;
import com.addedfooddelivery_user.common.views.CustomEditText;
import com.addedfooddelivery_user.common.views.CustomTextView;
import com.addedfooddelivery_user.loginEmail.LoginEmailActivity;
import com.addedfooddelivery_user.verificationPhone.api.VerifyPhoneConstructor;
import com.addedfooddelivery_user.verificationPhone.api.VerifyPhonePresenter;
import com.addedfooddelivery_user.verificationPhone.model.PhoneVerifyResponse;
import com.addedfooddelivery_user.verifyPhoneOtp.OtpActivity;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VerifyPhoneActivity extends AppCompatActivity implements VerifyPhoneConstructor.View {
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
    String country_code = "";
    Dialog dialog;
    VerifyPhonePresenter verifyPhonePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_verify_phone);
        ButterKnife.bind(this);
        mCountryPicker = CountryPicker.newInstance("Select Country");
        setupCountry();

        initProgressBar();
        verifyPhonePresenter = new VerifyPhonePresenter(this);
    }

    @OnClick({R.id.ll_country, R.id.btNext, R.id.img_back_verify})
    public void eventClicK(View view) {
        switch (view.getId()) {
            case R.id.ll_country:
                mCountryPicker.show(VerifyPhoneActivity.this.getSupportFragmentManager(), "COUNTRY_PICKER");
                break;
            case R.id.btNext:
                if (edPhone.getText().toString().trim().equalsIgnoreCase("")) {
                    ReusedMethod.showSnackBar(VerifyPhoneActivity.this, getResources().getString(R.string.val_validate_phone), 1);
                } else if (txtContryCode.getText().toString().trim().equalsIgnoreCase("")) {
                    ReusedMethod.showSnackBar(VerifyPhoneActivity.this, getResources().getString(R.string.val_country_selction), 1);
                } else {
                    verifyPhonePresenter.requestAPIKey(VerifyPhoneActivity.this, txtContryCode.getText().toString().trim(), edPhone.getText().toString().trim());

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
            txtContryCode.setText(dialCode.trim());
            country_code = dialCode;
            // imgFlag.setImageResource(flagDrawableResID);
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
                txtContryCode.setText(country.getDialCode().trim());
                //txtContryCode.setCompoundDrawablesWithIntrinsicBounds(country.getFlag(), 0, 0, 0);
                // imgFlag.setImageResource(country.getFlag());
                country_code = country.getDialCode();
            } else {
                Country us = new Country("US", "United States", "+1");
                txtContryCode.setText(us.getDialCode().trim());
                // txtContryCode.setCompoundDrawablesWithIntrinsicBounds(us.getFlag(), 0, 0, 0);
                //imgFlag.setImageResource(us.getFlag());
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

    @Override
    public void onResponseFailure(Throwable throwable) {
        displayMessage(throwable.getMessage());
    }

    @Override
    public void onResponseSuccess(PhoneVerifyResponse response) {
        if (response.getStatus() == 1) {
            startActivity(new Intent(VerifyPhoneActivity.this, OtpActivity.class)
                    .putExtra("otp", response.getData().toString()));

            overridePendingTransition(R.anim.rightto, R.anim.left);
            finish();
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
