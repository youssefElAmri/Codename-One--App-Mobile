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
import com.mycompany.entities.Commande;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceCommande;
import com.mycompany.services.ServiceUser;

/**
 *
 * @author MSI
 */
public class ModifCommande extends Form{
    
     private TextField tfTitre,tfContent,tfLikes;
    
    public ModifCommande(Form previos,Commande produit,double id) {
        
          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previos.showBack());
        
        
        setTitle("Modifier Commande");
        setLayout(BoxLayout.yCenter());
        
         tfTitre = new TextField(produit.getStatus(),"");
         tfContent = new TextField(produit.getTotal(),"");
         
        
        Button btnValider = new Button("Enregistrer");
        
        
        
        
        add(tfTitre);
        add(tfContent);
        setScrollable(true);
        add(btnValider);
        show();
        
        btnValider.requestFocus();
        btnValider.addActionListener((e) -> {
            
           Commande s = new Commande((int) id,tfTitre.getText().toString(),tfContent.getText().toString());
           ServiceCommande.getInstance().modifierCommande(s);
        });
    }
    
    
}
