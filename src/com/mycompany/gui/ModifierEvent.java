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
import com.mycompany.entities.Evenement;
import com.mycompany.entities.Service;
import com.mycompany.services.ServiceEvenement;
import com.mycompany.services.ServiceService;

/**
 *
 * @author MSI
 */
public class ModifierEvent extends Form{
    
    private TextField tfTitreV,tfTypeV,tfDescV;
    
    public ModifierEvent(Form previos,Evenement evenement,double id) {
        
          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previos.showBack());
        
        
        setTitle("Modifier service");
        setLayout(BoxLayout.yCenter());
        
         tfTitreV = new TextField(evenement.getTitreev(), "");
         tfTypeV = new TextField(evenement.getTypeev(), "");
         tfDescV = new TextField(evenement.getDescriptionev(), "");
         
        
        Button btnValider = new Button("Enregistrer");
        
        
        
        
        add(tfTitreV);
        add(tfTypeV);
        add(tfDescV);
        setScrollable(true);
        add(btnValider);
        show();
        
        btnValider.requestFocus();
        btnValider.addActionListener((e) -> {
            
           Evenement ev = new Evenement((int) id,tfTitreV.getText().toString(),tfDescV.getText().toString(),tfTypeV.getText().toString());
           ServiceEvenement.getInstance().modifierEvenement(ev);
        });
    }
    
    
}
