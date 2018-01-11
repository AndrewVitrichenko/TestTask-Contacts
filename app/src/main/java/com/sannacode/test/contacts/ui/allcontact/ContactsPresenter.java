package com.sannacode.test.contacts.ui.allcontact;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.sannacode.test.contacts.R;
import com.sannacode.test.contacts.entity.Contact;
import com.sannacode.test.contacts.entity.SortType;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Andrew on 05.01.2018.
 */

public class ContactsPresenter implements ContactsContract.Presenter, OnCompleteListener<Void> {

    private ContactsContract.Model mRepository;
    private ContactsContract.View mView;

    public ContactsPresenter(ContactsContract.Model mRepository) {
        this.mRepository = mRepository;
    }

    public void bindView(ContactsContract.View mView) {
        this.mView = mView;
    }


    @Override
    public void loadContacts(String accountId, SortType sortType) {
        mRepository.getContactsByAccountId(accountId, sortType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mContacts -> {
                            if (mView != null)
                                mView.showContacts(mContacts);
                        },
                        throwable -> {
                            if (mView != null)
                                mView.showNoContactsMessage();
                        });
    }

    @Override
    public void unbindView() {
        mView = null;
    }

    @Override
    public void onLogoutClickEvent() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        if (mView != null) {
            GoogleSignInClient mGoogleSignInClient = mView.getGoogleClient(gso);
            mGoogleSignInClient.signOut()
                    .addOnCompleteListener(this);
        }
    }

    @Override
    public void onComplete(@NonNull Task<Void> task) {
        if (task.isSuccessful()) {
            if (mView != null) {
                mView.showMessage(R.string.title_logout_success);
                mView.performLogout();
            }
        } else {
            if (mView != null) {
                mView.showMessage(R.string.message_error);
            }
        }
    }
}
