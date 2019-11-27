package com.addedfooddelivery_user.home_deliverylist.api;

import android.app.Activity;

import com.addedfooddelivery_user.home_deliverylist.adpter.AddressListAdpter;
import com.addedfooddelivery_user.home_deliverylist.model.ListAddResponse;
import com.addedfooddelivery_user.home_deliverylist.model.SaveAddResponse;
import com.addedfooddelivery_user.home_deliverylist.model.SetDefaultAddResponse;

public class AddAddressPresenter implements AddAddressConstructor.Presenter, AddAddressConstructor.Model.OnFinishedListener {

    String TAG = "SignupPresenter";
    private AddAddressConstructor.View addAddView;
    private AddAddressConstructor.Model addAddModel;

    public AddAddressPresenter(AddAddressConstructor.View loginView) {
        this.addAddView = loginView;
        this.addAddModel = new AddAddressApiModel();
    }


    @Override
    public void onSaveAddressFinished(SaveAddResponse response) {

        addAddView.onSaveAddressResponseSuccess(response);
        addAddView.showLoadingIndicator(false);
    }


    @Override
    public void onSaveAddressFailure(String t) {
        if (addAddView != null) {
            addAddView.showLoadingIndicator(false);
            addAddView.displayMessage(t);
        }

    }

    @Override
    public void onListAddressFinished(ListAddResponse response) {
        addAddView.onListAddressResponseSuccess(response);
        addAddView.showLoadingIndicator(false);
    }

    @Override
    public void onListAddressFailure(String response) {
        if (addAddView != null) {
            addAddView.showLoadingIndicator(false);
            addAddView.displayMessage(response);
        }
    }

    @Override
    public void onsetDefaultAddFinished(SetDefaultAddResponse response) {
        addAddView.onsetDefaultAddSuccess(response);
        addAddView.showLoadingIndicator(false);
    }

    @Override
    public void onsetDefaultAddFailure(String response) {
        if (addAddView != null) {
            addAddView.showLoadingIndicator(false);
            addAddView.displayMessage(response);
            addAddView.onsetDefaultAddailure(response);
        }
    }

    @Override
    public void onDestroy() {
        addAddView = null;
        addAddModel = null;
    }



    @Override
    public void requestAddAddress(Activity activity, String addressType, String addressLine, double latitude, double longitude,String landmark, String locality) {
        if (addAddView != null) {
            addAddView.showLoadingIndicator(true);
        }
        addAddModel.addAddressData(this,activity,addressType,addressLine,latitude,longitude,landmark,locality);
    }

    @Override
    public void listAddAddress(Activity activity) {
        if (addAddView != null) {
            addAddView.showLoadingIndicator(true);
        }
        addAddModel.listAddress(this,activity);
    }

    @Override
    public void requestAddAddress(Activity activity, int addressId) {

    }


    public void requestAddAddress(Activity activity, Integer customerAddressID) {
        if (addAddView != null) {
            addAddView.showLoadingIndicator(true);
        }
        addAddModel.setDefaulAdd(this,customerAddressID);
    }
}
