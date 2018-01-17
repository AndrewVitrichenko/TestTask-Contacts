package com.sannacode.test.contacts.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.sannacode.test.contacts.entity.Contact;

import java.util.List;

import io.reactivex.Single;


/**
 * Created by Andrew on 05.01.2018.
 */

@Dao
public interface ContactsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertContact(Contact contact);

    @Query("SELECT * FROM contacts WHERE accountId LIKE :accountId")
    Single<List<Contact>> getContactsByAccountId(String accountId);

    @Query("SELECT * FROM contacts WHERE accountId LIKE :accountId ORDER BY firstName")
    Single<List<Contact>> getContactsByAccountIdSortedByName(String accountId);

    @Query("SELECT * FROM contacts WHERE accountId LIKE :accountId ORDER BY email")
    Single<List<Contact>> getContactsByAccountIdSortedByEmail(String accountId);
}
