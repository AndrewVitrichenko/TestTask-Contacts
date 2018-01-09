package com.sannacode.test.contacts.ui.allcontact;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sannacode.test.contacts.R;
import com.sannacode.test.contacts.entity.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Andrew on 05.01.2018.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder> {

    private List<Contact> contacts;

    public ContactsAdapter() {
        contacts = new ArrayList<>();
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts.clear();
        this.contacts.addAll(contacts);
        notifyDataSetChanged();
    }

    @Override
    public ContactsAdapter.ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactsAdapter.ContactViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.setContactData(String.format(Locale.getDefault(), "%s %s", contact.getFirstName(), contact.getLastName()));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_view_name_first_letter)
        AppCompatTextView mTextViewFirstLetter;
        @BindView(R.id.text_view_contact_full_name)
        AppCompatTextView mTextViewFullName;


        public ContactViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setContactData(String fullName) {
            mTextViewFirstLetter.setText(fullName.substring(0, 1));
            mTextViewFullName.setText(fullName);
        }
    }
}
