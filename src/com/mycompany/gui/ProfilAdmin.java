/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author MSI
 */
public class ProfilAdmin extends Form{
    
    private Form current;
    private Resources theme;
    
    private String fullName,email,fullAddress,numTell,role;
    private Label tfFullName,tfEmail,tfFullAddress,tfNumTell,tfRole;
    private Button modifProfil;
    private ArrayList<String> array;
    
    
    public ProfilAdmin(Form previos) {
        
          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previos.showBack());
        
        current=this;
        setTitle("Admin Profil");
        setLayout(BoxLayout.yCenter());
        
        fullName= SessionManager.getUserName();
        email = SessionManager.getEmail();
        fullAddress = SessionManager.getFullAddress();
        numTell = SessionManager.getNumTel();
        array = SessionManager.getRoles();
        
        role = array.get(0);
        
        modifProfil = new Button("Modifier profil");
        
        
        tfFullName = new Label("UserName : "+fullName);
        tfEmail = new Label("Email : "+email);
        tfFullAddress = new Label("Full Address : "+fullAddress);
        tfNumTell = new Label("Num Tel : "+numTell);
        tfRole = new Label("role : "+role);
        
        
        
         modifProfil.addActionListener(e -> new EditProfilAdmin(current));
        addAll(tfFullName,tfEmail,tfFullAddress,tfNumTell,tfRole,modifProfil);
        
        show();
        
    }
    
}
