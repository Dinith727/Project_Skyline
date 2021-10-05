package com.example.task_it;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EmpRegSuccess extends AppCompatActivity {

    Button ex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_reg_success);

        ex = findViewById(R.id.btnlog1);

    }
    public void login(View view) {
        Intent intent = new Intent(this,EmpLogin.class);
        startActivity(intent);
    }

}