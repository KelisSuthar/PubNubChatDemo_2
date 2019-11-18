package com.addedfooddelivery_user.common.api;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.addedfooddelivery_user.apiKey.GetAPIKeyConstructor;
import com.addedfooddelivery_user.apiKey.model.GetAPIKeyResponse;
import com.addedfooddelivery_user.common.ReusedMethod;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetApiModel implements GetAPIKeyConstructor.Model {
    @Override
    public void getAPIKeyDetail(OnFinishedListener onFinishedListener, Activity activity) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseBody> call = apiService.getAPIKey();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                if (response.code() == 200) {
                    onFinishedListener.onFinished(response);
                }else {
                    onFinishedListener.onFailure(response.message());
                }

            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                // Log error here since request failed
                //Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t.getMessage());
            }
        });
    }
}
