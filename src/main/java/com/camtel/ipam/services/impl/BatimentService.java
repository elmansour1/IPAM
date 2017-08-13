/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.camtel.ipam.services.impl;

import com.camtel.ipam.data.Batiment;
import com.camtel.ipam.dao.IBatiment;
import com.camtel.ipam.services.IBatimentService;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 *
 * @author faouzi el mansour <faouzielmansour@yahoo.com>
 * 
 */
@Service
@Transactional
public class BatimentService implements IBatimentService{
    @Inject
    private IBatiment iBatiment;
    
    @Override
    public Batiment createOrUpdateBatiment(Batiment batiment) {
        return iBatiment.save(batiment);
    }

    @Override
    public List<Batiment> getAllBatiment() {
        return iBatiment.findAll();
    }

    @Override
    public void deleteBatimemt(Long id) {
        iBatiment.delete(id);
    }

    @Override
    public Batiment findByNameAndPosition(String name, String position) {
        return iBatiment.findByNameAndPosition(name, position);
    }

    @Override
    public Batiment findbatimentById(Long id) {
        return iBatiment.findOne(id);
    }

    @Override
    public Page<Batiment> listeBatitement(Long idBatimnet, int page, int zise) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Batiment findBatimentByLocalite(String localite) {
        return iBatiment.findBatimentByLocalite(localite);
    }

}
