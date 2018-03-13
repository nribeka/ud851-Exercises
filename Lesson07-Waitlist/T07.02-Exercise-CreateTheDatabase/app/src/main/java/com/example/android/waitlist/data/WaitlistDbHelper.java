package com.example.android.waitlist.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// DONE (1) extend the SQLiteOpenHelper class
public class WaitlistDbHelper extends SQLiteOpenHelper {

    // DONE (2) Create a static final String called DATABASE_NAME and set it to "waitlist.db"
    private static final String DATABASE_NAME = "waitlist.db";

    // DONE (3) Create a static final int called DATABASE_VERSION and set it to 1
    private static final int DATABASE_VERSION = 1;

    // DONE (4) Create a Constructor that takes a context and calls the parent constructor
    public WaitlistDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // DONE (5) Override the onCreate method
    @Override
    public void onCreate(SQLiteDatabase db) {
        // DONE (6) Inside, create an String query called SQL_CREATE_WAITLIST_TABLE that will create the table
        String query = "CREATE TABLE " + WaitlistContract.WaitlistEntry.TABLE_NAME + " ("
                + WaitlistContract.WaitlistEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME + " TEXT NOT NULL, "
                + WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE + " INTEGER NOT NULL, "
                + WaitlistContract.WaitlistEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                + ")";
        // DONE (7) Execute the query by calling execSQL on sqLiteDatabase and pass the string query SQL_CREATE_WAITLIST_TABLE
        db.execSQL(query);
    }

    // DONE (8) Override the onUpgrade method
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + WaitlistContract.WaitlistEntry.TABLE_NAME;
        // DONE (9) Inside, execute a drop table query, and then call onCreate to re-create it
        db.execSQL(query);
        onCreate(db);
    }

}