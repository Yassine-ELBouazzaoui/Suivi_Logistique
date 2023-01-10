package com.example.suivilogistique;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.contenair> {
    Context context;
    ArrayList<Users> users;

    public AdapterUser(Context context, ArrayList<Users> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public AdapterUser.contenair onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(context);
        View v = li.inflate(R.layout.model_user,parent,false);
        return new AdapterUser.contenair(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUser.contenair holder, int position) {
           holder.usename.setText(users.get(position).getUsername());
           holder.nom.setText(users.get(position).getNom());
           holder.email.setText(users.get(position).getEmail());
           holder.tele.setText(users.get(position).getTele());
           holder.role.setText(users.get(position).getAs());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class contenair extends RecyclerView.ViewHolder {
        TextView usename,nom,email,tele,role;
        public contenair(@NonNull View itemView) {
            super(itemView);
            usename = itemView.findViewById(R.id.displayname);
            role = itemView.findViewById(R.id.displayname3);
            nom = itemView.findViewById(R.id.displaynom);
            email = itemView.findViewById(R.id.displayemail);
            tele = itemView.findViewById(R.id.displaycontact);

        }
    }
}
