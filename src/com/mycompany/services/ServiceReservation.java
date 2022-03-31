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
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Commande;
import com.mycompany.entities.Reservation;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP USER
 */
public class ServiceReservation {
    public static ServiceReservation instance = null;

    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static ServiceReservation getInstance()
    {
        if(instance == null)
            instance = new ServiceReservation();
        return instance;  

    }

    public ServiceReservation()
    {
        req = new ConnectionRequest();
    }

    public void AjouterReservation(Reservation reservation)
    {
        String url = Statics.BASE_URL+"/AjouterReservationMobile?user="+reservation.getUser()+"&hebergement="+reservation.getHebergement()+"&dateDebut="+reservation.getDateDebut()+"&dateFin="+reservation.getDateFin();
        req.setUrl(url);
        req.addResponseListener ((e) -> {
             
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
  
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

    }

    public ArrayList<Reservation> AfficherReservations(int idUser)
    {

        ArrayList<Reservation> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/AfficherReservationsMobile?user="+idUser;
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try 
                {
                    Map<String,Object>mapReservations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> ListOfMaps = (List<Map<String,Object>>) mapReservations.get("root");
                    for(Map<String, Object> obj : ListOfMaps)
                    {
                        Reservation r = new Reservation();
                        float id = Float.parseFloat(obj.get("id").toString());
                        String dateDebut = obj.get("dateDebut").toString().substring(0 , 10);
                        String dateFin = obj.get("dateFin").toString().substring(0 , 10);



                        r.setId((int)id);
                        r.setDateDebut(dateDebut);
                        r.setDateFin(dateFin);

              

                        result.add(r);
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


    public boolean SupprimerReservation(int id)
    {
        String url = Statics.BASE_URL+"/SupprimerReservationMobile?id="+id;

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

    public boolean ModifierReservation(Reservation reservation)
    {
        String url = Statics.BASE_URL+"/ModifierReservationMobile?id="+reservation.getId()+"&user=1&hebergement="+reservation.getHebergement()+"&dateDebut="+reservation.getDateDebut()+"&dateFin="+reservation.getDateFin();

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
