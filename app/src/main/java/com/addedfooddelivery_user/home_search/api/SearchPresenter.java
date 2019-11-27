package com.addedfooddelivery_user.home_search.api;

import android.app.Activity;

import com.addedfooddelivery_user.common.AppConstants;
import com.addedfooddelivery_user.home_search.model.CategoryData;
import com.addedfooddelivery_user.home_search.model.CategoryResponse;

public class SearchPresenter implements SearchConstructor.Presenter, SearchConstructor.Model.OnFinishedListener, AppConstants {

    String TAG = "SignupPresenter";
    private SearchConstructor.View searchView;
    private SearchConstructor.Model searchModel;

    public SearchPresenter(SearchConstructor.View loginView) {
        this.searchView = loginView;
        this.searchModel = new SearchApiModel();
    }


    @Override
    public void onSearchFinished(CategoryResponse response) {

        searchView.onSearchResponseSuccess(response);
        searchView.showLoadingIndicator(false);
    }


    @Override
    public void onSearchFailure(String t) {
        if (searchView != null) {
            searchView.showLoadingIndicator(false);
            searchView.displayMessage(t);
        }

    }

    @Override
    public void onDestroy() {
        searchView = null;
        searchModel = null;
    }

    @Override
    public void requestCategoryData(Activity activity) {
        if (searchView != null) {
            searchView.showLoadingIndicator(true);
        }
        searchModel.getCategoryData(this, activity);
    }


}
