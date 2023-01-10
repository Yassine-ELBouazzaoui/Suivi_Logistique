package com.example.suivilogistique;

import java.io.Serializable;

public class Produit implements Serializable {
    String CodeP,Nom,Poids,Prix,NomClient,CodeM;

    public Produit(String codeP, String nom, String poids, String prix, String nomClient, String codeM) {
        CodeP = codeP;
        Nom = nom;
        Poids = poids;
        Prix = prix;
        NomClient = nomClient;
        CodeM = codeM;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPoids() {
        return Poids;
    }

    public void setPoids(String poids) {
        Poids = poids;
    }

    public String getNomClient() {
        return NomClient;
    }

    public void setNomClient(String nomClient) {
        NomClient = nomClient;
    }

    public String getPrix() {
        return Prix;
    }

    public void setPrix(String prix) {
        Prix = prix;
    }

    public String getCodeM() {
        return CodeM;
    }

    public void setCodeM(String codeM) {
        CodeM = codeM;
    }

    public String getCodeP() {
        return CodeP;
    }

    public void setCodeP(String codeP) {
        CodeP = codeP;
    }
}
