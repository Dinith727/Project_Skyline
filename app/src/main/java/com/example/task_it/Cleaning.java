package com.example.task_it;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Cleaning extends AppCompatActivity {
    RecyclerView rv;
    DatabaseReference database;
    com.example.task_it.carpAdapter carpAd;
    ArrayList<com.example.task_it.carpDetails> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categorylist);


        rv = findViewById(R.id.carpList);
        database = FirebaseDatabase.getInstance().getReference("Employee");
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        carpAd = new com.example.task_it.carpAdapter(this, list);
        rv.setAdapter(carpAd);

        database.orderByChild("category").equalTo("Cleaning").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    com.example.task_it.carpDetails cd = dataSnapshot.getValue(com.example.task_it.carpDetails.class);
                    list.add(cd);
                }
                carpAd.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull  DatabaseError error) { }
        });
    }
}