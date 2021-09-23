package com.example.task_it;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EmpRegEn extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private static final String[] paths = {"Select Category", "Mechanic", "Plumber", "Electrician", "Labour", "Security", "Mover", "Baby-Sitter"};

    String cat;
    EditText name, nic, email, phone;
    Button submit;
    DatabaseReference dbRef;
    Employee emp;

    private void clearControls(){
        name.setText("");
        nic.setText("");
        email.setText("");
        phone.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_reg_en);


        spinner = (Spinner)findViewById(R.id.catSpin);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(EmpRegEn.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



        name = findViewById(R.id.addpw55);
        nic = findViewById(R.id.addconpw55);
        email = findViewById(R.id.txtemail1);
        phone = findViewById(R.id.txtphone1);

        submit = findViewById(R.id.sub55);
        emp = new Employee();

        Intent intent = new Intent(EmpRegEn.this,EmpPwConfig.class);

        //SAVE BTN
        dbRef = FirebaseDatabase.getInstance().getReference().child("Employee");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(TextUtils.isEmpty(name.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter Name", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(nic.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter NIC", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(email.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter Email", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(phone.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter Contact Number", Toast.LENGTH_SHORT).show();
                    else if(cat == "null")
                        Toast.makeText(getApplicationContext(), "Enter Category", Toast.LENGTH_SHORT).show();
                    else{
                        emp.setFirstName(name.getText().toString().trim());
                        emp.setNIC(nic.getText().toString().trim());
                        emp.setEmail(email.getText().toString().trim());
                        emp.setTelNo(phone.getText().toString().trim());
                        emp.setCategory(cat);
                        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.hasChild(phone.getText().toString())){
                                    Toast.makeText(getApplicationContext(), "Account Already Exist", Toast.LENGTH_SHORT).show();
                                }else{
                                   // dbRef.push().setValue(emp);
                                   // dbRef.child(phone.getText().toString().trim()).setValue(emp);
                                   // Toast.makeText(getApplicationContext(), "Add A Password", Toast.LENGTH_SHORT).show();
                                    intent.putExtra("Phone",emp.getTelNo());
                                    intent.putExtra("name",emp.getFirstName());
                                    intent.putExtra("nic",emp.getNIC());
                                    intent.putExtra("email",emp.getEmail());
                                    intent.putExtra("cat",emp.getCategory());
                                    startActivity(intent);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                }catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Invalid Details", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void exit(View view){
        Intent intent2 = new Intent(this,EmpLogin.class);
        startActivity(intent2);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                cat = "null";
                break;
            case 1:
                cat = "Mechanic";
                break;
            case 2:
                cat = "Plumber";
                break;
            case 3:
                cat = "Electrician";
                break;
            case 4:
                cat = "Labour";
                break;
            case 5:
                cat = "Security";
                break;
            case 6:
                cat = "Mover";
                break;
            case 7:
                cat = "Baby-Sitter";
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }
}