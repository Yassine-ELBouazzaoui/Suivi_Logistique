package com.example.suivilogistique.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.suivilogistique.Marchandise;
import com.example.suivilogistique.R;
import com.example.suivilogistique.databinding.FragmentHomeBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment {

    TextView ad,mar,con,pro;
    DatabaseReference dbref;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ad =root.findViewById(R.id.ad);
        mar =root.findViewById(R.id.ma);
        con =root.findViewById(R.id.con);
        pro =root.findViewById(R.id.pr);
        dbref = FirebaseDatabase.getInstance().getReference();
        dbref.child("Database").child("Marchandises").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mar.setText(String.valueOf(snapshot.getChildrenCount()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dbref.child("Database").child("Produits").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                pro.setText(String.valueOf(snapshot.getChildrenCount()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dbref.child("Database").child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int a=0,c=0;
                for(DataSnapshot dataSnapshot :snapshot.getChildren()){
                   if(String.valueOf(dataSnapshot.child("as").getValue()).equals("Conducteur")){
                       c++;
                   }else {
                       a++;
                   }
                }
                ad.setText(String.valueOf(a));
                con.setText(String.valueOf(c));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return root;
    }


}