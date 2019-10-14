package com.addedfooddelivery_user._socialmedia;

import com.facebook.FacebookException;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

/**
 * Created by chirag on 22/1/18.
 */

public final class FacebookLoginView {

    public interface FacebookCallBack {

        public void onSuccess(LoginResult loginResult);

        public void onCancel();

        public void onError(FacebookException error);
    }

    public interface FacebookResponse {

        public void onFacebookCompleted(JSONObject object, GraphResponse response);

        public void onError();
    }
}
