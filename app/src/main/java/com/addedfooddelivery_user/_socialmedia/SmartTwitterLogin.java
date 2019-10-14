package com.addedfooddelivery_user._socialmedia;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.models.User;

import retrofit2.Call;

/**
 * Created by chirag on 22/1/18.
 */

public class SmartTwitterLogin {
    private static TwitterAuthClient mTwitterAuthClient;
    private TwitterLoginView.TwitterCallBack twitterCallBack;

    private static SmartTwitterLogin instance;

    public static SmartTwitterLogin getInstance() {
        if (instance == null) {
            synchronized (SmartTwitterLogin.class) {
                if (instance == null) {
                    mTwitterAuthClient = new TwitterAuthClient();
                    instance = new SmartTwitterLogin();
                }
            }
        }
        return instance;
    }

    public void setTwitterAuth(TwitterLoginView.TwitterCallBack twitterCallBack) {
        this.twitterCallBack = twitterCallBack;
    }

    public void TwitterLogin(final Activity activity) {
        Call<User> user = TwitterCore.getInstance().getApiClient().getAccountService().verifyCredentials(false, false, true);
        user.enqueue(new Callback<User>() {
            @Override
            public void success(Result<User> userResult) {
                if (twitterCallBack != null)
                    twitterCallBack.onSuccess(userResult);
            }

            @Override
            public void failure(TwitterException exc) {
                Log.d("TwitterKit", "Verify Credentials Failure", exc);
                mTwitterAuthClient.authorize(activity, new com.twitter.sdk.android.core.Callback<TwitterSession>() {
                    @Override
                    public void success(Result<TwitterSession> twitterSessionResult) {
                        final Call<User> user = TwitterCore.getInstance().getApiClient().getAccountService().verifyCredentials(false, false, true);
                        user.enqueue(new Callback<User>() {
                            @Override
                            public void success(Result<User> userResult) {
                                if (twitterCallBack != null)
                                    twitterCallBack.onSuccess(userResult);
                            }

                            @Override
                            public void failure(TwitterException exc) {
                                Log.d("TwitterKit", "Verify Credentials Failure", exc);
                            }
                        });
                    } // Success

                    @Override
                    public void failure(TwitterException e) {
                        e.printStackTrace();
                        if (twitterCallBack != null)
                            twitterCallBack.failure(e);
                    }
                });
            }
        });
    }

    public void callbackManager(final int requestCode, final int resultCode, final Intent data) {
        mTwitterAuthClient.onActivityResult(requestCode, resultCode, data);
    }

}
