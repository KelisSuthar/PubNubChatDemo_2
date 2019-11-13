package com.addedfooddelivery_user.common.api;

import android.app.Activity;

public interface BaseView {
    void showLoadingIndicator(boolean isShow);
    void displayMessage(String message);
    void initProgressBar();
    Activity getContext();
}
