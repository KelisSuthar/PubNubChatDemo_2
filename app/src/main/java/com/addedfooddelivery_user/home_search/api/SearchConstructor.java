package com.addedfooddelivery_user.home_search.api;

import android.app.Activity;

import com.addedfooddelivery_user.common.api.BaseView;
import com.addedfooddelivery_user.home_search.model.CategoryData;
import com.addedfooddelivery_user.home_search.model.CategoryResponse;

public interface SearchConstructor {

    interface Model {

        void getCategoryData(OnFinishedListener onFinishedListener, Activity activity);


        interface OnFinishedListener {
            void onSearchFinished(CategoryResponse response);

            void onSearchFailure(String response);

        }
    }

    interface View extends BaseView {

        void onSearchResponseFailure(Throwable throwable);

        void onSearchResponseSuccess(CategoryResponse response);

    }

    interface Presenter {
        void onDestroy();

        void requestCategoryData(Activity activity);

    }
}
