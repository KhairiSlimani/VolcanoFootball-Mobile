/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

/**
 *
 * @author Khairi
 */
public class Evenement {
    
    private int id;
    private String nom;
    private String date;

    public Evenement() {
    }

    public Evenement(int id, String nom, String date) {
        this.id = id;
        this.nom = nom;
        this.date = date;
    }

    public Evenement(String nom, String date) {
        this.nom = nom;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    

    
}
