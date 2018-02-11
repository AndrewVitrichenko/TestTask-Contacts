package com.sannacode.test.contacts.ui.allcontact;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.sannacode.test.contacts.R;
import com.sannacode.test.contacts.entity.Contact;
import com.sannacode.test.contacts.entity.SortType;
import com.sannacode.test.contacts.ui.ContactsActivity;
import com.sannacode.test.contacts.ui.addcontact.AddContactFragment;
import com.sannacode.test.contacts.ui.base.BaseFragment;
import com.sannacode.test.contacts.ui.signin.SignInActivity;
import com.sannacode.test.contacts.util.Constants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Andrew on 05.01.2018.
 */

public class ContactsFragment extends BaseFragment implements ContactsContract.View {

    @BindView(R.id.text_view_no_contacts)
    AppCompatTextView mTextViewNoContacts;
    @BindView(R.id.recycler_view_contacts)
    RecyclerView mRecyclerView;
    @BindView(R.id.floating_action_button)
    FloatingActionButton mFab;

    @Inject
    ContactsContract.Presenter mPresenter;

    private ContactsActivity mContactActivity;
    private ContactsAdapter mAdapter;
    private String accountId;

    public static ContactsFragment newInstance(String accountId) {
        ContactsFragment contactsFragment = new ContactsFragment();
        Bundle arguments = new Bundle();
        arguments.putString(Constants.ACCOUNT_ID, accountId);
        contactsFragment.setArguments(arguments);
        return contactsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            accountId = getArguments().getString(Constants.ACCOUNT_ID);
        }
        mAdapter = new ContactsAdapter();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_contacts, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                createLogoutDialog();
                break;
            case R.id.action_sort_by_name:
                mPresenter.loadContacts(accountId, SortType.BY_NAME);
                break;
            case R.id.action_sort_by_email:
                mPresenter.loadContacts(accountId, SortType.BY_EMAIL);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContactActivity = (ContactsActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contacts, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        setTitle(R.string.app_name);
        initRecyclerView();
        return rootView;
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContactActivity));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && mFab.getVisibility() == View.VISIBLE) {
                    mFab.hide();
                } else if (dy < 0 && mFab.getVisibility() != View.VISIBLE) {
                    mFab.show();
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.bindView(this);
        mPresenter.loadContacts(accountId, SortType.DEFAULT);
    }

    @Override
    public void showNoContactsMessage() {
        mTextViewNoContacts.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(@StringRes int messageId) {
        Toast.makeText(mContactActivity, messageId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showContacts(List<Contact> contacts) {
        mTextViewNoContacts.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mAdapter.setContacts(contacts);
    }


    @OnClick(R.id.floating_action_button)
    public void addContact() {
        mContactActivity.showFragment(AddContactFragment.newInstance(accountId));
    }

    private void createLogoutDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContactActivity);
        dialog.setTitle(R.string.title_logout)
                .setIcon(R.mipmap.ic_launcher)
                .setMessage(R.string.question_logout)
                .setPositiveButton(android.R.string.ok, (dialoginterface, i) -> {
                    mPresenter.onLogoutClickEvent();
                    dialoginterface.cancel();
                })
                .setNegativeButton(R.string.title_later, (dialogInterface, i) -> dialogInterface.cancel())
                .setCancelable(true)
                .create()
                .show();
    }

    @Override
    public GoogleSignInClient getGoogleClient(GoogleSignInOptions gso) {
        return GoogleSignIn.getClient(mContactActivity, gso);
    }


    @Override
    public void performLogout() {
        Intent intent = new Intent(mContactActivity, SignInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContactActivity.startActivity(intent);
        mContactActivity.finish();
    }


    @Override
    public void onStop() {
        super.onStop();
        mPresenter.unbindView();
    }

}
