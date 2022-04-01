/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Favori;
import com.mycompany.gui.SessionManager;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public void AjouterFavori(int idUser, int idProduit)
    {
        
        String url = Statics.BASE_URL+"/AjouterFavoriMobile?user="+idUser+"&produit="+idProduit;
        req.setUrl(url);
        req.addResponseListener ((e) -> {
             
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
  
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

    }

    public ArrayList<Favori> AfficherFavoris()
    {

        ArrayList<Favori> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/AfficherFavorisMobile?user="+SessionManager.getId();
        System.out.println("Ussssssssssssssssssssssr "+ SessionManager.getId());
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try 
                {
                    Map<String,Object>mapFavoris = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> ListOfFavoris = (List<Map<String,Object>>) mapFavoris.get("root");
                    for(Map<String, Object> obj : ListOfFavoris)
                    {
                        Favori f= new Favori();
                        float id = Float.parseFloat(obj.get("id").toString());
                        float idProduit = Float.parseFloat(obj.get("idProduit").toString());

                        f.setId((int)id);
                        f.setIdProduit((int)idProduit);
                        
                        result.add(f);
                    }
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;
    
    }

    public boolean SupprimerFavori(int id)
    {
        String url = Statics.BASE_URL+"/SupprimerFavoriMobile?id="+id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) 
            {
                req.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOk;
        
    }





}
