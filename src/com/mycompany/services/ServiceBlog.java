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
import com.mycompany.entities.Blog;
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
public class ServiceBlog {

    public ArrayList<Blog> tasks;

    public static ServiceBlog instance = null;
    public boolean resultOK;
    String json;
    public ArrayList<Blog> BlogArrayList;
    private ConnectionRequest req;

    private ServiceBlog() {
        req = new ConnectionRequest();
    }

    public static ServiceBlog getInstance() {
        if (instance == null) {
            instance = new ServiceBlog();
        }
        return instance;
    }

    public boolean addTask(Blog t) {

        
        String title=t.getTitle();
        String content=t.getContent();
        int likes=t.getLikes();
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "/blog/api/addblog/?title=" + title + "&content=" + content;

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
   
   public Map<String, Object> getAllBlogs() {
    req = new ConnectionRequest();
    String url = Statics.BASE_URL+"/blog/api/getblog";
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
    
    public boolean deleteBlog(int id) {
       System.out.println(id);
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "/blog/api/deleteblog/"+id;

        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Dialog.show("Success","Blog supprimer avec succes!","OK",null);
                new Home().show();
                    req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       return resultOK;
    }
    
    public void modifierBlog(Blog blog) {
        String url = Statics.BASE_URL+"/blog/api/updateblog/"+blog.getId()+"?title="+blog.getTitle()+"&content="+
                blog.getContent()+"&likes="+blog.getLikes();
        
        
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            String str = new String(req.getResponseData());//Respnse json
            System.out.println("data == "+str);

            Dialog.show("Success","Service modifier avec succes!","OK",null);
            new Home().show();
            req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution de req
        
    }

    
    }
