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

public class EmpLogin extends AppCompatActivity {

    EditText phone, password;
    Button log;
    DatabaseReference dbRef;
    Employee emp;

    private void clearControls(){
        phone.setText("");
        password.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_login);

        phone = findViewById(R.id.inputphone5);
        password = findViewById(R.id.inpw1);

        log = findViewById(R.id.btnlog1);

        emp = new Employee();



    }
    public void CreateAccount(View view){
        Intent intent = new Intent(this,empRegAccept.class);
        startActivity(intent);
    }
    public void ForgotPw(View view){
        Intent intent = new Intent(this,EmpRegResetPw.class);
        startActivity(intent);
    }
    public void Login(View view){

        String pp = password.getText().toString() ;

        Intent q = new Intent(this,EmpProfile.class);
        DatabaseReference logRef = FirebaseDatabase.getInstance().getReference().child("Employee");
        DatabaseReference passRef = logRef.child(phone.getText().toString());
        logRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(phone.getText().toString())){
                    passRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Employee pwd = snapshot.getValue(Employee.class);
                            String pw = pwd.getPassword();
                            if(pp.isEmpty()){
                                Toast.makeText(getApplicationContext(), "Enter Credentials", Toast.LENGTH_SHORT).show();
                            }else if(pw.equals(password.getText().toString())){
                                q.putExtra("phone",phone.getText().toString());
                                startActivity(q);
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
}}