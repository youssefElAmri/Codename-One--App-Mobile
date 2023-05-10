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
public class ListEvenementsFront  extends Form{

   private Form current;
   Container container ;
    
 public ListEvenementsFront(Form previous) {
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
     
     
    
       
         //container.addComponent( IDTxt);
    
     
   
    
    
    
   
container.addComponent(nomTxt);
    container.addComponent(descriptionTxt);
    container.addComponent(typeTxt);
    container.add(tri);
     
      
            add(container);
    }
    
       
    
    }
