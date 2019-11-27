package com.addedfooddelivery_user.home_deliverylist.api;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.addedfooddelivery_user.common.api.ApiClient;
import com.addedfooddelivery_user.common.api.ApiInterface;
import com.addedfooddelivery_user.home.model.HomeRestaurantResponse;
import com.addedfooddelivery_user.home_deliverylist.model.ListAddResponse;
import com.addedfooddelivery_user.home_deliverylist.model.SaveAddResponse;
import com.addedfooddelivery_user.home_deliverylist.model.SetDefaultAddResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAddressApiModel implements AddAddressConstructor.Model {
    private final String TAG = "LoginModel";

    @Override
    public void addAddressData(OnFinishedListener onFinishedListener, Activity activity, String addressType, String addressLine, double latitude, double longitude, String landmark,String locality) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<SaveAddResponse> call = apiService.addAddress(addressType,addressLine,latitude,longitude,landmark,locality);
        call.enqueue(new Callback<SaveAddResponse>() {
            @Override
            public void onResponse(@NonNull Call<SaveAddResponse> call, @NonNull Response<SaveAddResponse> response) {
                int success;

                if (response.body() != null) {
                    success = response.body().getStatus();
                    if (success == 1) {
                        SaveAddResponse keyResponse = response.body();
                        onFinishedListener.onSaveAddressFinished(keyResponse);
                    } else {
                        onFinishedListener.onSaveAddressFailure(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<SaveAddResponse> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onSaveAddressFailure(t.getMessage());
            }
        });
    }

    @Override
    public void listAddress(OnFinishedListener onFinishedListener, Activity activity) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ListAddResponse> call = apiService.getAddressList();
        call.enqueue(new Callback<ListAddResponse>() {
            @Override
            public void onResponse(@NonNull Call<ListAddResponse> call, @NonNull Response<ListAddResponse> response) {
                int success;

                if (response.body() != null) {
                    success = response.body().getStatus();
                    if (success == 1) {
                        ListAddResponse keyResponse = response.body();
                        onFinishedListener.onListAddressFinished(keyResponse);
                    } else {
                        onFinishedListener.onListAddressFailure(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<ListAddResponse> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onListAddressFailure(t.getMessage());
            }
        });
    }

    @Override
    public void setDefaulAdd(OnFinishedListener onFinishedListener, Integer customerAddressID) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<SetDefaultAddResponse> call = apiService.setDefaultAdd(customerAddressID);
        call.enqueue(new Callback<SetDefaultAddResponse>() {
            @Override
            public void onResponse(@NonNull Call<SetDefaultAddResponse> call, @NonNull Response<SetDefaultAddResponse> response) {
                int success;

                if (response.body() != null) {
                    success = response.body().getStatus();
                    if (success == 1) {
                        SetDefaultAddResponse keyResponse = response.body();
                        onFinishedListener.onsetDefaultAddFinished(keyResponse);
                    } else {
                        onFinishedListener.onsetDefaultAddFailure(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<SetDefaultAddResponse> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onsetDefaultAddFailure(t.getMessage());
            }
        });
    }



}
