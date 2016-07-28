package com.example.sugan.myappo;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by sugan on 23/07/16.
 */
public class ConfirmDataDialogFragment extends DialogFragment {

    Appointment appointment;

    static ConfirmDataDialogFragment newInstance(Appointment appointment) {
        ConfirmDataDialogFragment confirmDataDialogFragment
                = new ConfirmDataDialogFragment();
        Bundle args = new Bundle();
        confirmDataDialogFragment.setArguments(args);
        confirmDataDialogFragment.setAppointment(appointment);
        return confirmDataDialogFragment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.confirmation, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView nameTV = (TextView) view.findViewById(R.id.name_textView);
        nameTV.setText(appointment.getName() + " " + appointment.getSurname());

        TextView genderTV = (TextView) view.findViewById(R.id.gender_textView);
        genderTV.setText(appointment.getGender());

        TextView streetTV = (TextView) view.findViewById(R.id.street_textView);
        streetTV.setText(appointment.getStreet());

        TextView cityTV = (TextView) view.findViewById(R.id.city_textView);
        cityTV.setText(appointment.getCity());

        TextView zipTV = (TextView) view.findViewById(R.id.zip_textView);
        zipTV.setText(appointment.getZipCode());

        TextView countryTV = (TextView) view.findViewById(R.id.country_textView);
        countryTV.setText(appointment.getCountry());

        TextView phoneTV = (TextView) view.findViewById(R.id.phone_textView);
        phoneTV.setText(appointment.getPhone());

        TextView emailTV = (TextView) view.findViewById(R.id.email_textView);
        emailTV.setText(appointment.getEmail());

        TextView dateTV = (TextView) view.findViewById(R.id.date_textView);
        dateTV.setText(appointment.getDate());

        TextView timeTV = (TextView) view.findViewById(R.id.time_textView);
        timeTV.setText(appointment.getTime());

        Button emailButton = (Button) view.findViewById(R.id.email_button);
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                String[] TO = {"rupenman@gmail.com"};
                String[] CC = {""};
                String emailText = "Appointment Request from \n" + appointment.toString();
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Request for Appointment");
                emailIntent.putExtra(Intent.EXTRA_TEXT, emailText);
                try {
                    startActivity(Intent.createChooser(emailIntent, "Send email..."));
                } catch (ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "No email client", Toast.LENGTH_SHORT);
                }
            }
        });

        Button addToDatabase = (Button) view.findViewById(R.id.add_db_button);
        addToDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToDatabase();
            }
        });

        Button cancelButton = (Button) view.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().cancel();
            }
        });
    }

    public void addToDatabase() {
        AppointmentDatabaseHelper appointAppointmentDatabaseHelper = AppointmentDatabaseHelper.getInstance(getContext());
        appointAppointmentDatabaseHelper.addAppointment(appointment);
    }
}
