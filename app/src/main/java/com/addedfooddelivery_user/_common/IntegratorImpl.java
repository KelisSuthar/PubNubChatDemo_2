package com.addedfooddelivery_user._common;

import android.text.TextUtils;

public class IntegratorImpl {

    public static void LoginIntegrator(String email, String password, LoginImaplementView loginImaplementView) {
        if (TextUtils.isEmpty(email)) {
            loginImaplementView.email();
        } else if (!SmartUtils.emailValidator(email)) {
            loginImaplementView.emailValidation();
        } else if (TextUtils.isEmpty(password)) {
            loginImaplementView.passwordValidation();
        } else if (password.length() < 8) {
            loginImaplementView.passwordMinValidation();
        } else {
            loginImaplementView.success();
        }
    }

    public static void SignUpIntegrator(String firstName, String email, String edtPassword, String edtConfirmPassword, LoginImaplementView.SignUpImaplementView signUpImaplementView) {
        if (TextUtils.isEmpty(firstName) || SmartUtils.checkPersonValidation(firstName)) {
            signUpImaplementView.firstName();
        } else if (TextUtils.isEmpty(email)) {
            signUpImaplementView.email();
        } else if (!SmartUtils.emailValidator(email)) {
            signUpImaplementView.emailValidation();
        } else if (TextUtils.isEmpty(edtPassword)) {
            signUpImaplementView.passwordValidation();
        } else if (edtPassword.length() < 8) {
            signUpImaplementView.passwordMinValidation();
        } else if (TextUtils.isEmpty(edtConfirmPassword)) {
            signUpImaplementView.confirmPasswordValidation();
        } else if (!(edtConfirmPassword.equalsIgnoreCase(edtPassword))) {
            signUpImaplementView.passwordCompare();
        } else {
            signUpImaplementView.success();
        }
    }



}
