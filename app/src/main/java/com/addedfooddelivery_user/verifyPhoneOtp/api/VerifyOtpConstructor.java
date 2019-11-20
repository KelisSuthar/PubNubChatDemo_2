package com.addedfooddelivery_user.verifyPhoneOtp.api;

import android.app.Activity;

import com.addedfooddelivery_user.common.api.BaseView;
import com.addedfooddelivery_user.verifyPhoneOtp.OtpActivity;
import com.addedfooddelivery_user.verifyPhoneOtp.model.PhoneOtpResponse;

public interface VerifyOtpConstructor {

    interface Model {

        void VerifyOtpData(OnFinishedListener onFinishedListener, Activity activity, String otp);

        void getResetOtp(OnFinishedListener onFinishedListener, Activity otpActivity);


        interface OnFinishedListener {
            void onFinished(PhoneOtpResponse response);

            void onFailure(String response);

            void onOtpFinished(PhoneOtpResponse response);

            void onOtpFailure(String response);
        }
    }

    interface View extends BaseView {

        void onResponseFailure(Throwable throwable);

        void onResponseSuccess(PhoneOtpResponse response);

        void onOtpResponseFailure(Throwable throwable);

        void onOtpResponseSuccess(PhoneOtpResponse response);

    }

    interface Presenter {
        void onDestroy();

        void requestOtpVerify(Activity activity, String otp);

        void  getResetOtp(Activity activity);

    }
}
