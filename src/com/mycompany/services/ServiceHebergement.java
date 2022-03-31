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
import com.mycompany.entities.Hebergement;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP USER
 */
public class ServiceHebergement {
    
  public static ServiceHebergement instance = null;

    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static ServiceHebergement getInstance()
    {
        if(instance == null)
            instance = new ServiceHebergement();
        return instance;  

    }

    public ServiceHebergement()
    {
        req = new ConnectionRequest();
    }


    public ArrayList<Hebergement> AfficherHebergements(int agence)
    {

         ArrayList<Hebergement> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/AfficherHebergementsMobile?agence="+agence;
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try 
                {
                    Map<String,Object>mapHebergements = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> ListOfMaps = (List<Map<String,Object>>) mapHebergements.get("root");
                    for(Map<String, Object> obj : ListOfMaps)
                    {
                        Hebergement h = new Hebergement();
                        float id = Float.parseFloat(obj.get("id").toString());
                        String nomH = obj.get("nomH").toString();
                        String type = obj.get("type").toString();


                        h.setId((int)id);
                        h.setNomH(nomH);
                        h.setType(type);
                        
                        result.add(h);
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

