package com.addedfooddelivery_user.verifyPhoneOtp.api;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.addedfooddelivery_user.common.api.ApiClient;
import com.addedfooddelivery_user.common.api.ApiInterface;
import com.addedfooddelivery_user.verifyPhoneOtp.model.PhoneOtpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyOtpApiModel implements VerifyOtpConstructor.Model {
    private final String TAG = "ForgotPassModel";

    @Override
    public void VerifyOtpData(OnFinishedListener onFinishedListener, Activity activity, String otp) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<PhoneOtpResponse> call = apiService.submitOtp(otp);
        call.enqueue(new Callback<PhoneOtpResponse >() {
            @Override
            public void onResponse(@NonNull Call<PhoneOtpResponse > call, @NonNull Response<PhoneOtpResponse > response) {
                int success;

                if (response.body() != null) {
                    success = response.body().getStatus();
                    if (success == 1) {
                        PhoneOtpResponse  keyResponse = response.body();
                        onFinishedListener.onFinished(keyResponse);
                    } else {
                        onFinishedListener.onFailure(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<PhoneOtpResponse > call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t.getMessage());
            }
        });
    }



    @Override
    public void getResetOtp(OnFinishedListener onFinishedListener, Activity otpActivity) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<PhoneOtpResponse> call = apiService.submitResendOtp();
        call.enqueue(new Callback<PhoneOtpResponse >() {
            @Override
            public void onResponse(@NonNull Call<PhoneOtpResponse > call, @NonNull Response<PhoneOtpResponse > response) {
                int success;

                if (response.body() != null) {
                    success = response.body().getStatus();
                    if (success == 1) {
                        PhoneOtpResponse  keyResponse = response.body();
                        onFinishedListener.onOtpFinished(keyResponse);
                    } else {
                        onFinishedListener.onOtpFailure(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<PhoneOtpResponse > call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onOtpFailure(t.getMessage());
            }
        });
    }
}
