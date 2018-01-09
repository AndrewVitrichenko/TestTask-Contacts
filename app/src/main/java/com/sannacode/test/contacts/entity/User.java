package com.sannacode.test.contacts.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Andrew on 05.01.2018.
 */
@Entity(tableName = "users")
public class User {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String accountId = "";
    private String fullName = "";

    public User() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
