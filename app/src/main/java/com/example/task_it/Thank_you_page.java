package com.example.task_it;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Thank_you_page extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.thank_you_page);


        }
    public void BackToHome(View view){
        Intent i = new Intent(this,HomePage.class);
        startActivity(i);
    }
}
