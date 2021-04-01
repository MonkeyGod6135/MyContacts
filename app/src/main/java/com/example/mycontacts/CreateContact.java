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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateContact extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
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

    // declare Strings to store year and major selected in Spinners
    String group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //start edit texts
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        phoneEditText = (EditText) findViewById(R.id.phoneEditText);

        //start dbhanlder
        dbHandler = new DBHandler(this,null);

        // initialize Spinners
        groupSpinner = (Spinner) findViewById(R.id.qroupSpinner);

        // initialize ArrayAdapters with values in year and major string arrays
        // and stylize them with style defined by simple_spinner_item
        ArrayAdapter<CharSequence> groupAdapter = ArrayAdapter.createFromResource(this,
                R.array.group, android.R.layout.simple_spinner_item);

        // further stylize ArrayAdapters with style defined by simple_spinner_dropdown_item
        groupAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        // set ArrayAdapters on Spinners
        groupSpinner.setAdapter(groupAdapter);


        // register On Item Selected Listener to Spinners
        groupSpinner.setOnItemSelectedListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_contact, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
    public void createContact(MenuItem menuItem){
        //get data input in EditTexts and store into strings
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String phone = phoneEditText.getText().toString();

        if (name.trim().equals("") || email.trim().equals("") || group.trim().equals("") || phone.trim().equals("")) {
            Toast.makeText(this, "Please enter name, Email, Group, and Phone Number!",
                    Toast.LENGTH_LONG).show();
        } else {
            dbHandler.addContact(name, email, phone ,group);

            Toast.makeText(this, "Contact Created!",
                    Toast.LENGTH_LONG).show();

        }

    }
    /**
     * This method gets called when an item in one of the Spinners is selected.
     * @param parent Spinner AdapterView
     * @param view MainActivity view
     * @param position position of item in Spinner that was selected
     * @param id database id of item in Spinner that was selected
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // get the item selected in the Spinner and store it in String
                group = parent.getItemAtPosition(position).toString();



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}