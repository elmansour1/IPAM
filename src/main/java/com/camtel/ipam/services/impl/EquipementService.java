/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.camtel.ipam.services.impl;

import com.camtel.ipam.dao.IEquipement;
import com.camtel.ipam.data.Equipement;
import com.camtel.ipam.services.IEquipementService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author  faouzi el mansour <faouzielmansour@yahoo.com>
 */
@Service
@Transactional
public class EquipementService implements IEquipementService{
    @Inject
    private IEquipement iEquipement;

    @Override
    public Equipement createOrUpdateEquipement(Equipement equipement) {
        return iEquipement.save(equipement);
    }

    @Override
    public Equipement findEquipementById(Long id) {
        return iEquipement.findOne(id);
    }

    @Override
    public List<Equipement> findAllEquipement() {
        return iEquipement.findAll();
    }

    @Override
    public void deleteEquipement(Long id) {
        iEquipement.delete(id);
    }
    
    
}
