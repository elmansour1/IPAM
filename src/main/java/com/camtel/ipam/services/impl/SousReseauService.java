/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.camtel.ipam.services.impl;

import com.camtel.ipam.data.Sous_Reseau;
import com.camtel.ipam.dao.ISous_Reseau;
import com.camtel.ipam.services.ISous_ReseauService;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author faouzi el mansour <faouzielmansour@yahoo.com>
 * 
 */
@Service
@Transactional
public class SousReseauService implements ISous_ReseauService{
    
    @Inject
    private ISous_Reseau iSous_Reseau;

    @Override
    public Sous_Reseau createOrUpdate(Sous_Reseau sous_Reseau) {
        return iSous_Reseau.save(sous_Reseau);
    }

    @Override
    public List<Sous_Reseau> getAllSubNetwork() {
        return iSous_Reseau.findAll();
    }

    @Override
    public void deleteSubNetwork(Long id) {
        iSous_Reseau.delete(id);
    }

    @Override
    public Sous_Reseau findById(Long id) {
        return iSous_Reseau.findOne(id);
    }
    
    

}
