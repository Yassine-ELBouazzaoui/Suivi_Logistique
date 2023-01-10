package com.example.suivilogistique;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterLocation extends RecyclerView.Adapter<AdapterLocation.contenair> {

    Context context;
    ArrayList<LocationUser> locationUsers;

    public AdapterLocation(Context context, ArrayList<LocationUser> locationUsers) {
        this.context = context;
        this.locationUsers = locationUsers;
    }

    @NonNull
    @Override
    public AdapterLocation.contenair onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(context);
        View v = li.inflate(R.layout.model_location,parent,false);
        return new AdapterLocation.contenair(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLocation.contenair holder, @SuppressLint("RecyclerView") int position) {
        holder.location.setText(locationUsers.get(position).getPosition());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ReceiveLocation.class);
                Bundle bnd = new Bundle();
                bnd.putSerializable("location",locationUsers.get(position));
                intent.putExtras(bnd);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return locationUsers.size();
    }

    public class contenair extends RecyclerView.ViewHolder{
        TextView location;
        public contenair(@NonNull View itemView) {
            super(itemView);
            location = itemView.findViewById(R.id.tvText);
        }
    }
}
