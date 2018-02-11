package com.sannacode.test.contacts.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.sannacode.test.contacts.MainApplication;
import com.squareup.leakcanary.RefWatcher;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by Andrew on 05.01.2018.
 */

public class BaseActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }

    public void showFragment(BaseFragment f) {
        showFragment(f, f.getTag());
    }


    public void showFragment(BaseFragment f, String fragmentTag) {
        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        Fragment fragment = fm.findFragmentByTag(fragmentTag);

        if (fragment == null) {
            fragmentTransaction
                    .replace(getContainerID(), f, fragmentTag)
                    .addToBackStack(fragmentTag)
                    .commitAllowingStateLoss();
        } else {
            fm.popBackStackImmediate(fragmentTag, 0);
        }
    }

    protected int getContainerID() {
        return -1;
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = MainApplication.getRefWatcher(this);
        refWatcher.watch(this);
    }
}
