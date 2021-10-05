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
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyProfile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //side Bar
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    CusTempData cus = new CusTempData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        //side bar
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        //toolbar=findViewById(R.id.toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

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
//                Intent in=new Intent(this,updateUser.class);
//                startActivity(in);
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


    public void MyProfile(View view){
        Intent i = new Intent(this,MyCusAccount.class);
        startActivity(i);
    }

    public void MyLocation(View view){
        Intent i = new Intent(this,CusLocation.class);
        startActivity(i);
    }

    public void DeleteAcc(View view){
        Intent i = new Intent(this,LoginPage_Cus.class);
        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Customer");
        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(cus.getTelNo())){
                    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Customer").child(cus.getTelNo());
                    dbRef.removeValue();
                    Toast.makeText(getApplicationContext(), "Account Deleted", Toast.LENGTH_SHORT).show();
                    startActivity(i);
                }else
                    Toast.makeText(getApplicationContext(), "No SOURCE TO Delete", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}