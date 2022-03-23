/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

/**
 *
 * @author Khairi
 */
public class Commande {

    private int id;
    private int user;
    private int produit;
    private int quantite;
    private String adresse;
    private String date;

    public Commande() {
    }

    public Commande(int id, int user, int produit, int quantite, String adresse, String date) {
        this.id = id;
        this.user = user;
        this.produit = produit;
        this.quantite = quantite;
        this.adresse = adresse;
        this.date = date;
    }

    public Commande(int user, int produit, int quantite, String adresse) {
        this.user = user;
        this.produit = produit;
        this.quantite = quantite;
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getProduit() {
        return produit;
    }

    public void setProduit(int produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    
}
