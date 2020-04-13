package com.example.androidproject.ui;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    static final String TABLE_NAME = "MyMatches";

    // Table columns
    static final String _ID = "_id";
    static final String NAME1 = "name1";
    static final String NAME2 = "name2";
    static final String TYPE = "type";
    static final String LOCALISATION = "localisation";
    static final String DATE = "date";
    static final String STATS = "stats";
    static final String PHOTONUMBER = "PhotoNB";
    static final String PHOTOADRESSES = "PhotoAddresses";

    // Database Information
    private static final String DB_NAME = "MYFIGHTS.DB";

    // database version
    private static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME1 + " TEXT NOT NULL, " +
            NAME2 + " TEXT NOT NULL, " +
            TYPE + " TEXT, " +
            LOCALISATION + " TEXT, " +
            DATE + " DATE, " +
            STATS + " TEXT, " +
            PHOTONUMBER + " INT, " +
            PHOTOADRESSES + " TEXT);";

    DatabaseHelper(Context context) { super(context, DB_NAME, null, DB_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
