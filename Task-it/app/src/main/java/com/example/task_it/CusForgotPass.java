package com.example.task_it;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CusForgotPass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_forgot_pass);
    }

    public void BackToCusLog(View view){
        Intent i = new Intent(this,LoginPage_Cus.class);
        startActivity(i);
    }

}