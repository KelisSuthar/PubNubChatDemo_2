package com.addedfooddelivery_user.restaurantList.api;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.addedfooddelivery_user.restaurantList.model.AllRestCategoryResponse;
import com.addedfooddelivery_user.restaurantList.model.AllRestaurantResponse;
import com.addedfooddelivery_user.common.api.ApiClient;
import com.addedfooddelivery_user.common.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantApiModel implements RestaurantConstructor.Model {
    private final String TAG = "LoginModel";


    @Override
    public void getAllRestaurant(OnFinishedListener onFinishedListener, Activity activity, String restaurantType, String sort_by, String direction, String category, String price) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<AllRestaurantResponse> call = apiService.getAllRestaurant(restaurantType,sort_by,direction,category,price);
        call.enqueue(new Callback<AllRestaurantResponse>() {
            @Override
            public void onResponse(@NonNull Call<AllRestaurantResponse> call, @NonNull Response<AllRestaurantResponse> response) {
                int success;

                if (response.body() != null) {
                    success = response.body().getStatus();
                    if (success == 1) {
                        AllRestaurantResponse keyResponse = response.body();
                        onFinishedListener.onRestaurantFinished(keyResponse);
                    } else {
                        onFinishedListener.onRestaurantFailure(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<AllRestaurantResponse> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onRestaurantFailure(t.getMessage());
            }
        });
    }

    @Override
    public void getAllRestaurantCategory(OnFinishedListener onFinishedListener, Activity activity, String categoryName, String sort_by, String direction, String price) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<AllRestCategoryResponse> call = apiService.getAllRestaurantCategory(categoryName,sort_by,direction,price);
        call.enqueue(new Callback<AllRestCategoryResponse>() {
            @Override
            public void onResponse(@NonNull Call<AllRestCategoryResponse> call, @NonNull Response<AllRestCategoryResponse> response) {
                int success;

                if (response.body() != null) {
                    success = response.body().getStatus();
                    if (success == 1) {
                        AllRestCategoryResponse keyResponse = response.body();
                        onFinishedListener.onRestCategoryFinished(keyResponse);
                    } else {
                        onFinishedListener.onRestCategoryFailure(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<AllRestCategoryResponse> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onRestCategoryFailure(t.getMessage());
            }
        });
    }
}
