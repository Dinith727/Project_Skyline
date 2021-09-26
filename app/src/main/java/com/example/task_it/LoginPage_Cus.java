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

public class LoginPage_Cus extends AppCompatActivity {

    EditText phone, password;
    Button log;
    DatabaseReference dbRef;
    Customer cus;
    CusTempData t;

    private void clearControls(){
        phone.setText("");
        password.setText("");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page_cus);

        phone = findViewById(R.id.et_telNo);
        password = findViewById(R.id.et_password);

        log = findViewById(R.id.btn_submitLogin);

        cus = new Customer();
        t = new CusTempData();
    }

    public void CreateNew(View view){
        Intent i = new Intent(this,CreateNewAccount.class);
        startActivity(i);
    }

    public void Forgot(View view){
        Intent in = new Intent(this,CusForgotPass.class);
        startActivity(in);
    }

    public void Login(View view){

        Intent inte = new Intent(this,MyProfile.class);
        DatabaseReference logRef = FirebaseDatabase.getInstance().getReference().child("Customer");
        DatabaseReference passRef = logRef.child(phone.getText().toString());
        logRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(phone.getText().toString())){
                    passRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Customer pwd = snapshot.getValue(Customer.class);
                            String pw = pwd.getPassword();
                            if(pw.equals(password.getText().toString())){
                                t.setTelNo(phone.getText().toString());
                                startActivity(inte);
                            }else
                                Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_SHORT).show();
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