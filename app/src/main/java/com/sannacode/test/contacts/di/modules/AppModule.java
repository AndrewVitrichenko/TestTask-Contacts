package com.sannacode.test.contacts.di.modules;

import com.sannacode.test.contacts.di.scopes.ActivityScope;
import com.sannacode.test.contacts.ui.ContactsActivity;
import com.sannacode.test.contacts.ui.ContactsModule;
import com.sannacode.test.contacts.ui.signin.SignInActivity;
import com.sannacode.test.contacts.ui.signin.SignInModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Andrew on 05.01.2018.
 */

@Module(includes = {AndroidSupportInjectionModule.class, PersistenceModule.class})
public interface AppModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = {SignInModule.class})
    SignInActivity signInActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {ContactsModule.class})
    ContactsActivity contactsActivityInjector();

}
