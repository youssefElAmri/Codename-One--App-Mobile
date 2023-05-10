/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author MSI
 */
public class HomeClient extends Form{
    
    private Form current;
    private Resources theme;
    
     public HomeClient() {
   
    
    //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previos.showBack());
    
         
        current=this;
        setTitle("Welcome Client");
        setLayout(BoxLayout.y());
        
        getToolbar().addCommandToSideMenu("List Blog", null, ev -> {
                    new ListServiceFront(current).show();
                });
        getToolbar().addCommandToSideMenu("List Produit", null, ev -> {
                    new ListProduitFront(current).show();
                });
        getToolbar().addCommandToSideMenu("List Evenements", null, ev -> {
                    new ListEvenementsFront(current).show();
                });
        getToolbar().addCommandToSideMenu("List Commandes", null, ev -> {
            new ListCommandeFront(current).show();
        });
         getToolbar().addCommandToSideMenu("Ajout Reclamation", null, ev -> {
            new AddReclamationForm(current).show();
        });
        getToolbar().addCommandToSideMenu("List Reclamation", null, ev -> {
                    new ListReclamationForm(current).show();
                });
        getToolbar().addCommandToSideMenu("Profile", null, ev -> {
                    new ProfilForm(current);
                });
         getToolbar().addCommandToSideMenu("LogOut", null, ev -> {
                    new SignInForm(theme);
                    Dialog.show("Success","Log out succesfully!","OK",null);
                });
        show();
        
     }
    
}
