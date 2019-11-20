package com.addedfooddelivery_user.forgottPassword.api;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.addedfooddelivery_user.common.api.ApiClient;
import com.addedfooddelivery_user.common.api.ApiInterface;
import com.addedfooddelivery_user.forgottPassword.model.ForgotPassResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassApiModel implements ForgotPassConstructor.Model {
    private final String TAG = "ForgotPassModel";

    @Override
    public void getForgotData(OnFinishedListener onFinishedListener, Activity activity, String email) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ForgotPassResponse> call = apiService.submitForgotPass(email);
        call.enqueue(new Callback<ForgotPassResponse>() {
            @Override
            public void onResponse(@NonNull Call<ForgotPassResponse> call, @NonNull Response<ForgotPassResponse> response) {
                int success;

                if (response.body() != null) {
                    success = response.body().getStatus();
                    if (success == 1) {
                        ForgotPassResponse keyResponse = response.body();
                        onFinishedListener.onFinished(keyResponse);
                    } else {
                        onFinishedListener.onFailure(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<ForgotPassResponse> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t.getMessage());
            }
        });
    }
}
