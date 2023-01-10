package com.example.suivilogistique.ui.Employer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.suivilogistique.R;

public class SlideshowFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        ((FragmentActivity) root.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.conteneur,new AfficheUserFragment()).commit();

        return root;
    }

}