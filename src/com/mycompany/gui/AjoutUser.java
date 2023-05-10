/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceUser;

/**
 *
 * @author MSI
 */
public class AjoutUser extends Form{
    
    private Form current;
    private Resources theme;
    
    private Container cnt1;
    private TextField tfFullName,tfEmail,tfFullAddress,tfNumTell,tfPwd;

    
    public AjoutUser(Form previos) {
        
          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previos.showBack());
        
        current=this;
        setTitle("Ajout Client");
        setLayout(BoxLayout.yCenter());
        
         tfFullName = new TextField("", "FullName");
         tfEmail = new TextField("", "Email");
         tfFullAddress = new TextField("", "FullAddress");
         tfNumTell = new TextField("", "NumTel");
         
         tfPwd = new TextField("", "Password");
         
        tfPwd.setConstraint(TextField.PASSWORD);
        
        Button btnValider = new Button("Ajouter");
        
        
        add(tfFullName);
        add(tfEmail);
        add(tfFullAddress);
        add(tfNumTell);
        add(tfPwd);
        setScrollable(true);
        add(btnValider);
        show();
        
        btnValider.requestFocus();
        btnValider.addActionListener((e) -> {
            User user = new User(tfFullName.getText().toString(), tfEmail.getText().toString(), tfNumTell.getText().toString(),
                    tfFullAddress.getText().toString(),tfPwd.getText().toString());
            ServiceUser.getInstance().ajoutUser(user);
           
        });
    }
    
}
