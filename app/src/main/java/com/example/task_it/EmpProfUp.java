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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EmpProfUp extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    TextView name;
    TextView equip,price,location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_prof_up);

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        //toolbar=findViewById(R.id.toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        name = findViewById(R.id.nameProf);
        equip = findViewById(R.id.equp);
        price = findViewById(R.id.pup);
        location = findViewById(R.id.dislo);

        String ph = getIntent().getStringExtra("iphone");


        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Employee").child(ph);
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    name.setText(snapshot.child("firstName").getValue().toString());
                    equip.setText(snapshot.child("requiredEquipment").getValue().toString());
                    price.setText(snapshot.child("fee").getValue().toString());
                    location.setText(snapshot.child("location").getValue().toString());
                } else
                    Toast.makeText(getApplicationContext(), "No SOURCE TO DISPLAY", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }


    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String ph = getIntent().getStringExtra("iphone");

        switch (item.getItemId()) {
            case R.id.profile:
                Intent q = new Intent(this, EmpProfile.class);
                q.putExtra("phone",ph);
                startActivity(q);
                break;
            case R.id.taskHis:
                Intent qr = new Intent(this,JobHistory.class);
                qr.putExtra("iphone",getIntent().getStringExtra("phone"));
                startActivity(qr);
                break;
            case R.id.settings:
                break;
            case R.id.ongoing:
//                Intent i = new Intent(this, activity_show_hotel.class);
//                startActivity(i);
                break;
            case R.id.notification:
                break;
            case R.id.logout:
                Intent i1 = new Intent(this, EmpLogin.class);
                startActivity(i1);
                break;
        }
        return true;
    }

    public void save(View view){
        String ph = getIntent().getStringExtra("iphone");
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Employee");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dbRef.child(ph).child("requiredEquipment").setValue(equip.getText().toString());
                dbRef.child(ph).child("fee").setValue(price.getText().toString());
                dbRef.child(ph).child("location").setValue(location.getText().toString());
                Toast.makeText(getApplicationContext(), "Data updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void reset(View view){
        String ph = getIntent().getStringExtra("iphone");
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Employee").child(ph);
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    equip.setText(snapshot.child("requiredEquipment").getValue().toString());
                    price.setText(snapshot.child("fee").getValue().toString());
                    location.setText(snapshot.child("location").getValue().toString());
                } else
                    Toast.makeText(getApplicationContext(), "No SOURCE TO DISPLAY", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}