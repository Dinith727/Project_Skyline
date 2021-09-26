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

public class MyCusAccount extends AppCompatActivity {

    TextView fName, lName, email, telNo, password;
    Button update;

    CusTempData t;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cus_account);

        fName = findViewById(R.id.et_firstName);
        lName = findViewById(R.id.et_lastName);
        email = findViewById(R.id.et_emailAdd);
        telNo = findViewById(R.id.et_telephone);
        password = findViewById(R.id.et_passw);
        update = findViewById(R.id.btn_updateCusAccUp);
        name = findViewById(R.id.txt_CustomercareHead);



        t = new CusTempData();


        String tel = t.getTelNo();

        DatabaseReference logRef = FirebaseDatabase.getInstance().getReference().child("Customer").child(t.getTelNo());
        logRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    fName.setText(snapshot.child("fName").getValue().toString());
                    name.setText(snapshot.child("fName").getValue().toString());
                    lName.setText(snapshot.child("lName").getValue().toString());
                    email.setText(snapshot.child("email").getValue().toString());
                    telNo.setText(snapshot.child("telNo").getValue().toString());
                    password.setText(snapshot.child("password").getValue().toString());
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

    public void BackToMyPro(View view){
        Intent i = new Intent(this,MyProfile.class);
        startActivity(i);
    }

    public void UpdateAcc(View view){
        Intent i = new Intent(this,CusAccUpdate.class);
        i.putExtra("telNo",t.getTelNo());
        startActivity(i);
    }
}