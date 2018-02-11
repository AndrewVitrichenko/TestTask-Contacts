package com.sannacode.test.contacts.ui.base;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.sannacode.test.contacts.MainApplication;
import com.squareup.leakcanary.RefWatcher;

import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

/**
 * Created by Andrew on 05.01.2018.
 */

public class BaseFragment extends Fragment {

    protected Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

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
        RefWatcher refWatcher = MainApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }

}
