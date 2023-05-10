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
public class Service {
    
   private int id;
    private String titreS,descriptionS,typeS;
    private String dateS;


    public String getTitreS() {
        return titreS;
    }

    public void setTitreS(String titreS) {
        this.titreS = titreS;
    }

    public String getDescriptionS() {
        return descriptionS;
    }

    public void setDescriptionS(String descriptionS) {
        this.descriptionS = descriptionS;
    }

    public String getTypeS() {
        return typeS;
    }

    public void setTypSe(String typeS) {
        this.typeS = typeS;
    }

    public String getDateS() {
        return dateS;
    }

    public void setDate(String dateS) {
        this.dateS = dateS;
    }

    public Service( String titreS, String descriptionS, String typeS, String dateS) {
        this.titreS = titreS;
        this.descriptionS = descriptionS;
        this.typeS = typeS;
        this.dateS = dateS;
    }
    
    public Service() {}
      @Override
    public String toString() {
        return "Service{ titre=" + titreS + ", description=" + descriptionS + ", type=" + typeS + ", date=" + dateS + '}';
    }

    public Service(int id, String titreS, String descriptionS, String typeS, String dateS) {
        this.id = id;
        this.titreS = titreS;
        this.descriptionS = descriptionS;
        this.typeS = typeS;
        this.dateS = dateS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Service(int id, String titreS, String descriptionS, String typeS) {
        this.id = id;
        this.titreS = titreS;
        this.descriptionS = descriptionS;
        this.typeS = typeS;
    }
    
}
