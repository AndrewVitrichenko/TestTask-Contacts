package com.sannacode.test.contacts;

import android.arch.persistence.room.EmptyResultSetException;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;

import com.sannacode.test.contacts.database.AppDatabase;
import com.sannacode.test.contacts.entity.Contact;
import com.sannacode.test.contacts.entity.SortType;
import com.sannacode.test.contacts.entity.User;
import com.sannacode.test.contacts.listeners.DatabaseOperationListener;
import com.sannacode.test.contacts.ui.addcontact.AddContactContract;
import com.sannacode.test.contacts.ui.addcontact.AddContactRepository;
import com.sannacode.test.contacts.ui.allcontact.ContactsContract;
import com.sannacode.test.contacts.ui.allcontact.ContactsRepository;
import com.sannacode.test.contacts.ui.signin.SignInContract;
import com.sannacode.test.contacts.ui.signin.UserRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Andrew on 18.01.2018.
 */

public class ContactsRepositoryTest {

    private AppDatabase mDatabase;
    private SignInContract.Model mUserRepository;
    private AddContactContract.Model mAddContactRepository;
    private ContactsContract.Model mContactsRepository;
    private User mUser = new User(1, "1234", "Test Testerov");
    private Contact mContact = new Contact(1, mUser.getAccountId(),
            "Name", "Surname", "test@gmail.com");

    @Before
    public void initDb() throws Exception {
        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                AppDatabase.class)
                .build();
        mUserRepository = new UserRepository(mDatabase.getUserDao());
        mAddContactRepository = new AddContactRepository(mDatabase.getContactDao());
        mContactsRepository = new ContactsRepository(mDatabase.getContactDao());
    }

    @After
    public void closeDb() throws Exception {
        mDatabase.close();
    }



    @Test
    public void getContactsWhenRepositoryIsEmpty() throws EmptyResultSetException {
        mContactsRepository.getContactsByAccountId(mUser.getAccountId(),SortType.DEFAULT);
    }

    @Test
    public void insertAndGetContacts() {
        mAddContactRepository.insertContact(mContact, new DatabaseOperationListener() {
            @Override
            public void onDatabaseOperationSucceed() {
                mContactsRepository.getContactsByAccountId(mUser.getAccountId(), SortType.DEFAULT)
                        .test()
                        .assertValue(mContacts ->
                                !mContacts.isEmpty() &&
                                        mContacts.get(0).getAccountId().equals(mUser.getAccountId())
                        );
            }

            @Override
            public void onDatabaseOperationFailure() {
                mContactsRepository.getContactsByAccountId(mUser.getAccountId(), SortType.DEFAULT)
                        .test()
                        .assertEmpty();
            }
        });
    }


}
