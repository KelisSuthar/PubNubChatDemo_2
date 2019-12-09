package com.addedfooddelivery_user.cart.api;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.addedfooddelivery_user.RestaurantDetails.model.addQTYResponce.QtyAddResponce;
import com.addedfooddelivery_user.cart.model.CartDataResponce;
import com.addedfooddelivery_user.cart.model.CouponList;
import com.addedfooddelivery_user.common.api.ApiClient;
import com.addedfooddelivery_user.common.api.ApiInterface;
import com.addedfooddelivery_user.common.model.CommonResponce;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartApiModel implements CartConstructor.Model {
    private final String TAG = "CartModel";

    @Override
    public void getCartData(OnFinishedListener onFinishedListener, Activity activity) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<CartDataResponce> call = apiService.getCartDetails();
        call.enqueue(new Callback<CartDataResponce>() {
            @Override
            public void onResponse(@NonNull Call<CartDataResponce> call, @NonNull Response<CartDataResponce> response) {
                int success;

                if (response.body() != null) {
                    success = response.body().getStatus();
                    if (success == 1) {
                        CartDataResponce keyResponse = response.body();
                        onFinishedListener.onCartFinished(keyResponse);
                    } else {
                        onFinishedListener.onCartFailure(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<CartDataResponce> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onCartFailure(t.getMessage());
            }
        });
    }

    @Override
    public void updateCart(OnFinishedListener onFinishedListener, Activity activity, String restaurantID, int itemID, int count) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<QtyAddResponce> call = apiService.updateQty(restaurantID, itemID, count);
        call.enqueue(new Callback<QtyAddResponce>() {
            @Override
            public void onResponse(@NonNull Call<QtyAddResponce> call, @NonNull Response<QtyAddResponce> response) {
                int success;

                if (response.body() != null) {
                    success = response.body().getStatus();
                    if (success == 1) {
                        QtyAddResponce keyResponse = response.body();
                        onFinishedListener.onCartUpdateFinished(keyResponse);
                    } else {
                        onFinishedListener.onCartUpdateFailure(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<QtyAddResponce> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onCartUpdateFailure(t.getMessage());
            }
        });
    }

    @Override
    public void addItemQTY(OnFinishedListener onFinishedListener, Activity activity, String restaurantID, int itemID, String itemPrice, int count) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<QtyAddResponce> call = apiService.addQTY(restaurantID, itemID, itemPrice, count);
        call.enqueue(new Callback<QtyAddResponce>() {
            @Override
            public void onResponse(@NonNull Call<QtyAddResponce> call, @NonNull Response<QtyAddResponce> response) {
                int success;

                if (response.body() != null) {
                    success = response.body().getStatus();
                    if (success == 1) {
                        QtyAddResponce keyResponse = response.body();
                        onFinishedListener.onCartAddFinished(keyResponse);
                    } else {
                        onFinishedListener.onCartAddFailure(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<QtyAddResponce> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onCartAddFailure(t.getMessage());
            }
        });
    }

    @Override
    public void getCouponData(OnFinishedListener onFinishedListener, Activity activity) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<CouponList> call = apiService.getCouponData();
        call.enqueue(new Callback<CouponList>() {
            @Override
            public void onResponse(@NonNull Call<CouponList> call, @NonNull Response<CouponList> response) {
                int success;

                if (response.body() != null) {
                    success = response.body().getStatus();
                    if (success == 1) {
                        CouponList keyResponse = response.body();
                        onFinishedListener.onCouponListFinished(keyResponse);
                    } else {
                        onFinishedListener.onCouponListFailure(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<CouponList> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onCouponListFailure(t.getMessage());
            }
        });
    }

    @Override
    public void getApplyCoupon(OnFinishedListener onFinishedListener, Activity activity, String couponCode) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<CommonResponce> call = apiService.applyCouponCode(couponCode);
        call.enqueue(new Callback<CommonResponce>() {
            @Override
            public void onResponse(@NonNull Call<CommonResponce> call, @NonNull Response<CommonResponce> response) {
                int success;

                if (response.body() != null) {
                    success = response.body().getStatus();
                    if (success == 1) {
                        CommonResponce keyResponse = response.body();
                        onFinishedListener.onApplyCouponFinished(keyResponse);
                    } else {
                        onFinishedListener.onApplyCouponListFailure(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<CommonResponce> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onApplyCouponListFailure(t.getMessage());
            }
        });
    }

    @Override
    public void removeCouponData(OnFinishedListener onFinishedListener, Activity activity) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<CommonResponce> call = apiService.removeCouponData();
        call.enqueue(new Callback<CommonResponce>() {
            @Override
            public void onResponse(@NonNull Call<CommonResponce> call, @NonNull Response<CommonResponce> response) {
                int success;

                if (response.body() != null) {
                    success = response.body().getStatus();
                    if (success == 1) {
                        CommonResponce keyResponse = response.body();
                        onFinishedListener.onRemoveCouponFinished(keyResponse);
                    } else {
                        onFinishedListener.onRemoveCouponListFailure(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<CommonResponce> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onRemoveCouponListFailure(t.getMessage());
            }
        });
    }

    @Override
    public void deleteCart(OnFinishedListener onFinishedListener, Activity activity, String cartId) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<CommonResponce> call = apiService.deleteCartData(cartId);
        call.enqueue(new Callback<CommonResponce>() {
            @Override
            public void onResponse(@NonNull Call<CommonResponce> call, @NonNull Response<CommonResponce> response) {
                int success;

                if (response.body() != null) {
                    success = response.body().getStatus();
                    if (success == 1) {
                        CommonResponce keyResponse = response.body();
                        onFinishedListener.onDeletCartinished(keyResponse);
                    } else {
                        onFinishedListener.onDeletCartFailure(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<CommonResponce> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onDeletCartFailure(t.getMessage());
            }
        });
    }




}


