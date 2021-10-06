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

public class EmpCard extends AppCompatActivity {
    TextView crdes, crFee;
    Button ecard;
    CheckBox checkcrd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_card);
        crdes = findViewById(R.id.crdes);
        crFee = findViewById(R.id.crFee);
        ecard = (Button) findViewById(R.id.ecard);
        checkcrd = (CheckBox) findViewById(R.id.checkcrd);

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Job").child("Table light");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    if (snapshot.hasChildren()) {
                        crdes.setText(snapshot.child("jobid").getValue().toString());
                        crFee.setText(snapshot.child("fee").getValue().toString());

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

    public void statuscrd(View view){

        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Job").child("Table light");
        dbref.child("paymentStatus").setValue("Paid");

    }
    public void proceedcrd(View view){
        Intent in = new Intent(this,HomePage.class);

        startActivity(in);
    }
}
