package com.sannacode.test.contacts.ui.signin;

import android.content.Intent;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.sannacode.test.contacts.R;
import com.sannacode.test.contacts.entity.User;

/**
 * Created by Andrew on 06.01.2018.
 */

public class SignInPresenter implements SignInContract.Presenter {

    private final SignInContract.Model mRepository;
    private SignInContract.View mView;

    private GoogleSignInClient mGoogleSignInClient;

    public SignInPresenter(SignInContract.Model mRepository) {
        this.mRepository = mRepository;
    }

    public void bindView(SignInContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void unbindView() {
        mView = null;
    }

    @Override
    public void checkIsUserAuthorized() {
        GoogleSignInAccount account = mView.getLastSignedAccount();
        if (account != null) {
            if (mView != null) {
                mView.startContactsActivity(account);
            }
        } else {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();
            if (mView != null) {
                mGoogleSignInClient = mView.getGoogleClient(gso);
            }
        }
    }

    @Override
    public void onSignInButtonClickEvent() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        if (mView != null) {
            mView.startSignInIntent(signInIntent);
        }
    }

    @Override
    public void handleSignInResult(Intent signInData) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(signInData);
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            checkIfUserExistInDatabase(account);
            if (mView != null) {
                mView.startContactsActivity(account);
            }
        } catch (ApiException e) {
            if (mView != null) {
                mView.startContactsActivity();
//                mView.showMessage(R.string.message_error);
            }
        }
    }


    private void checkIfUserExistInDatabase(GoogleSignInAccount account) {
        User user = mRepository.getUserByAccountId(account.getId());
        if (user == null) {
            user = new User();
            user.setAccountId(account.getId());
            user.setFullName(account.getDisplayName());
            mRepository.insertUser(user);
        }
    }

}
