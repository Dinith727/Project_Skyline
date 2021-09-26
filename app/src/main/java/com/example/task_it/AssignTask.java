package com.example.task_it;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AssignTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assigntask);
    }

    public void back(View v) {
        Intent intent = new Intent (AssignTask.this, carpProfile.class);
        //intent.putExtra("ref", value);
        startActivity(intent);
    }

    public void assign(View v) {
        Intent intent = new Intent (AssignTask.this, EmpJob.class);
        startActivity(intent);
    }
}