package com.example.task_it;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MyProfile extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);


    }
    public void MyProfile(View view){
        Intent i = new Intent(this,MyCusAccount.class);
        startActivity(i);
    }

    public void MyLocation(View view){
        Intent i = new Intent(this,CusLocation.class);
        startActivity(i);
    }

    public void cUS(View view){
        Intent i = new Intent(this,CustomerCare.class);
        startActivity(i);
    }
}