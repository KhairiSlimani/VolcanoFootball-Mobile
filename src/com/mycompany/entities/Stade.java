/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author bhk
 */
public class Stade {
    private int id;
    private String nom,adresse;
     private String photo;
     private int capacite ;
 
    
    public Stade( int id  , String nom , String adresse , String photo , int capacite) {
     
        this.id = id ;
        this.nom = nom;
        this.adresse = adresse;
        this.photo = photo;
        this.capacite = capacite;
       
    }  
     public Stade( String nom , String adresse , String photo , int capacite) {
     
       
        this.nom = nom;
        this.adresse = adresse;
        this.photo = photo;
        this.capacite = capacite;
       
    }  

   

    public Stade() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    
}
 