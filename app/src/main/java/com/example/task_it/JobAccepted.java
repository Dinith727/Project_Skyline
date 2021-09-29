package com.example.task_it;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JobAccepted extends AppCompatActivity {

    TextView accTime, eta1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobaccepted);

        accTime = findViewById(R.id.accTime);
        eta1 = findViewById(R.id.eta);

        DateFormat df = new SimpleDateFormat("hh.mm aa");
        String date = df.format(Calendar.getInstance().getTime());
        accTime.setText(date);

        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE,20);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 20);
        Date date1 = cal.getTime();
        SimpleDateFormat format1 = new SimpleDateFormat("hh.mm aa");
        String eta = format1.format(date1);

        eta1.setText(eta);


    }

    public void Contact (View view){
        String phoneNo = getIntent().getStringExtra("tel");
        Toast.makeText(getApplicationContext(),phoneNo, Toast.LENGTH_SHORT).show();
    }

}