package com.sannacode.test.contacts.ui.allcontact;

import com.sannacode.test.contacts.database.dao.ContactsDao;
import com.sannacode.test.contacts.entity.Contact;
import com.sannacode.test.contacts.entity.SortType;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;


/**
 * Created by Andrew on 05.01.2018.
 */

public class ContactsRepository implements ContactsContract.Model {

    private ContactsDao mDao;

    public ContactsRepository(ContactsDao mDao) {
        this.mDao = mDao;
    }


    @Override
    public Single<List<Contact>> getContactsByAccountId(String accountId, SortType sortType) {
        switch (sortType) {
            case DEFAULT:
                return mDao.getContactsByAccountId(accountId);
            case BY_NAME:
                return mDao.getContactsByAccountIdSortedByName(accountId);
            case BY_EMAIL:
                return mDao.getContactsByAccountIdSortedByEmail(accountId);
        }
        return null;
    }

}
