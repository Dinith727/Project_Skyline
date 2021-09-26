package com.example.task_it;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EmpProfile extends AppCompatActivity {

    TextView phone, name, email;
    TextView cat, eq, avai, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_profile);

        phone = findViewById(R.id.Phoneqwe);
        name = findViewById(R.id.nameProf);
        email = findViewById(R.id.email);
        cat = findViewById(R.id.cat);
        eq = findViewById(R.id.equip);
        avai = findViewById(R.id.availablity);
        price = findViewById(R.id.dispri);

        String ph = getIntent().getStringExtra("phone");

        phone.setText(ph);

        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Employee").child(ph);
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    name.setText(snapshot.child("firstName").getValue().toString());
                    cat.setText(snapshot.child("category").getValue().toString());
                    email.setText(snapshot.child("email").getValue().toString());
                    eq.setText(snapshot.child("requiredEquipment").getValue().toString());
                    avai.setText(snapshot.child("status").getValue().toString());
                    price.setText(snapshot.child("fee").getValue().toString());
                } else
                    Toast.makeText(getApplicationContext(), "No SOURCE TO DISPLAY", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        }
        public void available(View view){
        avai.setText("Available");
            String ph = getIntent().getStringExtra("phone");
            DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Employee");
            upRef.child(ph).child("status").setValue("Available");

    }
    public void notavailable(View view){
        avai.setText("Not Available");
        String ph = getIntent().getStringExtra("phone");
        DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Employee");
        upRef.child(ph).child("status").setValue("Not Available");
    }
    public void up(View view){
        Intent q = new Intent(this,EmpProfUp.class);
        q.putExtra("iphone",getIntent().getStringExtra("phone"));
        startActivity(q);
    }


}






