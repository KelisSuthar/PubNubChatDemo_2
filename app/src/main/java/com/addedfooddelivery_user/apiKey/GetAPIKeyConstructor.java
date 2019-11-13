package com.addedfooddelivery_user.apiKey;

import android.app.Activity;

import com.addedfooddelivery_user.apiKey.model.GetAPIKeyResponse;
import com.addedfooddelivery_user.common.api.BaseView;

public interface GetAPIKeyConstructor {

    interface Model {

        void getAPIKeyDetail(OnFinishedListener onFinishedListener, Activity activity);


        interface OnFinishedListener {
            void onFinished(GetAPIKeyResponse response);

            void onFailure(String t);

        }
    }

    interface View extends BaseView {

        void onResponseFailure(Throwable throwable);

        void onResponseSuccess(GetAPIKeyResponse response);

    }

    interface Presenter {
        void onDestroy();

        void requestAPIKey(Activity splashActivity);

    }
}
