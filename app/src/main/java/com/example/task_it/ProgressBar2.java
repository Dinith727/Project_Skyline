package com.example.task_it;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProgressBar2 extends AppCompatActivity {

    private Chronometer chronometer;
    private long pauseOffset ;
    private long z;
    private boolean running;
    Timec t = new Timec();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar2);

        chronometer = findViewById(R.id.chronometer);
        chronometer.setFormat("Time: %s");
        chronometer.setBase(SystemClock.elapsedRealtime());
        job jb = new job();


        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if ((SystemClock.elapsedRealtime() - chronometer.getBase()) >= 10*60*60*1000) {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    Toast.makeText(ProgressBar2.this, "Bing!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
   // long startTime = System.nanoTime();

    public void startChronometer(View v) {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - (pauseOffset));
            chronometer.start();
            running = true;
            SimpleDateFormat formatter = new SimpleDateFormat(" hh.mm aa");
            Date date1 = new Date();

            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Job").child("Table light");
            dbRef.child("startTime").setValue(formatter.format(date1));
            dbRef.child("status").setValue("Started");
        }
    }

    public void pauseChronometer(View v) {
        if (running) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();

            running = false;
            DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Job").child("Table light");
            //float th = (((float) pauseOffset)/(1000));
            //t.setPausetime(th);
            //dbref.child("Duration").setValue(th);
            dbref.child("status").setValue("Paused");

        }

    }

    public void resetChronometer(View v) {
        Intent in = new Intent(this,EmpPayment.class);
        //chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 5;
        SimpleDateFormat formatter = new SimpleDateFormat(" hh.mm aa");
        Date date2 = new Date();


        float y = t.getPausetime();

        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Job").child("Table light");
        try {
            if (y == 0) {
                chronometer.stop();
                long z = SystemClock.elapsedRealtime() - chronometer.getBase();
                //float x = (((float) z)/(1000));
                //int roundOff = Math.round(z*100)/100;
                long r = z / 1000;
                if (r < 60) {
                    dbref.child("duration").setValue(r + " sec ");
                } else if (r < 3600) {
                    dbref.child("duration").setValue(r / 60 + " mins ");
                } else {
                    dbref.child("duration").setValue(r / (60 * 60) + " hrs ");
                }
                dbref.child("status").setValue("completed");
            } else {

                dbref.child("duration").setValue(y/1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //long totalTime = endTime - startTime;

        //dbref.child("Duration").setValue(totalTime/1000);
        //dbref.child("startTime").setValue(formatter.format(date));


        dbref.child("endTime").setValue(formatter.format(date2));
        startActivity(in);
    }

}