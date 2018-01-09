package com.sannacode.test.contacts.ui.signin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.sannacode.test.contacts.MainApplication;
import com.sannacode.test.contacts.R;
import com.sannacode.test.contacts.ui.ContactsActivity;
import com.sannacode.test.contacts.util.Constants;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity implements SignInContract.View {


    @Inject
    SignInContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ((MainApplication) getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.bindView(this);
        mPresenter.checkIsUserAuthorized();
    }

    @Override
    public GoogleSignInAccount getLastSignedAccount() {
        return GoogleSignIn.getLastSignedInAccount(this);
    }

    @Override
    public GoogleSignInClient getGoogleClient(GoogleSignInOptions gso) {
        return GoogleSignIn.getClient(this, gso);
    }

    @Override
    public void startSignInIntent(Intent signInIntent) {
        startActivityForResult(signInIntent, Constants.CODE_SIGN_IN);
    }

    @Override
    public void showMessage(@StringRes int messageId) {
        Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startContactsActivity(GoogleSignInAccount account) {
        Intent openContactsIntent = new Intent(this, ContactsActivity.class);
        openContactsIntent.putExtra(Constants.ACCOUNT_ID, account.getId());
        openContactsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(openContactsIntent);
        finish();
    }

    @OnClick(R.id.sign_in_button)
    public void signIn() {
        mPresenter.onSignInButtonClickEvent();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.CODE_SIGN_IN) {
            mPresenter.handleSignInResult(data);
        }
    }

    @Override
    protected void onStop() {
        mPresenter.unbindView();
        super.onStop();
    }
}
