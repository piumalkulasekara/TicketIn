package com.example.piumalk.ticketin;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etext_Name, etext_NIC, etext_Mobile, etext_Email;
    Button btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etext_Name = (EditText) findViewById(R.id.etext_Time);
        etext_NIC = (EditText) findViewById(R.id.etext_NIC);
        etext_Mobile = (EditText) findViewById(R.id.etext_Mobile);
        etext_Email = (EditText) findViewById(R.id.etext_Email);

        btnReg = (Button) findViewById(R.id.btnReg);
        btnReg.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnReg:
                //TODO Navigate to next activity.
                break;
        }
    }
}
