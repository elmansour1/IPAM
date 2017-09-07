/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.camtel.ipam.data;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
 * @author faouzi el mansour <faouzielmansour@yahoo.fr>
 */

@Entity
public class Equipement implements Serializable{
    
    private static final long serialVersionUID = 8129901851444620386L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String marque;
    @Column(nullable = false)
    private String description;
    @ManyToOne(optional = true)
    @JoinColumn(name = "users_id")
//    @JsonManagedReference
    private Users users;
    @ManyToOne(optional = true)
    @JoinColumn(name = "reseau_id")
//    @JsonManagedReference
    private Reseau reseau;

    public Equipement() {
    }

    public Equipement(String marque, String description) {
        this.marque = marque;
        this.description = description;
    }

    public Equipement(String marque, String description, Users users, Reseau reseau) {
        this.marque = marque;
        this.description = description;
        this.users = users;
        this.reseau = reseau;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Reseau getReseau() {
        return reseau;
    }

    public void setReseau(Reseau reseau) {
        this.reseau = reseau;
    }

    @Override
    public String toString() {
        return "Equipement{" + "marque=" + marque + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.marque);
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
        final Equipement other = (Equipement) obj;
        return Objects.equals(this.marque, other.marque);
    }
    
    
}
    
    
    
