package com.sannacode.test.contacts.ui.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Andrew on 05.01.2018.
 */

public class BaseActivity extends AppCompatActivity {

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
}
