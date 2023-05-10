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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceUser;

/**
 *
 * @author MSI
 */
public class ModifClientFromAdmin extends Form{
    
    private Form current;
    private Resources theme;
    
    private Container cnt1;
    private TextField tfFullName,tfEmail,tfFullAddress,tfNumTell,tfPwd;
    
     public ModifClientFromAdmin(Form previos,User user) {
    
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previos.showBack());
        
        current=this;
        setTitle("Modifier profil client");
        setLayout(BoxLayout.yCenter());
        
         tfFullName = new TextField(user.getUserName(), "");
         tfEmail = new TextField(user.getEmail(), "");
         tfFullAddress = new TextField(user.getFullAddress(), "");
         tfNumTell = new TextField(user.getNumTel(), "");
        
        Button btnValider = new Button("Enregistrer");
        
        
        
        
        add(tfFullName);
        add(tfEmail);
        add(tfFullAddress);
        add(tfNumTell);
        setScrollable(true);
        add(btnValider);
        show();
        
        
        
        btnValider.requestFocus();
        btnValider.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            user.setId(user.getId());
            user.setUserName(tfFullName.getText());
            user.setEmail(tfEmail.getText());
            user.setNumTel(tfNumTell.getText());
            user.setFullAddress(tfFullAddress.getText());
            ServiceUser.getInstance().ModifierAdmin(user);
        }
    });
    }
    
}
