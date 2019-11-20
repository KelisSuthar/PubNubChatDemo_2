package com.addedfooddelivery_user.loginEmail.api;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.addedfooddelivery_user.common.api.ApiClient;
import com.addedfooddelivery_user.common.api.ApiInterface;
import com.addedfooddelivery_user.loginEmail.model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginApiModel implements LoginConstructor.Model {
    private final String TAG = "LoginModel";

    @Override
    public void getLoginData(OnFinishedListener onFinishedListener, Activity activity, String email, String password) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<LoginResponse> call = apiService.submitLogin(email,password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                int success;

                if (response.body() != null) {
                    success = response.body().getStatus();
                    if (success == 1) {
                        LoginResponse keyResponse = response.body();
                        onFinishedListener.onFinished(keyResponse);
                    } else {
                        onFinishedListener.onFailure(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t.getMessage());
            }
        });
    }
}
