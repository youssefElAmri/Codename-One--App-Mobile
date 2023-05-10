/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceUser;
import java.util.ArrayList;

/**
 *
 * @author MSI
 */
public class ListClients extends Form{
    
    private Form current;
    private Resources theme;
    
    private Container cnt;
     private String fullName,email,fullAddress,numTell,pass;
     private Label tfFullName,tfEmail,tfFullAddress,tfNumTell,tfPass,tfId,tri;
    private float id;
    
    public ListClients(Form previos) {
       
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previos.showBack());
        
        current=this;
        setTitle("List Clients");
        setLayout(BoxLayout.y());
        
        
        
        
        // Welcome current user
        
        //Appel affichage methode
        ArrayList<User> list = ServiceUser.getInstance().affichageUser();
        
        
        
        for(User user : list ) {
            cnt = new Container(BoxLayout.x());
             //Id = user.getId();
             id = user.getId();
             fullName = user.getUserName();
             email = user.getEmail();
             fullAddress = user.getFullAddress();
             numTell = user.getNumTel();
             Button modifProfil = new Button("Modifier profil");
             Button suppProfil = new Button("Supprimer Client");
             suppProfil.setAlignment(RIGHT);
            
             tri= new Label("******************");
             tri.setAlignment(CENTER);
             
             cnt.add(modifProfil);
             cnt.add(suppProfil);
            // tfFullName = new Label(fullName+tfEmail+tfFullAddress+tfNumTell+pass+"**********\n");
             //tfEmail = new Label(email);
             //tfFullAddress = new Label(fullAddress);
             //tfNumTell = new Label(numTell);
             //tfId = new Label(""+Id);
             //addAll(tfId,tfFullName,tfEmail,tfFullAddress,tfNumTell,new Label("****************"));
             //add("Id : "+(int)id);
             add("UserName : "+fullName);
             add("EMail : "+email);
             add("FullAddress : "+fullAddress);
             add("Num Tel : "+numTell);
             add(cnt);
             add(tri);
             setScrollable(true);
             
            modifProfil.addActionListener(e -> new ModifClientFromAdmin(current,user));
            
            
            suppProfil.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ServiceUser.getInstance().deleteClient((int) user.getId());
                }
            });
             
             show();
        }
         
        show();
        
    }
    
}
