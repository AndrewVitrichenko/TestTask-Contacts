package com.sannacode.test.contacts.ui.addcontact;

import com.sannacode.test.contacts.database.dao.ContactsDao;
import com.sannacode.test.contacts.entity.Contact;

/**
 * Created by Andrew on 06.01.2018.
 */

public class AddContactRepository implements AddContactContract.Model {

    private ContactsDao mDao;

    public AddContactRepository(ContactsDao mDao) {
        this.mDao = mDao;
    }

    @Override
    public void insertContact(Contact contact) {
        mDao.insertContact(contact);
    }
}
