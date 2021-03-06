package com.sannacode.test.contacts.ui.signin;

import com.sannacode.test.contacts.database.dao.UsersDao;
import com.sannacode.test.contacts.entity.User;
import com.sannacode.test.contacts.listeners.DatabaseOperationListener;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Andrew on 06.01.2018.
 */

public class UserRepository implements SignInContract.Model {

    private UsersDao mDao;

    public UserRepository(UsersDao mDao) {
        this.mDao = mDao;
    }

    @Override
    public Single<User> getUserByAccountId(String accountId) {
        return mDao.getUserByAccountId(accountId);
    }

    @Override
    public void insertUser(DatabaseOperationListener databaseListener, User... mUsers) {
        Observable.just(mUsers)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> {
                    mDao.insertUsers(users);
                    databaseListener.onDatabaseOperationSucceed();
                }, throwable -> databaseListener.onDatabaseOperationFailure());
    }
}
