/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.mycompany.utils.Statics;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author Khairi
 */
public class ServiceUser {


    public static ServiceUser instance = null ;
    
    public static boolean resultOk = true;
    String json;

    private ConnectionRequest req;
    
    public static ServiceUser getInstance() {
        if(instance == null )
            instance = new ServiceUser();
        return instance ;
    }
    
    public ServiceUser() {
        req = new ConnectionRequest();
        
    }


    public void signup(TextField username,TextField password,TextField nom,TextField prenom,TextField email,TextField confirmPassword, Resources res ) {
        
     
        
        String url = Statics.BASE_URL+"/SignUpMobile?username="+username.getText().toString()+"&password="+password.getText().toString()+"&nom="+nom.getText().toString()+"&prenom="+prenom.getText().toString()+"&email="+email.getText().toString()+"&roles=ROLE_USER";
        
        req.setUrl(url);
       
        if(username.getText().equals(" ") || password.getText().equals(" ") || nom.getText().equals(" ") || prenom.getText().equals(" ") || email.getText().equals(" ") || confirmPassword.getText().equals(" ")) 
        {
            
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
        }
        
        req.addResponseListener((e)-> {
         
            byte[]data = (byte[]) e.getMetaData();
            String responseData = new String(data);
            
            System.out.println("data ===>"+responseData);
        }
        );
        
        NetworkManager.getInstance().addToQueueAndWait(req);     
    }


    
}
