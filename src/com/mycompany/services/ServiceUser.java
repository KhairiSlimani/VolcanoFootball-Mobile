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
import com.mycompany.gui.ListProduitsForm;
import com.mycompany.gui.ProfileForm;
import com.mycompany.gui.SessionManager;
import com.mycompany.gui.SignInForm;
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
       
        if(username.getText().equals("") || password.getText().equals("") || nom.getText().equals("") || prenom.getText().equals("") || email.getText().equals("") || confirmPassword.getText().equals("")) 
        {
            
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
        }
        else
        {
            req.addResponseListener((e)-> {

                byte[]data = (byte[]) e.getMetaData();
                String responseData = new String(data);

                System.out.println("data ===>"+responseData);
                new SignInForm(res).show();
            }
            );

            NetworkManager.getInstance().addToQueueAndWait(req);     

        }

    }


    public void signin(TextField username,TextField password, Resources res ) {
        
        
        String url = Statics.BASE_URL+"/SignInMobile?username="+username.getText().toString()+"&password="+password.getText().toString();

        req = new ConnectionRequest(url, false); 
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            try {
            
            if(json.equals("failed")) 
            {
                Dialog.show("Echec d'authentification","Username ou mot de passe éronné","OK",null);
            }
            else 
            {
                System.out.println("data =="+json);
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);
                
                SessionManager.setUsername(user.get("username").toString());
                SessionManager.setEmail(user.get("email").toString());
                SessionManager.setPassword(user.get("password").toString());
                SessionManager.setNom(user.get("nom").toString());
                SessionManager.setPrenom(user.get("prenom").toString());
                                             
                System.out.println("User Connecté! .. id: "+SessionManager.getId()+" username:"+SessionManager.getUsername()+" email:"+SessionManager.getEmail()+ " password:"+SessionManager.getPassword()+" nom:"+SessionManager.getNom()+" prenom:"+SessionManager.getPrenom());
                if(user.size() >0 ) 
                    new ProfileForm(res).show();

                    
            }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public void ModifierProfile(int id,String username,String password,String nom,String prenom,String email) {
        
        
        String url = Statics.BASE_URL+"/ModifierUserMobile?id="+id+"&username="+username+"&password="+password+"&nom="+nom+"&prenom="+prenom+"&email="+email+"&roles=ROLE_USER";

        req = new ConnectionRequest(url, false); 
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            SessionManager.setId((int)id);

            SessionManager.setPassword(password);
            SessionManager.setUsername(username);
            SessionManager.setEmail(email);
            SessionManager.setNom(nom);
            SessionManager.setPrenom(prenom);

            System.out.println("User Modifié! .. username:"+SessionManager.getUsername()+" email:"+SessionManager.getEmail()+" nom:"+SessionManager.getNom()+" email:"+SessionManager.getPrenom());
  
        });
    
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public void ResetPassword(String email, Resources res)
    {
        String url = Statics.BASE_URL+"/ResetPassword?email="+email;
        req.setUrl(url);
        req.addResponseListener ((e) -> {
             
            String str = new String(req.getResponseData());
            System.out.println("mot de passe= "+str);
            new SignInForm(res).show();
  
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

    }




    
}
