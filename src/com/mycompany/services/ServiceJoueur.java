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
import com.mycompany.entities.Joueur;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author jrady
 */
public class ServiceJoueur {
    
    public static ServiceJoueur instance = null;

    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static ServiceJoueur getInstance()
    {
        if(instance == null)
            instance = new ServiceJoueur();
        return instance;  

    }

    public ServiceJoueur()
    {
        req = new ConnectionRequest();
    }
     public ArrayList<Joueur> AfficherJoueurs()
    {

        ArrayList<Joueur> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/api/joueurafficher";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try 
                {
                    Map<String,Object>mapJoueurs = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> ListOfMaps = (List<Map<String,Object>>) mapJoueurs.get("root");
                    for(Map<String, Object> obj : ListOfMaps)
                    {
                        Joueur j = new Joueur();
                        
                        float id = Float.parseFloat(obj.get("id").toString());             
                        String nom_joueur = obj.get("nom_joueur").toString();
                        String prenom_joueur = obj.get("prenom_joueur").toString();
                        String position = obj.get("position").toString();
                        float age = Float.parseFloat(obj.get("age").toString());   
                        String photo = obj.get("photo").toString();
                        //String description = obj.get("Description").toString();
                   
                        j.setNom_joueur(nom_joueur);
                        j.setPrenom_joueur(prenom_joueur);
                        //j.setDescription(description);
                        j.setAge((int)age);
                        j.setPhoto(photo);
                        j.setPosition(position);
                        
                                           
                        result.add(j);
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
