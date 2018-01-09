package com.sannacode.test.contacts.ui.allcontact;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.sannacode.test.contacts.entity.Contact;
import com.sannacode.test.contacts.entity.SortType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 05.01.2018.
 */

public class ContactsPresenter implements ContactsContract.Presenter {

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
        List<Contact> contacts = new ArrayList<>();
        switch (sortType) {
            case DEFAULT:
                contacts = mRepository.getContactsByAccountId(accountId, SortType.DEFAULT);
                break;
            case BY_NAME:
                contacts = mRepository.getContactsByAccountId(accountId, SortType.BY_NAME);
                break;
            case BY_EMAIL:
                contacts = mRepository.getContactsByAccountId(accountId, SortType.BY_EMAIL);
                break;
        }
        if (contacts.isEmpty()) {
            mView.showNoContactsMessage();
        } else {
            mView.showContacts(contacts);
        }
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
                    .addOnCompleteListener(mView.getOnCompleteLogoutListener());
        }
    }

}
