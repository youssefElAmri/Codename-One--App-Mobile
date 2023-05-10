/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

/**
 *
 * @author Sondes
 */
public class Evenement {
    String titreev;
    String descriptionev;
    int id;
    String typeev,dateev;

    public Evenement() {
    }

    public Evenement(String titreev, String descriptionev, int id, String typeev, String dateev) {
        this.titreev = titreev;
        this.descriptionev = descriptionev;
        this.id = id;
        this.typeev = typeev;
        this.dateev = dateev;
    }

    public Evenement(String descriptionev, int id, String dateev) {
        this.descriptionev = descriptionev;
        this.id = id;
        this.dateev = dateev;
    }

    public String getTitreev() {
        return titreev;
    }

    public void setTitreev(String titreev) {
        this.titreev = titreev;
    }

    public String getDescriptionev() {
        return descriptionev;
    }

    public void setDescriptionev(String descriptionev) {
        this.descriptionev = descriptionev;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeev() {
        return typeev;
    }

    public void setTypeev(String typeev) {
        this.typeev = typeev;
    }

    public String getDateev() {
        return dateev;
    }

    public void setDateev(String dateev) {
        this.dateev = dateev;
    }

    public Evenement(String titreev, String descriptionev, String typeev) {
        this.titreev = titreev;
        this.descriptionev = descriptionev;
        this.typeev = typeev;
    }

    public Evenement(int id,String titreev, String descriptionev, String typeev) {
        this.id = id;
        this.titreev = titreev;
        this.descriptionev = descriptionev;
        this.typeev = typeev;
    }

    
   
}
