package com.addedfooddelivery_user.common.api;

import com.addedfooddelivery_user.apiKey.model.GetAPIKeyResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    //get API Key
    @GET("userapikey")
    Call<GetAPIKeyResponse> getAPIKey();

}
