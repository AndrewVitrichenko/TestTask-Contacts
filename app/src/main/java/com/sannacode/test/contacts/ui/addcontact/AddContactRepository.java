package com.sannacode.test.contacts.ui.addcontact;

import com.sannacode.test.contacts.database.dao.ContactsDao;
import com.sannacode.test.contacts.entity.Contact;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
        Observable.just(contact)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(mContact -> mDao.insertContact(mContact));
    }
}
