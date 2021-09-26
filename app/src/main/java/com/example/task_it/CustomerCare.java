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

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerCare extends AppCompatActivity {

    TextView name;

    CusTempData t;

    EditText issue;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_care);

        name = findViewById(R.id.txt_txt_CustomercareHeadName);
        issue = findViewById(R.id.et_issue);

        t = new CusTempData();

        String tel = t.getTelNo();

        DatabaseReference logRef = FirebaseDatabase.getInstance().getReference().child("Customer").child(t.getTelNo());
        logRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    name.setText(snapshot.child("fName").getValue().toString());
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

    public void Submit(View view){
        Customer_Care c = new Customer_Care();

        SimpleDateFormat formatter = new SimpleDateFormat(" dd:MM:yyyy/HH:mm:ss");
        Date date = new Date();


        DatabaseReference logRef = FirebaseDatabase.getInstance().getReference().child("Customer_Care");
        logRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                c.setDate(formatter.format(date));
                c.setIssue(issue.getText().toString());
                c.setName(name.getText().toString());
                c.setTelNo(t.getTelNo());

                logRef.child(c.getDate()).setValue(c);
                Toast.makeText(getApplicationContext(), "Issue added successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void BackToPro(View view){
        Intent i = new Intent(this,MyProfile.class);
        startActivity(i);
    }

}