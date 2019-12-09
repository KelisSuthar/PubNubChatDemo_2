package com.addedfooddelivery_user.payment.api;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.addedfooddelivery_user.common.api.ApiClient;
import com.addedfooddelivery_user.common.api.ApiInterface;
import com.addedfooddelivery_user.common.model.CommonResponce;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentApiModel implements PaymentConstructor.Model {
    private final String TAG = "SettingModel";


    @Override
    public void addCard(OnFinishedListener onFinishedListener, Activity activity) {

    }
}
