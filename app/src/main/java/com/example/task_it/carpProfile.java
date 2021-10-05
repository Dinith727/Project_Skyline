package com.example.task_it;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class carpProfile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //side Bar
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;



    TextView name, location, telNo, status, fee, jobs;
    Button fav, assign;
    DatabaseReference dbRef;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carpprofile);

        //side bar
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        //toolbar=findViewById(R.id.toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        String phoneNo = getIntent().getStringExtra("Phone");

        name = findViewById(R.id.name_carp);
        location = findViewById(R.id.loc_carp);
        telNo = findViewById(R.id.tel_carp);
        status = findViewById(R.id.status_carp);
        fee = findViewById(R.id.fee_carp);
        jobs = findViewById(R.id.jobs_carp);


        dbRef = FirebaseDatabase.getInstance().getReference("Employee").child(phoneNo);

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.getChildrenCount() >= 0) {
                    name.setText(snapshot.child("firstName").getValue().toString());
                    location.setText(snapshot.child("location").getValue().toString());
                    telNo.setText(snapshot.child("telNo").getValue().toString());
                    status.setText(snapshot.child("status").getValue().toString());
                    fee.setText(snapshot.child("fee").getValue().toString());
                    jobs.setText(snapshot.child("jobs").getValue().toString());
                } else
                    Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


    }

    public void Fav(View view) {

        Favourites f1 = new Favourites();

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Favourites");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                f1.setName(name.getText().toString().trim());
                f1.setTelNo(telNo.getText().toString().trim());

                dbRef.child(f1.getTelNo()).setValue(f1);
                Toast.makeText(getApplicationContext(), "Added to favourites", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void assign(View v) {
        String stat = status.getText().toString().trim();
        String phone = telNo.getText().toString().trim();


        if (stat.equalsIgnoreCase("busy")) {
            Toast.makeText(getApplicationContext(), "Status: Busy. Employee Unavailable", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(carpProfile.this, com.example.task_it.AssignTask.class);
            intent.putExtra("tel", phone);
            startActivity(intent);
        }

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
