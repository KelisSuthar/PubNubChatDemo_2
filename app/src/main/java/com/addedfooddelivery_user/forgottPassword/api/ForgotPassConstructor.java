package com.addedfooddelivery_user.forgottPassword.api;

import android.app.Activity;

import com.addedfooddelivery_user.common.api.BaseView;
import com.addedfooddelivery_user.forgottPassword.model.ForgotPassResponse;
import com.addedfooddelivery_user.loginEmail.model.LoginResponse;

public interface ForgotPassConstructor {

    interface Model {

        void getForgotData(OnFinishedListener onFinishedListener, Activity activity, String email);


        interface OnFinishedListener {
            void onFinished(ForgotPassResponse response);

            void onFailure(String response);

        }
    }

    interface View extends BaseView {

        void onResponseFailure(Throwable throwable);

        void onResponseSuccess(ForgotPassResponse response);

    }

    interface Presenter {
        void onDestroy();

        void requestAPIKey(Activity activity, String trim);

    }
}
