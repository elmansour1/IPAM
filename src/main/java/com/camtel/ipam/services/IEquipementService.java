/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.camtel.ipam.services;

import com.camtel.ipam.data.Equipement;
import java.util.List;

/**
 *
 * @author faouzi el mansour <faouzielmansour@yahoo.com>
 */
public interface IEquipementService {
    public Equipement createOrUpdateEquipement(Equipement equipement);
    public Equipement findEquipementById(Long id);
    public List<Equipement> findAllEquipement();
    public void deleteEquipement(Long id);
    
}
