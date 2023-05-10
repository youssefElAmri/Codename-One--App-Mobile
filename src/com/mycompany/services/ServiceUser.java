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
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.User;
import com.mycompany.gui.Home;
import com.mycompany.gui.HomeClient;
import com.mycompany.gui.SessionManager;
import com.mycompany.gui.SignInForm;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MSI
 */
public class ServiceUser {
    
    public ArrayList<User> users;
    
    //singleton
    public static ServiceUser instance = null ;
    
    Form current;
     private Resources theme;
    
    public static boolean resultOk = true;
    String json;
    
    //initialisation connection request 
    private ConnectionRequest req ;
    
    public static ServiceUser getInstance() {
        if(instance == null )
            instance = new ServiceUser();
        return instance;
    }
    
    
    public ServiceUser() {
        req = new ConnectionRequest();
    }
    
    
    
    //Signup
    public void signup(TextField username,TextField password,TextField email, TextField numTel,
            TextField fullAddress , Resources res ) {
        
        
        String url = Statics.BASE_URL+"/userJson/signUp?userName="+username.getText().toString()+"&email="+email.getText().toString()+
                "&numTel="+numTel.getText().toString()+"&password="+password.getText().toString()+"&address="+fullAddress.getText().toString();
        
        req.setUrl(url);
       
        //Control saisi
        if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty() || email.getText().toString().isEmpty()
                || numTel.getText().toString().isEmpty() || fullAddress.getText().toString().isEmpty() ) {
            
            Dialog.show("Erreur","Veuillez remplir tous les champs","OK",null);
            
        }else{
           // execution url 
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
         
            //Get data from form
            byte[]data = (byte[]) evt.getMetaData();
            String responseData = new String(data);
            
            System.out.println("data ===>"+responseData);
            Dialog.show("Success","account is saved","OK",null);
            new SignInForm(res);
            req.removeResponseListener(this);
            }
        }
            );
        }
        
        
        
        
        //Response serveur
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    
    
     //SignIn
    
    public void signin(TextField email,TextField password, Resources res ) {
        
        
        String url = Statics.BASE_URL+"/userJson/signIn?email="+email.getText().toString()+"&password="+password.getText().toString();
        req = new ConnectionRequest(url, false); 
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt){
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("\"User not found!\"")) {
                Dialog.show("Echec d'authentification","Utilisateur non trouvé","OK",null);
            }else if(json.equals("\"Password not found!\"")){
                Dialog.show("Echec d'authentification","Mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
                
                //Session 
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);
                
                
                SessionManager.setPassowrd(user.get("password").toString());
                SessionManager.setUserName(user.get("username").toString());
                SessionManager.setEmail(user.get("email").toString());
                SessionManager.setFullAddress(user.get("fullAddress").toString());
                SessionManager.setNumTel(user.get("numTel").toString());
                SessionManager.setRoles((ArrayList<String>) user.get("roles"));
                ArrayList<String> array = (ArrayList<String>) user.get("roles");
                String role = array.get(0);
                if(role.equals("ROLE_ADMIN")){
                    Dialog.show("Success","Admin sign in succesfully!","OK",null);
                    new Home().show();
                }else{
                  Dialog.show("Success","Client sign in succesfully!","OK",null);
                    new HomeClient().show();
                }
                req.removeResponseListener(this);
                    
                    }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            }
        });
    
         //Respense serveur
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    
    
    
    
    //Ajout client
    public void ajoutUser(User user) {
        String url = Statics.BASE_URL+"/addClientjson?userName="+user.getUserName()+"&email="+
                user.getEmail()+"&numTel="+user.getNumTel()+"&address="+user.getFullAddress()+"&password="+user.getPassword();
        if(user.getUserName().isEmpty() || user.getEmail().isEmpty()
                || user.getNumTel().isEmpty() || user.getFullAddress().isEmpty() || user.getPassword().isEmpty()) {
            
            Dialog.show("Erreur","Veuillez remplir tous les champs","OK",null);
            
        }else{
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt){
            String str = new String(req.getResponseData());//Respnse json
            System.out.println("data == "+str);
            
            Dialog.show("Success","Client ajouté avec succes!","OK",null);
                new Home().show();
            req.removeResponseListener(this);
            }
        });
        }
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution de req
        
    }
    
     //Modif client
    public void ModifierUser(User user) {
        String url = Statics.BASE_URL+"/modifieUserJson?id="+user.getId()+"&userName="+user.getUserName()+"&email="+
                user.getEmail()+"&numTel="+user.getNumTel()+"&address="+user.getFullAddress();
        
        if(user.getUserName().isEmpty() || user.getEmail().isEmpty()
                || user.getNumTel().isEmpty() || user.getFullAddress().isEmpty()) {
            
            Dialog.show("Erreur","Veuillez remplir tous les champs","OK",null);
            
        }else{
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            String str = new String(req.getResponseData());//Respnse json
            System.out.println("data == "+str);

            Dialog.show("Success","Client modifier avec succes!","OK",null);
            new HomeClient().show();
            req.removeResponseListener(this);
            }
        });
        }
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution de req
        
    }
    
    //Modif admin
    public void ModifierAdmin(User user) {
        String url = Statics.BASE_URL+"/modifieUserJson?id="+user.getId()+"&userName="+user.getUserName()+"&email="+
                user.getEmail()+"&numTel="+user.getNumTel()+"&address="+user.getFullAddress();
        
        if(user.getUserName().isEmpty() || user.getEmail().isEmpty()
                || user.getNumTel().isEmpty() || user.getFullAddress().isEmpty()) {
            
            Dialog.show("Erreur","Veuillez remplir tous les champs","OK",null);
            
        }else{
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            String str = new String(req.getResponseData());//Respnse json
            System.out.println("data == "+str);
            

            Dialog.show("Success","Client modifier avec succes!","OK",null);
                new Home().show();
                req.removeResponseListener(this);
            }
        });
        }
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution de req
        
    }
    
    //Modif profil admin
    public void ModifierProfilAdmin(User user) {
        String url = Statics.BASE_URL+"/modifieAdminJson?id="+user.getId()+"&userName="+user.getUserName()+"&email="+
                user.getEmail()+"&numTel="+user.getNumTel()+"&address="+user.getFullAddress();
        
        if(user.getUserName().isEmpty() || user.getEmail().isEmpty()
                || user.getNumTel().isEmpty() || user.getFullAddress().isEmpty()) {
            
            Dialog.show("Erreur","Veuillez remplir tous les champs","OK",null);
            
        }else{
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            String str = new String(req.getResponseData());//Respnse json
            System.out.println("data == "+str);
            

            Dialog.show("Success","Admin modifier avec succes!","OK",null);
                new Home().show();
                req.removeResponseListener(this);
            }
        });
        }
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution de req
        
    }
    
    
    
    
    //Delete 
    public boolean deleteClient(int id ) {
        String url = Statics.BASE_URL +"/deleteClientjson?id="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                     Dialog.show("Success","Client supprimer avec succes!","OK",null);
                    new Home().show();
                    req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
    //Affichage list client
    public ArrayList<User> affichageUser() {
        ArrayList<User> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/displayUserJson";
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object> mapUsers = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String, Object>> listOfMaps =  (List<Map<String, Object>>) mapUsers.get("root");
                    System.out.println("data == "+listOfMaps);
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        User user = new User();
                        
                        
                        
                        
                        
                        float id = Float.parseFloat(obj.get("id").toString());
                        user.setId(id);
                        user.setUserName(obj.get("username").toString());
                        user.setEmail(obj.get("email").toString());
                        user.setNumTel(obj.get("numTel").toString());
                        user.setFullAddress(obj.get("fullAddress").toString());
                       
                        
                        //insert data into ArrayList result
                        result.add(user);
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
    }

}
    
    
    

    
    


