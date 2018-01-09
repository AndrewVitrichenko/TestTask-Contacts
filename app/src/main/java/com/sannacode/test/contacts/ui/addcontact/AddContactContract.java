package com.sannacode.test.contacts.ui.addcontact;

import com.sannacode.test.contacts.entity.Contact;

/**
 * Created by Andrew on 06.01.2018.
 */

public interface AddContactContract {

    interface Model {

        void insertContact(Contact contact);
    }

    interface View {

        void showSaveContactMessage();

        void setSavedValues(String firstName, String lastName, String email);
    }

    interface Presenter {

        void bindView(AddContactContract.View mView);

        void onSaveContactButtonClickedEvent(String accountId, String firstName, String lastName, String email);

        void onSaveInstanceState(String accountId, String firstName, String lastName, String email);

        void onRestoreInstanceState();

        void unbindView();
    }
}
