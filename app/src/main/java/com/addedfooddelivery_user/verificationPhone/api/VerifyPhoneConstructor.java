package com.addedfooddelivery_user.verificationPhone.api;

import android.app.Activity;

import com.addedfooddelivery_user.common.api.BaseView;
import com.addedfooddelivery_user.verificationPhone.model.PhoneVerifyResponse;

public interface VerifyPhoneConstructor {

    interface Model {

        void getForgotData(OnFinishedListener onFinishedListener, Activity activity, String countryCode,String phoneNumber);


        interface OnFinishedListener {
            void onFinished(PhoneVerifyResponse response);

            void onFailure(String response);

        }
    }

    interface View extends BaseView {

        void onResponseFailure(Throwable throwable);

        void onResponseSuccess(PhoneVerifyResponse response);

    }

    interface Presenter {
        void onDestroy();

        void requestAPIKey(Activity activity, String countryCode,String phoneNumber);

    }
}
