/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.camtel.ipam.data;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author faouzi el mansour <faouzielmansour@yahoo.com>
 * 
 */
@Entity
public class Sous_Reseau implements Serializable{
    
    private static final long serialVersionUID = -1165374957417679501L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String adress;
    @Column(nullable = false)
    private String description;
    @ManyToOne(optional = true)
    @JoinColumn(name = "reseau_id", nullable = false)
    private Reseau reseau;

    public Sous_Reseau() {
    }

    public Sous_Reseau(String adress, String description) {
        this.adress = adress;
        this.description = description;
    }

    public Sous_Reseau(String adress, String description, Reseau reseau) {
        this.adress = adress;
        this.description = description;
        this.reseau = reseau;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Reseau getReseau() {
        return reseau;
    }

    public void setReseau(Reseau reseau) {
        this.reseau = reseau;
    }

    @Override
    public String toString() {
        return "Sous_Reseau{" + "adress=" + adress + ", description=" + description + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.adress);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sous_Reseau other = (Sous_Reseau) obj;
        if (!Objects.equals(this.adress, other.adress)) {
            return false;
        }
        return true;
    }
    
    
}
