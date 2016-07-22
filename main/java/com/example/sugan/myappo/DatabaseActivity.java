package com.example.sugan.myappo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class DatabaseActivity extends AppCompatActivity {

    DbHelper appointDbHelper;
    int dbCounter = 0;

    EditText nameET, surnameET;
    Button deleteButton, addButton, showButton;

    TextView dbTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        appointDbHelper = new DbHelper(this);

        Intent newIntent = getIntent();
        Bundle newBundle = newIntent.getExtras();
        myFindViewById();

    }

    private void myFindViewById() {
        nameET = (EditText) findViewById(R.id.name_editText2);
        surnameET = (EditText) findViewById(R.id.surname_editText2);
        addButton = (Button) findViewById(R.id.add_db_button);
        showButton = (Button) findViewById(R.id.show_db_button);
        dbTextView = (TextView) findViewById(R.id.db_textView);
        deleteButton = (Button) findViewById(R.id.delete_db_button);
//        tableLayout = (TableLayout) findViewById(R.id.db_table_layout);
    }


    public void addToDatabase(View view) {
        SQLiteDatabase db = appointDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        dbCounter++;
        values.put(AppointmentContract.FeedEntry.COLUMN_NAME_ENTRY_ID, dbCounter);
        values.put(AppointmentContract.FeedEntry.COLUMN_NAME_NAME, nameET.getText().toString());
        values.put(AppointmentContract.FeedEntry.COLUMN_NAME_LASTNAME, surnameET.getText().toString());
        values.put(AppointmentContract.FeedEntry.COLUMN_NAME_GENDER, "");
        values.put(AppointmentContract.FeedEntry.COLUMN_NAME_STREET, "");
        values.put(AppointmentContract.FeedEntry.COLUMN_NAME_CITY, "");
        values.put(AppointmentContract.FeedEntry.COLUMN_NAME_ZIP, "");
        values.put(AppointmentContract.FeedEntry.COLUMN_NAME_COUNTRY, "");
        values.put(AppointmentContract.FeedEntry.COLUMN_NAME_EMAIL, "");
        values.put(AppointmentContract.FeedEntry.COLUMN_NAME_DATE, "");
        values.put(AppointmentContract.FeedEntry.COLUMN_NAME_TIME, "");
        long newRowId = db.insert(AppointmentContract.FeedEntry.TABLE_NAME, null, values);
        Toast.makeText(this.getApplicationContext(), "Data added", Toast.LENGTH_SHORT).show();
    }

    public void showDatabase(View view) {
        SQLiteDatabase db = appointDbHelper.getReadableDatabase();
        if (db == null) {
            Toast.makeText(this.getApplicationContext(), "No database", Toast.LENGTH_SHORT).show();
            return;
        }
        String[] projection = {
                AppointmentContract.FeedEntry._ID,
                AppointmentContract.FeedEntry.COLUMN_NAME_NAME,
                AppointmentContract.FeedEntry.COLUMN_NAME_LASTNAME
        };

        String sortOrder = AppointmentContract.FeedEntry.COLUMN_NAME_NAME;

        Cursor cursor = db.query(AppointmentContract.FeedEntry.TABLE_NAME, projection, null, null, null, null, sortOrder);

        if (cursor == null) {
            Toast.makeText(this.getApplicationContext(), "No Result", Toast.LENGTH_SHORT).show();
        }


        TableLayout tableLayout = (TableLayout) findViewById(R.id.db_table_layout);
        tableLayout.removeAllViewsInLayout();   // erase previous results
        tableLayout.setStretchAllColumns(true);
        tableLayout.setShrinkAllColumns(true);
        TableRow headRow = new TableRow(this);

        headRow.setBackgroundColor(Color.rgb(200, 200, 200));
        headRow.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)
        );

        TextView idTextView = new TextView(this);
        idTextView.setText("ID");
        headRow.addView(idTextView);

        TextView nameTextView = new TextView(this);
        nameTextView.setText("Name");
        headRow.addView(nameTextView);

        TextView surnameTextView = new TextView(this);
        surnameTextView.setText("Surname");
        headRow.addView(surnameTextView);

        tableLayout.addView(headRow, new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        int count = 0;
        if (cursor.moveToFirst()) {
            do {
                TableRow tablerow = new TableRow(this);
                if (count % 2 == 0) {
                    tablerow.setBackgroundColor(Color.rgb(240, 240, 240));
                }
                count++;
                tablerow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

                TextView idTextView1 = new TextView(this);
                idTextView1.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppointmentContract.FeedEntry._ID)));
                tablerow.addView(idTextView1);

                TextView nameTextView1 = new TextView(this);
                nameTextView1.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppointmentContract.FeedEntry.COLUMN_NAME_NAME)));
                tablerow.addView(nameTextView1);

                TextView surnameTextView1 = new TextView(this);
                surnameTextView1.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppointmentContract.FeedEntry.COLUMN_NAME_LASTNAME)));
                tablerow.addView(surnameTextView1);

                tableLayout.addView(tablerow, new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            } while (cursor.moveToNext());
        }
    }

    public void deleteDatabase(View view) {
        SQLiteDatabase db = appointDbHelper.getWritableDatabase();
        appointDbHelper.deleteTable(db);
    }
}
