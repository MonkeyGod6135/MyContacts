package com.example.mycontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    //intialize constants for db name and version
    public static final String DATABASE_NAME = "Shopper.db";
    public static final int DATABASE_VERSION = 2;

    //Intilizes constants for the shopperinglist table
    public static final String TABLE_SHOPPING_LIST = "Contact";
    public static final String COLUMN_LIST_ID = "_id";
    public static final String COLUMN_LIST_NAME = "name";
    public static final String COLUMN_LIST_EMAIL = "email";
    public static final String COLUMN_LIST_PHONE = "phone";
    public static final String COLUMN_LIST_GROUP = "group";

    //Intilizes constants for the shopperinglistitem table
    public static final String TABLE_SHOPPING_LIST_ITEM = "shoppinglistitem";
    public static final String COLUMN_ITEM_ID = "_id";
    public static final String COLUMN_ITEM_NAME = "name";
    public static final String COLUMN_ITEM_EMAIL = "email";
    public static final String COLUMN_ITEM_PHONE = "phone";
    public static final String COLUMN_ITEM_GROUP = "group";
    public static final String COLUMN_ITEM_LIST_ID = "list_id";


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
                COLUMN_LIST_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_LIST_NAME + " TEXT, " +
                COLUMN_LIST_EMAIL + " TEXT, " +
                COLUMN_LIST_GROUP + " TEXT, " +
                COLUMN_LIST_PHONE + " TEXT); ";

        //execute the statement
        sqLiteDatabase.execSQL(query);

        //define create statement for shopping list and store it in a string
        String query2 = "CREATE TABLE " + TABLE_SHOPPING_LIST_ITEM + "(" +
                COLUMN_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ITEM_NAME + " TEXT, " +
                COLUMN_ITEM_EMAIL + " TEXT, " +
                COLUMN_ITEM_PHONE + " TEXT, " +
                COLUMN_ITEM_GROUP + " TEXT, " +
                COLUMN_ITEM_LIST_ID + " INTEGER);";

        //execute the statement
        sqLiteDatabase.execSQL(query2);

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
    public void addContact(String name, String email, String phone){
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

    public Cursor getContact(){
        //reference to database
        SQLiteDatabase db = getWritableDatabase();

        //define statement and store it in string
        String query ="SELECT * FROM " + TABLE_SHOPPING_LIST;

        //execute statement
        return db.rawQuery(query, null);


    }

}
