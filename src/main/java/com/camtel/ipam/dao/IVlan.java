/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.camtel.ipam.dao;

import com.camtel.ipam.data.Vlan;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author faouzi el mansour <faouzielmansour@yahoo.com>
 */
public interface IVlan extends JpaRepository<Vlan, Long>{
    
}
