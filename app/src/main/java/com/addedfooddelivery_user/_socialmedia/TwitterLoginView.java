package com.addedfooddelivery_user._socialmedia;

import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.User;

/**
 * Created by chirag on 22/1/18.
 */

public final class TwitterLoginView {

    public interface TwitterCallBack {

        public void onSuccess(Result<User> userResult);

        public void failure(TwitterException exc);
    }
}
