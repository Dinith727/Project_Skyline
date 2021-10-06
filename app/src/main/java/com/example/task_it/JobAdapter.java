package com.example.task_it;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class JobAdapter extends  RecyclerView.Adapter<JobAdapter.CarpViewHolder>{

        Context context;
        ArrayList<JobHisClass> list;
        public JobAdapter(Context context, ArrayList<JobHisClass> list) {
        this.context = context;
        this.list = list;
        }




@NonNull
@Override
public CarpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.job_row,parent,false);
        return new CarpViewHolder(v);
        }


@Override
public void onBindViewHolder(@NonNull  CarpViewHolder holder, int position) {

        JobHisClass c1 = list.get(position);
        holder.JobId.setText(c1.getJobid());
        holder.JobDate.setText(c1.getDate());
        holder.JobFee.setText(c1.getFee());
        holder.JobDuration.setText(c1.getDuration());
        }

@Override
public int getItemCount() {
        return list.size();
        }

public static class CarpViewHolder extends RecyclerView.ViewHolder{

    TextView JobId, JobDate, JobFee, JobDuration;
    public CarpViewHolder(@NonNull View itemView) {
        super(itemView);
        JobId = itemView.findViewById(R.id.jid);
        JobDate = itemView.findViewById(R.id.jDate);
        JobFee = itemView.findViewById(R.id.jf);
        JobDuration = itemView.findViewById(R.id.jobDu);
    }


}

}
