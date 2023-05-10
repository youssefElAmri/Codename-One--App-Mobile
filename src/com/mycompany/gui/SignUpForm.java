/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.ImageViewer;
import com.codename1.components.OnOffSwitch;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.services.ServiceUser;
import java.util.Vector;

/**
 *
 * @author MSI
 */
public class SignUpForm extends Form{
    
    private Form current;
    private Resources theme;
    
    private Container cnt1;
    private TextField tfFullName,tfEmail,tfFullAddress,tfNumTell,tfPwd;

    
    public SignUpForm(Resources res,Form previos) {
        
          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previos.showBack());
        
         current=this;
        setTitle("Inscription");
        setLayout(BoxLayout.yCenter());
         tfFullName = new TextField("", "FullName");
         tfEmail = new TextField("", "Email");
         tfFullAddress = new TextField("", "FullAddress");
         tfNumTell = new TextField("", "NumTel");
         tfPwd = new TextField("", "Password");
         
        tfPwd.setConstraint(TextField.PASSWORD);
        
        Button btnValider = new Button("Valider");
         Button  alreadHaveAnAccount = new Button("Already have an account??","CenterLabel");
         alreadHaveAnAccount.setAlignment(alreadHaveAnAccount.CENTER);
        
        add(tfFullName);
        add(tfEmail);
        add(tfFullAddress);
        add(tfNumTell);
        add(tfPwd);
        setScrollable(true);
        add(btnValider);
        add(alreadHaveAnAccount);
        show();
        
        alreadHaveAnAccount.addActionListener((e) -> {
           
            new SignInForm(res);
            
            
        });
        
         btnValider.requestFocus();
        btnValider.addActionListener((e) -> {
            
            ServiceUser.getInstance().signup(tfFullName, tfPwd, tfEmail, tfNumTell, tfFullAddress, res);
           // Dialog.show("Success","account is saved","OK",null);
           // new SignInForm(res);
        });
    }
    
}
