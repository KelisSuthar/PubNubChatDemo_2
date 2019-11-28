package com.addedfooddelivery_user.RestaurantDetails.api;

import android.app.Activity;

import com.addedfooddelivery_user.RestaurantDetails.model.RestDetailsResponse;
import com.addedfooddelivery_user.RestaurantDetails.model.addQTYResponce.QtyAddResponce;
import com.addedfooddelivery_user.RestaurantDetails.model.addQTYResponce.QtyResponceData;
import com.addedfooddelivery_user.common.api.BaseView;

public interface RestDetailsConstructor {

    interface Model {

        void getRestaurantDetails(OnFinishedListener onFinishedListener, Activity activity, String restaurantID, String vegType);

        void updateItemQTY(OnFinishedListener onFinishedListener, Activity activity, String restId, int itemID, int count);

        void addItemQTY(OnFinishedListener onFinishedListener, Activity activity, String restaurantID, int itemID, String itemPrice, int count);


        interface OnFinishedListener {
            void onResDetailsFinished(RestDetailsResponse response);

            void onRestDetailsFailure(String response);

            void onUpdateQTYFinished(QtyAddResponce response);

            void onUpdateQTYFailure(String response);

            void onAddQTYFinished(QtyAddResponce response);

            void onAddQTYFailure(String response);


        }
    }

    interface View extends BaseView {

        void onRestDetailsResponseFailure(String throwable);

        void onRestDetailsResponseSuccess(RestDetailsResponse response);

        void onUpdateQTYFailure(String throwable);

        void onUpdateQTYSuccess(QtyAddResponce response);

        void onAddQTYFailure(String throwable);

        void onAddQTYSuccess(QtyAddResponce response);


    }

    interface Presenter {
        void onDestroy();

        void requestAPIRestDetails(Activity activity, String restaurantID, String vegType);

        void requestUpdateQTY(Activity activity, String restaurantID, int itemID, int count);

        void requestAddQTY(Activity activity, String restaurantID, int itemID, String itemPrice,int count);

    }
}
