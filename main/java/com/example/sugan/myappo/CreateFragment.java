package com.example.sugan.myappo;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateFragment extends Fragment {

    EditText nameET, surnameET;

    RadioGroup genderRadioGroup;
    RadioButton maleRB, femaleRB, othersRB;
    EditText streetET, cityET, zipET, phoneET, emailET;
    Spinner countrySpinner;
    EditText dateET, timeET;
    Button okButton, resetButton;
    String gender;

    Calendar date;
    int defaultCountryPosition;

    SimpleDateFormat dateFormatter;

    Appointment appointment;

    public CreateFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View createFragmentView = inflater.inflate(R.layout.fragment_create, container, false);
        nameET = (EditText) createFragmentView.findViewById(R.id.name_edittext);
        surnameET = (EditText) createFragmentView.findViewById(R.id.surname_editText);

        genderRadioGroup = (RadioGroup) createFragmentView.findViewById(R.id.gender_radio_group);
        maleRB = (RadioButton) createFragmentView.findViewById(R.id.male_rb);
        femaleRB = (RadioButton) createFragmentView.findViewById(R.id.female_rb);
        othersRB = (RadioButton) createFragmentView.findViewById(R.id.others_rb);
        streetET = (EditText) createFragmentView.findViewById(R.id.address_editText);
        cityET = (EditText) createFragmentView.findViewById(R.id.city_editText);
        zipET = (EditText) createFragmentView.findViewById(R.id.zip_editText);
        countrySpinner = (Spinner) createFragmentView.findViewById(R.id.country_editText);
        phoneET = (EditText) createFragmentView.findViewById(R.id.phone_editText);
        emailET = (EditText) createFragmentView.findViewById(R.id.email_editText);
        dateET = (EditText) createFragmentView.findViewById(R.id.date_editText);
        timeET = (EditText) createFragmentView.findViewById(R.id.time_editText);
        okButton = (Button) createFragmentView.findViewById(R.id.okBtn);
        resetButton = (Button) createFragmentView.findViewById(R.id.cancelBtn);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.male_rb:
                        gender = "Male";
                        break;
                    case R.id.female_rb:
                        gender = "Female";
                        break;
                    case R.id.others_rb:
                        gender = "Other";
                        break;

                }
            }
        });

        dateET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSetDate();
            }
        });

        timeET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSetTime();
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmData();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetData();
            }
        });

        getCountryList();
        return createFragmentView;
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
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, countries);
        // set the view for the Drop down list
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // set the ArrayAdapter to the spinner
        countrySpinner.setAdapter(dataAdapter);
        defaultCountryPosition = dataAdapter.getPosition("Nepal");
        countrySpinner.setSelection(defaultCountryPosition);

    }

    public void onClickSetDate() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayofMonth) {
                date = Calendar.getInstance();
                date.set(year, monthOfYear, dayofMonth);
                dateET.setText(dateFormatter.format(date.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void onClickSetTime() {
        final Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                timeET.setText(hour + ":" + minute);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }

    public void confirmData() {

        appointment = new Appointment(1, nameET.getText().toString(),
                surnameET.getText().toString(),
                gender,
                streetET.getText().toString(),
                cityET.getText().toString(),
                zipET.getText().toString(),
                countrySpinner.getSelectedItem().toString(),
                phoneET.getText().toString(),
                emailET.getText().toString(),
                dateET.getText().toString(),
                timeET.getText().toString()
        );

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        DialogFragment dialogFragment = ConfirmDataDialogFragment.newInstance(appointment);
        dialogFragment.show(ft, "dialog");
    }

    public void resetData() {
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

}
