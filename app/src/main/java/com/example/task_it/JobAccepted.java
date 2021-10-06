package com.example.task_it;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JobAccepted extends AppCompatActivity {

    //side Bar
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    TextView accTime, eta1;
    Animation anim;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobaccepted);





        accTime = findViewById(R.id.accTime);
        img = findViewById(R.id.jobImg);
        eta1 = findViewById(R.id.eta);
        anim = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move);

        img.startAnimation(anim);

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
    public void proceed (View view){
        Intent in = new Intent(this, CusProgressBar.class);

        startActivity(in);
    }




}