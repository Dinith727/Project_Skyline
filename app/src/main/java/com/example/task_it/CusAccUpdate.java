package com.example.task_it;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CusAccUpdate extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //side Bar
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    EditText fName, lName, email, password;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_acc_update);

        //side bar
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        //toolbar=findViewById(R.id.toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);


        fName = findViewById(R.id.et_firName);
        lName = findViewById(R.id.et_lasName);
        email = findViewById(R.id.et_emailAdd);
        password = findViewById(R.id.et_passWordd);
        submit = findViewById(R.id.btn_updateCusAcc);

        String ph = getIntent().getStringExtra("telNo");
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Customer").child(ph);
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                fName.setText(snapshot.child("fName").getValue().toString());
                lName.setText(snapshot.child("lName").getValue().toString());
                email.setText(snapshot.child("email").getValue().toString());
                password.setText(snapshot.child("password").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void BackToCreNewAcc(View view){
        Intent i = new Intent(this,MyCusAccount.class);
        startActivity(i);
    }

    public void Submit(View view){
        Intent in = new Intent(this,MyCusAccount.class);

        String ph = getIntent().getStringExtra("telNo");
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Customer");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dbRef.child(ph).child("fName").setValue(fName.getText().toString());
                dbRef.child(ph).child("lName").setValue(lName.getText().toString());
                dbRef.child(ph).child("email").setValue(email.getText().toString());
                dbRef.child(ph).child("password").setValue(password.getText().toString());

                Toast.makeText(getApplicationContext(), "Account Updated Successfully", Toast.LENGTH_SHORT).show();
                startActivity(in);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Account Update Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //side bar
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    //side bar
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_cat:
                Intent in=new Intent(this,Categories.class);
                startActivity(in);
                break;
            case R.id.nav_pri:
//                Intent inte=new Intent(this,CustomerCare.class);
//                startActivity(inte);
                break;
            case R.id.nav_cusC:
                Intent i0=new Intent(this,CustomerCare.class);
                startActivity(i0);
                break;
            case R.id.profile:
                Intent i = new Intent(this,MyProfile.class);
                startActivity(i);
                break;
            case R.id.fav:
                Intent in1 = new Intent(this, FavList.class);
                startActivity(in1);
                break;
            case R.id.jobHis:
//                Intent in2 = new Intent(this, MainActivity.class);
//                startActivity(in2);
                break;
            case R.id.logout:
                Intent in3 = new Intent(this, LoginPage_Cus.class);
                startActivity(in3);
                break;
        }
        return true;
    }


}