package com.example.sugan.myappo;

import android.content.Context;
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

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewFragment extends Fragment {

    Spinner columnSpinner, columnValueSpinner;

    TableLayout tableLayout;

    String columnName, columnValue;


    public ViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View viewView = inflater.inflate(R.layout.fragment_view, container, false);
        columnSpinner = (Spinner) viewView.findViewById(R.id.column_spinner);
        TextView selectionTextView = (TextView) viewView.findViewById(R.id.selection_textView);
        columnValueSpinner = (Spinner) viewView.findViewById(R.id.column_value_spinner);
        tableLayout = (TableLayout) viewView.findViewById(R.id.view_table_layout);

        setSelectionCriteriaList();

        columnSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                columnName = (String) adapterView.getItemAtPosition(position);
                columnName = columnName.toLowerCase();
                setSelectionList(columnName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        columnValueSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                columnValue = (String) adapterView.getItemAtPosition(position);

                displayFilterView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return viewView;
    }

    private void displayFilterView() {
        Context context = getContext();
        AppointmentDatabaseHelper appointDbHelper = AppointmentDatabaseHelper.getInstance(context);

        tableLayout.removeAllViews();

        TableRow headRow = new TableRow(context);

        headRow.setBackgroundColor(ContextCompat.getColor(context, R.color.colorTableRowHead));
        headRow.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)
        );

        int dpToPixelValue = dpToPixel(5, context);

        for (int i = 0; i < AppointmentDatabaseHelper.projection.length; ++i) {
            TextView textView = new TextView(context);
            textView.setPadding(dpToPixelValue, dpToPixelValue, dpToPixelValue, dpToPixelValue);
            textView.setText(AppointmentDatabaseHelper.projection[i].toUpperCase());
            headRow.addView(textView);
        }

        tableLayout.addView(headRow, new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        List<Appointment> appointmentList = appointDbHelper.getAppointments(columnName + " LIKE ?",
                new String[]{columnValue});

        int count = 0;

        for (Appointment appointment : appointmentList) {
            TableRow tablerow = new TableRow(context);
            if (count % 2 == 0) {
                tablerow.setBackgroundColor(ContextCompat.getColor(context, R.color.colorTableRowEven));
            } else {
                tablerow.setBackgroundColor(ContextCompat.getColor(context, R.color.colorTableRowOdd));
            }
            count++;
            tablerow.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            for (int i = 0; i < AppointmentDatabaseHelper.projection.length; ++i) {
                TextView textView = new TextView(context);
                textView.setPadding(dpToPixelValue, dpToPixelValue, dpToPixelValue, dpToPixelValue);
                textView.setText(appointment.getValue(i));
                tablerow.addView(textView);
            }

            tableLayout.addView(tablerow, new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        }
    }

    public void setSelectionCriteriaList() {
        ArrayAdapter<CharSequence> criteriaAdapter = ArrayAdapter.createFromResource(
                getContext(), R.array.column_name, android.R.layout.simple_spinner_item);
        // set the view for the Drop down list
        criteriaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // set the ArrayAdapter to the spinner
        columnSpinner.setAdapter(criteriaAdapter);
        int defaultPosition = criteriaAdapter.getPosition("Name");
        columnSpinner.setSelection(defaultPosition);
    }

    public void setSelectionList(String criteria) {
        Context context = getContext();
        AppointmentDatabaseHelper appointmentDatabaseHelper =
                AppointmentDatabaseHelper.getInstance(context);

        List<String> results = appointmentDatabaseHelper.getColumnValue(columnName);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, results);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        columnValueSpinner.setAdapter(dataAdapter);
    }

    public static int dpToPixel(int dp, Context context) {
        Float scale = context.getResources().getDisplayMetrics().density;
        return (int) ((float) dp * scale);
    }

}