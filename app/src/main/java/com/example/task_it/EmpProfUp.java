package com.example.task_it;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EmpProfUp extends AppCompatActivity {

    TextView name;
    TextView equip,price,location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_prof_up);

        name = findViewById(R.id.nameProf);
        equip = findViewById(R.id.equp);
        price = findViewById(R.id.pup);
        location = findViewById(R.id.dislo);

        String ph = getIntent().getStringExtra("iphone");

        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Employee").child(ph);
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    name.setText(snapshot.child("firstName").getValue().toString());
                    equip.setText(snapshot.child("requiredEquipment").getValue().toString());
                    price.setText(snapshot.child("fee").getValue().toString());
                    location.setText(snapshot.child("location").getValue().toString());
                } else
                    Toast.makeText(getApplicationContext(), "No SOURCE TO DISPLAY", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    public void save(View view){
        String ph = getIntent().getStringExtra("iphone");
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Employee");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dbRef.child(ph).child("requiredEquipment").setValue(equip.getText().toString());
                dbRef.child(ph).child("fee").setValue(price.getText().toString());
                dbRef.child(ph).child("location").setValue(location.getText().toString());
                Toast.makeText(getApplicationContext(), "Data updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void reset(View view){
        String ph = getIntent().getStringExtra("iphone");
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Employee").child(ph);
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    equip.setText(snapshot.child("requiredEquipment").getValue().toString());
                    price.setText(snapshot.child("fee").getValue().toString());
                    location.setText(snapshot.child("location").getValue().toString());
                } else
                    Toast.makeText(getApplicationContext(), "No SOURCE TO DISPLAY", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}