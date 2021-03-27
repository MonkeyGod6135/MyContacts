package com.example.mycontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    //intialize constants for db name and version
    public static final String DATABASE_NAME = "Shopper.db";
    public static final int DATABASE_VERSION = 4;

    //Intilizes constants for the shopperinglist table
    public static final String TABLE_SHOPPING_LIST = "contact";
    public static final String COLUMN_LIST_ID = "_id";
    public static final String COLUMN_LIST_NAME = "name";
    public static final String COLUMN_LIST_EMAIL = "email";
    public static final String COLUMN_LIST_PHONE = "phone";
    public static final String COLUMN_LIST_GROUP = "group_name";



    /**
     * Creates a version of the shopper database
     * @param context reference to the activity that starts dbhandler
     * @param factory null
     */
    public DBHandler(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    /**
     * Creates shopper database tables
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //define create statement for shopping list and store it in a string
        String query = "CREATE TABLE " + TABLE_SHOPPING_LIST + "( " +
                COLUMN_LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_LIST_NAME + " TEXT, " +
                COLUMN_LIST_EMAIL + " TEXT, " +
                COLUMN_LIST_GROUP + " TEXT, " +
                COLUMN_LIST_PHONE + " TEXT); ";

        //execute the statement
        sqLiteDatabase.execSQL(query);


    }

    /**
     * Creates a new version
     * @param sqLiteDatabase
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        //define drop statement and store it in a string
        String query = "DROP TABLE IF EXISTS " + TABLE_SHOPPING_LIST;

        //execute statement
        sqLiteDatabase.execSQL(query);

    }

    /**
     * This method gets called whhen the add button in the action bar of the createlist activity
     * gets clicked and makes a new row into the shopping list table
     */
    public void addContact(String name, String email, String phone, String group){
        //reference to database
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        //put data into content values
        values.put(COLUMN_LIST_NAME, name);
        values.put(COLUMN_LIST_EMAIL, email);
        values.put(COLUMN_LIST_PHONE, phone);
        values.put(COLUMN_LIST_GROUP, group);

        //insert data in Content values into shopping list table
        db.insert(TABLE_SHOPPING_LIST, null, values);

        db.close();



    }

    /**
     * This method is called when the viewlist activity is launched
     * @param group shopping list id
     * @return cursor that contains the shopping list id
     */
    public Cursor getContactList(String group){

        //reference to database
        SQLiteDatabase db = getWritableDatabase();

        //define statement and store it in string
        String query ="SELECT * FROM " + TABLE_SHOPPING_LIST +
                " WHERE " + COLUMN_LIST_GROUP + " = " + "'" + group + "'" ;

        //execute statement
        return db.rawQuery(query, null);

    }

    public Cursor getContact(){
        //reference to database
        SQLiteDatabase db = getWritableDatabase();

        //define statement and store it in string
        String query ="SELECT * FROM " + TABLE_SHOPPING_LIST;

        //execute statement
        return db.rawQuery(query, null);


    }
    public void deleteList(Integer listId){

        //reference to database
        SQLiteDatabase db = getWritableDatabase();


        //Define in string
        String query2 = "DELETE FROM " + TABLE_SHOPPING_LIST +
                "WHERE " + COLUMN_LIST_ID + " = " + listId;

        //execute
        db.execSQL(query2);

        db.close();

    }

}
