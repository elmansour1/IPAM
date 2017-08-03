/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.camtel.ipam.services.impl;

import com.camtel.ipam.data.Vlan;
import com.camtel.ipam.dao.IVlan;
import com.camtel.ipam.services.IVlanService;
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
public class VlanService implements IVlanService{
    @Inject
    private IVlan ivlan;

    @Override
    public Vlan createOrUpdatevlan(Vlan vlan) {
        return ivlan.save(vlan);
    }

    @Override
    public List<Vlan> getAllVlan() {
        return ivlan.findAll();
    }

    @Override
    public void deleteVlan(Long id) {
        ivlan.delete(id);
    }

    @Override
    public Vlan findVlanById(Long id) {
        return ivlan.findOne(id);
    }
    
    

}
