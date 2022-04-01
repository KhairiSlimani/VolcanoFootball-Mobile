/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

/**
 *
 * @author Khairi
 */
public class Artiste {
    
    private int id;
    private int age;
    private String nom;
    private String type;

    public Artiste() {
    }

    public Artiste(int id, int age, String nom, String type) {
        this.id = id;
        this.age = age;
        this.nom = nom;
        this.type = type;
    }

    public Artiste(int age, String nom, String type) {
        this.age = age;
        this.nom = nom;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
    
    

    
}
