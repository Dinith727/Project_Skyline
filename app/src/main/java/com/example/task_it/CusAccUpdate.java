package com.example.task_it;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CusAccUpdate extends AppCompatActivity {

    EditText fName, lName, email, password;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_acc_update);

        fName = findViewById(R.id.et_firName);
        lName = findViewById(R.id.et_lasName);
        email = findViewById(R.id.et_emailAdd);
        password = findViewById(R.id.et_passWordd);
        submit = findViewById(R.id.btn_updateCusAcc);

        String ph = getIntent().getStringExtra("telNo");
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Customer").child(ph);
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                fName.setText(snapshot.child("fName").getValue().toString());
                lName.setText(snapshot.child("lName").getValue().toString());
                email.setText(snapshot.child("email").getValue().toString());
                password.setText(snapshot.child("password").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void BackToCreNewAcc(View view){
        Intent i = new Intent(this,MyCusAccount.class);
        startActivity(i);
    }

    public void Submit(View view){
        Intent in = new Intent(this,MyCusAccount.class);

        String ph = getIntent().getStringExtra("telNo");
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Customer");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dbRef.child(ph).child("fName").setValue(fName.getText().toString());
                dbRef.child(ph).child("lName").setValue(lName.getText().toString());
                dbRef.child(ph).child("email").setValue(email.getText().toString());
                dbRef.child(ph).child("password").setValue(password.getText().toString());

                Toast.makeText(getApplicationContext(), "Account Updated Successfully", Toast.LENGTH_SHORT).show();
                startActivity(in);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Account Update Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}