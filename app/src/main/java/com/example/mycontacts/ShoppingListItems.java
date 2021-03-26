package com.example.mycontacts;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class ShoppingListItems extends CursorAdapter {
    /**
     *
     * @param context reference to the activity that initializes the shoppinglistitem cursoradapter
     * @param c reference to the cursor that contains the data selected
     * @param flags determines special behavior of the cursoradapter
     */
    public ShoppingListItems(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    /**
     *
     * @param context reference to the activity that initializes the shoppinglistitem cursoradapter
     * @param cursor reference to the cursor that contains the data selected
     * @param parent reference
     * @return
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.li_item_list,parent,false);
    }

    /**
     * Bind new view to data in cursor
     * @param view reference to view
     * @param context
     * @param cursor
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ((TextView) view.findViewById(R.id.nameTextView)).
                setText(cursor.getString(cursor.getColumnIndex("name")));
        ((TextView) view.findViewById(R.id.emailTextView)).
                setText(cursor.getString(cursor.getColumnIndex("email")));
        ((TextView) view.findViewById(R.id.phoneTextView)).
                setText(cursor.getString(cursor.getColumnIndex("phone")));
        ((TextView) view.findViewById(R.id.groupTextView)).
                setText(cursor.getString(cursor.getColumnIndex("group")));

    }
}
