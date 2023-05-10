/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Reclamation;
import com.mycompany.gui.Home;
import com.mycompany.gui.SessionManager;
import com.mycompany.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceReclamation {

    public ArrayList<Reclamation> tasks;

    public static ServiceReclamation instance = null;
    public boolean resultOK;
    String json;
    public ArrayList<Reclamation> ReclamationArrayList;
    private ConnectionRequest req;

    private ServiceReclamation() {
        req = new ConnectionRequest();
    }

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }

    public boolean addTask(Reclamation t) {

        
        String type=t.getType();
        String nom=t.getNom();
        String description=t.getDescription();
        String status=t.getStatus();
        String date=t.getDatereclamation();
        String email=t.getEmail();
        String email_c=SessionManager.getEmail();
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "/api/addreclamation/?type=" + type + "&nom=" + nom+"&description="+description+"&status="+0+"&date_reclamation="+date+"&email_reclamation="+email+"&email_connecte="+email_c;

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
   
   public Map<String, Object> getAllclaim() {
    req = new ConnectionRequest();
    String url = Statics.BASE_URL+"/api/getreclamation";
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
    
    public void deleteReclamation(int id) {
       System.out.println(id);
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "/api/deletereclamation/"+id;

        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Dialog.show("Success","Reclamation supprimer avec succes!","OK",null);
                new Home().show();
                    req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }

    
    }
