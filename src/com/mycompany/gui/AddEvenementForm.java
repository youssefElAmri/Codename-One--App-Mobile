/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entities.Evenement;
import com.mycompany.services.ServiceEvenement;


/**
 *
 * @author Sondes
 */
public class AddEvenementForm extends Form {
     public AddEvenementForm(Form previous) {
        setTitle("Add a new evenement");
        setLayout(BoxLayout.y());
        
        TextField tfName = new TextField("","titreev ");
        TextField tdescription = new TextField("","descriptionev");
        TextField ttype = new TextField("","typeev");
        
        
        
        
        Button btnValider = new Button("Add ");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                       
                        Evenement t = new Evenement(tfName.getText().toString(),tdescription.getText().toString(),ttype.getText().toString());
                        if( ServiceEvenement.getInstance().addCategory(t))
                        {
                           Dialog.show("Success","Ajouter avec succée",new Command("OK"));
                           new Home().show();
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfName,tdescription,ttype,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
