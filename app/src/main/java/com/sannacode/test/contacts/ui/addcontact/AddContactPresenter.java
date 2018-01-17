package com.sannacode.test.contacts.ui.addcontact;

import com.sannacode.test.contacts.R;
import com.sannacode.test.contacts.entity.Contact;
import com.sannacode.test.contacts.listeners.DatabaseOperationListener;

/**
 * Created by Andrew on 06.01.2018.
 */

public class AddContactPresenter implements AddContactContract.Presenter, DatabaseOperationListener {

    private AddContactContract.Model mRepository;
    private AddContactContract.View mView;
    private String lastEnteredFirstName = "";
    private String lastEnteredLastName = "";
    private String lastEnteredEmail = "";

    public AddContactPresenter(AddContactContract.Model mRepository) {
        this.mRepository = mRepository;
    }

    @Override
    public void bindView(AddContactContract.View mView) {
        this.mView = mView;
    }


    @Override
    public void unbindView() {
        mView = null;
    }

    @Override
    public void onSaveContactButtonClickedEvent(String accountId, String firstName, String lastName, String email) {
        Contact contact = new Contact();
        contact.setAccountId(accountId);
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setEmail(email);
        setDefaultValues();
        mRepository.insertContact(contact, this);
    }

    @Override
    public void onSaveInstanceState(String firstName, String lastName, String email) {
        saveEnteredValues(firstName, lastName, email);
    }

    private void saveEnteredValues(String firstName, String lastName, String email) {
        lastEnteredFirstName = firstName;
        lastEnteredLastName = lastName;
        lastEnteredEmail = email;
    }

    private void setDefaultValues() {
        lastEnteredFirstName = "";
        lastEnteredLastName = "";
        lastEnteredEmail = "";
    }

    @Override
    public void onRestoreInstanceState() {
        if (mView != null) {
            mView.setSavedValues(lastEnteredFirstName, lastEnteredLastName, lastEnteredEmail);
        }
    }

    @Override
    public void onDatabaseOperationSucceed() {
        if (mView != null) {
            mView.showSaveContactMessage();
        }
    }

    @Override
    public void onDatabaseOperationFailure() {
        if (mView != null) {
            mView.showMessage(R.string.message_error);
        }
    }

}


