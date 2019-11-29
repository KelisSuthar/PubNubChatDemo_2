package com.addedfooddelivery_user.cart.api;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.addedfooddelivery_user.RestaurantDetails.api.RestDetailsConstructor;
import com.addedfooddelivery_user.RestaurantDetails.model.addQTYResponce.QtyAddResponce;
import com.addedfooddelivery_user.cart.model.CartDataResponce;
import com.addedfooddelivery_user.common.api.ApiClient;
import com.addedfooddelivery_user.common.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartApiModel implements CartConstructor.Model {
    private final String TAG = "CartModel";

    @Override
    public void getCartData(OnFinishedListener onFinishedListener, Activity activity) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<CartDataResponce> call = apiService.getCartDetails();
        call.enqueue(new Callback<CartDataResponce>() {
            @Override
            public void onResponse(@NonNull Call<CartDataResponce> call, @NonNull Response<CartDataResponce> response) {
                int success;

                if (response.body() != null) {
                    success = response.body().getStatus();
                    if (success == 1) {
                        CartDataResponce keyResponse = response.body();
                        onFinishedListener.onCartFinished(keyResponse);
                    } else {
                        onFinishedListener.onCartFailure(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<CartDataResponce> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onCartFailure(t.getMessage());
            }
        });
    }

    @Override
    public void updateCart(OnFinishedListener onFinishedListener, Activity activity, String restaurantID, int itemID, int count) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<QtyAddResponce> call = apiService.updateQty(restaurantID, itemID, count);
        call.enqueue(new Callback<QtyAddResponce>() {
            @Override
            public void onResponse(@NonNull Call<QtyAddResponce> call, @NonNull Response<QtyAddResponce> response) {
                int success;

                if (response.body() != null) {
                    success = response.body().getStatus();
                    if (success == 1) {
                        QtyAddResponce keyResponse = response.body();
                        onFinishedListener.onCartUpdateFinished(keyResponse);
                    } else {
                        onFinishedListener.onCartUpdateFailure(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<QtyAddResponce> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onCartUpdateFailure(t.getMessage());
            }
        });
    }


}
