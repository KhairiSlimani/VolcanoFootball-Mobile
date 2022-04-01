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
import com.mycompany.entities.Artiste;
import com.mycompany.entities.Evenement;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Khairi
 */

public class ServiceEvenement {
    
    public static ServiceEvenement instance = null;

    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static ServiceEvenement getInstance()
    {
        if(instance == null)
            instance = new ServiceEvenement();
        return instance;  

    }

    public ServiceEvenement()
    {
        req = new ConnectionRequest();
    }

    
    public ArrayList<Evenement> AfficherServiceEvenements()
    {

        ArrayList<Evenement> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/AfficherEvenementsMobile";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try 
                {

                    Map<String,Object>mapEvenements = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> ListOfMaps = (List<Map<String,Object>>) mapEvenements.get("root");
                    for(Map<String, Object> obj : ListOfMaps)
                    {
                        Evenement e = new Evenement();
                        float id = Float.parseFloat(obj.get("id").toString());
                        String nom = obj.get("nom").toString();
                        String date = obj.get("date").toString();

                        e.setId((int)id);
                        e.setNom(nom);
                        e.setDate(date);
                        
                        result.add(e);
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

    
}
