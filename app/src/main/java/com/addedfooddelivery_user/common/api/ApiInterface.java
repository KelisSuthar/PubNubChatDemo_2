package com.addedfooddelivery_user.common.api;

import com.addedfooddelivery_user.RestaurantDetails.model.RestDetailsResponse;
import com.addedfooddelivery_user.RestaurantDetails.model.addQTYResponce.QtyAddResponce;
import com.addedfooddelivery_user.apiKey.model.GetAPIKeyResponse;
import com.addedfooddelivery_user.cart.model.CartDataResponce;
import com.addedfooddelivery_user.cart.model.CouponList;
import com.addedfooddelivery_user.common.model.CommonResponce;
import com.addedfooddelivery_user.forgottPassword.model.ForgotPassResponse;
import com.addedfooddelivery_user.home.model.DefaultAddResponse;
import com.addedfooddelivery_user.home.model.HomeRestaurantResponse;
import com.addedfooddelivery_user.home_deliverylist.model.ListAddResponse;
import com.addedfooddelivery_user.home_deliverylist.model.SaveAddResponse;
import com.addedfooddelivery_user.home_deliverylist.model.SetDefaultAddResponse;
import com.addedfooddelivery_user.home_search.model.CategoryResponse;
import com.addedfooddelivery_user.loginEmail.model.LoginResponse;
import com.addedfooddelivery_user.restaurantList.model.AllRestCategoryResponse;
import com.addedfooddelivery_user.restaurantList.model.AllRestaurantResponse;
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

    //view all restaurant list by category
    @FormUrlEncoded
    @POST("restaurant_by_category")
    Call<AllRestCategoryResponse> getAllRestaurantCategory(
            @Field("foodCategoryName") String foodCategoryName,
            @Field("sort_by") String sort_by,
            @Field("direction") String direction,
            @Field("price") String price);

    //add address
    @FormUrlEncoded
    @POST("addDeliveryAddress")
    Call<SaveAddResponse> addAddress(
            @Field("adderessType") String adderessType,
            @Field("adderess") String adderess,
            @Field("adderessLatitude") Double adderessLatitude,
            @Field("adderessLongitude") Double adderessLongitude,
            @Field("landmark") String landmark,
            @Field("adderessCity") String adderessCity);


    @GET("AddressList")
    Call<ListAddResponse> getAddressList();

    @POST("default_address")
    Call<DefaultAddResponse> getDefaultAdd();

    @GET("categoryList")
    Call<CategoryResponse> getCategoryList();

    //get default address
    @FormUrlEncoded
    @POST("setAddressDefault")
    Call<SetDefaultAddResponse> setDefaultAdd(@Field("customerAddressID") int customerAddressID);

    //view  restaurant details
    @FormUrlEncoded
    @POST("restaurantDetails")
    Call<RestDetailsResponse> getRestaurantDetails(
            @Field("restaurantID") String restaurantID,
            @Field("vegType") String vegType);

    //add QTY CartData
    @FormUrlEncoded
    @POST("addCart")
    Call<QtyAddResponce> addQTY(
            @Field("restaurantID") String restaurantID,
            @Field("itemID") int itemID,
            @Field("itemPrice") String itemPrice,
            @Field("itemQuantity") int itemQuantity);

    //update QTY CartData
    @FormUrlEncoded
    @POST("quantityUpdate")
    Call<QtyAddResponce> updateQty(
            @Field("restaurantID") String restaurantID,
            @Field("itemID") int itemID,
            @Field("itemQuantity") int itemQuantity);

    //CartData
    @POST("cartDetail")
    Call<CartDataResponce> getCartDetails();

    //CouponData
    @POST("getCoupon")
    Call<CouponList> getCouponData();

    //apply coupon
    @FormUrlEncoded
    @POST("applyCoupon")
    Call<CommonResponce> applyCouponCode(
            @Field("couponcode") String couponcode);

    //remove coupon
    @POST("removeCoupon")
    Call<CommonResponce> removeCouponData();

    //delete CartData
    @FormUrlEncoded
    @POST("emptyCart")
    Call<CommonResponce> deleteCartData(
            @Field("cartID") String cartID);

    //logout
    @GET("user_Logout")
    Call<CommonResponce> logout();

    //delete account
    @GET("deleteAccount")
    Call<CommonResponce> deleteAccount();
}
