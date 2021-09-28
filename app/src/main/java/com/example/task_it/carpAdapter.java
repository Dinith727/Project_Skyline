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
        holder.name.setText(c1.getName());
        holder.location.setText(c1.getLocation());
        holder.fee.setText(c1.getFee());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, carpProfile.class);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CarpViewHolder extends RecyclerView.ViewHolder{

        TextView name, location, fee;
        Button view;

        public CarpViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.carpName);
            location = itemView.findViewById(R.id.carpLocation);
            fee = itemView.findViewById(R.id.carpFee);
            view = itemView.findViewById(R.id.view);
        }


    }

    public void showProfile (View v){
        Intent i = new Intent(v.getContext(), carpProfile.class);
        v.getContext().startActivity(i);
    }


}
