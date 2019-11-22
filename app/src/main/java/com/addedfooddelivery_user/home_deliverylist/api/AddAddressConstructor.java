package com.addedfooddelivery_user.home_deliverylist.api;

import android.app.Activity;

import com.addedfooddelivery_user.common.api.BaseView;
import com.addedfooddelivery_user.home_deliverylist.model.ListAddResponse;
import com.addedfooddelivery_user.home_deliverylist.model.SaveAddResponse;

public interface AddAddressConstructor {

    interface Model {

        void addAddressData(OnFinishedListener onFinishedListener, Activity activity, String addressType, String addressLine, double latitude, double longitude, String locality);

        void listAddress(OnFinishedListener onFinishedListener, Activity activity);

        interface OnFinishedListener {
            void onSaveAddressFinished(SaveAddResponse response);

            void onSaveAddressFailure(String response);

            void onListAddressFinished(ListAddResponse response);

            void onListAddressFailure(String response);

        }
    }

    interface View extends BaseView {

        void onSaveAddressResponseFailure(Throwable throwable);

        void onSaveAddressResponseSuccess(SaveAddResponse response);

        void onListAddressResponseFailure(Throwable throwable);

        void onListAddressResponseSuccess(ListAddResponse response);

    }

    interface Presenter {
        void onDestroy();

        void requestAddAddress(Activity activity, String addressType, String addressLine, double latitude, double longitude, String locality);

        void listAddAddress(Activity activity);

    }
}
