package com.sannacode.test.contacts.ui.signin;

import android.content.Intent;
import android.support.annotation.StringRes;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.sannacode.test.contacts.entity.User;

/**
 * Created by Andrew on 06.01.2018.
 */

public interface SignInContract {

    interface Model {

        User getUserByAccountId(String accountId);

        void insertUser(User user);
    }

    interface View {

        GoogleSignInAccount getLastSignedAccount();

        void startContactsActivity(GoogleSignInAccount account);

        void startContactsActivity();

        GoogleSignInClient getGoogleClient(GoogleSignInOptions gso);

        void startSignInIntent(Intent signInIntent);

        void showMessage(@StringRes int messageId);
    }

    interface Presenter {

        void checkIsUserAuthorized();

        void onSignInButtonClickEvent();

        void handleSignInResult(Intent signInData);

        void bindView(SignInContract.View mView);

        void unbindView();
    }

}
