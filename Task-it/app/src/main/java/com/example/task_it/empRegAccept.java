package com.example.task_it;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class empRegAccept extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_reg_accept);

    }
    public void accept(View view){
        Intent intent = new Intent(this,EmpRegEn.class);
        startActivity(intent);
    }
    public void nothanks(View view){
        Intent intent = new Intent(this,EmpLogin.class);
        startActivity(intent);
    }
}