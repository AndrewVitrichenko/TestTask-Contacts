package com.sannacode.test.contacts.ui.allcontact;

import com.sannacode.test.contacts.database.dao.ContactsDao;
import com.sannacode.test.contacts.entity.Contact;
import com.sannacode.test.contacts.entity.SortType;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Andrew on 05.01.2018.
 */

public class ContactsRepository implements ContactsContract.Model {

    private ContactsDao mDao;

    public ContactsRepository(ContactsDao mDao) {
        this.mDao = mDao;
    }


    @Override
    public List<Contact> getContactsByAccountId(String accountId, SortType sortType) {
        List<Contact> contacts = new ArrayList<>();
        switch (sortType) {
            case DEFAULT:
                contacts = mDao.getContactsByAccountId(accountId);
                break;
            case BY_NAME:
                contacts = mDao.getContactsByAccountIdSortedByName(accountId);
                break;
            case BY_EMAIL:
                contacts = mDao.getContactsByAccountIdSortedByEmail(accountId);
                break;
        }
        return contacts;
    }

}
