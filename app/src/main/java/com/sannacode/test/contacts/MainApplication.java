package com.sannacode.test.contacts;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.sannacode.test.contacts.di.components.DaggerAppComponent;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by Andrew on 05.01.2018.
 */

public class MainApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;


    public static RefWatcher getRefWatcher(Context context) {
        MainApplication application = (MainApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        refWatcher = LeakCanary.install(this);
        DaggerAppComponent
                .builder()
                .context(this)
                .build()
                .inject(this);

    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }


}
