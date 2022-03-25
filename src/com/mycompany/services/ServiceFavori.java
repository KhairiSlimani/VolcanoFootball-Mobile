/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.mycompany.entities.Favori;
import com.mycompany.utils.Statics;

/**
 *
 * @author Khairi
 */
public class ServiceFavori {

    public static ServiceFavori instance = null;

    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static ServiceFavori getInstance()
    {
        if(instance == null)
            instance = new ServiceFavori();
        return instance;  

    }

    public ServiceFavori()
    {
        req = new ConnectionRequest();
    }

    public void AjouterFavori(Favori favori)
    {
        
        String url = Statics.BASE_URL+"/AfficherFavorisMobile?user="+favori.getIdUser()+"&produit="+favori.getIdProduit();
        req.setUrl(url);
        req.addResponseListener ((e) -> {
             
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
  
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

    }

}
