/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;



/**
 *
 * @author bhk
 */
public class Reclamation {
    private String type,nom,description,status,email;
    private String date_reclamation;
    
    public Reclamation(String type, String nom,String description,String status,String date_reclamation,String email) {
        this.type=type;
        this.nom=nom;
        this.description=description;
        this.status=status;
        this.date_reclamation=date_reclamation;
        this.email=email;
        
    }

    public Reclamation() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom){
        this.nom=nom; 
    }
      public String getEmail() {
        return email;
    }
    public void setEmail(String email){
        this.email=email; 
    }
    
    public String getDescription() {
        return description;
    }
    public void setDescrition(String description){
        this.description=description; 
    }
    
    public String getStatus() {
        return status;
    }
    public void setStatus(String status){
        this.status=status; 
    }
    
    
    public String getDatereclamation() {
        return date_reclamation;
    }
    public void setDatereclamation(String date_reclamation){
        this.date_reclamation=date_reclamation; 
    }
    

   

    @Override
    public String toString() {
        return "Task{" + "type=" + type + ", nom=" + nom + ", description=" + description + ", status=" + status+", date reclamation=" + date_reclamation+ ", email="+email+"\n";
    }
    
    
}
