package com.example.mycontacts;

import android.content.Context;
import android.database.Cursor;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class FriendsContacts extends CursorAdapter {
    public FriendsContacts(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.li_view_friends_list,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ((TextView) view.findViewById(R.id.nameTextView)).
                setText(cursor.getString(cursor.getColumnIndex("name")));
        ((TextView) view.findViewById(R.id.emailTextView)).
                setText(cursor.getString(cursor.getColumnIndex("email")));
        ((TextView) view.findViewById(R.id.phoneTextView)).
                setText(cursor.getString(cursor.getColumnIndex("phone")));

    }
}