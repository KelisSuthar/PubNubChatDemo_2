package com.addedfooddelivery_user.settings.api;

import android.app.Activity;

import com.addedfooddelivery_user.common.api.BaseView;
import com.addedfooddelivery_user.common.model.CommonResponce;

public interface SettingConstructor {

    interface Model {

        void logout(OnFinishedListener onFinishedListener, Activity activity);

        void deleteAccount(OnFinishedListener onFinishedListener, Activity activity);


        interface OnFinishedListener {
            void onLogoutFinished(CommonResponce response);

            void onLogoutFailure(String response);

            void onDeleteAccFinished(CommonResponce response);

            void onDeleteAccFailure(String response);


        }
    }

    interface View extends BaseView {

        void onLogoutFailure(Throwable throwable);

        void onLogoutSuccess(CommonResponce response);

        void onDeleteAccFailure(Throwable throwable);

        void onDeleteAccSuccess(CommonResponce response);



    }

    interface Presenter {
        void onDestroy();

        void requestLogout(Activity activity);

        void requestDelete(Activity activity);

    }
}
