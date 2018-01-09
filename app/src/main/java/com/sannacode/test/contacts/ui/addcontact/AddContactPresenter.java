package com.sannacode.test.contacts.ui.addcontact;

import com.sannacode.test.contacts.entity.Contact;

/**
 * Created by Andrew on 06.01.2018.
 */

public class AddContactPresenter implements AddContactContract.Presenter {

    private AddContactContract.Model mRepository;
    private AddContactContract.View mView;
    private Contact currentContact;

    public AddContactPresenter(AddContactContract.Model mRepository) {
        this.mRepository = mRepository;
        this.currentContact = new Contact();
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
        currentContact = new Contact();
        currentContact.setAccountId(accountId);
        currentContact.setFirstName(firstName);
        currentContact.setLastName(lastName);
        currentContact.setEmail(email);
        mRepository.insertContact(currentContact);
        if (mView != null) {
            mView.showSaveContactMessage();
        }
    }

    @Override
    public void onSaveInstanceState(String accountId, String firstName, String lastName, String email) {
        currentContact.setAccountId(accountId);
        currentContact.setFirstName(firstName);
        currentContact.setLastName(lastName);
        currentContact.setEmail(email);
    }

    @Override
    public void onRestoreInstanceState() {
        if (mView != null) {
            mView.setSavedValues(currentContact.getFirstName(), currentContact.getLastName(), currentContact.getEmail());
        }
    }


}
