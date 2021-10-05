package com.example.task_it;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CreateNewAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);
    }

    public void AsaCus(View view){
        Intent i = new Intent(this,CusNewAccount.class);
        startActivity(i);
    }

    public void BackCre(View view){
        Intent in = new Intent(this,LoginPage_Cus.class);
        startActivity(in);
    }

}