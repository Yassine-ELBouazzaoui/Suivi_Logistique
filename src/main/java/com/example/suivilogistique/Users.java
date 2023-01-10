package com.example.suivilogistique;

import java.io.Serializable;

public class Users implements Serializable {
    String username,email,password,Nom,tele,as;

    public Users(String username, String email, String password, String nom, String tele) {
        this.username = username;
        this.email = email;
        this.password = password;
        Nom = nom;
        this.tele = tele;
    }

    public Users(String username, String email, String nom, String tele) {
        this.username = username;
        this.email = email;
        Nom = nom;
        this.tele = tele;
    }

    public Users(String username, String email, String password, String nom, String tele, String as) {
        this.username = username;
        this.email = email;
        this.password = password;
        Nom = nom;
        this.tele = tele;
        this.as = as;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getAs() {
        return as;
    }

    public void setAs(String as) {
        this.as = as;
    }
}
