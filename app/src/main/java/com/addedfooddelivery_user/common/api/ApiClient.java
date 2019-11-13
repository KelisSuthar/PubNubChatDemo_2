/**
 * @file ApiClient.java
 * @brief This class will have all constants related to the networking and retrofit instance
 * @author Shrikant
 * @date 15/04/2018
 */
package com.addedfooddelivery_user.common.api;


import androidx.annotation.NonNull;

import com.addedfooddelivery_user.common.AppConstants;
import com.addedfooddelivery_user.common.SharedPreferenceManager;
import com.addedfooddelivery_user.common.ShowLogToast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient implements AppConstants {


    private static final String BASE_URL = "http://webapps.iqlance-demo.com/added_food/api/";
    private static Retrofit retrofit = null;

    /**
     * This method returns retrofit client instance
     *
     * @return Retrofit object
     */
    public static Retrofit getClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(getHttpClient())
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient getHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .addNetworkInterceptor(new AddHeaderInterceptor())
                .connectTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.MINUTES)
                .retryOnConnectionFailure(true)
                .build();

        client.connectionPool().evictAll();
        return client;
    }

    public static class AddHeaderInterceptor implements Interceptor {
        @NonNull
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();

            String apikey = SharedPreferenceManager.getString(API_KEY_VALUE, STATIC_API_KEY);
            String accessToken = SharedPreferenceManager.getString(ACCESS_TOKEN, "");
            int userID = SharedPreferenceManager.getInt(LOGGED_IN_USER_ID, -1);
            if (apikey.equals(STATIC_API_KEY))
                builder.addHeader("Accesskey", "" + apikey);
            else
                builder.addHeader("apikey", "" + apikey);


            if (!accessToken.equals(""))
                builder.addHeader("accesstoken", "" + accessToken);

            if (userID != -1)
                builder.addHeader("userID", "" + userID);

            ShowLogToast.ShowLog("http Apikey -->" + apikey);
            ShowLogToast.ShowLog("http accesstoken -->" + accessToken);
            ShowLogToast.ShowLog("http userID -->" + userID);

            return chain.proceed(builder.build());
        }
    }
}
