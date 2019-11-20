package com.addedfooddelivery_user.common.api;

import com.addedfooddelivery_user.apiKey.model.GetAPIKeyResponse;
import com.addedfooddelivery_user.forgottPassword.model.ForgotPassResponse;
import com.addedfooddelivery_user.loginEmail.model.LoginResponse;
import com.addedfooddelivery_user.signup.model.SignupResponse;
import com.addedfooddelivery_user.verificationPhone.model.PhoneVerifyResponse;
import com.addedfooddelivery_user.verifyPhoneOtp.model.PhoneOtpResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    //get API Key
    @GET("userapikey")
    Call<GetAPIKeyResponse> getAPIKey();

    @FormUrlEncoded
    @POST("customerLogin")
    Call<LoginResponse> submitLogin(@Field("email") String email,
                                    @Field("password") String password);

    //forgot password
    @FormUrlEncoded
    @POST("customerforgotpassword")
    Call<ForgotPassResponse> submitForgotPass(@Field("email") String email);

    //forgot password
    @FormUrlEncoded
    @POST("customerSignup")
    Call<SignupResponse> submitSignup(
            @Field("userName") String userName,
            @Field("password") String password,
            @Field("email") String email,
            @Field("loginType") String loginType,
            @Field("socialID") String socialID);

    @FormUrlEncoded
    @POST("customermobile")
    Call<PhoneVerifyResponse> submitPhone(@Field("countryCode") String countryCode,
                                          @Field("phoneNumber") String phoneNumber);

    @FormUrlEncoded
    @POST("customerVerifyOTP")
    Call<PhoneOtpResponse> submitOtp(@Field("otp") String otpPhone);

    @POST("resendOTP")
    Call<PhoneOtpResponse> submitResendOtp();
}
