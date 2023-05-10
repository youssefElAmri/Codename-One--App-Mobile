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
import com.mycompany.entities.Commande;
import com.mycompany.services.ServiceCommande;
import com.mycompany.services.ServiceReclamation;

import java.util.List;

/**
 *
 * @author bhk
 */
public class ListCommandeFront extends Form {
        private Form current;


     public ListCommandeFront(Form previous) {
         current = this;
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
         setTitle("Liste des commandes");
         setLayout(BoxLayout.y());
       Map<String, Object> parsedData = ServiceCommande.getInstance().getAllOrder();
    List<Map<String, Object>> dataList = (List<Map<String, Object>>) parsedData.get("root");
    int len = dataList.size();
         Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    for (int i = 0; i < len; i++) {
        Map<String, Object> dataItem = dataList.get(i);
       // Label idLabel = new Label("ID: " + dataItem.get("id"));
        //Label iduserLabel = new Label("User: " + dataItem.get("user"));
        Label createdAtLabel = new Label("Status: " + dataItem.get("status"));
        Label isPaidLabel = new Label("total: " + dataItem.get("total"));
        Label tri= new Label("******************");
        tri.setAlignment(CENTER);
        Button btnSupprimer = new Button("Supprimer ");
        Button update = new Button("Editer");


           btnSupprimer.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent evt) {


                  double id = (double) dataItem.get("id");
              ServiceCommande.getInstance().deleteCommande((int) id);


          }
      });
           update.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        // create a new instance of the Service entity
        Commande produit = new Commande();
        //service.setId((int)dataItem.get("id"));
        produit.setStatus((String) dataItem.get("status"));
        produit.setTotal(String.valueOf(dataItem.get("total")));

        new ModifCommande(current,produit, (Double) dataItem.get("id"));
    }
            });
                
        container.addAll(createdAtLabel, isPaidLabel,btnSupprimer,tri);
                
    }
    add(container);   
     }
}
