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
import com.mycompany.gui.SessionManager;
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
        //88-ff-dffd

        String p1 = reservation.getDateDebut().substring(0 , 2);
        String p2 = reservation.getDateDebut().substring(3 , 5);
        String p3 = reservation.getDateDebut().substring(6);
        
        String dateDebut = "20"+p3+"-"+p1+"-"+p2;
        
        String p11 = reservation.getDateFin().substring(0 , 2);
        String p22 = reservation.getDateFin().substring(3 , 5);
        String p33 = reservation.getDateFin().substring(6);
        
        String dateFin = "20"+p33+"-"+p11+"-"+p22;

    
        String url = Statics.BASE_URL+"/AjouterReservationMobile?user="+SessionManager.getId()+"&hebergement="+reservation.getHebergement()+"&dateDebut="+dateDebut+"&dateFin="+dateFin;
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
                        
                        Map<String,Object>heb = (Map<String, Object>) obj.get("hebergement");
                        String nomH=heb.get("nomH").toString();
                        
                        float id = Float.parseFloat(obj.get("id").toString());
                        String dateDebut = obj.get("dateDebut").toString().substring(0 , 10);
                        String dateFin = obj.get("dateFin").toString().substring(0 , 10);

                        r.setId((int)id);
                        r.setDateDebut(dateDebut);
                        r.setDateFin(dateFin);
                        r.setHebergement(nomH);
                        
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

    
    public boolean ModifierR(Reservation r)
    {
        String p1 = r.getDateDebut().substring(0 , 2);
        String p2 = r.getDateDebut().substring(3 , 5);
        String p3 = r.getDateDebut().substring(6);
        
        String dateDebut = "20"+p3+"-"+p1+"-"+p2;
        
        String p11 = r.getDateFin().substring(0 , 2);
        String p22 = r.getDateFin().substring(3 , 5);
        String p33 = r.getDateFin().substring(6);
        
        String dateFin = "20"+p33+"-"+p11+"-"+p22;

        
        String url = Statics.BASE_URL+"/ModifierReservationMobile?id="+r.getId()+"&user="+ SessionManager.getId()+"&hebergement="+r.getHebergement()+"&dateDebut="+ dateDebut +"&dateFin=" + dateFin;
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
