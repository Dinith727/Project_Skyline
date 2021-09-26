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

public class EmpResetPw extends AppCompatActivity {

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
        setContentView(R.layout.activity_emp_reset_pw);

        pw = findViewById(R.id.rthth88);
        conpw = findViewById(R.id.addconpw5588);
        can = findViewById(R.id.resetvals);
        sub = findViewById(R.id.savechan);

        emp = new Employee();
    }
    public void add(View view){

        String pw1 = pw.getText().toString();
        String pw2 = conpw.getText().toString();

        Intent q = new Intent(this,EmpLogin.class);

        String ph = getIntent().getStringExtra("Phone");

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Employee").child(ph);
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(pw1.equals(pw2) && !(pw1.isEmpty())){
                    dbRef.child("password").setValue(pw1);
                    Toast.makeText(getApplicationContext(), "Reset Successful", Toast.LENGTH_SHORT).show();
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
        Intent d = new Intent(this,EmpLogin.class);
        startActivity(d);
    }
}