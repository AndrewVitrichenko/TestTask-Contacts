package com.sannacode.test.contacts.ui.addcontact;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.METValidator;
import com.sannacode.test.contacts.R;
import com.sannacode.test.contacts.ui.ContactsActivity;
import com.sannacode.test.contacts.ui.base.BaseFragment;
import com.sannacode.test.contacts.util.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Andrew on 05.01.2018.
 */

public class AddContactFragment extends BaseFragment implements AddContactContract.View {


    @BindView(R.id.edit_text_first_name)
    MaterialEditText mEditTextFirstName;
    @BindView(R.id.edit_text_last_name)
    MaterialEditText mEditTextLastName;
    @BindView(R.id.edit_text_email)
    MaterialEditText mEditTextEmail;

    @Inject
    AddContactContract.Presenter mPresenter;

    private ContactsActivity mContactActivity;
    private String accountId;


    public static AddContactFragment newInstance(String accountId) {
        AddContactFragment addContactFragment = new AddContactFragment();
        Bundle arguments = new Bundle();
        arguments.putString(Constants.ACCOUNT_ID, accountId);
        addContactFragment.setArguments(arguments);
        return addContactFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            accountId = getArguments().getString(Constants.ACCOUNT_ID);
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContactActivity = (ContactsActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_contact, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        setTitle(R.string.title_add_contact);
        addValidators();
        return rootView;
    }

    private void addValidators() {
        METValidator nameValidator = new METValidator(getString(R.string.error_incorrect_user_name)) {
            @Override
            public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
                String enteredText = text.toString().trim();
                return enteredText.length() <= 30 && enteredText.length() >= 2;
            }
        };
        mEditTextFirstName.addValidator(nameValidator);
        mEditTextLastName.addValidator(nameValidator);
        mEditTextEmail.addValidator(new METValidator(getString(R.string.error_incorrect_email)) {
            @Override
            public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
                return android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.bindView(this);
        mPresenter.onRestoreInstanceState();
    }

    @OnClick(R.id.button_save_contact)
    public void saveContact() {
        if (mEditTextFirstName.validate() && mEditTextLastName.validate() && mEditTextEmail.validate()) {
            mPresenter.onSaveContactButtonClickedEvent(accountId
                    , mEditTextFirstName.getText().toString().trim()
                    , mEditTextLastName.getText().toString().trim()
                    , mEditTextEmail.getText().toString().trim());
        }
    }


    @Override
    public void showSaveContactMessage() {
        getActivity().runOnUiThread(() -> {
            showMessage(R.string.title_contact_saved);
            mContactActivity.onBackPressed();
        });
    }

    @Override
    public void showMessage(int messageId) {
        getActivity().runOnUiThread(() ->
                Toast.makeText(mContactActivity, messageId, Toast.LENGTH_SHORT).show());
    }

    @Override
    public void setSavedValues(String firstName, String lastName, String email) {
        mEditTextFirstName.setText(firstName);
        mEditTextLastName.setText(lastName);
        mEditTextEmail.setText(email);
    }


    @Override
    public void onStop() {
        super.onStop();
        hideKeyboardIfVisible();
        mPresenter.unbindView();
    }

    private void hideKeyboardIfVisible() {
        InputMethodManager mInputMethodManager = (InputMethodManager)
                mContactActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (mInputMethodManager != null) {
            if (mInputMethodManager.isActive()) {
                View focus = mContactActivity.getCurrentFocus();
                if (focus != null) {
                    mInputMethodManager.hideSoftInputFromWindow(focus.getWindowToken(), 0);
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onSaveInstanceState(mEditTextFirstName.getText().toString()
                , mEditTextLastName.getText().toString(), mEditTextEmail.getText().toString());
    }
}
