//package com.example.task_it;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.provider.ContactsContract;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//public class CusNewLocation extends AppCompatActivity {
//
//    EditText HouseNo, street, city, district, zipCode;
//    Button submit;
//    DatabaseReference dbRef;
//    Customer cus;
//
//    private void clearControls(){
//        HouseNo.setText("");
//        street.setText("");
//        city.setText("");
//        district.setText("");
//        zipCode.setText("");
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_cus_new_location);
//
//        HouseNo = findViewById(R.id.et_HouseNo3);
//        street = findViewById(R.id.et_street3);
//        city = findViewById(R.id.et_city3);
//        district = findViewById(R.id.et_district3);
//        zipCode = findViewById(R.id.et_zipCode3);
//        submit = findViewById(R.id.btn_submitAdd);
//
//        cus = new Customer();
//
//        //Save
//
//        String fn = getIntent().getStringExtra("fName");
//        String ln = getIntent().getStringExtra("lName");
//        String em = getIntent().getStringExtra("email");
//        String tn = getIntent().getStringExtra("telNo");
//        String pa = getIntent().getStringExtra("password");
//
//        Intent in = new Intent(this,LoginPage_Cus.class);
//        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Customer");
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try{
//                    if(TextUtils.isEmpty(HouseNo.getText().toString()))
//                        Toast.makeText(getApplicationContext(), "Enter House No", Toast.LENGTH_SHORT).show();
//                    else if(TextUtils.isEmpty(street.getText().toString()))
//                        Toast.makeText(getApplicationContext(), "Enter Street Name", Toast.LENGTH_SHORT).show();
//                    else if(TextUtils.isEmpty(city.getText().toString()))
//                        Toast.makeText(getApplicationContext(), "Enter city address", Toast.LENGTH_SHORT).show();
//                    else if(TextUtils.isEmpty(district.getText().toString()))
//                        Toast.makeText(getApplicationContext(), "Enter District", Toast.LENGTH_SHORT).show();
//                    else if(TextUtils.isEmpty(zipCode.getText().toString()))
//                        Toast.makeText(getApplicationContext(), "Enter Zip Code", Toast.LENGTH_SHORT).show();
//                    else{
//
//
//                        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                cus.setHouseNo(HouseNo.getText().toString().trim());
//                                cus.setStreet(street.getText().toString().trim());
//                                cus.setCity(city.getText().toString().trim());
//                                cus.setDistrict(district.getText().toString().trim());
//                                cus.setZipCode(zipCode.getText().toString().trim());
//
//                                cus.setfName(fn);
//                                cus.setlName(ln);
//                                cus.setEmail(em);
//                                cus.setTelNo(tn);
//                                cus.setPassword(pa);
//                                dbRef.child(em).setValue(cus);
//                                dbRef.push().setValue(cus);
//                                Toast.makeText(getApplicationContext(), "Successfully Saved", Toast.LENGTH_SHORT).show();
//                                startActivity(in);
//
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//
//                            }
//                        });
//
//                    }
//                }catch(NumberFormatException e){
//                    Toast.makeText(getApplicationContext(), "Invalid Data", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//
//
//
//
//
//
//
//
//    }
//
//}

package com.example.task_it;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CusNewLocation extends AppCompatActivity {

    EditText HouseNo, street, city, district, zipCode;
    Button submit;
    DatabaseReference dbRef;
    Customer cus;

    private void clearControls(){
        HouseNo.setText("");
        street.setText("");
        city.setText("");
        district.setText("");
        zipCode.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_new_location);

        HouseNo = findViewById(R.id.et_HouseNo3);
        street = findViewById(R.id.et_street3);
        city = findViewById(R.id.et_city3);
        district = findViewById(R.id.et_district3);
        zipCode = findViewById(R.id.et_zipCode3);
        submit = findViewById(R.id.btn_submitAdd);

        cus = new Customer();



    }

    public void addCus(View view){

        Intent in = new Intent(this,LoginPage_Cus.class); //redirecting to other page

        String fn = getIntent().getStringExtra("fName");
        String ln = getIntent().getStringExtra("lName");
        String em = getIntent().getStringExtra("email");
        String tn = getIntent().getStringExtra("telNo");
        String pa = getIntent().getStringExtra("password");

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Customer");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    if (TextUtils.isEmpty(HouseNo.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter House No", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(street.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter Street Name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(city.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter city address", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(district.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter District", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(zipCode.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter Zip Code", Toast.LENGTH_SHORT).show();
                    else {
                        cus.setHouseNo(HouseNo.getText().toString().trim());
                        cus.setStreet(street.getText().toString().trim());
                        cus.setCity(city.getText().toString().trim());
                        cus.setDistrict(district.getText().toString().trim());
                        cus.setZipCode(zipCode.getText().toString().trim());

                        cus.setfName(fn);
                        cus.setlName(ln);
                        cus.setEmail(em);
                        cus.setTelNo(tn);
                        cus.setPassword(pa);
                        dbRef.child(tn).setValue(cus);

                        Toast.makeText(getApplicationContext(), "Successfully Saved", Toast.LENGTH_SHORT).show();
                        startActivity(in);
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid Data", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void BackToCusNewAcc(View view){
        Intent d = new Intent(this,CreateNewAccount.class);
        startActivity(d);
    }
}
