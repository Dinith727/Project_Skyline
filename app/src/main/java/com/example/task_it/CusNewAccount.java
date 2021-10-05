package com.example.task_it;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CusNewAccount extends AppCompatActivity {

    EditText fName, lName, email, telNo, password;
    Button submit;
    DatabaseReference dbRef;
    Customer cus;

    private void clearControls(){
        fName.setText("");
        lName.setText("");
        email.setText("");
        telNo.setText("");
        password.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_new_account);

        fName = findViewById(R.id.et_fName);
        lName = findViewById(R.id.et_lName);
        email = findViewById(R.id.et_email);
        telNo = findViewById(R.id.et_telNo2);
        password = findViewById(R.id.et_password4);
        submit = findViewById(R.id.btn_submitLogin2Next);

        cus = new Customer();

        //SAVE BTN
        Intent i = new Intent(this,CusNewLocation.class);
        dbRef = FirebaseDatabase.getInstance().getReference().child("Customer");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    if(TextUtils.isEmpty(fName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter First Name", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(lName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter Last Name", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(email.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter email address", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(telNo.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter Contact Number", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(password.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
                    else{
                        cus.setfName(fName.getText().toString().trim());
                        cus.setlName(lName.getText().toString().trim());
                        cus.setEmail(email.getText().toString().trim());
                        cus.setTelNo(telNo.getText().toString().trim());
                        cus.setPassword(password.getText().toString().trim());
                        //dbRef.push().setValue(cus);
                        //Toast.makeText(getApplicationContext(), "Successfully Saved", Toast.LENGTH_SHORT).show();

                        i.putExtra("fName", cus.getfName());
                        i.putExtra("lName", cus.getlName());
                        i.putExtra("email", cus.getEmail());
                        i.putExtra("telNo", cus.getTelNo());
                        i.putExtra("password", cus.getPassword());

                        startActivity(i);
                    }
                }catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Invalid Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void BackToCreate(View view){
        Intent d = new Intent(this,CreateNewAccount.class);
        startActivity(d);
    }
}
