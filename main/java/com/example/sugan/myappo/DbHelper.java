package com.example.sugan.myappo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sugan on 20/07/16.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static DbHelper dbHelper;

    public static final String DATABASE_NAME = "appointment.db";
    public static final int DATABASE_VERSION = 1;

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + AppointmentContract.FeedEntry.TABLE_NAME + " (" +
                    AppointmentContract.FeedEntry._ID + " INTEGER PRIMARY KEY, " +
                    AppointmentContract.FeedEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA +
                    AppointmentContract.FeedEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA +
                    AppointmentContract.FeedEntry.COLUMN_NAME_LASTNAME + TEXT_TYPE + COMMA +
                    AppointmentContract.FeedEntry.COLUMN_NAME_GENDER + TEXT_TYPE + COMMA +
                    AppointmentContract.FeedEntry.COLUMN_NAME_STREET + TEXT_TYPE + COMMA +
                    AppointmentContract.FeedEntry.COLUMN_NAME_CITY + TEXT_TYPE + COMMA +
                    AppointmentContract.FeedEntry.COLUMN_NAME_ZIP + TEXT_TYPE + COMMA +
                    AppointmentContract.FeedEntry.COLUMN_NAME_COUNTRY + TEXT_TYPE + COMMA +
                    AppointmentContract.FeedEntry.COLUMN_NAME_PHONE + TEXT_TYPE + COMMA +
                    AppointmentContract.FeedEntry.COLUMN_NAME_EMAIL + TEXT_TYPE + COMMA +
                    AppointmentContract.FeedEntry.COLUMN_NAME_DATE + TEXT_TYPE + COMMA +
                    AppointmentContract.FeedEntry.COLUMN_NAME_TIME + TEXT_TYPE +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + AppointmentContract.FeedEntry.TABLE_NAME;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

//    public static synchronized DbHelper getInstance(Context context){
//        if(dbHelper == null){
//            dbHelper = new DbHelper(context);
//        }
//        return dbHelper;
//    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        database.execSQL(SQL_DELETE_ENTRIES);
        onCreate(database);
    }

    public void deleteTable(SQLiteDatabase database){
        database.execSQL(SQL_DELETE_ENTRIES);
    }



}
