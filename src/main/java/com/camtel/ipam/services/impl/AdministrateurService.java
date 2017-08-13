/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.camtel.ipam.services.impl;

import com.camtel.ipam.dao.IAdministrateur;
import com.camtel.ipam.data.Administrateur;
import com.camtel.ipam.services.IAdministrateurService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author faouzi el mansour <faouzielmansour@yahoo.com>
 */
@Service
@Transactional
public class AdministrateurService implements IAdministrateurService{
    @Inject
    private IAdministrateur iAdministrateur;

    @Override
    public Administrateur createOrUpdateAdministrateur(Administrateur administrateur) {
        return iAdministrateur.save(administrateur);
    }

    @Override
    public Administrateur findAdministrateurById(Long id) {
        return iAdministrateur.findOne(id);
    }

    @Override
    public List<Administrateur> findAllAdminstrateur() {
        return iAdministrateur.findAll();
    }

    @Override
    public void deleteAdministrateur(Long id) {
        iAdministrateur.delete(id);
    }
    
    
    
}
