/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;


import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Evenement;
import com.mycompany.entities.Service;
import com.mycompany.services.ServiceEvenement;
import com.mycompany.services.ServiceService;

import java.util.ArrayList;


/**
 *
 * @author Sondes
 */
public class ListEvenements  extends Form{

   private Form current;
   Container container ;
    
 public ListEvenements(Form previous) {
     current=this;
        setTitle("Liste des evenements");
        setLayout(BoxLayout.y());

        /*SpanLabel sp = new SpanLabel();
        sp.setText(ServiceTask.getInstance().getAllTasks().toString());
        add(sp);
         */
        ArrayList<Evenement> tasks = ServiceEvenement.getInstance().affichageCategory();
        for (Evenement t : tasks) {
            addElement(t);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

   public void addElement(Evenement t) {
    container = new Container(BoxLayout.y());
     //Label IDTxt = new Label("ID : " + t.getId());
    Label nomTxt = new Label("titreev : " + t.getTitreev());
    Label descriptionTxt = new Label("descriptionev : " + t.getDescriptionev());
    Label typeTxt = new Label("typeev : " + t.getTypeev());
    Label tri= new Label("******************");
             tri.setAlignment(CENTER);
     Button btnModifier = new Button("Modifier ");
     Button btnSupprimer = new Button("Supprimer ");
     
     btnSupprimer.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        
        
            double id = (double) t.getId();
        ServiceEvenement.getInstance().deleteEvent((int) id);
            
        
    }
});
       
         //container.addComponent( IDTxt);
    
     
   
    
    
    
    btnModifier.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        // create a new instance of the Service entity
        Evenement evenement = new Evenement();
        //service.setId((int)dataItem.get("id"));
        evenement.setTitreev(t.getTitreev());
        evenement.setDescriptionev(t.getDescriptionev());
        evenement.setTypeev( t.getTypeev());

        new ModifierEvent(current,evenement, (double) t.getId());
    }
            });
container.addComponent(nomTxt);
    container.addComponent(descriptionTxt);
    container.addComponent(typeTxt);
    container.add(tri);
     
      container.addComponent( btnSupprimer);
      
           container.addComponent( btnModifier);
            add(container);
    }
    
       
    
    }
