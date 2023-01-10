package com.example.suivilogistique;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Connexion extends AppCompatActivity {
    EditText username;
    EditText password;
    Button loginButton;
    TextView signupText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        username = findViewById(R.id.username);
        signupText = findViewById(R.id.signupText);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("Database").child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String input1 = username.getText().toString();
                        String input2 = password.getText().toString();
                        if (snapshot.child(input1).exists()) {
                            if (String.valueOf(snapshot.child(input1).child("password").getValue()).equals(input2)) {
                                if (snapshot.child(input1).child("as").getValue().equals("Admin")) {
                                    Intent intent = new Intent(Connexion.this, AdminActivity.class);
                                    Bundle b = new Bundle();
                                    Users s1  = new Users(String.valueOf(snapshot.child(input1).child("username").getValue()),String.valueOf(snapshot.child(input1).child("email").getValue()),String.valueOf(snapshot.child(input1).child("nom").getValue()),String.valueOf(snapshot.child(input1).child("tele").getValue()));
                                    b.putSerializable("user",s1);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                    } else if (snapshot.child(input1).child("as").getValue().equals("Conducteur")){
                                     Intent intent1 = new Intent(Connexion.this, UserActivity.class);
                                    Bundle b = new Bundle();
                                    Users s2  = new Users(String.valueOf(snapshot.child(input1).child("username").getValue()),String.valueOf(snapshot.child(input1).child("email").getValue()),String.valueOf(snapshot.child(input1).child("nom").getValue()),String.valueOf(snapshot.child(input1).child("tele").getValue()));
                                    b.putSerializable("user",s2);
                                    intent1.putExtras(b);
                                    startActivity(intent1);
                                    }

                            } else {
                                signupText.setText("Mot De Passe ou Username Incorrect");
                            }
                        } else {
                            Toast.makeText(Connexion.this, "Les données n'ont pas été enregistrées", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
}