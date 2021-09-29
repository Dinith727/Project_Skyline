package com.example.task_it;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AssignTask extends AppCompatActivity {

    EditText description;
    String desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.assigntask);
        description = findViewById(R.id.desc);


    }

    public void back(View v) {
        Intent intent = new Intent (AssignTask.this, carpProfile.class);
        startActivity(intent);
    }

    public void assign(View v) {
        desc = description.getText().toString().trim();
        String s = getIntent().getStringExtra("tel");
        Intent intent = new Intent (AssignTask.this, EmpJob.class);
        intent.putExtra("desc", desc);
        intent.putExtra("tel", s);
        startActivity(intent);

    }
}