package com.example.androidproject.ui.newmatch;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name1, String name2, String type, String localisation) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME1, name1);
        contentValue.put(DatabaseHelper.NAME2, name2);
        contentValue.put(DatabaseHelper.TYPE, type);
        contentValue.put(DatabaseHelper.LOCALISATION, localisation);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.NAME1, DatabaseHelper.NAME2, DatabaseHelper.TYPE, DatabaseHelper.LOCALISATION };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public  Cursor ExecuteQuery(String table, String projection[], String selection, String selectionArgs[], String group_rows, String filter_rows, String sortOrder){
        return dbHelper.getReadableDatabase().query(table,projection,selection,selectionArgs,group_rows,filter_rows,sortOrder);
    }



    public int update(long _id, String name1, String name2, String type, String localisation) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME1, name1);
        contentValues.put(DatabaseHelper.NAME2, name2);
        contentValues.put(DatabaseHelper.TYPE, type);
        contentValues.put(DatabaseHelper.LOCALISATION, localisation);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

}
