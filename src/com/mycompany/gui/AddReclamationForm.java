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
import com.mycompany.entities.Reclamation;
import com.mycompany.services.ServiceReclamation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;



/**
 *
 * @author bhk
 */
public class AddReclamationForm extends Form{
    public ArrayList<Reclamation> claimlist;

    public AddReclamationForm(Form previous) {
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
        setTitle("Add a new claim");
        setLayout(BoxLayout.y());
        ComboBox<String> combobox=new ComboBox<>("","Problèmes achat en ligne","Problèmes de navigation sur le site","Problèmes de qualité de produits ou de services","Problèmes de confidentialité et de sécurité des données");
        combobox.addActionListener(evt->{
        String selectedoption=(String)combobox.getSelectedItem();
        switch(selectedoption){
             case "":
            // User selected Option 1
            break;
        case "Option 2":
            // User selected Option 2
            break;
        case "Option 3":
            // User selected Option 3
            break;
        default:
            break;
        }
        
        });
        Label nom_lab=new Label("Nom");
        TextField nom = new TextField("","UserName");
        Label email_lab=new Label("Email");
        TextField email=new TextField("","Email");
        Label des_lab=new Label("Description");
        TextField description=new TextField("","Description");
        Button btnValider = new Button("envoyer une reclamation");
        Date currentDate=new Date();
        SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date_reclamation = dateformat.format(currentDate);
       btnValider.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        if ((nom.getText().length()==0)||(description.getText().length()==0)||(combobox.getSelectedItem().length()==0))
            Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
        else
        {  
            Reclamation t = new Reclamation(combobox.getSelectedItem().toString(), nom.getText().toString(),description.getText().toString(),"en cours",date_reclamation,email.getText().toString());
            boolean result = ServiceReclamation.getInstance().addTask(t);
            if (result) {
                Dialog.show("Success", "Claim added successfully", new Command("OK"));
                // Navigate back to the previous form
                previous.showBack();
            } else {
                Dialog.show("Error", "Error occurred while adding task", new Command("OK"));
            }
        }
    }
});
        
        addAll(combobox,nom_lab,nom,email_lab,email,des_lab,description,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());  
    }
    
    
}
