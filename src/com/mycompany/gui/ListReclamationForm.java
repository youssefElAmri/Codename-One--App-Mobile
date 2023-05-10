/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;


import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import java.util.Map;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.services.ServiceEvenement;

import com.mycompany.services.ServiceReclamation;

import java.util.List;

/**
 *
 * @author bhk
 */
public class ListReclamationForm extends Form {
    
    private String status;

     public ListReclamationForm(Form previous) {
         
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
         setTitle("Liste des reclamation");
         setLayout(BoxLayout.y());
       Map<String, Object> parsedData = ServiceReclamation.getInstance().getAllclaim();
    List<Map<String, Object>> dataList = (List<Map<String, Object>>) parsedData.get("root");
    int len = dataList.size();
         Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    for (int i = 0; i < len; i++) {
        Map<String, Object> dataItem = dataList.get(i);
        //Label idLabel = new Label("ID: " + dataItem.get("id"));
        Label typeLabel = new Label("Type: " + dataItem.get("type"));
        Label descriptionLabel = new Label("Description: " + dataItem.get("description"));
        switch (((String)dataItem.get("status")).toString()) {
                 case "0":
                     status="en cours";
                     System.out.println("en cours");
                     break;
                 case "1":
                     status="accepter";
                  System.out.println("accepter");
                     break;
                 default:
                     status="refuser";
                     System.out.println("refuser");
                     break;
    }
        Label statusLabel = new Label("Status: " + status);
        Label dateLabel = new Label("Date: " + dataItem.get("date_reclamation"));
        Label tri= new Label("******************");
             tri.setAlignment(CENTER);
              Button btnSupprimer = new Button("Supprimer ");
     
                btnSupprimer.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent evt) {


                       double id = (double) dataItem.get("id");
                   ServiceReclamation.getInstance().deleteReclamation((int) id);


               }
           });
               
                    container.addAll( typeLabel, descriptionLabel, statusLabel, dateLabel,btnSupprimer,tri);
               
        
    }
    
    add(container);   
     }
}
