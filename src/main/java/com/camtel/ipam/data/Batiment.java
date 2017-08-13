/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.camtel.ipam.data;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 *
 * @author faouzi el mansour <faouzielmansour@yahoo.com>
 * 
 */
@Entity
public class Batiment implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column
    private String localite;
    
    @Column(nullable = false)
    private String position;
    
    @OneToMany(mappedBy = "batiment", cascade = CascadeType.ALL)
    private List<Vlan> listOfVlan;

    public Batiment(String name, String localite, String position) {
        this.name = name;
        this.localite = localite;
        this.position = position;
    }

    public Batiment() {
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    
    public void setName(String name) {
        this.name = name;
    }

    public String getLocalite() {
        return localite;
    }

    public void setLocalite(String localite) {
        this.localite = localite;
    }
    
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<Vlan> getListOfVlan() {
        return listOfVlan;
    }

    public void setListOfVlan(List<Vlan> listOfVlan) {
        this.listOfVlan = listOfVlan;
    }

    @Override
    public String toString() {
        return "Batiment{" + "name=" + name + ", localite=" + localite + ", position=" + position + ", listOfVlan=" + listOfVlan + '}';
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.localite);
        hash = 53 * hash + Objects.hashCode(this.position);
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
        final Batiment other = (Batiment) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.localite, other.localite)) {
            return false;
        }
        if (!Objects.equals(this.position, other.position)) {
            return false;
        }
        return true;
    }
    
    
}
