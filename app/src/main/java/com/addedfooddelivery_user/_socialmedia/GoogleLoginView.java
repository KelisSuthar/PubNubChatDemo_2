package com.addedfooddelivery_user._socialmedia;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

/**
 * Created by chirag on 22/1/18.
 */

public final class GoogleLoginView {

    public interface GoogleLogin {

        void onGoogleSuccess(GoogleSignInAccount googleSignInAccount);

        void onFail();

    }

}
