package com.example.suivilogistique.ui.Marchandise;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.suivilogistique.Produit;
import com.example.suivilogistique.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AjouterProduitFragment extends Fragment {

    String CodeM;
    TextInputLayout CodeP_edittext,Nom_edittext,Poids_edittext,Prix_edittext,NomClient_edittext;
    Button btnRes,btnAn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ajouter_produit, container, false);
        if(getArguments()!=null){
            CodeM = String.valueOf(getArguments().getString("CodeM"));
        }
        CodeP_edittext =view.findViewById(R.id.CodeP_edittext);
        Nom_edittext =view.findViewById(R.id.Nom_edittext);
        Poids_edittext =view.findViewById(R.id.Poids_edittext);
        Prix_edittext =view.findViewById(R.id.Prix_edittext);
        NomClient_edittext =view.findViewById(R.id.NomClient_edittext);
        btnRes = view.findViewById(R.id.register_button);
        btnAn = view.findViewById(R.id.Annuler_button);

        btnRes.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String CodeP = String.valueOf(CodeP_edittext.getEditText().getText());
                 String NomP = String.valueOf(Nom_edittext.getEditText().getText());
                 String Poids = String.valueOf(Poids_edittext.getEditText().getText());
                 String Prix = String.valueOf(Prix_edittext.getEditText().getText());
                 String NomClient = String.valueOf(NomClient_edittext.getEditText().getText());

                 Produit p = new Produit(CodeP,NomP,Poids,Prix,NomClient,CodeM);
                 DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                 databaseReference.child("Database").child("Produits").child(CodeP).setValue(p).addOnCompleteListener(new OnCompleteListener<Void>() {
                     @Override
                     public void onComplete(@NonNull Task<Void> task) {
                         Toast.makeText(view.getContext(), "Produit crée avec succès", Toast.LENGTH_SHORT).show();
                     }

                 });
             }
         });

         btnAn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 AfficherProduitFragment fr = new AfficherProduitFragment();
                 Bundle bnd = new Bundle();
                 bnd.putString("CodeM",CodeM);
                 fr.setArguments(bnd);
                 ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.conteneur1,fr).commit();

             }
         });

        return view;
    }
}