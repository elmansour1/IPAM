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
public class Vlan implements Serializable{
    
    private static final long serialVersionUID = -895213272854416387L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private int number;
    @Column(nullable = false, length = 10)
    private String name;
    @ManyToOne
    @JoinColumn(name = "batiment_id" )
//    @JsonManagedReference
    private Batiment batiment;
//    @OneToMany(mappedBy = "vlan")
//    @JsonBackReference
//    private List<Reseau> listOfReseaux;

    public Vlan() {
    }

    public Vlan(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public Vlan(int number, String name, Batiment batiment) {
        this.number = number;
        this.name = name;
        this.batiment = batiment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Batiment getBatiment() {
        return batiment;
    }

    public void setBatiment(Batiment batiment) {
        this.batiment = batiment;
    }

//    public List<Reseau> getListOfReseaux() {
//        return listOfReseaux;
//    }
//
//    public void setListOfReseaux(List<Reseau> listOfReseaux) {
//        this.listOfReseaux = listOfReseaux;
//    }

    @Override
    public String toString() {
        return "Vlan{" + "number=" + number + ", name=" + name + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + this.number;
        hash = 19 * hash + Objects.hashCode(this.name);
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
        final Vlan other = (Vlan) obj;
        if (this.number != other.number) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
}
