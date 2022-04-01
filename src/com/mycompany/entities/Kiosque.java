/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author jasser
 */
public class Kiosque {
     private int id;
    private String nom,type;
     private String photo;
     private int stade_id ;
     private String nomStade;

    public Kiosque() {
    }

    public Kiosque(int id, String nom, String type, String photo, int stade_id, String nomStade) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.photo = photo;
        this.stade_id = stade_id;
        this.nomStade = nomStade;
    }

    public Kiosque(String nom, String type, String photo, int stade_id, String nomStade) {
        this.nom = nom;
        this.type = type;
        this.photo = photo;
        this.stade_id = stade_id;
        this.nomStade = nomStade;
    }
    
        public Kiosque(String type, String nom, int stade_id) {
        this.nom = nom;
        this.type = type;
        this.photo = photo;
        this.stade_id = stade_id;
        this.nomStade = nomStade;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getStade_id() {
        return stade_id;
    }

    public void setStade_id(int stade_id) {
        this.stade_id = stade_id;
    }

    public String getNomStade() {
        return nomStade;
    }

    public void setNomStade(String nomStade) {
        this.nomStade = nomStade;
    }
 
    

}
