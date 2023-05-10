/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.mycompany.services.ServiceUser;
import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.gui.SessionManager;



/**
 *
 * @author MSI
 */
public class SignInForm extends Form{
    
    
    private Form current;
    private Resources theme;
    
    private Container cnt1,cnt2;
    private TextField tfEmail,tfPwd;
    private ImageViewer img;
    
    private Button btnValider,signUp,mp;

    
    public SignInForm(Resources res) {
        
    
    current=this;
        setTitle("Connexion");
        setLayout(BoxLayout.yCenter());
        
         tfEmail = new TextField("", "Email");
         
         tfPwd = new TextField("", "Password");
         
        tfPwd.setConstraint(TextField.PASSWORD);
        
         btnValider = new Button("Connexion");
        
         signUp = new Button("Register");
        
       
        //add(BorderLayout.NORTH, new Label(res.getImage("bg.png"), "LogoLabel"));
        
        //mp oublié
         mp = new Button("oublier mot de passe?","CenterLabel");
        
        mp.setAlignment(CENTER);
        
        
        
        signUp.addActionListener(e -> new SignUpForm(res,current));
        
        Label doneHaveAnAccount = new Label("Vous n'avez pas de compte?","CenterLabel");
        
        cnt1 = new Container(BoxLayout.x());
        cnt1.addAll(doneHaveAnAccount,signUp);
       
        
       
        add(tfEmail);
        
        add(tfPwd);
        add(btnValider);
        add(cnt1);
        //add(mp);
        
        setScrollable(true);
        show();
        
         btnValider.requestFocus();
        btnValider.addActionListener((e) -> {
            
           ServiceUser.getInstance().signin(tfEmail,tfPwd, res);
            Dialog.show("Success","Sign in succesfully!","OK",null);
            SessionManager session = new SessionManager();
            System.out.println(session.getRoles());
            if(session.getRoles().contains("ROLE_ADMIN")) {
                new Home();
            } else {
                new HomeClient();

            }
        });
    }
    
}
