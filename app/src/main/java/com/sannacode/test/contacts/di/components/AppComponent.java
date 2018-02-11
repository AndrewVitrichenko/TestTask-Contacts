package com.sannacode.test.contacts.di.components;

import android.content.Context;

import com.sannacode.test.contacts.MainApplication;
import com.sannacode.test.contacts.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by Andrew on 05.01.2018.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder context(Context context);

        AppComponent build();
    }

    void inject(MainApplication app);

}
