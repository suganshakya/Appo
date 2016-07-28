package com.example.sugan.myappo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

/**
 * Created by sugan on 23/07/16.
 */
public class EditDataDialogFragment extends DialogFragment {
    int idValue;

    EditText nameET, surnameET, genderET, streetET, cityET, zipCodeET, countryET, phoneET, emailET,
            dateET, timeET;

    static EditDataDialogFragment newInstance(int idValue) {
        EditDataDialogFragment editDataDialogFragment = new EditDataDialogFragment();
        Bundle args = new Bundle();
        editDataDialogFragment.setArguments(args);
        editDataDialogFragment.setIdValue(idValue);
        return editDataDialogFragment;
    }

    public void setIdValue(int idValue) {
        this.idValue = idValue;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.edit_confirmation, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppointmentDatabaseHelper appointAppointmentDatabaseHelper =
                AppointmentDatabaseHelper.getInstance(getContext());
        List<Appointment> appointmentList =
                appointAppointmentDatabaseHelper.getAppointments(
                        "id LIKE ?", new String[]{String.valueOf(idValue)});

        if (appointmentList.size() == 1) {
            Appointment appointment = appointmentList.get(0);

            nameET = (EditText) view.findViewById(R.id.edit_name_editText);
            nameET.setText(appointment.getName());

            surnameET = (EditText) view.findViewById(R.id.edit_surname_editText);
            surnameET.setText(appointment.getSurname());

            genderET = (EditText) view.findViewById(R.id.edit_gender_editText);
            genderET.setText(appointment.getGender());

            streetET = (EditText) view.findViewById(R.id.edit_street_editText);
            streetET.setText(appointment.getStreet());

            cityET = (EditText) view.findViewById(R.id.edit_city_edittText);
            cityET.setText(appointment.getCity());

            zipCodeET = (EditText) view.findViewById(R.id.edit_zipcode_editText);
            zipCodeET.setText(appointment.getZipCode());

            countryET = (EditText) view.findViewById(R.id.edit_country_editText);
            countryET.setText(appointment.getCountry());

            phoneET = (EditText) view.findViewById(R.id.edit_phone_editText);
            phoneET.setText(appointment.getPhone());

            emailET = (EditText) view.findViewById(R.id.edit_email_editText);
            emailET.setText(appointment.getEmail());

            dateET = (EditText) view.findViewById(R.id.edit_date_editText);
            dateET.setText(appointment.getDate());

            timeET = (EditText) view.findViewById(R.id.edit_time_editTtext);
            timeET.setText(appointment.getTime());
        }

        Button addToDatabase = (Button) view.findViewById(R.id.edit_update_button);
        addToDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editUpdateDatabase();
            }
        });

        Button cancelButton = (Button) view.findViewById(R.id.edit_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().cancel();
            }
        });
    }

    public void editUpdateDatabase() {
        AppointmentDatabaseHelper appointAppointmentDatabaseHelper =
                AppointmentDatabaseHelper.getInstance(getContext());

        Appointment appointment = new Appointment(
                idValue,
                nameET.getText().toString(),
                surnameET.getText().toString(),
                genderET.getText().toString(),
                streetET.getText().toString(),
                cityET.getText().toString(),
                zipCodeET.getText().toString(),
                countryET.getText().toString(),
                phoneET.getText().toString(),
                emailET.getText().toString(),
                dateET.getText().toString(),
                timeET.getText().toString()
        );

        appointAppointmentDatabaseHelper.updateAppointment(idValue, appointment);
    }
}
