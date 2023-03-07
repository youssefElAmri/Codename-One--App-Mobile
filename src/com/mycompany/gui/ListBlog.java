/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.IconHolder;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Blog;
import com.mycompany.services.ServiceBlog;
import java.io.IOException;
import java.util.ArrayList.*;
import java.util.ArrayList;

/**
 *
 * @author 
 */
public class ListBlog extends Form {

    public ListBlog(Form previous) {
        setTitle("List tasks");
        setLayout(BoxLayout.y());

        ArrayList<Blog> blogs = ServiceBlog.getInstance().getAllBlogs();
        
        for (Blog b : blogs) {
            addElement(b);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    public void addElement(Blog blog) {
        // create container to hold blog information and buttons
        Container blogContainer = new Container(new BorderLayout());
        Label title = new Label(blog.getTitle());
        Label content = new Label(blog.getContent());
        ImageViewer image = new ImageViewer();
        if (blog.getImage() != null) {
            try {
                Image img = Image.createImage(blog.getImage());
                image.setImage(img);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        blogContainer.add(BorderLayout.NORTH, title);
        blogContainer.add(BorderLayout.CENTER, content);
        blogContainer.add(BorderLayout.EAST, createButtons(blog));

        add(blogContainer);
    }

    public Container createButtons(Blog blog) {
        Container buttons = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Button delete = new Button("Delete");
        delete.addActionListener(e -> {
            ServiceBlog.getInstance().deleteBlog(blog.getId());
            refresh();
        });
        Button update = new Button("Update");
        update.addActionListener(e -> {
            // show the update form passing the current blog
        	UpdateBlogForm updateBlog = new UpdateBlogForm(ListBlog.this, blog);
            updateBlog.show();
        });
        buttons.add(delete);
        buttons.add(update);
        return buttons;
    }

    public void refresh() {
        removeAll();
        ArrayList<Blog> blogs = ServiceBlog.getInstance().getAllBlogs();
        for (Blog b : blogs) {
            addElement(b);
        }
        revalidate();
    }
}