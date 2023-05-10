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
import com.mycompany.entities.Blog;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceBlog;
import com.mycompany.services.ServiceUser;

/**
 *
 * @author MSI
 */
public class ModifBlog extends Form{
    
     private TextField tfTitre,tfContent,tfLikes;
    
    public ModifBlog(Form previos,Blog blog,double id) {
        
          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previos.showBack());
        
        
        setTitle("Modifier Blog");
        setLayout(BoxLayout.yCenter());
        
         tfTitre = new TextField(blog.getTitle(),"");
         tfContent = new TextField(blog.getContent(),"");
         tfLikes = new TextField((Integer.toString(blog.getLikes())),"");
         
        
        Button btnValider = new Button("Enregistrer");
        
        
        
        
        add(tfTitre);
        add(tfContent);
        add(tfLikes);
        setScrollable(true);
        add(btnValider);
        show();
        
        btnValider.requestFocus();
        btnValider.addActionListener((e) -> {
            
           Blog s = new Blog((int) id,tfTitre.getText().toString(),tfContent.getText().toString(),Integer.parseInt(tfLikes.getText().toString()));
           ServiceBlog.getInstance().modifierBlog(s);
        });
    }
    
    
}
