package com.sannacode.test.contacts.di.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.sannacode.test.contacts.database.AppDatabase;
import com.sannacode.test.contacts.database.dao.ContactsDao;
import com.sannacode.test.contacts.database.dao.UsersDao;
import com.sannacode.test.contacts.ui.addcontact.AddContactContract;
import com.sannacode.test.contacts.ui.addcontact.AddContactRepository;
import com.sannacode.test.contacts.ui.allcontact.ContactsContract;
import com.sannacode.test.contacts.ui.allcontact.ContactsRepository;
import com.sannacode.test.contacts.ui.signin.SignInContract;
import com.sannacode.test.contacts.ui.signin.UserRepository;
import com.sannacode.test.contacts.util.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Andrew on 05.01.2018.
 */

@Module
public class PersistenceModule {

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(Context mContext) {
        return Room.databaseBuilder(mContext,
                AppDatabase.class, Constants.DATABASE_NAME).build();
    }

    @Provides
    @Singleton
    UsersDao provideUsersDao(AppDatabase mDatabase) {
        return mDatabase.getUserDao();
    }

    @Provides
    @Singleton
    ContactsDao provideContactsDao(AppDatabase mDatabase) {
        return mDatabase.getContactDao();
    }

    @Provides
    @Singleton
    SignInContract.Model provideUserRepository(UsersDao mDao) {
        return new UserRepository(mDao);
    }

    @Provides
    @Singleton
    ContactsContract.Model provideContactsRepository(ContactsDao mDao) {
        return new ContactsRepository(mDao);
    }

    @Provides
    @Singleton
    AddContactContract.Model provideAddContactRepository(ContactsDao mDao) {
        return new AddContactRepository(mDao);
    }


}
