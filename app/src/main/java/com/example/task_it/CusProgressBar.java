package com.example.task_it;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class CusProgressBar extends AppCompatActivity {
    TextView cus;
    String i ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_progress_bar);

        cus = findViewById(R.id.cus);
        job jb = new job();
        Intent in = new Intent(this,CusPayement.class);
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Job").child("Table light");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                    try {
                        if (snapshot.hasChildren()) {
                            cus.setText(snapshot.child("status").getValue().toString());
                            i = snapshot.child("status").getValue().toString();
                            if(i.equals("completed")) {

                                  startActivity(in);
                              }


                        } else
                            Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();

                    } catch (NumberFormatException e) {
                        Toast.makeText(getApplicationContext(), "Invalid Data", Toast.LENGTH_SHORT).show();
                    }


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });

    }


    public void sup(View view){
        Intent in = new Intent(this,CusPayement.class);

        startActivity(in);
    }
    }

