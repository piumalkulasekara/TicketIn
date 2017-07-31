package com.example.piumalk.ticketin;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Spinner spinner_from, spinner_to;
    Button btnProceed;
    EditText etext_Date, etext_Time, etext_noOfTickets;

    static String orderDate, orderTime;
    private int year, month, day, hour, minute, second;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get Current dates and times
        final Calendar cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        //Get current Times
        hour = cal.get(Calendar.HOUR);
        minute = cal.get(Calendar.MINUTE);
        second = cal.get(Calendar.SECOND);
        spinner_from = (Spinner) findViewById(R.id.spinner_From);
        spinner_to = (Spinner) findViewById(R.id.spinner_To);
        etext_Date = (EditText) findViewById(R.id.etext_Date);
        etext_Time = (EditText) findViewById(R.id.etext_Time);
        etext_noOfTickets = (EditText) findViewById(R.id.edittext_No_of_Tickets);
        btnProceed = (Button) findViewById(R.id.button_Proceed);
        btnProceed.setOnClickListener(this);
        //OnClick Listeners
        etext_Date.setOnClickListener(this);
        etext_Time.setOnClickListener(this);


        List<String> from = new ArrayList<String>();
        from.add("Anuradhapura");
        from.add("Colombo");
        from.add("Galle");
        from.add("Thangalle");
        from.add("Kataragama");
        from.add("Trincomalee");


        List<String> to = new ArrayList<String>();
        to.add("Anuradhapura");
        to.add("Colombo");
        to.add("Galle");
        to.add("Thangalle");
        to.add("Kataragama");
        to.add("Trincomalee");

        // Creating adapter for spinner
        ArrayAdapter<String> fromAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, from);
        ArrayAdapter<String> toAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, to);

        // Drop down layout style - list view with radio button
        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_from.setAdapter(fromAdapter);
        spinner_to.setAdapter(toAdapter);

    }


    int getPrice(int from, int to) {
        if (from > to) {
            return 50 * (from - to);
        } else {
            return 50 * (to - from);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_icons, menu);
        return true;
    }


    //OnClick Listener
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.etext_Date:
                setDate();
                break;
            case R.id.etext_Time:
                setTime();
                break;
            case R.id.button_Proceed:
                Parser.parseDate(getApplicationContext(), etext_Date.getText().toString() + etext_Time.getText().toString(), 1, 2, 10, getPrice(2, 10));
                Intent in = new Intent(this, SummaryActivity.class);
                in.putExtra("user", "1");
                in.putExtra("from", "Colombo");
                in.putExtra("to", "Trincomalee");
                in.putExtra("price", getPrice(2, 10) + "");
                in.putExtra("date", etext_Date.getText().toString() + etext_Time.getText().toString());
                startActivity(in);
                finish();
                break;

        }
    }


    //This method will set the time
    public void setTime() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                String hour = String.valueOf(hourOfDay);
                if (hourOfDay < 10) {
                    hour = "0" + String.valueOf(hourOfDay);
                }

                String min = String.valueOf(minute);
                if (minute < 10) {
                    min = "0" + String.valueOf(minute);
                }

                final String dateNew = hour + ":" + min + ":" + "00";
                etext_Time.setText(dateNew);
                orderTime = (hourOfDay + "/" + minute);

            }
        }, hour, minute, false);
        timePickerDialog.show();

    }


    //This method will set the Date
    public void setDate() {

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year_x, int monthOfYear, int dayOfMonth) {
                year = year_x;
                month = monthOfYear + 1;
                day = dayOfMonth;


                if (year < Calendar.YEAR || month < Calendar.MONTH || day < Calendar.DAY_OF_MONTH) {
                    Log.d("Selected Date", dayOfMonth + "/" + monthOfYear + "/" + year);
                    Log.d("Selected Date Error", "Selected date is a past day or invalid format");
                    Toast.makeText(MainActivity.this, "You cannot select past days.! Re-check", Toast.LENGTH_LONG).show();
                    return;
                }

                String day = String.valueOf(dayOfMonth);
                if (dayOfMonth < 10) {
                    day = "0" + String.valueOf(dayOfMonth);
                }
                String mon = String.valueOf(monthOfYear);
                if (monthOfYear < 10) {
                    mon = "0" + String.valueOf(monthOfYear);
                }

                etext_Date.setText(year + "-" + mon + "-" + day + " ");
                orderDate = (dayOfMonth + "/" + monthOfYear + "/" + year);

            }
        }
                , day, month, year);
        datePickerDialog.show();

    }


}//End of the class
