package com.example.mycontacts;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.ContactsContract;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.ListView;

import static com.example.mycontacts.DBHandler.DATABASE_VERSION;

public class MainActivity extends AppCompatActivity {

    //start intent
    Intent intent;

    //start dbhandler
    DBHandler Dbhandler;

   MyContatcs myContatcs;

    //start shopperlist
    ListView shopperListView;

    /**
     * This method intializies the action bar and view of the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Start DbHandler
        DBHandler dbHandler = new DBHandler(this, null);

        shopperListView = (ListView) findViewById(R.id.shopperListView);

        myContatcs = new MyContatcs(this,
                dbHandler.getContact(),0);

        shopperListView.setAdapter(myContatcs);

    }

    /**
     * This method further intializes the action bar activity
     * @param menu menu resource file for the activity
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            case R.id.View_Family:
                intent = new Intent(this, ViewFamily.class);
                startActivity(intent);
                return true;
            case R.id.View_Friends:
                intent = new Intent(this, ViewFriends.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);


        }
    }

    public void openCreateList(View view) {
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}