package com.example.task_it;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EmpPayment extends AppCompatActivity {

    TextView desc, start, end, duration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_payment);
        desc = findViewById(R.id.desc);
        start = findViewById(R.id.start);
        /*end = findViewById(R.id.endTime);
        duration = findViewById(R.id.duration);*/
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Job").child("job1");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    desc.setText(snapshot.child("jobId").getValue().toString());
                    start.setText(snapshot.child("startTime").getValue().toString());
                    /*end.setText(snapshot.child("endTime").getValue().toString());
                    duration.setText(snapshot.child("Duration").getValue().toString());*/
                } else
                    Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show(); }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }


}
