package com.addedfooddelivery_user.common.api;

import com.addedfooddelivery_user.RestaurantList.model.AllRestaurantResponse;
import com.addedfooddelivery_user.apiKey.model.GetAPIKeyResponse;
import com.addedfooddelivery_user.forgottPassword.model.ForgotPassResponse;
import com.addedfooddelivery_user.home.model.DefaultAddResponse;
import com.addedfooddelivery_user.home.model.HomeRestaurantResponse;
import com.addedfooddelivery_user.home_deliverylist.model.ListAddResponse;
import com.addedfooddelivery_user.home_deliverylist.model.SaveAddResponse;
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

    //add phone number
    @FormUrlEncoded
    @POST("customermobile")
    Call<PhoneVerifyResponse> submitPhone(@Field("countryCode") String countryCode,
                                          @Field("phoneNumber") String phoneNumber);

    //verify otp
    @FormUrlEncoded
    @POST("customerVerifyOTP")
    Call<PhoneOtpResponse> submitOtp(@Field("otp") String otpPhone);

    //resend otp
    @POST("resendOTP")
    Call<PhoneOtpResponse> submitResendOtp();

    //get home restaurant data
    @FormUrlEncoded
    @POST("home")
    Call<HomeRestaurantResponse> getHomeRestaurant(@Field("restaurantCity") String city);

    //view all restaurant list
    @FormUrlEncoded
    @POST("restaurantList")
    Call<AllRestaurantResponse> getAllRestaurant(
            @Field("restaurantType") String restaurantType,
            @Field("sort_by") String sort_by,
            @Field("direction") String direction,
            @Field("category") String category,
            @Field("price") String price);

    //add address
    @FormUrlEncoded
    @POST("addDeliveryAddress")
    Call<SaveAddResponse> addAddress(
            @Field("adderessType") String adderessType,
            @Field("adderess") String adderess,
            @Field("adderessLatitude") Double direction,
            @Field("adderessLongitude") Double category,
            @Field("adderessCity") String adderessCity);

    @GET("AddressList")
    Call<ListAddResponse> getAddressList();

    @POST("default_address")
    Call<DefaultAddResponse> getDefaultAdd();
}
