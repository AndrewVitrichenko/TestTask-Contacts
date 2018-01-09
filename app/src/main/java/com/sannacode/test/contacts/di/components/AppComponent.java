package com.sannacode.test.contacts.di.components;

import com.sannacode.test.contacts.di.modules.AppModule;
import com.sannacode.test.contacts.di.modules.PersistenceModule;
import com.sannacode.test.contacts.di.modules.PresentationModule;
import com.sannacode.test.contacts.ui.addcontact.AddContactFragment;
import com.sannacode.test.contacts.ui.allcontact.ContactsFragment;
import com.sannacode.test.contacts.ui.signin.SignInActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Andrew on 05.01.2018.
 */


@Component(modules = {AppModule.class, PersistenceModule.class, PresentationModule.class})
@Singleton
public interface AppComponent {

    void inject(SignInActivity target);

    void inject(ContactsFragment target);

    void inject(AddContactFragment target);

}
