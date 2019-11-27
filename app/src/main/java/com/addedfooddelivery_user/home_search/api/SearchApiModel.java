package com.addedfooddelivery_user.home_search.api;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.addedfooddelivery_user.common.api.ApiClient;
import com.addedfooddelivery_user.common.api.ApiInterface;
import com.addedfooddelivery_user.home.model.HomeRestaurantResponse;
import com.addedfooddelivery_user.home_search.model.CategoryData;
import com.addedfooddelivery_user.home_search.model.CategoryResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchApiModel implements SearchConstructor.Model {
    private final String TAG = "ForgotPassModel";


    @Override
    public void getCategoryData(OnFinishedListener onFinishedListener, Activity activity) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<CategoryResponse> call = apiService.getCategoryList();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(@NonNull Call<CategoryResponse> call, @NonNull Response<CategoryResponse> response) {
                int success;

                if (response.body() != null) {
                    success = response.body().getStatus();
                    if (success == 1) {
                        CategoryResponse keyResponse = response.body();
                        onFinishedListener.onSearchFinished(keyResponse);
                    }
                   else {
                        CategoryResponse keyResponse = response.body();
                        onFinishedListener.onSearchFailure(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<CategoryResponse> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());

                onFinishedListener.onSearchFailure(t.getMessage());
            }
        });
    }
}
