/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

/**
 *
 * @author jasser
 */
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Stade;
import com.mycompany.entities.Produit;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


public class ServiceStade {


    public static ServiceStade instance = null;

    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static ServiceStade getInstance()
    {
        if(instance == null)
            instance = new ServiceStade();
        return instance;  

    }

    public ServiceStade()
    {
        req = new ConnectionRequest();
    }


    public ArrayList<Stade> AfficheStade()
    {

        ArrayList<Stade> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/AfficherStadeMobile";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try 
                {

                    Map<String,Object>mapStade = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> ListOfMaps = (List<Map<String,Object>>) mapStade.get("root");
                    for(Map<String, Object> obj : ListOfMaps)
                    {
                        Stade s = new Stade();
                        float id = Float.parseFloat(obj.get("id").toString());
                        String nom = obj.get("nom").toString();
                        String adresse= obj.get("adresse").toString();
                        float capacite = Float.parseFloat(obj.get("capacite").toString());
                        
                        String photo = obj.get("photo").toString();  

                        s.setId((int)id);
                        s.setNom(nom);
                        s.setAdresse(adresse);
                        s.setCapacite((int)capacite);
                        s.setPhoto(photo);
                        
                        result.add(s);
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
