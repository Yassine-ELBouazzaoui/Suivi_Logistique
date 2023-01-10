package com.example.suivilogistique;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suivilogistique.ui.Marchandise.AfficherProduitFragment;
import com.example.suivilogistique.ui.Marchandise.LocationFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AdapterMarchandise extends RecyclerView.Adapter<AdapterMarchandise.contenair> {
    Context context;
    ArrayList<Marchandise> marchandises;

    public AdapterMarchandise(Context context, ArrayList<Marchandise> marchandises) {
        this.context = context;
        this.marchandises = marchandises;
    }

    @NonNull
    @Override
    public AdapterMarchandise.contenair onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(context);
        View v = li.inflate(R.layout.model_marchandise,parent,false);
        return new AdapterMarchandise.contenair(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMarchandise.contenair holder, @SuppressLint("RecyclerView") int position) {
        holder.username.setText(marchandises.get(position).getUsername());
        holder.CodeM.setText(marchandises.get(position).getCodeM());
        holder.villeF.setText(marchandises.get(position).getVilleF());
        holder.villeD.setText(marchandises.get(position).getVilleD());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AfficherProduitFragment fr = new AfficherProduitFragment();
                Bundle bnd = new Bundle();
                bnd.putString("CodeM",marchandises.get(position).getCodeM());
                fr.setArguments(bnd);
                ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.conteneur1,fr).commit();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocationFragment lf = new LocationFragment();
                Bundle bnd = new Bundle();
                bnd.putString("CodeM",marchandises.get(position).getCodeM());
                lf.setArguments(bnd);
                ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.conteneur1,lf).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return marchandises.size();
    }

    public class contenair extends RecyclerView.ViewHolder {
        TextView username,CodeM,villeF,villeD;
        FloatingActionButton btn;
        public contenair(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.displayusername);
            CodeM = itemView.findViewById(R.id.displayCodeM);
            villeF = itemView.findViewById(R.id.displayvilleF);
            villeD = itemView.findViewById(R.id.displayvilleD);
            btn = itemView.findViewById(R.id.fab2);
        }
    }
}
