package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.utils.*;
import com.mycompany.entities.Blog;
import com.mycompany.utils.Statics;
import com.mycompany.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceBlog {

    private ArrayList<Blog> blogs;
    private static ServiceBlog instance = null;
    private boolean resultOK;
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

    public boolean addBlog(Blog b) {
        String url = Statics.BASE_URL + "addblogJSON?title=" + b.getTitle() + "&content=" + b.getContent() + "&image=" + b.getImage();
        req.setUrl(url);
        req.setPost(true);

        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code http 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }



    public ArrayList<Blog> parseBlogs(String jsonText) {
        try {
            blogs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> blogsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) blogsListJson.get("root");

            for (Map<String, Object> obj : list) {
                Blog b = new Blog();
                float id = Float.parseFloat(obj.get("id").toString());
                b.setId((int) id);
                b.setTitle(obj.get("title").toString());
                b.setContent(obj.get("content").toString());
                b.setImage(obj.get("image").toString());
                blogs.add(b);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return blogs;
    }

    public ArrayList<Blog> getAllBlogs() {
        String url = Statics.BASE_URL + "showblogJSON";
        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                blogs = parseBlogs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return blogs;
    }

    
    public boolean deleteBlog(int id) {
        //int id = b.getId();
        String url = Statics.BASE_URL + "deleteblogJSON/" + id;
        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; // HTTP 200 OK
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean updateBlog(Blog b) {
    	
        String url = Statics.BASE_URL + "updateblogJSON/";
        req.setUrl(url);
        req.setPost(true);
        
        String requestBody = "{\"title\":\"" + b.getTitle() + "\",\"content\":\"" + b.getContent() + "\",\"image\":\"" + b.getImage() + "\"}";
        req.setRequestBody(requestBody);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; // HTTP 200 OK
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }


}
