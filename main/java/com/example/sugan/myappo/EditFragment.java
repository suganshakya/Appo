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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditFragment extends Fragment {

    Spinner selectionCriteriaSpinner, selectionSpinner;
    TableLayout tableLayout;
    String criteriaValue, selectionValue;


    public EditFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View editView = inflater.inflate(R.layout.fragment_edit, container, false);
        selectionCriteriaSpinner = (Spinner) editView.findViewById(R.id.selection_criteria_spinner);
        TextView selectionTextView = (TextView) editView.findViewById(R.id.selection_textView);
        selectionSpinner = (Spinner) editView.findViewById(R.id.selection_spinner);
        tableLayout =(TableLayout) editView.findViewById(R.id.edit_table_layout);

        setSelectionCriteriaList();

        selectionCriteriaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                criteriaValue = (String) adapterView.getItemAtPosition(position);
                if (criteriaValue.contentEquals("ID")) {
                    criteriaValue = "_id";
                } else {
                    criteriaValue = criteriaValue.toLowerCase();
                }
                setSelectionList(criteriaValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        selectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectionValue = (String) adapterView.getItemAtPosition(position);

                displayFilterView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return editView;
    }

    private void displayFilterView() {
        Context context = getContext();
        DbHelper appointDbHelper = new DbHelper(getActivity().getApplicationContext());

        SQLiteDatabase db = appointDbHelper.getReadableDatabase();
        if (db == null) {
            Toast.makeText(context, "No Database", Toast.LENGTH_SHORT).show();
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

        String[] selectionArgs = new String[1];
        selectionArgs[0] = selectionValue;

        String selection;

        if(criteriaValue == null){
            selection = null;
        } else{
            selection = criteriaValue + " LIKE ?";
        }

        tableLayout.removeAllViews();

        Cursor cursor = db.query(AppointmentContract.FeedEntry.TABLE_NAME, projection,
                selection, selectionArgs, null, null, sortOrder);

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

    public void setSelectionCriteriaList() {
        ArrayAdapter<CharSequence> criteriaAdapter = ArrayAdapter.createFromResource(
                getContext(), R.array.column_name, android.R.layout.simple_spinner_item);
        // set the view for the Drop down list
        criteriaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // set the ArrayAdapter to the spinner
        selectionCriteriaSpinner.setAdapter(criteriaAdapter);
        int defaultPosition = criteriaAdapter.getPosition("Name");
        selectionCriteriaSpinner.setSelection(defaultPosition);
    }

    public void setSelectionList(String criteria) {
        Context context = getContext();
        DbHelper appointDbHelper = new DbHelper(getActivity().getApplicationContext());

        SQLiteDatabase db = appointDbHelper.getReadableDatabase();
        if (db == null) {
            Toast.makeText(context, "No database", Toast.LENGTH_SHORT).show();
            return;
        }

        String[] columns = new String[1];
        columns[0] = criteria;

        ArrayList<String> results = new ArrayList<>();

        Cursor cursor = db.query(true, AppointmentContract.FeedEntry.TABLE_NAME, columns,
                null, null, null, null, null, null, null);

        if (cursor == null) {
            Toast.makeText(context, "No Result", Toast.LENGTH_SHORT).show();
        }

        if (cursor.moveToFirst()) {
            do {
                results.add(cursor.getString(cursor.getColumnIndexOrThrow(columns[0])));
            } while (cursor.moveToNext());
        }
        db.close();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, results);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectionSpinner.setAdapter(dataAdapter);
    }

    public static int dpToPixel(int dp, Context context) {
        Float scale = context.getResources().getDisplayMetrics().density;
        return (int) ((float) dp * scale);
    }

}
