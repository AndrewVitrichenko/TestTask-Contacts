package com.sannacode.test.contacts.ui.signin;

import com.sannacode.test.contacts.di.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Andrew on 11.02.2018.
 */
@Module
public class SignInModule {

    @Provides
    @ActivityScope
    SignInContract.Presenter provideSignInPresenter(SignInContract.Model mRepository) {
        return new SignInPresenter(mRepository);
    }

}
