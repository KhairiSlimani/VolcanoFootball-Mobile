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
import com.mycompany.entities.Produit;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Khairi
 */
public class ServiceArtiste {
    
    public static ServiceArtiste instance = null;

    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static ServiceArtiste getInstance()
    {
        if(instance == null)
            instance = new ServiceArtiste();
        return instance;  

    }

    public ServiceArtiste()
    {
        req = new ConnectionRequest();
    }

    
    public ArrayList<Artiste> AfficherArtistes()
    {

        ArrayList<Artiste> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/AfficherArtistesMobile";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try 
                {

                    Map<String,Object>mapArtistes = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> ListOfMaps = (List<Map<String,Object>>) mapArtistes.get("root");
                    for(Map<String, Object> obj : ListOfMaps)
                    {
                        Artiste a = new Artiste();
                        float id = Float.parseFloat(obj.get("id").toString());
                        String nom = obj.get("nom").toString();
                        String type = obj.get("type").toString();
                        float age = Float.parseFloat(obj.get("age").toString());

                        a.setId((int)id);
                        a.setNom(nom);
                        a.setType(type);
                        a.setAge((int)age);
                        
                        result.add(a);
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
