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

import com.example.suivilogistique.AdapterMarchandise;
import com.example.suivilogistique.Marchandise;
import com.example.suivilogistique.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AfficheMarchandiseFragment extends Fragment {

    AdapterMarchandise ad;
    RecyclerView rv;
    FloatingActionButton btnA;
    ArrayList<Marchandise> marchandises;
    DatabaseReference dbref;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_affiche_marchandise, container, false);
        rv = view.findViewById(R.id.RV1);
        marchandises = new ArrayList<>();
        btnA = view.findViewById(R.id.fab1);
        ad = new AdapterMarchandise(view.getContext(),marchandises);
        rv.setAdapter(ad);
        rv.setLayoutManager(new GridLayoutManager(view.getContext(),1));
        dbref = FirebaseDatabase.getInstance().getReference();
        dbref.child("Database").child("Marchandises").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot :snapshot.getChildren()){
                    Marchandise marchandise = new Marchandise(String.valueOf(dataSnapshot.child("codeM").getValue()),String.valueOf(dataSnapshot.child("username").getValue()),String.valueOf(dataSnapshot.child("villeD").getValue()),String.valueOf(dataSnapshot.child("villeF").getValue())) ;
                    marchandises.add(marchandise);
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

                ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.conteneur1,new AjouterMarchandiseFragment()).commit();

            }
        });

        return view;
    }
}