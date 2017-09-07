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
public class Reseau implements Serializable{
    
    private static final long serialVersionUID = -6187989417804681804L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String adress;
    @Column(nullable = false)
    private String description;
    @Column
    private String masque;
    @Column(nullable = false)
    private String gateway; 
    
    @Column
    private String broadcast;
    @Column
    private String plage;
    @Column
    private String Classe;
    @Column
    private String nid;
//    @OneToMany(mappedBy = "reseau")
//    @JsonBackReference
//    private List<Equipement> listOfEquipements;
//    @OneToMany(mappedBy = "reseau")
//    private List<Sous_Reseau> listOfSubnet;
    @ManyToOne(optional = true)
    @JoinColumn(name = "administrateur_id")
    private Administrateur administrateur;
    @ManyToOne(optional = true)
//    @JsonManagedReference
    @JoinColumn(name = "vlan_id")
    private Vlan vlan;

    public Reseau() {
    }

    public Reseau(String adress, String description, String masque, String gateway, String broadcast, String plage, String Classe, String nid) {
        this.adress = adress;
        this.description = description;
        this.masque = masque;
        this.gateway = gateway;
        this.broadcast = broadcast;
        this.plage = plage;
        this.Classe = Classe;
        this.nid = nid;
    }

    public Reseau(String adress, String description, String masque, String gateway, Administrateur administrateur, Vlan vlan) {
        this.adress = adress;
        this.description = description;
        this.masque = masque;
        this.gateway = gateway;
        this.administrateur = administrateur;
        this.vlan = vlan;
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

    public String getMasque() {
        return masque;
    }

    public void setMasque(String masque) {
        this.masque = masque;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getBroadcast() {
        return broadcast;
    }

    public void setBroadcast(String broadcast) {
        this.broadcast = broadcast;
    }

    public String getPlage() {
        return plage;
    }

    public void setPlage(String plage) {
        this.plage = plage;
    }

    public String getClasse() {
        return Classe;
    }

    public void setClasse(String Classe) {
        this.Classe = Classe;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

//    public List<Equipement> getListOfEquipements() {
//        return listOfEquipements;
//    }
//
//    public void setListOfEquipements(List<Equipement> listOfEquipements) {
//        this.listOfEquipements = listOfEquipements;
//    }
//
//    public List<Sous_Reseau> getListOfSubnet() {
//        return listOfSubnet;
//    }
//
//    public void setListOfSubnet(List<Sous_Reseau> listOfSubnet) {
//        this.listOfSubnet = listOfSubnet;
//    }

    public Administrateur getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(Administrateur administrateur) {
        this.administrateur = administrateur;
    }

    public Vlan getVlan() {
        return vlan;
    }

    public void setVlan(Vlan vlan) {
        this.vlan = vlan;
    }

    @Override
    public String toString() {
        return "Reseau{" + "adress=" + adress + ", masque=" + masque + ", gateway=" + gateway + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.adress);
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
        final Reseau other = (Reseau) obj;
        if (!Objects.equals(this.adress, other.adress)) {
            return false;
        }
        return true;
    }
    
    
   
}
