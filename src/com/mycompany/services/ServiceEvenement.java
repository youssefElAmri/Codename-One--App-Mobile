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
import com.codename1.ui.Dialog;

import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Evenement;
import com.mycompany.entities.Service;
import com.mycompany.gui.Home;
import com.mycompany.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sondes
 */
public class ServiceEvenement {
    public static ServiceEvenement instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ArrayList<Evenement> listCategory=new ArrayList<>();

    private ServiceEvenement() {
        req = new ConnectionRequest();
    }

    public static ServiceEvenement getInstance() {
        if (instance == null) {
            instance = new ServiceEvenement();
        }
        return instance;
    }

    public boolean addCategory(Evenement t) {

        String titreev = t.getTitreev();
      String descriptionev=t.getDescriptionev();
      String typeev=t.getTypeev();
        
       
        String url =  Statics.BASE_URL + "/create?titreev=" + titreev + "&descriptionev=" + descriptionev +"&typeev="+typeev;
        
        
        //String url = statics.BASE_URL + "/evenementAddjson?titreev=" + titreev + "&descriptionev=" + descriptionev + "&typeev="+ typeev;

        
        
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    /********** Affichage********************/
      
     public ArrayList<Evenement> affichageCategory()
    {

        ArrayList<Evenement> result = new ArrayList<>();
        String url =Statics.BASE_URL +"/get";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try 
                {
                    Map<String,Object>mapMaison = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> ListOfMaps = (List<Map<String,Object>>) mapMaison.get("root");
                    System.out.println(mapMaison);
                    for(Map<String, Object> obj : ListOfMaps)
                    {
                        System.out.println(obj);
                       Evenement c = new Evenement();
                       
                        String titreev = obj.get("titreev").toString();
                         float id = Float.parseFloat(obj.get("id").toString());
                         String descriptionev = obj.get("descriptionev").toString();
                         String typeev = obj.get("typeev").toString();
                         //Date dateev = obj.get("dateev");
                       

                       c.setId((int)id);
                        c.setTitreev(titreev);
                        
                        
                        c.setDescriptionev(descriptionev);
                        c.setTypeev(typeev);
                        
                        
                        result.add(c);
                        System.out.println(c.getTitreev()+c.getDescriptionev()+c.getTypeev());
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
     
     
     public void deleteEvent(int id) {
       System.out.println(id);
        String url = Statics.BASE_URL + "/deleteEvenementjson?id="+id;

        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                    Dialog.show("Success","Event supprimer avec succes!","OK",null);
                    new Home().show();
                    req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
     
     /************************************************************************************************/    
   /*public ArrayList<category> DeleteCategorieAction(int id){
        String url ="http://127.0.0.1:8000" + "/delete" + id;
         ConnectionRequest req = new ConnectionRequest(url);
         System.out.println(url);
         req.setUrl(url);
         req.setPost(false);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            
                    req.removeResponseListener(this);
              
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listCategory;
    }*/  
     
     
      public void modifierEvenement(Evenement evenement) {
        String url = Statics.BASE_URL+"/modifieEvenementJson?id="+evenement.getId()+"&titreev="+evenement.getTitreev()+"&descriptionev="+
                evenement.getDescriptionev()+"&typeev="+evenement.getTypeev();
        
        
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            String str = new String(req.getResponseData());//Respnse json
            System.out.println("data == "+str);

            Dialog.show("Success","Event modifier avec succes!","OK",null);
            new Home().show();
            req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution de req
        
    }
     
   public Map<String, Object> getAllServices() {
        
    req = new ConnectionRequest();
    String url = Statics.BASE_URL+"/get";
    req.setUrl(url);
    req.setPost(false);
    final Map<String, Object>[] response = new Map[]{null};
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            JSONParser j = new JSONParser();
            req.removeResponseListener(this);
            String json = new String(req.getResponseData()) + "";
            try {
                response[0] = j.parseJSON(new CharArrayReader(json.toCharArray()));
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return response[0];
}
    
}
   


    
   

