package com.sannacode.test.contacts.ui.allcontact;

import android.support.annotation.StringRes;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.sannacode.test.contacts.entity.Contact;
import com.sannacode.test.contacts.entity.SortType;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Andrew on 05.01.2018.
 */

public interface ContactsContract {

    interface Model {

        Single<List<Contact>> getContactsByAccountId(String accountId, SortType sortType);
    }

    interface View {

        void showNoContactsMessage();

        void showMessage(@StringRes int messageId);

        void showContacts(List<Contact> contacts);

        GoogleSignInClient getGoogleClient(GoogleSignInOptions gso);

        void performLogout();
    }

    interface Presenter {

        void bindView(ContactsContract.View mView);

        void loadContacts(String accountId, SortType sortType);

        void unbindView();

        void onLogoutClickEvent();

    }
}
