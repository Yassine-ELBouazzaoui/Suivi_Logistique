package com.example.suivilogistique;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterProduit extends RecyclerView.Adapter<AdapterProduit.contenair>{

    Context context;
    ArrayList<Produit> produits;

    public AdapterProduit(Context context, ArrayList<Produit> produits) {
        this.context = context;
        this.produits = produits;
    }

    @NonNull
    @Override
    public AdapterProduit.contenair onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(context);
        View v = li.inflate(R.layout.model_produit,parent,false);
        return new AdapterProduit.contenair(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProduit.contenair holder, int position) {
        holder.CodeP.setText(produits.get(position).getCodeP());
        holder.NomC.setText(produits.get(position).getNomClient());
        holder.NomP.setText(produits.get(position).getNom());
        holder.PoidP.setText(produits.get(position).getPoids());
        holder.PrixP.setText(produits.get(position).getPrix());
           }

    @Override
    public int getItemCount() {
        return produits.size();
    }

    public class contenair extends RecyclerView.ViewHolder {
        TextView CodeP,NomC,NomP,PoidP,PrixP;
        public contenair(@NonNull View itemView) {
            super(itemView);
            CodeP = itemView.findViewById(R.id.CodeP);
            NomC = itemView.findViewById(R.id.NomC);
            NomP = itemView.findViewById(R.id.NomP);
            PoidP = itemView.findViewById(R.id.PoidP);
            PrixP = itemView.findViewById(R.id.PrixP);
        }
    }
}
