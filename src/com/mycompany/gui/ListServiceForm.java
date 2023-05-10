/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Blog;
import com.mycompany.services.ServiceBlog;

import java.util.Map;
import java.util.List;


/**
 *
 * @author mfmma
 */
public class ListServiceForm extends Form {
    
    private Form current;
//    
//     public ListServiceForm(Form previous) {
//         
//         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
//             
//        Map<String, Object> parsedData = ServiceService.getInstance().getAllServices();
//        List<Map<String, Object>> dataList = (List<Map<String, Object>>) parsedData.get("root");
//        
//        int len = dataList.size();
//         Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//             for (int i = 0; i < len; i++) {
//            Map<String, Object> dataItem = dataList.get(i);
//            
//            Label titreLabel = new Label("Titre: " + dataItem.get("titreS"));
//            Label typeLabel = new Label("Type: " + dataItem.get("typeS"));
//            Label descriptionLabel = new Label("Description: " + dataItem.get("descriptionS"));
//            //Label dateLabel = new Label("Date: " + dataItem.get("date_reclamation"));
//            container.addAll(titreLabel, typeLabel, descriptionLabel);
//    }
//    add(container);
//       
//    }

    
    public ListServiceForm(Form previous) {
        current=this;
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
        
        setTitle("Liste des Blog");
        setLayout(BoxLayout.y());
        
             
        Map<String, Object> parsedData = ServiceBlog.getInstance().getAllBlogs();
        List<Map<String, Object>> dataList = (List<Map<String, Object>>) parsedData.get("root");
        
        int len = dataList.size();
         Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
             for (int i = 0; i < len; i++) {
            Map<String, Object> dataItem = dataList.get(i);
            
            Label titreLabel = new Label("Titre: " + dataItem.get("title"));
            Label contentLabel = new Label("Content: " + dataItem.get("content"));
            
 Button delete = new Button("Supprimer Blog");
                        Button update = new Button("Editer Blog");

            
        delete.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        
        System.out.println(dataItem.get("id").getClass().getName());
        System.out.println("---------------------------------------------------------");
            double id = (double) dataItem.get("id");
        boolean result = ServiceBlog.getInstance().deleteBlog((int) id);
            
            

            if (result) {
                Dialog.show("Success", "Claim deleted successfully", new Command("OK"));
                // Navigate back to the previous form
                previous.showBack();
            } else {
                Dialog.show("Error", "Error occurred while deleting blog", new Command("OK"));
            }
        
    }
});
        
    update.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        // create a new instance of the Service entity
        Blog blog = new Blog();
        //service.setId((int)dataItem.get("id"));
        blog.setTitle((String) dataItem.get("title"));
        blog.setContent((String) dataItem.get("content"));
        blog.setLikes(0);

        new ModifBlog(current,blog, (Double) dataItem.get("id"));
    }
            });

            Label tri= new Label("******************");
             tri.setAlignment(CENTER);
      
            container.addAll(titreLabel, contentLabel,update,delete,tri);
    }
    add(container);
       
    
    }
   
}
