package com.example.sugan.myappo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by sugan on 22/07/16.
 */
public class DatabaseContentProvider extends ContentProvider {
    DbHelper dbHelper;
    public static final String AUTHORITY = "com.example.sugan.myappo.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    SQLiteDatabase database;


    @Override
    public boolean onCreate() {
        dbHelper = new DbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        database = dbHelper.getReadableDatabase();
        String tableName = getTableName(uri);
        Cursor cursor = database.query(tableName, projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }

    private String getTableName(Uri uri) {
        return AppointmentContract.FeedEntry.TABLE_NAME;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        database = dbHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        long value = database.insert(tableName, null, contentValues);
        return Uri.withAppendedPath(CONTENT_URI, String.valueOf(value));
    }

    @Override
    public int delete(Uri uri, String where, String[] args) {
        database = dbHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        return database.delete(tableName, where, args);
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String whereClause, String[] whereArgs) {
        database = dbHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        return database.update(tableName, contentValues, whereClause, whereArgs);
    }
}
