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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Commande;
import com.mycompany.entities.Produit;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Khairi
 */
public class ServiceCommande {

    public static ServiceCommande instance = null;

    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static ServiceCommande getInstance()
    {
        if(instance == null)
            instance = new ServiceCommande();
        return instance;  

    }

    public ServiceCommande()
    {
        req = new ConnectionRequest();
    }

    public void AjouterCommande(Commande commande)
    {
        String url = Statics.BASE_URL+"/AjouterCommandeMobile?user="+commande.getUser()+"&produit="+commande.getProduit()+"&quantite="+commande.getQuantite()+"&adresse="+commande.getAdresse();
        req.setUrl(url);
        req.addResponseListener ((e) -> {
             
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
  
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

    }

    public ArrayList<Commande> AfficherCommandes(int idUser)
    {

        ArrayList<Commande> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/AfficherCommandesMobile?user="+idUser;
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try 
                {
                    Map<String,Object>mapCommandes = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> ListOfMaps = (List<Map<String,Object>>) mapCommandes.get("root");
                    for(Map<String, Object> obj : ListOfMaps)
                    {
                        Commande c = new Commande();
                        float id = Float.parseFloat(obj.get("id").toString());
                        //float produit = Float.parseFloat(obj.get("produit").toString());
                        float quantite = Float.parseFloat(obj.get("quantite").toString());
                        String adresse = obj.get("adresse").toString();
                        String date = obj.get("dateAjout").toString().substring(0 , 10);

                        c.setId((int)id);
                        //c.setProduit(produit.getId());
                        c.setQuantite((int)quantite);
                        c.setAdresse(adresse);
                        c.setDate(date);
                        
                        result.add(c);
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


    public boolean SupprimerCommande(int id)
    {
        String url = Statics.BASE_URL+"/SupprimerCommandeMobile?id="+id;

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

    public boolean ModifierCommande(Commande commande)
    {
        String url = Statics.BASE_URL+"/ModifierCommandeMobile?id="+commande.getId()+"&user=1&produit=2&quantite="+commande.getQuantite()+"&adresse="+commande.getAdresse();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) 
            {
                resultOk = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOk;

    }








    
}
