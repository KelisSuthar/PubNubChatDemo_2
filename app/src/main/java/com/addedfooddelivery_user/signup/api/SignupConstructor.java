package com.addedfooddelivery_user.signup.api;

import android.app.Activity;

import com.addedfooddelivery_user.common.api.BaseView;
import com.addedfooddelivery_user.signup.model.SignupResponse;

public interface SignupConstructor {

    interface Model {

        void getSignupData(OnFinishedListener onFinishedListener, Activity activity, String username,String password,String email,String logintype,String socialid);


        interface OnFinishedListener {
            void onFinished(SignupResponse response);

            void onFailure(String response);

        }
    }

    interface View extends BaseView {

        void onResponseFailure(Throwable throwable);

        void onResponseSuccess(SignupResponse response);

    }

    interface Presenter {
        void onDestroy();

        void requestAPIKey(Activity activity, String username,String password,String email,String logintype,String socialid);

    }
}
