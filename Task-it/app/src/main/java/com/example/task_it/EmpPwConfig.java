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

public class EmpPwConfig extends AppCompatActivity {

    EditText pw, conpw;
    Button can, sub;
    DatabaseReference dbRef;
    Employee emp;

    private void clearControls(){
        pw.setText("");
        conpw.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_pw_config);

        pw = findViewById(R.id.addpw55);
        conpw = findViewById(R.id.addconpw55);
        can = findViewById(R.id.ex23);
        sub = findViewById(R.id.sub55);

        emp = new Employee();



    }

    public void add(View view){

        String pw1 = pw.getText().toString();
        String pw2 = conpw.getText().toString();

        Intent q = new Intent(this,EmpRegSuccess.class);

        String ph = getIntent().getStringExtra("Phone");
        String na = getIntent().getStringExtra("name");
        String ni = getIntent().getStringExtra("nic");
        String em = getIntent().getStringExtra("email");
        String ca = getIntent().getStringExtra("cat");

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Employee");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(pw1.equals(pw2) && !(pw1.isEmpty())){

                    emp.setFirstName(na);
                    emp.setNIC(ni);
                    emp.setEmail(em);
                    emp.setTelNo(ph);
                    emp.setCategory(ca);
                    emp.setPassword(pw.getText().toString().trim());
                    dbRef.child(ph).setValue(emp);
                    dbRef.push().setValue(emp);
                        Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_SHORT).show();
                        startActivity(q);
                }else
                    Toast.makeText(getApplicationContext(), "Two Passwords Dosen't Match or Empty", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void back(View view){
        Intent d = new Intent(this,EmpRegEn.class);
        startActivity(d);
    }

}