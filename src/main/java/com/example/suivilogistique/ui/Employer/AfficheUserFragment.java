package com.example.suivilogistique.ui.Employer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.suivilogistique.AdapterUser;
import com.example.suivilogistique.R;
import com.example.suivilogistique.Users;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AfficheUserFragment extends Fragment {

    AdapterUser ad;
    RecyclerView rv;
    ArrayList<Users> users;
    DatabaseReference dbref;
    FloatingActionButton btnA;
       @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_affiche_user, container, false);
           rv = view.findViewById(R.id.RV1);
           users = new ArrayList<>();
           ad = new AdapterUser(view.getContext(),users);
           rv.setAdapter(ad);
           rv.setLayoutManager(new GridLayoutManager(view.getContext(),1));
           dbref = FirebaseDatabase.getInstance().getReference();
           dbref.child("Database").child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                   for(DataSnapshot dataSnapshot :snapshot.getChildren()){
                       Users user = new Users(String.valueOf(dataSnapshot.child("username").getValue()),String.valueOf(dataSnapshot.child("email").getValue()),String.valueOf(dataSnapshot.child("password").getValue()),String.valueOf(dataSnapshot.child("nom").getValue()),String.valueOf(dataSnapshot.child("tele").getValue()),String.valueOf(dataSnapshot.child("as").getValue())) ;
                       users.add(user);
                   }
                   ad.notifyDataSetChanged();
               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {

               }
           });
           btnA = view.findViewById(R.id.fab);
           btnA.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.conteneur,new AjouterUserFragment()).commit();
               }
           });
        return view;
    }
}