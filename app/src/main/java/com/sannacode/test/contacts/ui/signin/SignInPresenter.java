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

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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
//            checkIfUserExistInDatabase(account);
            if (mView != null) {
                mView.startContactsActivity(account);
            }
        } catch (ApiException e) {
//            if (mView != null) {
//                mView.showMessage(R.string.message_error);
//            }
            checkIfUserExistInDatabase("123456");
            if (mView != null) {
                mView.startContactsActivity("123456");
            }
        }
    }


    private void checkIfUserExistInDatabase(String accountId) {
        mRepository.getUserByAccountId(accountId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        throwable -> {
                            User mUser = new User();
                            mUser.setAccountId(accountId);
                            mUser.setFullName("Petya");
                            mRepository.insertUser(mUser);
                        }
                );
    }

}
