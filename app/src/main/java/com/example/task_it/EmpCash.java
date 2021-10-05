package com.example.task_it;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EmpCash extends AppCompatActivity {
    TextView edes, eFee;
    Button ecash;
    CheckBox check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_cash);
        edes = findViewById(R.id.crdes);
        eFee = findViewById(R.id.crFee);
        ecash = (Button) findViewById(R.id.ecard);
        check = (CheckBox) findViewById(R.id.checkcrd);

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Job").child("Table light");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    if (snapshot.hasChildren()) {
                        edes.setText(snapshot.child("jobid").getValue().toString());
                        eFee.setText(snapshot.child("fee").getValue().toString());

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

    public void status(View view){

        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Job").child("Table light");
        dbref.child("paymentStatus").setValue("Paid");

    }
    public void proceed(View view){
        Intent in = new Intent(this,Splash.class);

        startActivity(in);
    }
    }
