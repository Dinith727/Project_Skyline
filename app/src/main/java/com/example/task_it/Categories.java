package com.example.task_it;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Categories extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //side Bar
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);

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

    public void showCarpentry(View v) {
        Intent intent = new Intent (Categories.this, Carpentry.class);
        startActivity(intent);
    }

    public void showElectrician(View v) {
        Intent intent = new Intent (Categories.this, Electrician.class);
        startActivity(intent);
    }

    public void showCleaning(View v) {
        Toast.makeText(getApplicationContext(), "BLEH", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent (Categories.this, Cleaning.class);
        startActivity(intent);
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