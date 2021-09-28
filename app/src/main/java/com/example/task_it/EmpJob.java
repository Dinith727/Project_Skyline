package com.example.task_it;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EmpJob extends AppCompatActivity {

    TextView jobDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empjob);
        String s = getIntent().getStringExtra("desc");
        jobDesc = findViewById(R.id.jobDesc);
        jobDesc.setText(s) ;
    }

    public void updateStatus(View view) {

        DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Employee").child("emp1");
        upRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                upRef.child("status").setValue("Busy");
            }
            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });

        Intent intent = new Intent (EmpJob.this, JobAccepted.class);
        startActivity(intent);
    }

    public void redirect(View view){
        Intent intent = new Intent (EmpJob.this, com.example.task_it.Carpentry.class);
        startActivity(intent);
    }
}