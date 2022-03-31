/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

/**
 *
 * @author Khairi
 */
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Produit;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


public class ServiceProduit {


    public static ServiceProduit instance = null;

    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static ServiceProduit getInstance()
    {
        if(instance == null)
            instance = new ServiceProduit();
        return instance;  

    }

    public ServiceProduit()
    {
        req = new ConnectionRequest();
    }


    public ArrayList<Produit> AfficherProduits()
    {

        ArrayList<Produit> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/AfficherProduitsMobile";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try 
                {

                    Map<String,Object>mapProduits = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> ListOfMaps = (List<Map<String,Object>>) mapProduits.get("root");
                    for(Map<String, Object> obj : ListOfMaps)
                    {
                        Produit p = new Produit();
                        float id = Float.parseFloat(obj.get("id").toString());
                        String nom = obj.get("nom").toString();
                        String type = obj.get("type").toString();
                        String taille = obj.get("taille").toString();
                        String couleur = obj.get("couleur").toString();
                        float nbrEtoiles = Float.parseFloat(obj.get("nbrEtoiles").toString());
                        String description = obj.get("description").toString();  
                        float prix = Float.parseFloat(obj.get("prix").toString());    
                        String photo = obj.get("photo").toString();  

                        p.setId((int)id);
                        p.setNom(nom);
                        p.setType(type);
                        p.setTaille(taille);
                        p.setCouleur(couleur);
                        p.setNbrEtoiles((int)nbrEtoiles);
                        p.setDescription(description);
                        p.setPrix(prix);
                        p.setPhoto(photo);
                        
                        result.add(p);
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


    public Produit DetailProduit(int id)
    {

        Produit produit = new Produit();
        String url = Statics.BASE_URL+"/DetailProduitMobile?id="+id;
        req.setUrl(url);
        String str = new String(req.getResponseData());

        req.addResponseListener(((evt) -> {
             
            JSONParser jsonp;
            jsonp = new JSONParser();

            try 
            {
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));

                String nom = obj.get("nom").toString();
                String type = obj.get("type").toString();
                String taille = obj.get("taille").toString();
                String couleur = obj.get("couleur").toString();
                int nbrEtoiles = Integer.parseInt(obj.get("nbrEtoiles").toString());
                String description = obj.get("description").toString();  
                float prix = Float.parseFloat(obj.get("prix").toString());    
                String photo = obj.get("photo").toString();  

                produit.setNom(nom);
                produit.setType(type);
                produit.setTaille(taille);
                produit.setCouleur(couleur);
                produit.setNbrEtoiles(nbrEtoiles);
                produit.setDescription(description);
                produit.setPrix(prix);
                produit.setPhoto(photo);

            }
            catch(IOException ex)
            {
                System.out.println("error related to sql : "+ex.getMessage());
            }

            System.out.println("data === "+str);


  
        }));

        NetworkManager.getInstance().addToQueueAndWait(req);

        return produit;
    
    }
    


    
}
