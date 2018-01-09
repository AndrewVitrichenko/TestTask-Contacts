package com.sannacode.test.contacts.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.sannacode.test.contacts.database.dao.ContactsDao;
import com.sannacode.test.contacts.database.dao.UsersDao;
import com.sannacode.test.contacts.entity.Contact;
import com.sannacode.test.contacts.entity.User;
import com.sannacode.test.contacts.util.Constants;

/**
 * Created by Andrew on 05.01.2018.
 */

@Database(entities = {User.class, Contact.class}, version = Constants.DATABASE_VERSION, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UsersDao getUserDao();

    public abstract ContactsDao getContactDao();

}