package com.example.task_it;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class carpAdapter extends RecyclerView.Adapter<carpAdapter.CarpViewHolder>{

    Context context;
    ArrayList<carpDetails> list;

    public carpAdapter(Context context, ArrayList<carpDetails> list) {
        this.context = context;
        this.list = list;

    }




    @NonNull
    @Override
    public CarpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.carpenter_row,parent,false);
        return new CarpViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull  CarpViewHolder holder, int position) {

        carpDetails c1 = list.get(position);
        holder.firstName.setText(c1.getFirstName());
        holder.location.setText(c1.getLocation());
        holder.fee.setText(c1.getFee());
        holder.telNo.setText(c1.getTelNo());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, carpProfile.class);
                intent.putExtra("Phone", c1.getTelNo().toString());
                v.getContext().startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CarpViewHolder extends RecyclerView.ViewHolder{

        TextView firstName, location, fee, telNo;
        Button view;


        public CarpViewHolder(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.carpName);
            location = itemView.findViewById(R.id.carpLocation);
            fee = itemView.findViewById(R.id.carpFee);
            telNo = itemView.findViewById(R.id.telNo);
            view = itemView.findViewById(R.id.view);

        }


    }



}
