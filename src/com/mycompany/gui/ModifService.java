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
import com.mycompany.entities.Service;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceService;
import com.mycompany.services.ServiceUser;

/**
 *
 * @author MSI
 */
public class ModifService extends Form{
    
     private TextField tfTitre,tfType,tfDesc;
    
    public ModifService(Form previos,Service service,double id) {
        
          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previos.showBack());
        
        
        setTitle("Modifier service");
        setLayout(BoxLayout.yCenter());
        
         tfTitre = new TextField(service.getTitreS(), "");
         tfType = new TextField(service.getTypeS(), "");
         tfDesc = new TextField(service.getDescriptionS(), "");
         
        
        Button btnValider = new Button("Enregistrer");
        
        
        
        
        add(tfTitre);
        add(tfType);
        add(tfDesc);
        setScrollable(true);
        add(btnValider);
        show();
        
        btnValider.requestFocus();
        btnValider.addActionListener((e) -> {
            
           Service s = new Service((int) id,tfTitre.getText().toString(),tfDesc.getText().toString(),tfType.getText().toString());
           ServiceService.getInstance().modifierService(s);
        });
    }
    
    
}
