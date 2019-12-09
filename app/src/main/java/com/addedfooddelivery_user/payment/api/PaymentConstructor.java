package com.addedfooddelivery_user.payment.api;

import android.app.Activity;

import com.addedfooddelivery_user.common.api.BaseView;
import com.addedfooddelivery_user.common.model.CommonResponce;

public interface PaymentConstructor {

    interface Model {

        void addCard(OnFinishedListener onFinishedListener, Activity activity);


        interface OnFinishedListener {
            void onAddCardFinished(CommonResponce response);

            void onAddCardFailure(String response);

        }
    }

    interface View extends BaseView {

        void onAddCardFailure(Throwable throwable);

        void onAddCardSuccess(CommonResponce response);

    }

    interface Presenter {
        void onDestroy();

        void requestAddCard(Activity activity);


    }
}
