package com.sannacode.test.contacts;

import android.app.Application;

import com.sannacode.test.contacts.di.components.AppComponent;
import com.sannacode.test.contacts.di.components.DaggerAppComponent;
import com.sannacode.test.contacts.di.modules.AppModule;
import com.sannacode.test.contacts.di.modules.PersistenceModule;
import com.sannacode.test.contacts.di.modules.PresentationModule;

/**
 * Created by Andrew on 05.01.2018.
 */

public class MainApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .persistenceModule(new PersistenceModule())
                    .presentationModule(new PresentationModule())
                    .build();
        }
        return appComponent;
    }
}
