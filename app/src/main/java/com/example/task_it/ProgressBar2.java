package com.example.task_it;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if ((SystemClock.elapsedRealtime() - chronometer.getBase()) >= 1*60*60*1000) {
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
            SimpleDateFormat formatter = new SimpleDateFormat(" HH:mm:ss");
            Date date = new Date();
            DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Job").child("job1");
            dbref.child("startTime").setValue(formatter.format(date));
        }
    }

    public void pauseChronometer(View v) {
        if (running) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();

            running = false;
            DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Job").child("job1");
            float th = (((float) pauseOffset)/(1000));
            t.setPausetime(th);
            //dbref.child("Duration").setValue(th);

        }

    }

    public void resetChronometer(View v) {
        //chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 5;
        SimpleDateFormat formatter = new SimpleDateFormat(" HH:mm:ss");
        Date date = new Date();


        float y = t.getPausetime();
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Job").child("job1");
        if(y == 0){
            chronometer.stop();
            z = SystemClock.elapsedRealtime() - chronometer.getBase();
            float x = (((float) z)/(1000));
            dbref.child("Duration").setValue(x);
        }
        else{
            dbref.child("Duration").setValue(t.getPausetime());
        }
        //long totalTime = endTime - startTime;

        //dbref.child("Duration").setValue(totalTime/1000);
        //dbref.child("startTime").setValue(formatter.format(date));


        dbref.child("endTime").setValue(formatter.format(date));

    }
}