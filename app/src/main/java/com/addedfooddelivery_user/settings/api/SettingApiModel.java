package com.addedfooddelivery_user.settings.api;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.addedfooddelivery_user.common.api.ApiClient;
import com.addedfooddelivery_user.common.api.ApiInterface;
import com.addedfooddelivery_user.common.model.CommonResponce;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingApiModel implements SettingConstructor.Model {
    private final String TAG = "SettingModel";


    @Override
    public void logout(OnFinishedListener onFinishedListener, Activity activity) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<CommonResponce> call = apiService.logout();
        call.enqueue(new Callback<CommonResponce>() {
            @Override
            public void onResponse(@NonNull Call<CommonResponce> call, @NonNull Response<CommonResponce> response) {
                int success;

                if (response.body() != null) {
                    success = response.body().getStatus();
                    if (success == 1) {
                        CommonResponce keyResponse = response.body();
                        onFinishedListener.onLogoutFinished(keyResponse);
                    } else {
                        CommonResponce keyResponse = response.body();
                        onFinishedListener.onLogoutFailure(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<CommonResponce> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());

                onFinishedListener.onLogoutFailure(t.getMessage());
            }
        });
    }

    @Override
    public void deleteAccount(OnFinishedListener onFinishedListener, Activity activity) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<CommonResponce> call = apiService.deleteAccount();
        call.enqueue(new Callback<CommonResponce>() {
            @Override
            public void onResponse(@NonNull Call<CommonResponce> call, @NonNull Response<CommonResponce> response) {
                int success;

                if (response.body() != null) {
                    success = response.body().getStatus();
                    if (success == 1) {
                        CommonResponce keyResponse = response.body();
                        onFinishedListener.onDeleteAccFinished(keyResponse);
                    } else {
                        CommonResponce keyResponse = response.body();
                        onFinishedListener.onDeleteAccFailure(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<CommonResponce> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());

                onFinishedListener.onDeleteAccFailure(t.getMessage());
            }
        });
    }


}
