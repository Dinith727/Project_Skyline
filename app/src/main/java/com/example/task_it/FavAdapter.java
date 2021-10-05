package com.example.task_it;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.MyViewHolder>{

    Context context;
    ArrayList<Fav> list;

    public FavAdapter(Context context, ArrayList<Fav> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_fav,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Fav fav = list.get(position);
        holder.name.setText(fav.getName());
        holder.telNo.setText(fav.getTelNo());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, telNo;

       public MyViewHolder(@NonNull View itemView) {
           super(itemView);

           name = itemView.findViewById(R.id.txt_favName);
           telNo = itemView.findViewById(R.id.txt_favTelno);
       }
   }
}