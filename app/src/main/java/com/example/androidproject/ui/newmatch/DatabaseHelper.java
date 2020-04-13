package com.example.androidproject.ui.newmatch;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "MATCHES";

    // Table columns
    public static final String _ID = "_id";
    public static final String NAME1 = "name1";
    public static final String NAME2 = "name2";
    public static final String TYPE = "type";
    public static final String LOCALISATION = "localisation";


    // Database Information
    static final String DB_NAME = "MYFIGHTS.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME1 + " TEXT NOT NULL, " +
            NAME2 + " TEXT NOT NULL, " +
            TYPE + " TEXT, " +
            LOCALISATION + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

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
