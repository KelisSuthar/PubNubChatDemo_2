package com.addedfooddelivery_user.RestaurantDetails.api;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.addedfooddelivery_user.RestaurantDetails.model.RestDetailsResponse;
import com.addedfooddelivery_user.RestaurantDetails.model.addQTYResponce.QtyAddResponce;
import com.addedfooddelivery_user.common.api.ApiClient;
import com.addedfooddelivery_user.common.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestDetailsApiModel implements RestDetailsConstructor.Model {
    private final String TAG = "RestDetails";


    @Override
    public void getRestaurantDetails(OnFinishedListener onFinishedListener, Activity activity, String restaurantID, String vegType) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<RestDetailsResponse> call = apiService.getRestaurantDetails(restaurantID,vegType);
        call.enqueue(new Callback<RestDetailsResponse>() {
            @Override
            public void onResponse(@NonNull Call<RestDetailsResponse> call, @NonNull Response<RestDetailsResponse> response) {
                int success;

                if (response.body() != null) {
                    success = response.body().getStatus();
                    if (success == 1) {
                        RestDetailsResponse keyResponse = response.body();
                        onFinishedListener.onResDetailsFinished(keyResponse);
                    } else {
                        onFinishedListener.onRestDetailsFailure(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<RestDetailsResponse> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onRestDetailsFailure(t.getMessage());
            }
        });
    }

    @Override
    public void updateItemQTY(OnFinishedListener onFinishedListener, Activity activity, String restId, int itemID, int count) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<QtyAddResponce> call = apiService.updateQty(restId,itemID,count);
        call.enqueue(new Callback<QtyAddResponce>() {
            @Override
            public void onResponse(@NonNull Call<QtyAddResponce> call, @NonNull Response<QtyAddResponce> response) {
                int success;

                if (response.body() != null) {
                    success = response.body().getStatus();
                    if (success == 1) {
                        QtyAddResponce keyResponse = response.body();
                        onFinishedListener.onUpdateQTYFinished(keyResponse);
                    } else {
                        onFinishedListener.onUpdateQTYFailure(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<QtyAddResponce> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onUpdateQTYFailure(t.getMessage());
            }
        });
    }

    @Override
    public void addItemQTY(OnFinishedListener onFinishedListener, Activity activity, String restaurantID, int itemID, String itemPrice, int count) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<QtyAddResponce> call = apiService.addQTY(restaurantID,itemID,itemPrice,count);
        call.enqueue(new Callback<QtyAddResponce>() {
            @Override
            public void onResponse(@NonNull Call<QtyAddResponce> call, @NonNull Response<QtyAddResponce> response) {
                int success;

                if (response.body() != null) {
                    success = response.body().getStatus();
                    if (success == 1) {
                        QtyAddResponce keyResponse = response.body();
                        onFinishedListener.onAddQTYFinished(keyResponse);
                    } else {
                        onFinishedListener.onAddQTYFailure(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<QtyAddResponce> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onAddQTYFailure(t.getMessage());
            }
        });
    }
}
