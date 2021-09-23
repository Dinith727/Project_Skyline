package com.example.task_it;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EmpRegResetPw extends AppCompatActivity {

    EditText ph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_reg_reset_pw);

        ph = findViewById(R.id.inputphone5);

    }
    public void Back(View view){
        Intent intent = new Intent(this,EmpLogin.class);
        startActivity(intent);
    }
    public void Send(View view){
        String pho = ph.getText().toString();
        if(pho.isEmpty())
            Toast.makeText(getApplicationContext(), "Enter Phone Number", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(), "OTP Sent", Toast.LENGTH_SHORT).show();
    }
}