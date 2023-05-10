/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;



/**
 *
 * @author bhk
 */
public class Commande {
     private int id;

    
    private String status;
    private String total;

    public Commande() {
    }  

    public Commande(int id, String status, String total) {
        this.id = id;
        this.status = status;
        this.total = total;
    }

    public Commande(String status, String total) {
        this.status = status;
        this.total = total;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getTotal() {
        return total;
    }
    
    
   
}
