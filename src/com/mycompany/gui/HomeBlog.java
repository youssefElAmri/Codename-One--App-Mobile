/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Youssef El Amri
 */
public class HomeBlog extends Form{

    public HomeBlog() {
        
        setTitle("Home Blog");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddBlog = new Button("Add Blog");
        Button ListBlog = new Button("List Blogs");
        
        btnAddBlog.addActionListener(e-> new AddBlogForm(this).show());
        ListBlog.addActionListener(e-> new ListBlog(this).show());
        addAll(btnAddBlog,ListBlog);
        
        
    }
    
    
}