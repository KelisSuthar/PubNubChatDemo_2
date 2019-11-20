package com.addedfooddelivery_user.signup.api;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.addedfooddelivery_user.common.api.ApiClient;
import com.addedfooddelivery_user.common.api.ApiInterface;
import com.addedfooddelivery_user.signup.model.SignupResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupApiModel implements SignupConstructor.Model {
    private final String TAG = "ForgotPassModel";

    @Override
    public void getSignupData(OnFinishedListener onFinishedListener, Activity activity, String username,String password,String email,String logintype,String socialid) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<SignupResponse> call = apiService.submitSignup(username,password,email,logintype,socialid);
        call.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(@NonNull Call<SignupResponse> call, @NonNull Response<SignupResponse> response) {
                int success;

                if (response.body() != null) {
                    success = response.body().getStatus();
                    if (success == 1) {
                        SignupResponse keyResponse = response.body();
                        onFinishedListener.onFinished(keyResponse);
                    } else {
                        onFinishedListener.onFailure(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<SignupResponse> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t.getMessage());
            }
        });
    }
}
