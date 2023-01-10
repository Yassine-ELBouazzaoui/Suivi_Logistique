package com.example.suivilogistique;

import java.io.Serializable;
import java.util.ArrayList;

public class Marchandise implements Serializable {
    String CodeM,username,villeD,villeF;
    ArrayList<Produit> produit;

    public Marchandise(String CodeM, String username, String villeD, String villeF) {
        this.CodeM = CodeM;
        this.username = username;
        this.villeD = villeD;
        this.villeF = villeF;
    }

    public String getCodeM() {
        return CodeM;
    }

    public void setCodeM(String CodeM) {
        this.CodeM = CodeM;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVilleD() {
        return villeD;
    }

    public void setVilleD(String villeD) {
        this.villeD = villeD;
    }

    public String getVilleF() {
        return villeF;
    }

    public void setVilleF(String villeF) {
        this.villeF = villeF;
    }

    public ArrayList<Produit> getProduit() {
        return produit;
    }

    public void setProduit(ArrayList<Produit> produit) {
        this.produit = produit;
    }
}
