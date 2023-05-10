/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Service;
import com.mycompany.entities.User;
import com.mycompany.gui.Home;
import com.mycompany.gui.HomeClient;
import com.mycompany.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;



/**
 *
 * @author mfmma
 */
public class ServiceService {
    
    private ConnectionRequest serv;
    public ArrayList<Service> service;

    public static ServiceService instance = null;
    public boolean resultOK;
    boolean res = false;

    private ServiceService() {
        serv = new ConnectionRequest();
    }

        public static ServiceService getInstance() {
        if (instance == null) {
            instance = new ServiceService();
        }
        return instance;
    }
    
    
    
    public boolean ajoutService(Service service){
        
        String typeS=service.getTypeS();
        String descriptionS=service.getDescriptionS();
        String titreS=service.getTitreS();
        String dateS = service.getDateS();
        
        String url = Statics.BASE_URL+"/api/addServiceApi/?titreS="+service.getTitreS()+"&descriptionS="+service.getDescriptionS()
                + "&typeS="+service.getTypeS()+"&dateS="+service.getDateS();

        serv.setUrl(url);
        serv.setPost(false);
        
       serv.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = serv.getResponseCode() == 200; //Code HTTP 200 OK
                serv.removeResponseListener(this);
            }
        });
        
 
        
        //Execution de la request
        NetworkManager.getInstance().addToQueueAndWait(serv);
        return resultOK;

}
    
    public boolean deleteService(int id) {
       System.out.println(id);
        String url = Statics.BASE_URL + "/api/deleteService?id="+id;

        serv.setUrl(url);
        serv.setPost(false);
        
        serv.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                    Dialog.show("Success","Client supprimer avec succes!","OK",null);
                    new Home().show();
                    serv.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(serv);
        return resultOK;
    }

//Modif service
//    public boolean modifierService(Service service) {
//        
//              String url= Statics.BASE_URL +"/api/updateService?titreS="+service.getTitreS()+"&descriptionS="+service.getDescriptionS()+ "&typeS="+service.getTypeS()+"&dateS="+service.getDateS();
//              
//        
//        if(service.getTitreS().isEmpty() || service.getDescriptionS().isEmpty()
//                || service.getTypeS().isEmpty()) {
//            
//            Dialog.show("Erreur","Veuillez remplir tous les champs","OK",null);
//            
//        }else{
//        serv.setUrl(url);
//        serv.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//            String str = new String(serv.getResponseData());//Respnse json
//            System.out.println("data == "+str);
//            
//
//                Dialog.show("Success","Client modifier avec succes!","OK",null);
//                new Home().show();
//                serv.removeResponseListener(this);
//                res=true;
//            }
//            
//        });
//        }
//        
//        NetworkManager.getInstance().addToQueueAndWait(serv);//execution de req
//        return res;
//    }
    
    
     //Modif client
    public void modifierService(Service service) {
        String url = Statics.BASE_URL+"/modifieServiceJson?id="+service.getId()+"&titre_s="+service.getTitreS()+"&description_s="+
                service.getDescriptionS()+"&type_s="+service.getTypeS();
        
        
        serv.setUrl(url);
        serv.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            String str = new String(serv.getResponseData());//Respnse json
            System.out.println("data == "+str);

            Dialog.show("Success","Service modifier avec succes!","OK",null);
            new Home().show();
            serv.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(serv);//execution de req
        
    }
     
   public Map<String, Object> getAllServices() {
        
    serv = new ConnectionRequest();
    String url = Statics.BASE_URL+"/api/getService";
    serv.setUrl(url);
    serv.setPost(false);
    final Map<String, Object>[] response = new Map[]{null};
    serv.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            JSONParser j = new JSONParser();
            serv.removeResponseListener(this);
            String json = new String(serv.getResponseData()) + "";
            try {
                response[0] = j.parseJSON(new CharArrayReader(json.toCharArray()));
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(serv);
    return response[0];
}
    
}
