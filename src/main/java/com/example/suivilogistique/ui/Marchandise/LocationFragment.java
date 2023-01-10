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

import com.example.suivilogistique.AdapterLocation;
import com.example.suivilogistique.LocationUser;
import com.example.suivilogistique.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LocationFragment extends Fragment {

    ArrayList<LocationUser> locationUsers;
    DatabaseReference dbref;
    RecyclerView rv;
    AdapterLocation adapterLocation;
    String CodeM;
    FloatingActionButton btnR;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_location, container, false);
        rv = view.findViewById(R.id.recyclerView);
        locationUsers = new ArrayList<>();
        adapterLocation = new AdapterLocation(view.getContext(),locationUsers);
        rv.setAdapter(adapterLocation);
        rv.setLayoutManager(new GridLayoutManager(view.getContext(),1));
        if(getArguments()!=null){
            CodeM = String.valueOf(getArguments().getString("CodeM"));
        }
        dbref = FirebaseDatabase.getInstance().getReference();
        dbref.child("Database").child("Location").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot :snapshot.getChildren()){
                    if(CodeM.equals(String.valueOf(dataSnapshot.child("codeM").getValue()))){
                    LocationUser locationUser = new LocationUser(String.valueOf(dataSnapshot.child("latitude").getValue()),String.valueOf(dataSnapshot.child("longitude").getValue()),String.valueOf(dataSnapshot.child("codeM").getValue()),String.valueOf(dataSnapshot.child("position").getValue())) ;
                    locationUsers.add(locationUser);
                    }
                }
                adapterLocation.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnR = view.findViewById(R.id.fab4);

        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.conteneur1,new AfficheMarchandiseFragment()).commit();
            }
        });
        return view;
    }
}