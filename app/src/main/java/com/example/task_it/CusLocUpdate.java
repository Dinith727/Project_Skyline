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

public class CusLocUpdate extends AppCompatActivity {

    EditText HouseNo, street, city, district, zipCode;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_loc_update);

        HouseNo = findViewById(R.id.et_HouseNo2);
        street = findViewById(R.id.et_street2);
        city = findViewById(R.id.et_city2);
        district = findViewById(R.id.et_district2);
        zipCode = findViewById(R.id.et_zipCode2);
        submit = findViewById(R.id.btn_updateCusAccLoc);

        String phone = getIntent().getStringExtra("telNo");
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Customer").child(phone);
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HouseNo.setText(snapshot.child("houseNo").getValue().toString());
                street.setText(snapshot.child("street").getValue().toString());
                city.setText(snapshot.child("city").getValue().toString());
                district.setText(snapshot.child("district").getValue().toString());
                zipCode.setText(snapshot.child("zipCode").getValue().toString());
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
        Intent in = new Intent(this,CusLocation.class);

        String phone = getIntent().getStringExtra("telNo");
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Customer");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dbRef.child(phone).child("houseNo").setValue(HouseNo.getText().toString());
                dbRef.child(phone).child("street").setValue(street.getText().toString());
                dbRef.child(phone).child("city").setValue(city.getText().toString());
                dbRef.child(phone).child("district").setValue(district.getText().toString());
                dbRef.child(phone).child("zipCode").setValue(zipCode.getText().toString());

                Toast.makeText(getApplicationContext(), "Location Updated Successfully", Toast.LENGTH_SHORT).show();
                startActivity(in);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Location Update Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}