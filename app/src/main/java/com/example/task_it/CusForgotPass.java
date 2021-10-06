package com.example.task_it;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CusForgotPass extends AppCompatActivity {

    EditText email, telNo;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_forgot_pass);

        email = findViewById(R.id.et_email2);
        telNo = findViewById(R.id.et_email4);
    }

    public void BackToCusLog(View view){
        Intent i = new Intent(this,LoginPage_Cus.class);
        startActivity(i);
    }

    public void Submit(View view){
        CusTempData t = new CusTempData();
        Intent inte = new Intent(this,MyProfile.class);
        DatabaseReference logRef = FirebaseDatabase.getInstance().getReference().child("Customer");
        DatabaseReference passRef = logRef.child(telNo.getText().toString());
        logRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(telNo.getText().toString())){
                    passRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String em = snapshot.child("email").getValue().toString();
                            if (em.equals(email.getText().toString())){
                                t.setTelNo(telNo.getText().toString());
                                startActivity(inte);
                            }else
                                Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }else
                    Toast.makeText(getApplicationContext(), "Account Does Not Exist", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}