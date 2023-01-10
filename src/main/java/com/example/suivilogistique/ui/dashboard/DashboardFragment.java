package com.example.suivilogistique.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suivilogistique.AdapterLocation;
import com.example.suivilogistique.AdapterMarchandise;
import com.example.suivilogistique.AdapterMarchandise2;
import com.example.suivilogistique.LocationUser;
import com.example.suivilogistique.Marchandise;
import com.example.suivilogistique.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {
    AdapterMarchandise2 ad;
    RecyclerView rv;
    ArrayList<Marchandise> marchandises;
    DatabaseReference dbref;
    TextView user,liv,pro,pri;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root =inflater.inflate(R.layout.fragment_dashboard, container, false);
        user = root.findViewById(R.id.user);
        liv = root.findViewById(R.id.liv);
        pro = root.findViewById(R.id.pro);
        pri = root.findViewById(R.id.pri);
        rv = root.findViewById(R.id.rv);
        marchandises = new ArrayList<>();
        ad = new AdapterMarchandise2(root.getContext(),marchandises);
        rv.setAdapter(ad);
        rv.setLayoutManager(new GridLayoutManager(root.getContext(),1));
        dbref = FirebaseDatabase.getInstance().getReference();
        dbref.child("Database").child("Marchandises").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot :snapshot.getChildren()){
                    if(String.valueOf(dataSnapshot.child("username").getValue()).equals(user.getText())) {
                        Marchandise marchandise = new Marchandise(String.valueOf(dataSnapshot.child("codeM").getValue()), String.valueOf(dataSnapshot.child("username").getValue()), String.valueOf(dataSnapshot.child("villeD").getValue()), String.valueOf(dataSnapshot.child("villeF").getValue()));
                        marchandises.add(marchandise);
                    }
                }
                liv.setText(liv.getText()+""+marchandises.size());
                ad.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        dbref.child("Database").child("Produits").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int nb = 0;
                double prix = 0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    for (int i = 0; i < marchandises.size(); i++) {
                        if (String.valueOf(dataSnapshot.child("codeM").getValue()).equals(marchandises.get(i).getCodeM())) {

                            prix += Double.parseDouble(dataSnapshot.child("prix").getValue().toString());
                            nb++;
                        }
                    }


                }
                pro.setText(pro.getText() + "" + nb);
                pri.setText(prix + " DH");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return root;
    }




}