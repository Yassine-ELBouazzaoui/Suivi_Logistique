package com.example.suivilogistique.ui.Marchandise;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.suivilogistique.AdapterProduit;
import com.example.suivilogistique.Produit;
import com.example.suivilogistique.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AfficherProduitFragment extends Fragment {


    AdapterProduit ad;
    RecyclerView rv;
    ArrayList<Produit> produits;
    DatabaseReference dbref;
    String CodeM;
    TextView titel;
    FloatingActionButton btnA;
    FloatingActionButton btnR;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_afficher_produit, container, false);
        rv = view.findViewById(R.id.RV1);
        titel = view.findViewById(R.id.titel);
        produits = new ArrayList<>();
        btnA = view.findViewById(R.id.fab3);
        btnR = view.findViewById(R.id.fab4);
        ad = new AdapterProduit(view.getContext(),produits);
        rv.setAdapter(ad);
        rv.setLayoutManager(new GridLayoutManager(view.getContext(),1));
        if(getArguments()!=null){
           CodeM = String.valueOf(getArguments().getString("CodeM"));
            titel.setText(titel.getText()+CodeM);
        }
        dbref = FirebaseDatabase.getInstance().getReference();
        dbref.child("Database").child("Produits").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot :snapshot.getChildren()){
                    if(String.valueOf(dataSnapshot.child("codeM").getValue()).equals(CodeM)){
                    Produit produit = new Produit(String.valueOf(dataSnapshot.child("codeP").getValue()),String.valueOf(dataSnapshot.child("nom").getValue()),String.valueOf(dataSnapshot.child("poids").getValue()),String.valueOf(dataSnapshot.child("prix").getValue()),String.valueOf(dataSnapshot.child("nomClient").getValue()),String.valueOf(dataSnapshot.child("codeM").getValue())) ;
                    produits.add(produit);
                    }
                }
                ad.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AjouterProduitFragment fr = new AjouterProduitFragment();
                Bundle bnd = new Bundle();
                bnd.putString("CodeM",CodeM);
                fr.setArguments(bnd);
                ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.conteneur1,fr).commit();

            }
        });
        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.conteneur1,new AfficheMarchandiseFragment()).commit();
            }
        });
        return view;
    }
}