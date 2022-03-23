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
import com.mycompany.entities.Equipe;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jrady
 */
public class ServiceEquipe {
    public static ServiceEquipe instance = null;

    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static ServiceEquipe getInstance()
    {
        if(instance == null)
            instance = new ServiceEquipe();
        return instance;  

    }

    public ServiceEquipe()
    {
        req = new ConnectionRequest();
    }
     public ArrayList<Equipe> AfficherEquipes()
    {

        ArrayList<Equipe> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/api/equipeafficher";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try 
                {
                    Map<String,Object>mapEquipes = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> ListOfMaps = (List<Map<String,Object>>) mapEquipes.get("root");
                    for(Map<String, Object> obj : ListOfMaps)
                    {
                        Equipe e = new Equipe();
                        
                        float id = Float.parseFloat(obj.get("id").toString());             
                        String nom_equipe = obj.get("nom_equipe").toString();
                        String nom_entreneur = obj.get("nom_entreneur").toString();
                        String date_creation = obj.get("date_creation").toString().substring(0 , 10);
                        String drapeau_equipe = obj.get("drapeau_equipe").toString();
                        //String description = obj.get("Description").toString();
                   
                        e.setNom_equipe(nom_equipe);
                        e.setNom_entreneur(nom_entreneur);
                        e.setDate_creation(date_creation);
                        e.setDrapeau_equipe(drapeau_equipe);
                        
                                           
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
