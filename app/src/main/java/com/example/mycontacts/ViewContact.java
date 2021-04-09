package com.example.mycontacts;

import android.app.Notification;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import static com.example.mycontacts.App.CHANNEL_CONTACT_ID;

public class ViewContact extends AppCompatActivity {
    //declare intent
    Intent intent;

    //declare edittexts
    EditText nameEditText;
    EditText emailEditText;
    EditText phoneEditText;

    //declare Dbhandler
    DBHandler dbHandler;

    //declare spinner
    Spinner groupSpinner;

    // declare String
    String group;

    //declare bundle and long for data
    Bundle bundle;
    long id;

    String name;
    String email;
    String phone;

    //declare a shopping list cursor adaptor
    FamilyContacts familyContacts;

    //declare a listview
    ListView itemListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //start bundle
        bundle = this.getIntent().getExtras();

        //use Bundle to get id
        id = bundle.getLong("_id");

        //start dbhandler
        dbHandler = new DBHandler(this, null);

        //start Edittexts
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        phoneEditText = (EditText) findViewById(R.id.phoneEditText);

        //disablded edit texts
        nameEditText.setEnabled(false);
        emailEditText.setEnabled(false);
        phoneEditText.setEnabled(false);

        //call the dbhandler getShoppinglistItem
        Cursor cursor = dbHandler.getContact((int) id);


        //move to first row in the cursor
        cursor.moveToFirst();

        //get data and store into strings
        name = cursor.getString(cursor.getColumnIndex("name"));
        email = cursor.getString(cursor.getColumnIndex("email"));
        phone = cursor.getString(cursor.getColumnIndex("phone"));

        //set values in edit text
        nameEditText.setText(name);
        emailEditText.setText(email);
        phoneEditText.setText(phone);

        //initialize the listview
        itemListView = (ListView) findViewById(R.id.viewFamilyListView);

        //initialize the shoppingListItems
        familyContacts = new FamilyContacts(this, dbHandler.getContactList(group),0);

        //set the shoppinglist items
        itemListView.setAdapter(familyContacts);

        //register an OnItemClickListener on the viewFamily
        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * This method is called when an item on the listview is called
             * @param parent itemListView
             * @param view View contact view
             * @param position of clicked item
             * @param id database id of the clicked item
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                updateContact(id);

                //start an intent
                intent = new Intent(ViewContact.this, ViewContact.class);

                //put the database id of the clicked item
                intent.putExtra("_id",id);

                //start activity
                startActivity(intent);
            }
        });



    }

    /**
     * This method is called when a menu item in a overflow menu is selected
     * and it controls what happens when it is selected
     * @param item selected item in overflow menu
     * @return true if menu item is selected, else false
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_contact:
                intent = new Intent(this, ViewContact.class);
                startActivity(intent);
            case R.id.action_Home:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_create_list:
                intent = new Intent(this, CreateContact.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);


        }
    }

    public void deleteContact(MenuItem menuItem){
        //delete contact from the database
        dbHandler.deleteContact((int) id);

        //display Item deleted! toast
        Toast.makeText(this,"Item deleted!", Toast.LENGTH_LONG).show();

        //initialize notification
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_CONTACT_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Contact")
                .setContentText( Contact + "completed!").build();
    }

    public void updateContact(long id){
        dbHandler.updateContact((int)id);

        //refresh listview with updated data
        itemListViewAdapter.swapCursor(dbHandler.getContact((int)this.id));

    }
}