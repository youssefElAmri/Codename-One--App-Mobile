package com.mycompany.gui;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Blog;
import com.mycompany.services.ServiceBlog;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class UpdateBlogForm extends Form {

    private Blog blog;

    public UpdateBlogForm(Form previous, Blog blog) {
        setTitle("Update blog");
        setLayout(BoxLayout.y());
        this.blog = blog;

        TextField tfTitle = new TextField(blog.getTitle(), "Blog Title");
        TextArea taContent = new TextArea(blog.getContent(), 5, 20);
        //taContent.setHint("Blog Content");
        Button btnImage = new Button("Choose Image");
        Button btnUpdate = new Button("Update Blog");

        // When the "Choose Image" button is clicked, open a file chooser dialog
        btnImage.addActionListener(e -> {
            Display.getInstance().openGallery(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    if (ev != null && ev.getSource() != null) {
                        String filePath = (String) ev.getSource();
                        try {
                            // Scale the image to a maximum size of 400x400
                            Image img = Image.createImage(filePath).scaled(400, 400);
                            // Set the image on the button
                            btnImage.setIcon(img);
                        } catch (IOException ex) {
                            Dialog.show("Error", "Failed to load image: " + ex.getMessage(), "OK", null);
                        }
                    }
                }
            }, Display.GALLERY_IMAGE);
        });

        btnUpdate.addActionListener(e -> {
            // Check if the title and content fields are not empty
            if (tfTitle.getText().length() == 0 || taContent.getText().length() == 0) {
                Dialog.show("Error", "Please fill all the fields", "OK", null);
            } else {
                try {
                    // Update the Blog object
                    blog.setTitle(tfTitle.getText());
                    blog.setContent(taContent.getText());
                    // Set the image on the Blog object, if the button has an icon
                    if (btnImage.getIcon() != null) {
                        blog.setImage(btnImage.getIcon().getImageName());
                    }
                    // Update the Blog object using the ServiceBlog class
                    boolean success = ServiceBlog.getInstance().updateBlog(blog);
                    if (success) {
                        Dialog.show("Success", "Blog updated successfully", "OK", null);
                        // Navigate back to the previous form
                        previous.showBack();
                    } else {
                        Dialog.show("Error", "Failed to update blog", "OK", null);
                    }
                } catch (Exception ex) {
                    Dialog.show("Error", "Failed to update blog: " + ex.getMessage(), "OK", null);
                }
            }
        });

        addAll(tfTitle, taContent, btnImage, btnUpdate);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
        