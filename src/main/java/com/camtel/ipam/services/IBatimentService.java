/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.camtel.ipam.services;

import com.camtel.ipam.data.Batiment;
import java.util.List;
import org.springframework.data.domain.Page;

/**
 *
 * @author faouzi el mansour <faouzielmansour@yahoo.com>
 */
public interface IBatimentService {
    public Batiment createOrUpdateBatiment(Batiment batiment);
    public List<Batiment> getAllBatiment();
    public void deleteBatimemt(Long id);
    public Batiment findByNameAndPosition(String name, String position);
    public Batiment findbatimentById(Long id);
    public Batiment findBatimentByLocalite(String localite);
    public Page<Batiment> listeBatitement(Long idBatimnet, int page, int zise );
    
}
