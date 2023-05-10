/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

/**
 *
 * @author mfmma
 */

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.Form;
import com.mycompany.entities.Blog;
import com.mycompany.services.ServiceBlog;
import java.util.Date;




public class AddServiceForm extends Form {
    
    public AddServiceForm(Form previous){
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
     setTitle("Add a new Blog");
        setLayout(BoxLayout.y());
        
        TextField tfTitre = new TextField("","TITRE");
        TextField tfContent = new TextField("","Content");
        
        
        
        Date currentDate=new Date();
        SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date_service= dateformat.format(currentDate);
        

        Button btnValider = new Button("Add Service");
        
        
        btnValider.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        if ((tfTitre.getText().length()==0)||(tfContent.getText().length()==0))
            Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
        else
        {  
           Blog s = new Blog( tfTitre.getText().toString(),tfContent.getText().toString());
            boolean result = ServiceBlog.getInstance().addTask(s);
            if (result) {
                Dialog.show("Success", "Blog added successfully", new Command("OK"));
                // Navigate back to the previous form
                previous.showBack();
            } else {
                Dialog.show("Error", "Error occurred while adding Blog", new Command("OK"));
            }
        }
    }
});
        
        addAll(tfTitre,tfContent,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        show();
                
                
    }
    
    }



