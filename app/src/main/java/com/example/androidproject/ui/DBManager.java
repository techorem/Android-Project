package com.example.androidproject.ui;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DBManager {

    public DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    DBManager(Context c) {
        context = c;
    }

    DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    void insert(String[] texts, int[] data, String[] Photos) {

        // get current date //
        Date c = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        String formattedDate = df.format(c);

        /// retrieve Stats in a String ///
        String Statistics = "";
        for (int i = 1 ; i < 8 ; i++ )
            Statistics = Statistics + data[i] + "-";
        Statistics = Statistics + data[8];

        /// retrieve Photo Addresses in a String ///
        String addresses = "";
        if (data[0] > 0) {
            for (int i = 0; i < data[0] - 1; i++)
                addresses = addresses + Photos[i] + "-";
            addresses = addresses + Photos[data[0] - 1];
        }

        /// prepare all the database info ///

        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME1, texts[0]);
        contentValue.put(DatabaseHelper.NAME2, texts[1]);
        contentValue.put(DatabaseHelper.TYPE, texts[2]);
        contentValue.put(DatabaseHelper.LOCALISATION, texts[3]);
        contentValue.put(DatabaseHelper.DATE, formattedDate);
        contentValue.put(DatabaseHelper.STATS, Statistics);
        contentValue.put(DatabaseHelper.PHOTONUMBER, data[0]);
        contentValue.put(DatabaseHelper.PHOTOADRESSES, addresses);

        /// write in the database ///

        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.NAME1, DatabaseHelper.NAME2, DatabaseHelper.TYPE, DatabaseHelper.LOCALISATION, DatabaseHelper.DATE, DatabaseHelper.STATS, DatabaseHelper.PHOTONUMBER, DatabaseHelper.PHOTOADRESSES};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public  Cursor ExecuteQuery(String table, String[] projection, String selection, String[] selectionArgs, String group_rows, String filter_rows, String sortOrder){
        return dbHelper.getReadableDatabase().query(table,projection,selection,selectionArgs,group_rows,filter_rows,sortOrder);
    }

    /// Unused ( Yet ) ///

    /*public int update(long _id, String name1, String name2, String type, String localisation) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME1, name1);
        contentValues.put(DatabaseHelper.NAME2, name2);
        contentValues.put(DatabaseHelper.TYPE, type);
        contentValues.put(DatabaseHelper.LOCALISATION, localisation);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }*/

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

}
