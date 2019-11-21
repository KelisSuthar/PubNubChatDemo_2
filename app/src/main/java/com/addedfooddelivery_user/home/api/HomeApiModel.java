package com.addedfooddelivery_user.home.api;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.addedfooddelivery_user.common.api.ApiClient;
import com.addedfooddelivery_user.common.api.ApiInterface;
import com.addedfooddelivery_user.home.model.HomeRestaurantResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeApiModel implements HomeConstructor.Model {
    private final String TAG = "LoginModel";

    @Override
    public void getHomeData(OnFinishedListener onFinishedListener, Activity activity, String restaurantCity) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<HomeRestaurantResponse> call = apiService.getHomeRestaurant(restaurantCity);
        call.enqueue(new Callback<HomeRestaurantResponse>() {
            @Override
            public void onResponse(@NonNull Call<HomeRestaurantResponse> call, @NonNull Response<HomeRestaurantResponse> response) {
                int success;

                if (response.body() != null) {
                    success = response.body().getStatus();
                    if (success == 1) {
                        HomeRestaurantResponse keyResponse = response.body();
                        onFinishedListener.onHomeFinished(keyResponse);
                    }if (success == 0) {
                        HomeRestaurantResponse keyResponse = response.body();
                        onFinishedListener.onHomeFinished(keyResponse);
                    } else {
                        onFinishedListener.onHomeFailure(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<HomeRestaurantResponse> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onHomeFailure(t.getMessage());
            }
        });
    }
}
