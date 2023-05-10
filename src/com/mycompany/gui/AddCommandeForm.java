/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.JSONParser;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Commande;

import com.mycompany.services.ServiceCommande;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;



/**
 *
 * @author bhk
 */
public class AddCommandeForm extends Form{
    public ArrayList<Commande> claimlist;

    public AddCommandeForm(Form previous) {
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
        setTitle("Add a new order");
        setLayout(BoxLayout.y());
       
        
        
        
        Label reference=new Label("status");
        TextField referencee = new TextField("","status");
        Label prix_lab=new Label("total");
        TextField prix=new TextField("total");
        
        Button btnValider = new Button("ajouter une commande");
       btnValider.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        if ((reference.getText().length()==0))
            Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
        else
        {  
            Commande t = new Commande(referencee.getText().toString(),prix.getText().toString());
            boolean result = ServiceCommande.getInstance().addTask(t);
            
            

            if (result) {
                Dialog.show("Success", "Order added successfully", new Command("OK"));
                // Navigate back to the previous form
                previous.showBack();
            } else {
                Dialog.show("Error", "Error occurred while adding task", new Command("OK"));
            }
        }
    }
        });
        
        addAll(reference,referencee,prix_lab,prix,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());  
    }
    
    
}
