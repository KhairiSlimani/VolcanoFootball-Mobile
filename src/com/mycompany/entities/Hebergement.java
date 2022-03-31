/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

/**
 *
 * @author HP USER
 */
public class Hebergement {
     private int id;
    private int agence;
    private String type;
    private String nomH;
    private String photoH;

    public Hebergement() {
    }

    public Hebergement(int id, int agence, String type, String nomH, String photoH) {
        this.id = id;
        this.agence = agence;
        this.type = type;
        this.nomH = nomH;
        this.photoH = photoH;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAgence() {
        return agence;
    }

    public void setAgence(int agence) {
        this.agence = agence;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNomH() {
        return nomH;
    }

    public void setNomH(String nomH) {
        this.nomH = nomH;
    }

    public String getPhotoH() {
        return photoH;
    }

    public void setPhotoH(String photoH) {
        this.photoH = photoH;
    }


}
