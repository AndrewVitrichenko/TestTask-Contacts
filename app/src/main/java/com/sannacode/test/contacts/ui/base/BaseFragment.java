package com.sannacode.test.contacts.ui.base;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import butterknife.Unbinder;

/**
 * Created by Andrew on 05.01.2018.
 */

public class BaseFragment extends Fragment {

    protected Unbinder unbinder;

    public void setTitle(@StringRes int title) {
        android.support.v7.app.ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }
    }

    @Override
    public void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }

}
