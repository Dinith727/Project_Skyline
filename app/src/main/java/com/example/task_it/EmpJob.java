package com.example.task_it;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class EmpJob extends AppCompatActivity {

    TextView jobDesc;
    Animation anim1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empjob);
        String s = getIntent().getStringExtra("desc");
        jobDesc = findViewById(R.id.jobDesc);
        jobDesc.setText(s) ;
        anim1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slideup);
        jobDesc.startAnimation(anim1);
    }

    public void updateStatus(View view) {

        double id = Math.random() * 1000000000;
        String Jid = Double.toString(id);
        Calendar cal = Calendar.getInstance();
        Date st = cal.getTime();
        SimpleDateFormat format1 = new SimpleDateFormat("hh.mm aa");
        String eta = format1.format(st);
        SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
        Date d = new Date();
        String date = format2.format(d);
        String phone1 = getIntent().getStringExtra("tel");

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Job");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                CreateJob j1 = new CreateJob();
                j1.setJobid(Jid);
                j1.setJobname(jobDesc.getText().toString().trim());
                j1.setStatus("Accepted");
                j1.setFeedback("");
                j1.setFee("");
                j1.setStartTime("");
                j1.setAcceptedTime(eta);
                j1.setEndTime("");
                j1.setDuration("");
                j1.setRating("");
                j1.setDate(date);
                j1.setEmp(phone1);

                dbRef.child(j1.getJobname()).setValue(j1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Employee").child(phone1);
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
        String phone = getIntent().getStringExtra("tel");
        intent.putExtra("tel", phone);
        startActivity(intent);
    }

    public void redirect(View view){
        Intent intent = new Intent (EmpJob.this, com.example.task_it.Categories.class);
        startActivity(intent);
    }
}