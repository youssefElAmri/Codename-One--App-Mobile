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
import com.mycompany.entities.Commande;
import com.mycompany.gui.Home;
import com.mycompany.gui.SessionManager;
import com.mycompany.utils.Statics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceCommande {

    public ArrayList<Commande> tasks;

    public static ServiceCommande instance = null;
    public boolean resultOK;
    String json;
    public ArrayList<Commande> ReclamationArrayList;
    private ConnectionRequest req;

    private ServiceCommande() {
        req = new ConnectionRequest();
    }

    public static ServiceCommande getInstance() {
        if (instance == null) {
            instance = new ServiceCommande();
        }
        return instance;
    }

    public boolean addTask(Commande t) {

        
        //boolean isPaid=t.isIsPaid();
        String reference=t.getStatus();
        String  total=t.getTotal();
        //String createdAt=t.getCreatedAt();
        System.out.println(reference);
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "/order/api/addOrder/?status=" +reference+"&total="+total;
//        String url = Statics.BASE_URL+"api/addorderApi/?user_id="+commande.getUser_id()+"&reference="+commande.getReference()
//                + "&is_paid="+commande.isIsPaid()+"&dateS="+commande.getDateS();

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
   
   public Map<String, Object> getAllOrder() {
    req = new ConnectionRequest();
    String url = Statics.BASE_URL+"/order/api/getOrder";
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
   
   public void deleteCommande(int id) {
       System.out.println(id);
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "/order/api/deleteOrder/"+id;

        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Dialog.show("Success","Commande supprimer avec succes!","OK",null);
                new Home().show();
                    req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    
    
public void modifierCommande(Commande order) {
        String url = Statics.BASE_URL+"/order/api/updateorder/"+order.getId()+"?status="+order.getStatus()+"&total="+
                order.getTotal();
        
        
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            String str = new String(req.getResponseData());//Respnse json
            System.out.println("data == "+str);

            Dialog.show("Success","Commande modifier avec succes!","OK",null);
            new Home().show();
            req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution de req
        
    }
    
    }
