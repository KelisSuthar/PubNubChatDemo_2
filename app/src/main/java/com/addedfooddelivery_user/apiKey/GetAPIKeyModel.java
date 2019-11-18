package com.addedfooddelivery_user.apiKey;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.addedfooddelivery_user.apiKey.model.GetAPIKeyResponse;
import com.addedfooddelivery_user.common.api.ApiClient;
import com.addedfooddelivery_user.common.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAPIKeyModel implements GetAPIKeyConstructor.Model {
    private final String TAG = "GetAPIKeyModel";

    @Override
    public void getAPIKeyDetail(OnFinishedListener onFinishedListener, Activity activity) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        //Call<GetAPIKeyResponse> call = apiService.getAPIKey();
        /*call.enqueue(new Callback<GetAPIKeyResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetAPIKeyResponse> call, @NonNull Response<GetAPIKeyResponse> response) {
                int success;

//                if (response.code() == 401) {
//                    SharedPreferenceManager.removeAllDataWithErrorCode(activity);
//                    return;
//                }

                if (response.body() != null) {
                    success = response.body().getStatus();
                    if (success == 1) {
                        GetAPIKeyResponse keyResponse = response.body();
                        onFinishedListener.onFinished(keyResponse);
                    } else {
                        onFinishedListener.onFailure(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<GetAPIKeyResponse> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t.getMessage());
            }
        });*/
    }

}
