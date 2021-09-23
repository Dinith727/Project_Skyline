package com.example.task_it;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText txtID, txtName, txtAdd, txtConNo;
    Button btnSave, btnShow, btnUpdate, btnDelete;
    DatabaseReference dbRef;
    Employee emp;

    private void clearControls(){
        txtID.setText("");
        txtName.setText("");
        txtAdd.setText("");
        txtConNo.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtID = findViewById(R.id.txtID);
        txtName = findViewById(R.id.txtName);
        txtAdd = findViewById(R.id.txtAdd);
        txtConNo = findViewById(R.id.txtConNo);

        btnSave = findViewById(R.id.save);
        btnShow = findViewById(R.id.show);
        btnDelete = findViewById(R.id.delete);
        btnUpdate = findViewById(R.id.update);

        emp = new Employee();

        //SAVE BTN
        dbRef = FirebaseDatabase.getInstance().getReference().child("Employee");
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(TextUtils.isEmpty(txtID.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter ID", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter Name", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtAdd.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter Address", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtConNo.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter Contact Number", Toast.LENGTH_SHORT).show();
                    else{
                        emp.setEmpID(txtID.getText().toString().trim());
                        emp.setFirstName(txtName.getText().toString().trim());
                        emp.setLocation(txtAdd.getText().toString().trim());
                        emp.setTelNo(txtConNo.getText().toString().trim());
                        dbRef.push().setValue(emp);
                        Toast.makeText(getApplicationContext(), "Successfully Saved", Toast.LENGTH_SHORT).show();
                    }
                }catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Invalid Number", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}