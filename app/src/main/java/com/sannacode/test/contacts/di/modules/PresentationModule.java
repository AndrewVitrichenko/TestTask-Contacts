package com.sannacode.test.contacts.di.modules;

import com.sannacode.test.contacts.ui.addcontact.AddContactContract;
import com.sannacode.test.contacts.ui.addcontact.AddContactPresenter;
import com.sannacode.test.contacts.ui.allcontact.ContactsContract;
import com.sannacode.test.contacts.ui.allcontact.ContactsPresenter;
import com.sannacode.test.contacts.ui.signin.SignInContract;
import com.sannacode.test.contacts.ui.signin.SignInPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Andrew on 05.01.2018.
 */
@Module
public class PresentationModule {

    @Provides
    @Singleton
    SignInContract.Presenter provideSignInPresenter(SignInContract.Model mRepository) {
        return new SignInPresenter(mRepository);
    }

    @Provides
    @Singleton
    ContactsContract.Presenter provideContactsPresenter(ContactsContract.Model mRepository) {
        return new ContactsPresenter(mRepository);
    }

    @Provides
    @Singleton
    AddContactContract.Presenter provideAddContactPresenter(AddContactContract.Model mRepository){
        return new AddContactPresenter(mRepository);
    }


}
