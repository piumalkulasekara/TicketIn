package com.example.piumalk.ticketin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtFrom, txtTo, txtDate, txtTime, txtNoOfTickets, txtTotalFair;
    Button btnCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);


        btnCheckout = (Button) findViewById(R.id.btn_Checkout);
        btnCheckout.setOnClickListener(this);

        txtFrom = (TextView) findViewById(R.id.txt_From);

        txtTo = (TextView) findViewById(R.id.txt_To);
        txtDate = (TextView) findViewById(R.id.txt_Date);
        txtTime = (TextView) findViewById(R.id.txt_Time);
        txtNoOfTickets = (TextView) findViewById(R.id.txt_Ticket_Counter);
        txtTotalFair = (TextView) findViewById(R.id.txt_Cost);

        Intent in = getIntent();
        txtFrom.setText(in.getStringExtra("from"));
        txtTo.setText(in.getStringExtra("to"));
        txtTime.setText(in.getStringExtra("date"));
        txtTotalFair.setText(in.getStringExtra("price"));


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Checkout:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
