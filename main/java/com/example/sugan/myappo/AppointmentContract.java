package com.example.sugan.myappo;

import android.provider.BaseColumns;

/**
 * Created by sugan on 20/07/16.
 */
public final class AppointmentContract {
    public AppointmentContract(){}

    public static abstract class FeedEntry implements BaseColumns{
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_ENTRY_ID = "entry_id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_LASTNAME = "lastname";
        public static final String COLUMN_NAME_GENDER = "gender";
        public static final String COLUMN_NAME_STREET = "street";
        public static final String COLUMN_NAME_CITY = "city";
        public static final String COLUMN_NAME_ZIP = "zip";
        public static final String COLUMN_NAME_COUNTRY = "country";
        public static final String COLUMN_NAME_PHONE = "phone";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_TIME = "time";
    }
}
