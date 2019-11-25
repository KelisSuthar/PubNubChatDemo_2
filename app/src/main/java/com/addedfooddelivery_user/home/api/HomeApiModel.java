package com.addedfooddelivery_user.home.api;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.addedfooddelivery_user.common.api.ApiClient;
import com.addedfooddelivery_user.common.api.ApiInterface;
import com.addedfooddelivery_user.home.model.DefaultAddData;
import com.addedfooddelivery_user.home.model.DefaultAddResponse;
import com.addedfooddelivery_user.home.model.HomeRestaurantResponse;
import com.addedfooddelivery_user.home_deliverylist.model.ListAddResponse;

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
                        HomeRestaurantResponse keyResponse = response.body();
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

    @Override
    public void getDefaultAddress(OnFinishedListener onFinishedListener, Activity activity) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<DefaultAddResponse> call = apiService.getDefaultAdd();
        call.enqueue(new Callback<DefaultAddResponse>() {
            @Override
            public void onResponse(@NonNull Call<DefaultAddResponse> call, @NonNull Response<DefaultAddResponse> response) {
                int success;

                if (response.body() != null) {
                    success = response.body().getStatus();
                    if (success == 1) {
                        DefaultAddResponse keyResponse = response.body();
                        onFinishedListener.onDefaultAddFinished(keyResponse);
                    } else {
                        DefaultAddResponse keyResponse = response.body();
                        onFinishedListener.onDefaultAddFailure(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<DefaultAddResponse> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());

                onFinishedListener.onDefaultAddFailure(t.getMessage());
            }
        });
    }
}
