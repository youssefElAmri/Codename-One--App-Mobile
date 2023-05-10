/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Produit;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceProduit;
import com.mycompany.services.ServiceUser;

/**
 *
 * @author MSI
 */
public class ModifProduit extends Form{
    
     private TextField tfTitre,tfContent,tfLikes;
    
    public ModifProduit(Form previos,Produit produit,double id) {
        
          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previos.showBack());
        
        
        setTitle("Modifier Produit");
        setLayout(BoxLayout.yCenter());
        
         tfTitre = new TextField(produit.getNom(),"");
         tfContent = new TextField(produit.getDescription(),"");
         tfLikes = new TextField(produit.getPrix(),"");
         
        
        Button btnValider = new Button("Enregistrer");
        
        
        
        
        add(tfTitre);
        add(tfContent);
        add(tfLikes);
        setScrollable(true);
        add(btnValider);
        show();
        
        btnValider.requestFocus();
        btnValider.addActionListener((e) -> {
            
           Produit s = new Produit((int) id,tfTitre.getText().toString(),tfContent.getText().toString(),tfLikes.getText().toString());
           ServiceProduit.getInstance().modifierProduit(s);
        });
    }
    
    
}
