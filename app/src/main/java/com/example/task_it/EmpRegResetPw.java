package com.example.task_it;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EmpRegResetPw extends AppCompatActivity {

    EditText ph, nic, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_reg_reset_pw);

        ph = findViewById(R.id.inputphone);
        nic = findViewById(R.id.inputphone2);
        email = findViewById(R.id.inputphone5);

    }
    public void Back(View view){
        Intent intent = new Intent(this,EmpLogin.class);
        startActivity(intent);
    }
    public void Send(View view){
        Intent in = new Intent(this,EmpResetPw.class);
        String pho = ph.getText().toString();
        String NIC = nic.getText().toString();
        String Email = email.getText().toString();
        if(pho.isEmpty())
            Toast.makeText(getApplicationContext(), "Enter Phone Number", Toast.LENGTH_SHORT).show();
        else{
            DatabaseReference logRef = FirebaseDatabase.getInstance().getReference().child("Employee");
            logRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChild(pho)){
                        DatabaseReference ref = logRef.child(pho);
                        ref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(NIC.equals(snapshot.child("nic").getValue().toString())){
                                    if(Email.equals(snapshot.child("email").getValue().toString())){
                                        Toast.makeText(getApplicationContext(), "Enter new password", Toast.LENGTH_SHORT).show();
                                        in.putExtra("Phone",pho);
                                        startActivity(in);
                                    }else
                                      Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
                                }else
                                    Toast.makeText(getApplicationContext(), "Invalid NIC", Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }else{
                        Toast.makeText(getApplicationContext(), "Account Does Not Exist", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



        }

    }
}