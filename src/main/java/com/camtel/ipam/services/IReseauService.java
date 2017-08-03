/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.camtel.ipam.services;

import com.camtel.ipam.data.Reseau;
import java.util.List;

/**
 *
 * @author faouzi el mansour <faouzielmansour@yahoo.com>
 */
public interface IReseauService {
    public Reseau createOrUpdateReseau(Reseau reseau);
    public List<Reseau> getAllNetwork();
    public void deleteNetwork(Long id);
    public Reseau findNetworkById(Long id);
    
    
}
