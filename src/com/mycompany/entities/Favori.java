/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

/**
 *
 * @author Khairi
 */
public class Favori {

    private int id;
    private int idUser;
    private int idProduit;

    public Favori() {
    }

    public Favori(int id, int idUser, int idProduit) {
        this.id = id;
        this.idUser = idUser;
        this.idProduit = idProduit;
    }

    public Favori(int idUser, int idProduit) {
        this.idUser = idUser;
        this.idProduit = idProduit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }
  
    
}
