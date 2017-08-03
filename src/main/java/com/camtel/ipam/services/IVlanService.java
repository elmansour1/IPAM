/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.camtel.ipam.services;

import com.camtel.ipam.data.Vlan;
import java.util.List;

/**
 *
 * @author faouzi el mansour <faouzielmansour@yahoo.com>
 */
public interface IVlanService {
    public Vlan createOrUpdatevlan(Vlan vlan);
    public List<Vlan> getAllVlan();
    public void deleteVlan(Long id);
    public Vlan findVlanById(Long id);
    
}
