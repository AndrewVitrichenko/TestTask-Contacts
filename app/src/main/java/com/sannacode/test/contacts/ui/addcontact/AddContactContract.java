package com.sannacode.test.contacts.ui.addcontact;

import android.support.annotation.StringRes;

import com.sannacode.test.contacts.entity.Contact;
import com.sannacode.test.contacts.listeners.DatabaseOperationListener;

/**
 * Created by Andrew on 06.01.2018.
 */

public interface AddContactContract {

    interface Model {

        void insertContact(Contact contact, DatabaseOperationListener databaseListener);
    }

    interface View {

        void showSaveContactMessage();

        void setSavedValues(String firstName, String lastName, String email);

        void showMessage(@StringRes int messageId);
    }

    interface Presenter {

        void bindView(AddContactContract.View mView);

        void onSaveContactButtonClickedEvent(String accountId, String firstName, String lastName, String email);

        void onSaveInstanceState(String firstName, String lastName, String email);

        void onRestoreInstanceState();

        void unbindView();
    }
}
