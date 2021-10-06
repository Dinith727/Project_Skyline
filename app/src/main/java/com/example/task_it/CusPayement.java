package com.example.task_it;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class CusPayement extends AppCompatActivity {
    TextView des, cstart, cend, cduration;
    TextView cFee;
    Button ccash,ccard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_payement);
        des = findViewById(R.id.des);
        cstart = findViewById(R.id.cstart);
        cend = findViewById(R.id.cend);
        cduration = findViewById(R.id.cduration);
        cFee = (TextView) findViewById(R.id.cFee);
        ccash = (Button) findViewById(R.id.ccash);
        ccard = (Button) findViewById(R.id.ccard);



        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Job").child("Table light");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    if (snapshot.hasChildren()) {
                        des.setText(snapshot.child("jobid").getValue().toString());
                        cstart.setText(snapshot.child("startTime").getValue().toString());
                        cend.setText(snapshot.child("endTime").getValue().toString());
                        cduration.setText(snapshot.child("duration").getValue().toString());
                        cFee.setText(snapshot.child("fee").getValue().toString());


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
    public void addCash(View view){
        Intent in = new Intent(this,EmployeeRating.class);

        startActivity(in);
    }
    public void addCard(View view){
        Intent in = new Intent(this,EmployeeRating.class);

        startActivity(in);
    }
    }
