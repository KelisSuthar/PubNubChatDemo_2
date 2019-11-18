package com.addedfooddelivery_user.apiKey;

import android.app.Activity;

import com.addedfooddelivery_user.apiKey.model.GetAPIKeyResponse;
import com.addedfooddelivery_user.common.api.BaseView;

import okhttp3.ResponseBody;
import retrofit2.Response;

public interface GetAPIKeyConstructor {

    interface Model {

        void getAPIKeyDetail(OnFinishedListener onFinishedListener, Activity activity);


        interface OnFinishedListener {
            void onFinished(Response<ResponseBody> response);

            void onFailure(String response);

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
