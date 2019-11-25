package com.addedfooddelivery_user.RestaurantList.api;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.addedfooddelivery_user.RestaurantList.model.AllRestaurantResponse;
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
}
