/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.camtel.ipam.services;

import com.camtel.ipam.data.Sous_Reseau;
import java.util.List;

/**
 *
 * @author faouzi el mansour <faouzielmansour@yahoo.com>
 */
public interface ISous_ReseauService {
    public Sous_Reseau createOrUpdate(Sous_Reseau sous_Reseau);
    public List<Sous_Reseau> getAllSubNetwork();
    public void deleteSubNetwork(Long id);
    public Sous_Reseau findById(Long id);
    
    
}
