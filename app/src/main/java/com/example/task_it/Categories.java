package com.example.task_it;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Categories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);

    }

    public void showCarpentry(View v) {
        Intent intent = new Intent (Categories.this, Carpentry.class);
        startActivity(intent);
    }

    public void showElectrician(View v) {
        Intent intent = new Intent (Categories.this, Electrician.class);
        startActivity(intent);
    }
}