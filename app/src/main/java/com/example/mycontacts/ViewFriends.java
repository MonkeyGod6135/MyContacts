package com.example.mycontacts;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class ViewFriends extends AppCompatActivity {

    //declare DBHandler
    DBHandler dbHandler;

    //declare Intent
    Intent intent;

    //declare a shopping list cursor adaptor
    FriendsContacts friendsContacts;

    //declare a listview
    ListView itemListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_friends);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //start dbhandler
        dbHandler = new DBHandler(this,null);


        //set the sub title of the viewlist activity.
        toolbar.setSubtitle("Friends");

        //initialize the listview
        itemListView = (ListView) findViewById(R.id.viewFriendsListView);

        //initialize the shoppingListItems
        friendsContacts = new FriendsContacts(this, dbHandler.getContactList( "Friends"),0);

        //set the shoppinglist items
        itemListView.setAdapter(friendsContacts);


    }
    /**
     * This method further intializes the action bar activity
     * @param menu menu resource file for the activity
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_friends, menu);
        return true;
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
}