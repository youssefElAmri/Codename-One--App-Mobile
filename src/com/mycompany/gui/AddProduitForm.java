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
import com.mycompany.entities.Produit;
import com.mycompany.services.ServiceProduit;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;



/**
 *
 * @author bhk
 */
public class AddProduitForm extends Form{
    public ArrayList<Produit> claimlist;

    public AddProduitForm(Form previous) {
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
        setTitle("Add a new Produit");
        setLayout(BoxLayout.y());
        Label nom_lab=new Label("Nom");
        TextField nom = new TextField("","Nom");
        Label description_lab=new Label("Desciption");
        TextField description=new TextField("","Desciption");
        Label prix_lab=new Label("Prix");
        TextField prix=new TextField("","prix");
        Button btnValider = new Button("ajouter un produit");
       btnValider.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        if ((nom.getText().length()==0)||(description.getText().length()==0)||(prix.getText().length()==0))
            Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
        else
        {  
            Produit t = new Produit( nom.getText().toString(),description.getText().toString(),prix.getText());
            boolean result = ServiceProduit.getInstance().addTask(t);
            if (result) {
                Dialog.show("Success", "Produit added successfully", new Command("OK"));
                // Navigate back to the previous form
                previous.showBack();
            } else {
                Dialog.show("Error", "Error occurred while adding task", new Command("OK"));
            }
        }
    }
});
        
        addAll(nom_lab,nom,description_lab,description,prix_lab,prix,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());  
    }
    
    
}
