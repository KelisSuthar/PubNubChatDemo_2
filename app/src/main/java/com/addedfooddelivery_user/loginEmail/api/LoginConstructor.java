package com.addedfooddelivery_user.loginEmail.api;

import android.app.Activity;

import com.addedfooddelivery_user.common.api.BaseView;
import com.addedfooddelivery_user.loginEmail.model.LoginResponse;

public interface LoginConstructor {

    interface Model {

        void getLoginData(OnFinishedListener onFinishedListener, Activity activity, String email, String password);


        interface OnFinishedListener {
            void onFinished(LoginResponse response);

            void onFailure(String response);

        }
    }

    interface View extends BaseView {

        void onResponseFailure(Throwable throwable);

        void onResponseSuccess(LoginResponse response);

    }

    interface Presenter {
        void onDestroy();

        void requestAPIKey(Activity activity);

    }
}
