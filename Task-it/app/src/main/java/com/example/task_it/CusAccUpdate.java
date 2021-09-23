package com.example.task_it;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CusAccUpdate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_acc_update);
    }

    public void BackToCreNewAcc(View view){
        Intent i = new Intent(this,MyCusAccount.class);
        startActivity(i);
    }
}