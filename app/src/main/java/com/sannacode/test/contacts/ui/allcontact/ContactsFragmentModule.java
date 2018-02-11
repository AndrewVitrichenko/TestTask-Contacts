package com.sannacode.test.contacts.ui.allcontact;

import com.sannacode.test.contacts.di.scopes.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Andrew on 11.02.2018.
 */

@Module
public class ContactsFragmentModule {

    @Provides
    @FragmentScope
    ContactsContract.Presenter provideContactsPresenter(ContactsContract.Model mRepository) {
        return new ContactsPresenter(mRepository);
    }


}
