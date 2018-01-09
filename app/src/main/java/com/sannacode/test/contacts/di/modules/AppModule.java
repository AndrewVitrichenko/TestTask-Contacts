package com.sannacode.test.contacts.di.modules;

import android.content.Context;

import com.sannacode.test.contacts.MainApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Andrew on 05.01.2018.
 */

@Module
public class AppModule {

    private final MainApplication mApplication;

    public AppModule(MainApplication mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mApplication.getApplicationContext();
    }


}
