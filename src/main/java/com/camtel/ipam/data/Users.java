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
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author faouzi el mansour <faouzielmansour@yahoo.com>
 * 
 */
@Entity
public class Users implements Serializable{
    @Id
    private String matricule;
    
    @Column(nullable = false)
    private String firstName;
    
    @Column(nullable = false)
    private String lastName;
    
    @Column(nullable = false)
    private Sexe sexe;
    
    @Column(nullable = false)
    private String poste;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Vlan> listOfVlan;

    public Users(String matricule, String firstName, String lastName, Sexe sexe, String poste) {
        this.matricule = matricule;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sexe = sexe;
        this.poste = poste;
    }

    public Users() {
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public void setListOfVlan(List<Vlan> listOfVlan) {
        this.listOfVlan = listOfVlan;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public String getPoste() {
        return poste;
    }

    public List<Vlan> getListOfVlan() {
        return listOfVlan;
    }
    
    

    @Override
    public String toString() {
        return "User{" + "matricule=" + matricule + ", firstName=" + firstName + ", lastName=" + lastName + ", sexe=" + sexe + ", poste=" + poste + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.matricule);
        hash = 79 * hash + Objects.hashCode(this.firstName);
        hash = 79 * hash + Objects.hashCode(this.lastName);
        hash = 79 * hash + Objects.hashCode(this.sexe);
        hash = 79 * hash + Objects.hashCode(this.poste);
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
        final Users other = (Users) obj;
        if (!Objects.equals(this.matricule, other.matricule)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.poste, other.poste)) {
            return false;
        }
        if (this.sexe != other.sexe) {
            return false;
        }
        return true;
    }
    
    

}
