package com.sannacode.test.contacts.ui;

import com.sannacode.test.contacts.di.scopes.FragmentScope;
import com.sannacode.test.contacts.ui.addcontact.AddContactFragment;
import com.sannacode.test.contacts.ui.addcontact.AddContactModule;
import com.sannacode.test.contacts.ui.allcontact.ContactsFragment;
import com.sannacode.test.contacts.ui.allcontact.ContactsFragmentModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Andrew on 11.02.2018.
 */
@Module
public interface ContactsModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = ContactsFragmentModule.class)
    ContactsFragment contactsFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = AddContactModule.class)
    AddContactFragment addContactFragment();

}
