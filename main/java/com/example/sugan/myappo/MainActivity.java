package com.example.sugan.myappo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText nameET, surnameET;
    RadioButton maleRB, femaleRB, othersRB;
    EditText streetET, cityET, zipET, phoneET, emailET;
    Spinner countrySpinner;
    EditText dateET, timeET;
    Button okButton, resetButton;
    Button goToDbButton;
    String gender;

    Calendar date;
    int defaultCountryPosition;

    SimpleDateFormat dateFormatter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        doFindViewById();
        getCountryList();   //get Country List in Country EditText

    }

    private void doFindViewById() {
        nameET = (EditText) findViewById(R.id.name_edittext);
        surnameET = (EditText) findViewById(R.id.surname_editText);
        maleRB = (RadioButton) findViewById(R.id.male_rb);
        femaleRB = (RadioButton) findViewById(R.id.female_rb);
        othersRB = (RadioButton) findViewById(R.id.others_rb);
        streetET = (EditText) findViewById(R.id.address_editText);
        cityET = (EditText) findViewById(R.id.city_editText);
        zipET = (EditText) findViewById(R.id.zip_editText);
        countrySpinner = (Spinner) findViewById(R.id.country_editText);
        phoneET = (EditText) findViewById(R.id.phone_editText);
        emailET = (EditText) findViewById(R.id.email_editText);
        dateET = (EditText) findViewById(R.id.date_editText);
        timeET = (EditText) findViewById(R.id.time_editText);
        okButton = (Button) findViewById(R.id.okBtn);
        resetButton = (Button) findViewById(R.id.cancelBtn);
        goToDbButton = (Button) findViewById(R.id.go_to_database);
    }


    public void getCountryList() {
        Locale[] locales = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        for (Locale locale : locales) {
            String country = locale.getDisplayCountry();
            if (country.trim().length() > 0 && !countries.contains(country)) {
                countries.add(country);
            }
        }
        Collections.sort(countries);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, countries);
        // set the view for the Drop down list
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // set the ArrayAdapter to the spinner
        countrySpinner.setAdapter(dataAdapter);
        defaultCountryPosition = dataAdapter.getPosition("Nepal");
        countrySpinner.setSelection(defaultCountryPosition);

    }

    public void setGender(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.male_rb:
                if (checked)
                    gender = "Male";
                break;
            case R.id.female_rb:
                if (checked)
                    gender = "Female";
                break;
            case R.id.others_rb:
                if (checked)
                    gender = "Other";
        }
    }

    public void onClickSetDate(View view) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayofMonth) {
                date = Calendar.getInstance();
                date.set(year, monthOfYear, dayofMonth);
                dateET.setText(dateFormatter.format(date.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void onClickSetTime(View view) {
        final Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                timeET.setText(hour + ":" + minute);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }

    public void confirmData(View view) {
        Bundle b = new Bundle();
        b.putString("passFirstName", nameET.getText().toString());
        b.putString("passSurname", surnameET.getText().toString());
        b.putString("passGender", gender);
        b.putString("passStreet", streetET.getText().toString());
        b.putString("passCity", cityET.getText().toString());
        b.putString("passZip", zipET.getText().toString());
        b.putString("passCountry", countrySpinner.getSelectedItem().toString());
        b.putString("passPhoneNumber", phoneET.getText().toString());
        b.putString("passEmail", emailET.getText().toString());
        b.putString("passDate", dateET.getText().toString());
        b.putString("passTime", timeET.getText().toString());

        Intent intent = new Intent(MainActivity.this, NextActivity.class);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void resetData(View view) {
        nameET.setText(null);
        surnameET.setText(null);
        maleRB.setChecked(false);
        femaleRB.setChecked(false);
        streetET.setText(null);
        cityET.setText(null);
        zipET.setText(null);
        phoneET.setText(null);
        countrySpinner.setSelection(defaultCountryPosition);
        dateET.setText(null);
        timeET.setText(null);
    }

    public void goToDatabase(View view) {
        Bundle b = new Bundle();
        Intent intent = new Intent(MainActivity.this, DatabaseActivity.class);
        intent.putExtras(b);
        startActivity(intent);
    }
}
