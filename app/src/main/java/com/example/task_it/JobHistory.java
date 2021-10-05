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
public class JobHistory extends AppCompatActivity{

    RecyclerView rv;
    com.example.task_it.JobAdapter carpAd;
    ArrayList<com.example.task_it.JobHisClass> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_history);
        rv = findViewById(R.id.jobList);

        Employee emp = new Employee();
        String ph = emp.getPo();

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        carpAd = new com.example.task_it.JobAdapter(this, list);
        rv.setAdapter(carpAd);
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Job");
        readRef.orderByChild("emp").equalTo(ph).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    JobHisClass cd = dataSnapshot.getValue(JobHisClass.class);
                    list.add(cd);
//                    com.example.task_it.JobHisClass cd = dataSnapshot.getValue(com.example.task_it.JobHisClass.class);
//                    list.add(cd);
                }
                carpAd.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull  DatabaseError error) { }
        });
    }

}



