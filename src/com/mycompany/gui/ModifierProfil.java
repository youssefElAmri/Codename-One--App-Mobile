/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceUser;
import java.util.ArrayList;

/**
 *
 * @author MSI
 */
public class ModifierProfil extends Form{
    
    private Form current;
    private Resources theme;
    
    private Container cnt1;
    private TextField tfFullName,tfEmail,tfFullAddress,tfNumTell,tfPwd;

    
    public ModifierProfil(Form previos) {
        
          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previos.showBack());
        
        current=this;
        setTitle("Modifier profil client");
        setLayout(BoxLayout.yCenter());
        
         tfFullName = new TextField(SessionManager.getUserName(), "");
         tfEmail = new TextField(SessionManager.getEmail(), "");
         tfFullAddress = new TextField(SessionManager.getFullAddress(), "");
         tfNumTell = new TextField(SessionManager.getNumTel(), "");
        
        Button btnValider = new Button("Enregistrer");
        
        
        
        
        add(tfFullName);
        add(tfEmail);
        add(tfFullAddress);
        add(tfNumTell);
        setScrollable(true);
        add(btnValider);
        show();
        
        btnValider.requestFocus();
        btnValider.addActionListener((e) -> {
            User user = new User(SessionManager.getId(),tfFullName.getText().toString(), tfEmail.getText().toString(),
                    tfNumTell.getText().toString(),tfFullAddress.getText().toString());
            
                
                SessionManager.setUserName(tfFullName.getText().toString());
                SessionManager.setEmail( tfEmail.getText().toString());
                SessionManager.setFullAddress(tfFullAddress.getText().toString());
                SessionManager.setNumTel(tfNumTell.getText().toString());
            ServiceUser.getInstance().ModifierUser(user);
           
        });
    }
    
}
