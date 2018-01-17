package com.sannacode.test.contacts.listeners;

/**
 * Created by Andrew on 17.01.2018.
 */

public interface OnUserAddedToDatabaseEvent {
    void onUserAddedToDatabaseSuccess();

    void onUserAddedToDatabaseFailure();
}
