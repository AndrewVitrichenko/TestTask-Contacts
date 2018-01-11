package com.sannacode.test.contacts.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.sannacode.test.contacts.entity.User;

import io.reactivex.Single;


/**
 * Created by Andrew on 05.01.2018.
 */
@Dao
public interface UsersDao {

    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM users WHERE accountId LIKE :accountId")
    Single<User> getUserByAccountId(String accountId);
}
