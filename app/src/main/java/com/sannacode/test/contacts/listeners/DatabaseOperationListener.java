package com.sannacode.test.contacts.listeners;

/**
 * Created by Andrew on 18.01.2018.
 */

public interface DatabaseOperationListener {

    void onDatabaseOperationSucceed();

    void onDatabaseOperationFailure();
}
