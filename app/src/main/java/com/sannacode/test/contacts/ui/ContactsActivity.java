package com.sannacode.test.contacts.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.sannacode.test.contacts.R;
import com.sannacode.test.contacts.ui.allcontact.ContactsFragment;
import com.sannacode.test.contacts.ui.base.BaseActivity;
import com.sannacode.test.contacts.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Andrew on 05.01.2018.
 */

public class ContactsActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        String accountId = getIntent().getExtras().getString(Constants.ACCOUNT_ID);
        showFragment(ContactsFragment.newInstance(accountId));
    }

    @Override
    protected int getContainerID() {
        return R.id.fragment_main_container;
    }
}
