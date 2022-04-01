/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Kiosque;
import com.mycompany.entities.Stade;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ServiceKiosque {
    
    public static ServiceKiosque instance = null;

    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static ServiceKiosque getInstance()
    {
        if(instance == null)
            instance = new ServiceKiosque();
        return instance;  

    }

    public ServiceKiosque()
    {
        req = new ConnectionRequest();
    }

    public void AjouterKiosque(Kiosque kiosque)
    {
        String url = Statics.BASE_URL+"/AjouterKiosqueMobile?type="+kiosque.getType()+"&nom="+kiosque.getNom()+"&stade="+kiosque.getStade_id();
        req.setUrl(url);
        req.addResponseListener ((e) -> {
             
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
  
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

    }

    public ArrayList<Kiosque> AfficherKiosque(int idUser)
    {

        ArrayList<Kiosque> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/AfficherKiosqueMobile?user="+idUser;
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
                        Kiosque k = new Kiosque();
                        float id = Float.parseFloat(obj.get("id").toString());
                        //float produit = Float.parseFloat(obj.get("produit").toString());
                       
                        String nom = obj.get("nom").toString();
                        String type = obj.get("type").toString();
                        
                        Map<String,Object>staded = (Map<String, Object>) obj.get("stade");
                        String stade=staded.get("nom").toString();

                       

                        k.setId((int)id);
                        //c.setProduit(produit.getId());
                        k.setNom(nom);
                        k.setType(type);
                        k.setNomStade(stade);
                        
                        result.add(k);
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


    public boolean SupprimerKiosque(int id)
    {
        String url = Statics.BASE_URL+"/SupprimerKiosqueMobile?id="+id;

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

    public boolean ModifierKiosque(Kiosque kiosque)
    {
        String url = Statics.BASE_URL+"/ModifierKiosqueMobileee/"+kiosque.getId()+"?type="+kiosque.getType()+"&nom="+kiosque.getNom();
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
