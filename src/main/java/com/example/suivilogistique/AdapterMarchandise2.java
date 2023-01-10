package com.example.suivilogistique;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdapterMarchandise2 extends RecyclerView.Adapter<AdapterMarchandise2.contenair> {
    Context context;
    ArrayList<Marchandise> marchandises;
    DatabaseReference dbref;
    ArrayList<View> v = new ArrayList<>();
    public AdapterMarchandise2(Context context, ArrayList<Marchandise> marchandises) {
        this.context = context;
        this.marchandises = marchandises;
    }

    @NonNull
    @Override
    public AdapterMarchandise2.contenair onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(context);
        View v = li.inflate(R.layout.model_marchandise2,parent,false);
        return new AdapterMarchandise2.contenair(v);
    }

    @Override
    public void onBindViewHolder(@NonNull contenair holder, @SuppressLint("RecyclerView") int position) {
        holder.villeF.setText(marchandises.get(position).getVilleF());
        holder.villeD.setText(marchandises.get(position).getVilleD());
        dbref = FirebaseDatabase.getInstance().getReference();
        dbref.child("Database").child("Produits").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int nb =0;
                double prix = 0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (String.valueOf(dataSnapshot.child("codeM").getValue()).equals(marchandises.get(position).getCodeM())) {
                        prix += Double.parseDouble(dataSnapshot.child("prix").getValue().toString());
                        nb++;
                    }

                }
                holder.prixM.setText(prix + " DH");
                holder.nbproduit.setText(holder.nbproduit.getText() +""+ nb);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        v.add(holder.itemView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(View ve : v){
                    ve.setBackgroundColor(Color.parseColor("#FFFFFF"));
                }
                view.setBackgroundColor(Color.parseColor("#94F879"));
                LocationService.codeM=marchandises.get(position).getCodeM();
            }
        });
    }


    @Override
    public int getItemCount() {
        return marchandises.size();
    }

    public class contenair extends RecyclerView.ViewHolder {
        TextView villeF,villeD,prixM,nbproduit;

        public contenair(@NonNull View itemView) {
            super(itemView);
            villeF = itemView.findViewById(R.id.tt12);
            villeD = itemView.findViewById(R.id.tt1);
            prixM = itemView.findViewById(R.id.MTP);
            nbproduit = itemView.findViewById(R.id.NBP);

        }
    }
}

