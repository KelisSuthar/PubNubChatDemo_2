package com.addedfooddelivery_user._socialmedia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by chirag on 22/1/18.
 */

public class SmartFacebookLogin {

    private CallbackManager callbackManager;
    private AccessToken accessToken;
    private FacebookLoginView.FacebookResponse facebookResponse;

    private static SmartFacebookLogin instance;

    public static SmartFacebookLogin getInstance() {
        if (instance == null) {
            synchronized (LoginManager.class) {
                if (instance == null) {
                    instance = new SmartFacebookLogin();
                }
            }
        }
        return instance;
    }

    /**
     * Facebook Connect
     **/

    public void setFacebookAuth(FacebookLoginView.FacebookResponse facebookResponse) {
        this.facebookResponse = facebookResponse;
    }

    public void connectFacebook(final FacebookLoginView.FacebookCallBack facebookCallBack) {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                if (facebookCallBack != null)
                    facebookCallBack.onSuccess(loginResult);

                accessToken = loginResult.getAccessToken();
                getFacebookSignInResult();
            }

            @Override
            public void onCancel() {
                if (facebookCallBack != null)
                    facebookCallBack.onCancel();
            }

            @Override
            public void onError(FacebookException error) {
                if (facebookCallBack != null)
                    facebookCallBack.onError(error);
            }
        });
    }

    public void facebookLogin(Activity activity) {
        accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            getFacebookSignInResult();
        } else {
            LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile", "email"));
        }
    }

    private void getFacebookSignInResult() {
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                if (object != null) {
                    facebookResponse.onFacebookCompleted(object, response);
                } else {
                    facebookResponse.onError();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, name, first_name,last_name, email");
        request.setParameters(parameters);
        request.executeAsync();
    }

    public void callbackManager(final int requestCode, final int resultCode, final Intent data) {
        if (requestCode == CallbackManagerImpl.RequestCodeOffset.Login.toRequestCode()) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }
}
