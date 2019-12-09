package com.addedfooddelivery_user.payment.api;

import android.app.Activity;

import com.addedfooddelivery_user.common.model.CommonResponce;

public class PaymentPresenter implements PaymentConstructor.Presenter, PaymentConstructor.Model.OnFinishedListener {

    String TAG = "PaymentPresenter";
    private PaymentConstructor.View paymentView;
    private PaymentConstructor.Model paymentModel;

    public PaymentPresenter(PaymentConstructor.View settingView) {
        this.paymentView = settingView;
        this.paymentModel = new PaymentApiModel();
    }


    @Override
    public void onDestroy() {
        paymentView = null;
        paymentModel = null;
    }

    @Override
    public void requestAddCard(Activity activity) {
        if (paymentView != null) {
            paymentView.showLoadingIndicator(true);
        }
        paymentModel.addCard(this,activity);
    }


    @Override
    public void onAddCardFinished(CommonResponce response) {
        paymentView.onAddCardSuccess(response);
        paymentView.showLoadingIndicator(false);
    }

    @Override
    public void onAddCardFailure(String t) {
        if (paymentView != null) {
            paymentView.showLoadingIndicator(false);
            paymentView.displayMessage(t);
        }
    }
}
