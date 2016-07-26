package com.example.sugan.myappo;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewFragment extends Fragment {

    TableLayout tableLayout;

    public ViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view, container, false);
        tableLayout =(TableLayout) view.findViewById(R.id.db_table_layout);
        showDatabase();
        return view;
    }

    public static int dpToPixel(int dp, Context context) {
        Float scale = context.getResources().getDisplayMetrics().density;
        return (int) ((float) dp * scale);
    }

    public void showDatabase() {
        Context context = getContext();
        DbHelper appointDbHelper = new DbHelper(getActivity().getApplicationContext());

        SQLiteDatabase db = appointDbHelper.getReadableDatabase();
        if (db == null) {
            Toast.makeText(context, "No database", Toast.LENGTH_SHORT).show();
            return;
        }
        // List of Column that we want to display
        String[] projection = {
                AppointmentContract.FeedEntry._ID,
                AppointmentContract.FeedEntry.COLUMN_NAME_NAME,
                AppointmentContract.FeedEntry.COLUMN_NAME_SURNAME,
                AppointmentContract.FeedEntry.COLUMN_NAME_GENDER,
                AppointmentContract.FeedEntry.COLUMN_NAME_STREET,
                AppointmentContract.FeedEntry.COLUMN_NAME_CITY,
                AppointmentContract.FeedEntry.COLUMN_NAME_ZIPCODE,
                AppointmentContract.FeedEntry.COLUMN_NAME_COUNTRY,
                AppointmentContract.FeedEntry.COLUMN_NAME_PHONE,
                AppointmentContract.FeedEntry.COLUMN_NAME_EMAIL,
                AppointmentContract.FeedEntry.COLUMN_NAME_DATE,
                AppointmentContract.FeedEntry.COLUMN_NAME_TIME
        };

        String sortOrder = AppointmentContract.FeedEntry.COLUMN_NAME_NAME;

        Cursor cursor = db.query(AppointmentContract.FeedEntry.TABLE_NAME, projection,
                null, null, null, null, sortOrder);

        if (cursor == null) {
            Toast.makeText(context, "No Result", Toast.LENGTH_SHORT).show();
        }

        TableRow headRow = new TableRow(context);

        headRow.setBackgroundColor(ContextCompat.getColor(context, R.color.colorTableRowHead));
        headRow.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)
        );
        int dpToPixelValue = dpToPixel(5,context);

        for(int i=0; i< projection.length; ++i){
            TextView textView = new TextView(context);
            textView.setPadding(dpToPixelValue, dpToPixelValue, dpToPixelValue, dpToPixelValue);
            textView.setText(projection[i].toUpperCase());
            headRow.addView(textView);
        }

        tableLayout.addView(headRow, new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        int count = 0;
        if (cursor.moveToFirst()) {
            do {
                TableRow tablerow = new TableRow(context);
                if (count % 2 == 0) {
                    tablerow.setBackgroundColor(ContextCompat.getColor(context, R.color.colorTableRowEven));
                } else {
                    tablerow.setBackgroundColor(ContextCompat.getColor(context, R.color.colorTableRowOdd));
                }
                count++;
                tablerow.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

                for(int i=0; i < projection.length; ++i){
                    TextView textView = new TextView(context);
                    textView.setPadding(dpToPixelValue, dpToPixelValue, dpToPixelValue ,dpToPixelValue);

                    textView.setText(cursor.getString(cursor.getColumnIndexOrThrow(projection[i])));
                    tablerow.addView(textView);
                }

                tableLayout.addView(tablerow, new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            } while (cursor.moveToNext());
        }
        db.close();
    }
}
