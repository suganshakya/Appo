package com.example.sugan.myappo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sugan on 20/07/16.
 */
public class AppointmentDatabaseHelper extends SQLiteOpenHelper {
//    private static int COUNT = 0;

    private static AppointmentDatabaseHelper instance;

    private static final String DATABASE = "appointment.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "entry";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA = ",";
    
    private final static String COLUMN_ID = "id";
    private final static String COLUMN_NAME = "name";
    private final static String COLUMN_SURNAME = "surname";
    private final static String COLUMN_GENDER = "gender";
    private final static String COLUMN_STREET = "street";
    private final static String COLUMN_CITY = "city";
    private final static String COLUMN_ZIPCODE = "zipcode";
    private final static String COLUMN_COUNTRY = "country";
    private final static String COLUMN_PHONE = "phone";
    private final static String COLUMN_EMAIL = "email";
    private final static String COLUMN_DATE = "date";
    private final static String COLUMN_TIME = "time";

    public static String [] projection ={
            COLUMN_ID, COLUMN_NAME, COLUMN_SURNAME, COLUMN_GENDER, COLUMN_STREET, COLUMN_CITY,
            COLUMN_ZIPCODE, COLUMN_COUNTRY, COLUMN_PHONE, COLUMN_EMAIL, COLUMN_DATE, COLUMN_TIME
    };

    private static final String TAG = "tag";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY, " + // Define as primary key
                    COLUMN_NAME + TEXT_TYPE + COMMA +
                    COLUMN_SURNAME + TEXT_TYPE + COMMA +
                    COLUMN_GENDER + TEXT_TYPE + COMMA +
                    COLUMN_STREET + TEXT_TYPE + COMMA +
                    COLUMN_CITY + TEXT_TYPE + COMMA +
                    COLUMN_ZIPCODE + TEXT_TYPE + COMMA +
                    COLUMN_COUNTRY + TEXT_TYPE + COMMA +
                    COLUMN_PHONE + TEXT_TYPE + COMMA +
                    COLUMN_EMAIL + TEXT_TYPE + COMMA +
                    COLUMN_DATE + TEXT_TYPE + COMMA +
                    COLUMN_TIME + TEXT_TYPE +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    private AppointmentDatabaseHelper(Context context) {
        super(context, DATABASE, null, DATABASE_VERSION);
    }

    public static synchronized AppointmentDatabaseHelper getInstance(Context context){
        if(instance == null){
            instance = new AppointmentDatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            database.execSQL(SQL_DELETE_ENTRIES);
            onCreate(database);
        }
    }

    public void deleteTable(SQLiteDatabase database){
        database.execSQL(SQL_DELETE_ENTRIES);
    }

    public long addAppointment(Appointment appointment){
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        long value=-1;
        try{
            ContentValues contentValues = new ContentValues();
            // ID is not set here
//            contentValues.put(projection[0], ++COUNT);
            for(int i = 1; i < projection.length; ++i){
                contentValues.put(projection[i], appointment.getValue(i));
            }
            value = db.insertOrThrow(TABLE_NAME, null, contentValues);
            db.setTransactionSuccessful();
        } catch (SQLException e){
            Log.d(TAG, "Error adding appointment to database");
        } finally {
            db.endTransaction();
        }
        return value;
    }

    public List<Appointment> getAppointments (String selection, String [] selectionArgs){ // null, null return all
        List<Appointment> appointmentList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        db.beginTransaction();
        try {
            Cursor cursor = db.query(TABLE_NAME, projection, selection, selectionArgs,
                    null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    Appointment appointment = new Appointment();
                    for (int i = 0; i < projection.length; ++i) {
                        appointment.setValue(i, cursor.getString(cursor.getColumnIndexOrThrow(projection[i])));
                    }
                    appointmentList.add(appointment);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            Log.d(TAG,"Error reading database");
        } finally {
            db.endTransaction();
            db.close();
        }
        return appointmentList;
    }

    public List<String> getColumnValue(String column){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> results = new ArrayList<>();

        Cursor cursor = db.query(true, TABLE_NAME, new String [] {column},
                null, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                results.add(cursor.getString(cursor.getColumnIndexOrThrow(column)));
            } while (cursor.moveToNext());
        }
        db.close();
        return results;
    }

    public long updateAppointment(int id, Appointment appointment){
        SQLiteDatabase db = getWritableDatabase();
        long appointmentId  = -1;
        db.beginTransaction();
        try{
            ContentValues contentValues = new ContentValues();
            for(int i = 1; i < projection.length; ++i){
                contentValues.put(projection[i], appointment.getValue(i));
            }
            int rows = db.update(TABLE_NAME, contentValues, COLUMN_ID + " LIKE ?",
                    new String[]{String.valueOf(id)});
            if (rows == 1){
                db.setTransactionSuccessful();
                appointmentId =0;
            }
        } catch (Exception e){
            Log.d(TAG, "Error Updating Appointment");

        } finally {
            db.endTransaction();
        }
        return appointmentId;
    }

    public void deleteAppointment(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete(TABLE_NAME, COLUMN_ID + " = ?" , new String[] {String.valueOf(id)});
            db.setTransactionSuccessful();
        } catch (Exception e){
            Log.d(TAG, "Error deleting Appointment");
        } finally {
            db.endTransaction();
        }
    }
}
