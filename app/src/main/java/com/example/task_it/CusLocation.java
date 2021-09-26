package com.example.task_it;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CusLocation extends AppCompatActivity {

    TextView HouseNo, street, city, district, zipCode;
    Button update;

    CusTempData t;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_location);

        HouseNo = findViewById(R.id.et_HouseNo);
        street = findViewById(R.id.et_street);
        city = findViewById(R.id.et_city);
        district = findViewById(R.id.et_district);
        zipCode = findViewById(R.id.et_zipCode);
        update = findViewById(R.id.btn_updateCusLocUp);
        name = findViewById(R.id.txt_headCusMyLoc);

        t = new CusTempData();

        String tel = t.getTelNo();

        DatabaseReference logRef = FirebaseDatabase.getInstance().getReference().child("Customer").child(t.getTelNo());
        logRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    HouseNo.setText(snapshot.child("houseNo").getValue().toString());
                    name.setText(snapshot.child("fName").getValue().toString());
                    street.setText(snapshot.child("street").getValue().toString());
                    city.setText(snapshot.child("city").getValue().toString());
                    district.setText(snapshot.child("district").getValue().toString());
                    zipCode.setText(snapshot.child("zipCode").getValue().toString());
                }
                else{
                    Toast.makeText(getApplicationContext(), "No Data to Display", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void BackToMyProfile(View view){
        Intent i = new Intent(this,MyProfile.class);
        startActivity(i);
    }

    public void UpdateLoc(View view){
        Intent i = new Intent(this,CusLocUpdate.class);
        i.putExtra("telNo",t.getTelNo());
        startActivity(i);
    }
}