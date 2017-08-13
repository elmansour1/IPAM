/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.camtel.ipam.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author faouzi el mansour <faouzielmansour@yahoo.com>
 * 
 */
@Entity
public class Reseau implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private int adress;
    
    @Column
    private String description;
    
    @Column
    private int masque;
    
    @Column
    private int gateway;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Vlan vlan;
    
    @OneToMany(mappedBy = "reseau", cascade = {CascadeType.ALL})
    @JsonBackReference
    private List<Equipement> listOfEquipements;
    
    @OneToMany(mappedBy = "reseau", cascade = CascadeType.ALL)
    private List<Sous_Reseau> listOfSubnet;
    
    @ManyToOne(optional = true)
    //@JsonBackReference
    //@JoinColumn
    private Administrateur administrateur;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAdress() {
        return adress;
    }

    public void setAdress(int adress) {
        this.adress = adress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMasque() {
        return masque;
    }

    public void setMasque(int masque) {
        this.masque = masque;
    }

    public int getGateway() {
        return gateway;
    }

    public void setGateway(int gateway) {
        this.gateway = gateway;
    }

    public Vlan getVlan() {
        return vlan;
    }

    public void setVlan(Vlan vlan) {
        this.vlan = vlan;
    }

    public List<Sous_Reseau> getListOfSubnet() {
        return listOfSubnet;
    }

    public void setListOfSubnet(List<Sous_Reseau> listOfSubnet) {
        this.listOfSubnet = listOfSubnet;
    }

    public List<Equipement> getListOfEquipements() {
        return listOfEquipements;
    }

    public void setListOfEquipements(List<Equipement> listOfEquipements) {
        this.listOfEquipements = listOfEquipements;
    }

    public Administrateur getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(Administrateur administrateur) {
        this.administrateur = administrateur;
    }

    
    
    @Override
    public String toString() {
        return "Reseau{" + "adress=" + adress + ", description=" + description + ", masque=" + masque + ", gateway=" + gateway + ", vlan=" + vlan + '}';
    }

    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.adress);
        hash = 23 * hash + Objects.hashCode(this.description);
        hash = 23 * hash + Objects.hashCode(this.masque);
        hash = 23 * hash + Objects.hashCode(this.gateway);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reseau other = (Reseau) obj;
        if (!Objects.equals(this.adress, other.adress)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.masque, other.masque)) {
            return false;
        }
        if (!Objects.equals(this.gateway, other.gateway)) {
            return false;
        }
        return true;
    }
    
    
    
}
