/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import java.util.Date;
/**
 *
 * @author mfmma
 */
public class Blog {
    
   private int id;
    private String title,content;
    private int likes;

    public Blog() {
    }

    public Blog(int id, String title, String content, int likes) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.likes = likes;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getLikes() {
        return likes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Blog{" + "id=" + id + ", title=" + title + ", content=" + content + ", likes=" + likes + '}';
    }

    public Blog(String title, String content) {
        this.title = title;
        this.content = content;
    }


    
}
