package com.sannacode.test.contacts.ui.addcontact;

import com.sannacode.test.contacts.di.scopes.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Andrew on 11.02.2018.
 */
@Module
public class AddContactModule {

    @Provides
    @FragmentScope
    AddContactContract.Presenter provideAddContactPresenter(AddContactContract.Model mRepository) {
        return new AddContactPresenter(mRepository);
    }
}
