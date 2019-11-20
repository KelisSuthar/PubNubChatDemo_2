package com.addedfooddelivery_user.verificationPhone.api;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.addedfooddelivery_user.common.api.ApiClient;
import com.addedfooddelivery_user.common.api.ApiInterface;
import com.addedfooddelivery_user.verificationPhone.model.PhoneVerifyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyPhoneApiModel implements VerifyPhoneConstructor.Model {
    private final String TAG = "ForgotPassModel";

    @Override
    public void getForgotData(OnFinishedListener onFinishedListener, Activity activity, String countryCode,String PhoneNo) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<PhoneVerifyResponse> call = apiService.submitPhone(countryCode,PhoneNo);
        call.enqueue(new Callback<PhoneVerifyResponse >() {
            @Override
            public void onResponse(@NonNull Call<PhoneVerifyResponse > call, @NonNull Response<PhoneVerifyResponse > response) {
                int success;

                if (response.body() != null) {
                    success = response.body().getStatus();
                    if (success == 1) {
                        PhoneVerifyResponse  keyResponse = response.body();
                        onFinishedListener.onFinished(keyResponse);
                    } else {
                        onFinishedListener.onFailure(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<PhoneVerifyResponse > call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t.getMessage());
            }
        });
    }
}
