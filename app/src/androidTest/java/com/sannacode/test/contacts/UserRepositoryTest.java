package com.sannacode.test.contacts;

import android.arch.persistence.room.EmptyResultSetException;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;

import com.sannacode.test.contacts.database.AppDatabase;
import com.sannacode.test.contacts.entity.User;
import com.sannacode.test.contacts.listeners.DatabaseOperationListener;
import com.sannacode.test.contacts.ui.signin.SignInContract;
import com.sannacode.test.contacts.ui.signin.UserRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Andrew on 17.01.2018.
 */

public class UserRepositoryTest {

    private AppDatabase mDatabase;
    private SignInContract.Model mUserRepository;
    private User mTestUser = new User(1, "1234", "Test Testerov");

    @Before
    public void initDb() throws Exception {
        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                AppDatabase.class)
                .build();
        mUserRepository = new UserRepository(mDatabase.getUserDao());
    }

    @After
    public void closeDb() throws Exception {
        mDatabase.close();
    }


    @Test
    public void insertAndGetUserTest() {
        mUserRepository.insertUser(new DatabaseOperationListener() {
            @Override
            public void onDatabaseOperationSucceed() {
                mUserRepository.getUserByAccountId(mTestUser.getAccountId())
                        .test()
                        .assertValue(user -> user != null && user.getId() == (mTestUser.getId()) &&
                                user.getAccountId().equals(mTestUser.getAccountId()) &&
                                user.getFullName().equals(mTestUser.getFullName()));
            }

            @Override
            public void onDatabaseOperationFailure() {
                mUserRepository.getUserByAccountId(mTestUser.getAccountId())
                        .test()
                        .assertError(new EmptyResultSetException("No results!"));
            }
        }, mTestUser);
    }


}
