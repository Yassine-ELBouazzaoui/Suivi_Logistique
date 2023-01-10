package com.example.suivilogistique.ui.Marchandise;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import com.example.suivilogistique.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GalleryFragment extends Fragment {

    FloatingActionButton btnA;

    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        ((FragmentActivity) root.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.conteneur1,new AfficheMarchandiseFragment()).commit();



        return root;
    }

}