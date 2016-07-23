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


        showDatabase();
        return view;
    }

    public void showDatabase() {
//        Context context = getContext();
//        DbHelper appointDbHelper = new DbHelper(context);
//        SQLiteDatabase db = appointDbHelper.getReadableDatabase();
//        if (db == null) {
            Toast.makeText(getContext(), "No database", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        String[] projection = {
//                AppointmentContract.FeedEntry._ID,
//                AppointmentContract.FeedEntry.COLUMN_NAME_NAME,
//                AppointmentContract.FeedEntry.COLUMN_NAME_LASTNAME
//        };
//
//        String sortOrder = AppointmentContract.FeedEntry.COLUMN_NAME_NAME;
//
//        Cursor cursor = db.query(AppointmentContract.FeedEntry.TABLE_NAME, projection, null, null, null, null, sortOrder);
//
//        if (cursor == null) {
//            Toast.makeText(this.getContext(), "No Result", Toast.LENGTH_SHORT).show();
//        }
//
////        tableLayout.removeView();
////        tableLayout.removeAllViewsInLayout();   // erase previous results
//        tableLayout.setStretchAllColumns(true);
//        tableLayout.setShrinkAllColumns(true);
//        TableRow headRow = new TableRow(context);
//
//        headRow.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorTableRowHead));
//        headRow.setLayoutParams(new TableRow.LayoutParams(
//                TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)
//        );
//
//        TextView idTextView = new TextView(context);
//        idTextView.setText("ID");
//        headRow.addView(idTextView);
//
//        TextView nameTextView = new TextView(context);
//        nameTextView.setText("Name");
//        headRow.addView(nameTextView);
//
//        TextView surnameTextView = new TextView(context);
//        surnameTextView.setText("Surname");
//        headRow.addView(surnameTextView);
//
//        tableLayout.addView(headRow, new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
//
//        int count = 0;
//        if (cursor.moveToFirst()) {
//            do {
//                TableRow tablerow = new TableRow(context);
//                if (count % 2 == 0) {
//                    tablerow.setBackgroundColor(ContextCompat.getColor(context, R.color.colorTableRowEven));
//                } else {
//                    tablerow.setBackgroundColor(ContextCompat.getColor(context, R.color.colorTableRowOdd));
//                }
//                count++;
//                tablerow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
//
//                TextView idTextView1 = new TextView(context);
//                idTextView1.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppointmentContract.FeedEntry._ID)));
//                tablerow.addView(idTextView1);
//
//                TextView nameTextView1 = new TextView(context);
//                nameTextView1.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppointmentContract.FeedEntry.COLUMN_NAME_NAME)));
//                tablerow.addView(nameTextView1);
//
//                TextView surnameTextView1 = new TextView(context);
//                surnameTextView1.setText(cursor.getString(cursor.getColumnIndexOrThrow(AppointmentContract.FeedEntry.COLUMN_NAME_LASTNAME)));
//                tablerow.addView(surnameTextView1);
//
//                tableLayout.addView(tablerow, new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
//
//            } while (cursor.moveToNext());
//        }
    }

}
