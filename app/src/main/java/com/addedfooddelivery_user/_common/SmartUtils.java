package com.addedfooddelivery_user._common;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmartUtils {

    private static final String TAG = "SmartUtil";

    public static boolean emailValidator(final String mailAddress) {
        Pattern pattern;
        Matcher matcher;

        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(mailAddress);
        return matcher.matches();
    }
    public static boolean checkPersonValidation(String personName) {
        Pattern regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]");
        if (regex.matcher(personName).find()) {
            Log.d(TAG, "SPECIAL CHARS FOUND");
            return true;
        } else {
            return false;
        }
    }
}
