package com.addedfooddelivery_user._socialmedia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

/**
 * Created by chirag on 22/1/18.
 */

public class SmartGoogleLogin {

    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;
    private static SmartGoogleLogin instance;

    private GoogleLoginView.GoogleLogin googleLoginView;

    public static SmartGoogleLogin getInstance() {
        if (instance == null) {
            synchronized (SmartGoogleLogin.class) {
                if (instance == null) {
                    instance = new SmartGoogleLogin();
                }
            }
        }
        return instance;
    }

    public void setGoogleAuth(GoogleLoginView.GoogleLogin googleLoginView) {
        this.googleLoginView = googleLoginView;
    }

    public void connect(Context context, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        } else {
            mGoogleApiClient = new GoogleApiClient.Builder(context).enableAutoManage((FragmentActivity) context, onConnectionFailedListener).addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions).build();
        }
    }

    public void loginGoogle(final Activity activity) {
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.clearDefaultAccountAndReconnect().setResultCallback(new ResultCallback<Status>() {

                @Override
                public void onResult(@NonNull Status status) {
                    mGoogleApiClient.disconnect();
                    Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                    activity.startActivityForResult(signInIntent, RC_SIGN_IN);
                }
            });

        } else {
            Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
            activity.startActivityForResult(signInIntent, RC_SIGN_IN);
        }
    }

    public void callbackManager(final int requestCode, final Intent data) {
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleGoogleSignInResult(result);
        }
    }

    private void handleGoogleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            if (googleLoginView != null) {
                googleLoginView.onGoogleSuccess(acct);
            }
        } else {
            if (googleLoginView != null)
                googleLoginView.onFail();
        }
    }
}
