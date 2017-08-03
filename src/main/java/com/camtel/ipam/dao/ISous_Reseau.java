/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.camtel.ipam.dao;

import com.camtel.ipam.data.Sous_Reseau;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author faouzi el mansour <faouzielmansour@yahoo.com>
 */
public interface ISous_Reseau extends JpaRepository<Sous_Reseau, Long>{
    
}
