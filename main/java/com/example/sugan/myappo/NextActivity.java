package com.example.sugan.myappo;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NextActivity extends AppCompatActivity {

    TextView nameTV, genderTV, streetTV, cityTV, zipCodeTV, countryTV;
    TextView phoneTV, dateTV, timeTV;
    TextView emailTV;

    Button submitButton, goBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        Intent newIntent = getIntent();
        Bundle newBundle=newIntent.getExtras();

        doFindViewById();

        nameTV.setText(newBundle.getString("passFirstName")+ " " + newBundle.getString("passSurname"));
        genderTV.setText(newBundle.getString("passGender"));
        streetTV.setText(newBundle.getString("passStreet"));
        cityTV.setText(newIntent.getStringExtra("passCity"));
        zipCodeTV.setText(newIntent.getStringExtra("passZip"));
        countryTV.setText(newIntent.getStringExtra("passCountry"));
        phoneTV.setText(newBundle.getString("passPhoneNumber"));
        emailTV.setText(newBundle.getString("passEmail"));
        dateTV.setText(newBundle.getString("passDate"));
        timeTV.setText(newBundle.getString("passTime"));
    }

    private void doFindViewById() {
        nameTV=(TextView)findViewById(R.id.name_textView);
        genderTV = (TextView) findViewById(R.id.gender_textView);
        streetTV =(TextView)findViewById(R.id.street_textView);
        cityTV =(TextView)findViewById(R.id.city_textView);
        zipCodeTV =(TextView)findViewById(R.id.zip_textView);
        countryTV =(TextView)findViewById(R.id.country_textView);
        phoneTV =(TextView)findViewById(R.id.phone_textView);
        dateTV =(TextView)findViewById(R.id.date_textView);
        timeTV =(TextView)findViewById(R.id.time_textView);
        emailTV =(TextView)findViewById(R.id.email_textView);

        submitButton =(Button)findViewById(R.id.submit_button);
        goBackButton =(Button)findViewById(R.id.goback_button);

    }

    public void submitAppointment(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        String[] TO = {"rupenman@gmail.com"};
        String[] CC = {""};

        String emailText = "Appointment request from " +
                "\n Name: " + nameTV.getText() +
                "\n Street: " + streetTV.getText() +
                "\n City: " + cityTV.getText() +
                "\n Zip Code: " + zipCodeTV.getText() +
                "\n Country: " + countryTV.getText() +
                "\n Phone:" + phoneTV.getText() +
                "\n Email: " + emailTV.getText() +
                "\n Date: " + dateTV.getText() +
                "\n Time:" + timeTV.getText();

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Request for Appointment");
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailText);
        try {
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
        } catch (ActivityNotFoundException ex){
            Toast.makeText(NextActivity.this, "No email client", Toast.LENGTH_SHORT);
        }
    }

    public void goBack(View view) {
        Intent intent = new Intent(NextActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
