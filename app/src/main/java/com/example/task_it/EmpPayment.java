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

public class EmpPayment extends AppCompatActivity {

    TextView desc, start, end, duration;
    EditText Fee;
    Button cash,card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_emp_payment);
        desc = findViewById(R.id.desc);
        start = findViewById(R.id.start);
        end = findViewById(R.id.end);
        duration = findViewById(R.id.duration);
        Fee = (EditText) findViewById(R.id.Fee);
        cash = (Button) findViewById(R.id.cash);
        card = (Button) findViewById(R.id.card);



        //job jb = new job();

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Job").child("Table light");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    if (snapshot.hasChildren()) {
                        desc.setText(snapshot.child("jobid").getValue().toString());
                        start.setText(snapshot.child("startTime").getValue().toString());
                        end.setText(snapshot.child("endTime").getValue().toString());
                        duration.setText(snapshot.child("duration").getValue().toString());


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

    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Job");

    job jb = new job();
    public void addFee(View view) {
        if (Fee.getText().toString().isEmpty()) {
            Toast.makeText(EmpPayment.this, "Please Add a Job Fee", Toast.LENGTH_LONG).show();
        } else {

            //mFeedback.setText("");
            Intent in = new Intent(this, EmpCash.class);


            String f = Fee.getText().toString();
            jb.setFee(" ");
            dbRef.child("Table light").child("fee").setValue(f);
            startActivity(in);
            Toast.makeText(EmpPayment.this, "Choose a Payment method and Proceed", Toast.LENGTH_SHORT).show();

        }
    }
    public void cardFee(View view){
        if (Fee.getText().toString().isEmpty()) {
            Toast.makeText(EmpPayment.this, "Please Add a Job Fee", Toast.LENGTH_LONG).show();
        } else {

            //mFeedback.setText("");
            Intent in = new Intent(this, EmpCash.class);


            String f = Fee.getText().toString();
            jb.setFee(" ");
            dbRef.child("Table light").child("fee").setValue(f);
            startActivity(in);
            Toast.makeText(EmpPayment.this, "Choose a Payment method and Proceed", Toast.LENGTH_SHORT).show();
    }
}
}
