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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    Spinner spinner_from, spinner_to;
    Button  btnProceed;
    EditText etext_Date, etext_Time,etext_noOfTickets;

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
        minute= cal.get(Calendar.MINUTE);
        second = cal.get(Calendar.SECOND);


        spinner_from = (Spinner) findViewById(R.id.spinner_From);
        spinner_to = (Spinner) findViewById(R.id.spinner_To);

        etext_Date = (EditText) findViewById(R.id.etext_Date);
        etext_Time = (EditText)findViewById(R.id.etext_Time);
        etext_noOfTickets =(EditText) findViewById(R.id.edittext_No_of_Tickets);

        btnProceed = (Button)findViewById(R.id.button_Proceed);

        //OnClick Listeners
        etext_Date.setOnClickListener(this);
        etext_Time.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_icons, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.etext_Date:
                setDate();
                break;
            case R.id.etext_Time:
                setTime();
                break;
            case R.id.button_Proceed:
                Intent intent = new Intent(this, SummaryActivity.class);
                startActivity(intent);

        }
    }

    //This method will set the time
    public void setTime(){
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {


                etext_Time.setText(hourOfDay+":"+minute);
                orderTime = (hourOfDay+"/"+minute);

            }
        },hour,minute,false);
        timePickerDialog.show();

    }


    //This method will set the Date
    public void setDate(){

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year_x, int monthOfYear, int dayOfMonth) {
                year = year_x;
                month = monthOfYear+1;
                day = dayOfMonth;


                if (year< Calendar.YEAR || month< Calendar.MONTH || day< Calendar.DAY_OF_MONTH){
                    Log.d("Selected Date",dayOfMonth+"/"+monthOfYear+"/"+year );
                    Log.d("Selected Date Error", "Selected date is a past day or invalid format");
                    Toast.makeText(MainActivity.this, "You cannot select past days.! Re-check",Toast.LENGTH_LONG).show();
                    return;
                }

                etext_Date.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
                orderDate = (dayOfMonth+"/"+monthOfYear+"/"+year);

            }
        }
        , day,month,year);
        datePickerDialog.show();

    }



}//End of the class
