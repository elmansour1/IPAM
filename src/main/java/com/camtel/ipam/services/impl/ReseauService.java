/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.camtel.ipam.services.impl;

import com.camtel.ipam.data.Reseau;
import com.camtel.ipam.dao.IReseau;
import com.camtel.ipam.services.IReseauService;
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
public class ReseauService implements IReseauService{
    @Inject
    private IReseau iReseau;

    @Override
    public Reseau createOrUpdateReseau(Reseau reseau) {
        return iReseau.save(reseau);
    }

    @Override
    public List<Reseau> getAllNetwork() {
        return iReseau.findAll();
    }

    @Override
    public void deleteNetwork(Long id) {
        iReseau.delete(id);
    }

    @Override
    public Reseau findNetworkById(Long id) {
        return iReseau.findOne(id);
    }
    
    

}
