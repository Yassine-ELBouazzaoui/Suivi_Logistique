package com.example.suivilogistique.ui.Marchandise;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.suivilogistique.Marchandise;
import com.example.suivilogistique.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AjouterMarchandiseFragment extends Fragment {

    AutoCompleteTextView myAutoComplete;
    TextInputLayout CodeM_edittext,villeD_edittext,villeF_edittext;
    Button btnRes,btnR;
    ArrayList<String> username;
    DatabaseReference dbref;
    ArrayAdapter<String> ad;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ajouter_marchandise, container, false);
        myAutoComplete = (AutoCompleteTextView)view.findViewById(R.id.username_edittext);
        CodeM_edittext =view.findViewById(R.id.CodeM_edittext);
        villeD_edittext =view.findViewById(R.id.villeD_edittext);
        villeF_edittext =view.findViewById(R.id.villeF_edittext);
        btnRes = view.findViewById(R.id.register_button);
        btnR = view.findViewById(R.id.ret);
        username = new ArrayList<>();
        ad = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_dropdown_item_1line, username);
        myAutoComplete.setAdapter(ad);
        dbref = FirebaseDatabase.getInstance().getReference();
        dbref.child("Database").child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot :snapshot.getChildren()){
                    if(String.valueOf(dataSnapshot.child("as").getValue()).equals("Conducteur")){
                        username.add(String.valueOf(dataSnapshot.child("username").getValue()));
                    }
                }
                ad.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {

                String CodeM = String.valueOf(CodeM_edittext.getEditText().getText());
                String villeD = String.valueOf(villeD_edittext.getEditText().getText());
                String villeF = String.valueOf(villeF_edittext.getEditText().getText());
                String user = String.valueOf(myAutoComplete.getText());

                Marchandise marchandise = new Marchandise(CodeM,user,villeD,villeF);
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("Database").child("Marchandises").child(CodeM).setValue(marchandise).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(view.getContext(), "Marchandise crée avec succès", Toast.LENGTH_SHORT).show();
                    }

                });


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