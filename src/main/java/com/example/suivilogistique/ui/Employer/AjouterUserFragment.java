package com.example.suivilogistique.ui.Employer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.suivilogistique.R;
import com.example.suivilogistique.Users;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AjouterUserFragment extends Fragment {

    TextInputLayout username,password,nom,email,tele;
    Button btnRes,btnR;
    RadioGroup RG;
    RadioButton RB;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ajouter_user, container, false);
        username = view.findViewById(R.id.username_edittext);
        password = view.findViewById(R.id.enterpass_edittext);
        nom = view.findViewById(R.id.fullname_edittext);
        email = view.findViewById(R.id.email_edittext);
        tele = view.findViewById(R.id.phone_edittext);
        btnRes = view.findViewById(R.id.register_button);
        btnR = view.findViewById(R.id.ret);
        RG = view.findViewById(R.id.RB);

        btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                int radio = RG.getCheckedRadioButtonId();
                RB = view.findViewById(radio);

                Toast.makeText(view1.getContext(), "Selected Radio Button: " + RB.getText(),
                        Toast.LENGTH_SHORT).show();
               String Username = String.valueOf(username.getEditText().getText());
                String Password = String.valueOf(password.getEditText().getText());
                String Nom = String.valueOf(nom.getEditText().getText());
                String Email = String.valueOf(email.getEditText().getText());
                String Tele = String.valueOf(tele.getEditText().getText());

                Users user = new Users(Username,Email,Password,Nom,Tele,RB.getText().toString());
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("Database").child("Users").child(Username).setValue(user);

            }
        });
          btnR.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.conteneur,new AfficheUserFragment()).commit();

         }
         });

        return view;
    }
}