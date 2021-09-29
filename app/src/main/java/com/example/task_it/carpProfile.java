package com.example.task_it;

import android.annotation.SuppressLint;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class carpProfile extends AppCompatActivity {
    TextView name, location, telNo, status, fee, jobs;
    Button fav, assign;
    DatabaseReference dbRef;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carpprofile);
        String phoneNo = getIntent().getStringExtra("Phone");

        name = findViewById(R.id.name_carp);
        location = findViewById(R.id.loc_carp);
        telNo = findViewById(R.id.tel_carp);
        status = findViewById(R.id.status_carp);
        fee = findViewById(R.id.fee_carp);
        jobs = findViewById(R.id.jobs_carp);


        dbRef = FirebaseDatabase.getInstance().getReference("Employee").child(phoneNo);

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.getChildrenCount() > 0) {
                    name.setText(snapshot.child("firstName").getValue().toString());
                    location.setText(snapshot.child("location").getValue().toString());
                    telNo.setText(snapshot.child("telNo").getValue().toString());
                    status.setText(snapshot.child("status").getValue().toString());
                    fee.setText(snapshot.child("fee").getValue().toString());
                    jobs.setText(snapshot.child("jobs").getValue().toString());
                } else
                    Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
       /* Query query = readRef.orderByChild("_age").equalTo("20");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() > 0){
                    String a = "";
                    int count = (int)dataSnapshot.getChildrenCount();
                    for(DataSnapshot child : dataSnapshot.getChildren()){
                        Emergency value = child.getValue(Emergency.class);
                        a = a + value.get_name() + "\n";
                    }
                    tSResult.setText(count + "\n" + a);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });*/

    }

    public void Fav(View view) {

        Favourites f1 = new Favourites();

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Favourites");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                f1.setName(name.getText().toString().trim());
                f1.setTelNo(telNo.getText().toString().trim());

                dbRef.child(f1.getTelNo()).setValue(f1);
                Toast.makeText(getApplicationContext(), "Added to favourites", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void assign(View v) {
        String stat = status.getText().toString().trim();
        String phone = telNo.getText().toString().trim();


        if (stat.equalsIgnoreCase("busy")) {
            Toast.makeText(getApplicationContext(), "Status: Busy. Employee Unavailable", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(carpProfile.this, com.example.task_it.AssignTask.class);
            intent.putExtra("tel", phone);
            startActivity(intent);
        }

    }

}
