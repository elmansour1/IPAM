/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.camtel.ipam.services;

import com.camtel.ipam.data.Administrateur;
import java.util.List;

/**
 *
 * @author faouzi el mansour <faouzielmansour@yahoo.com>
 */
public interface IAdministrateurService {
    public Administrateur createOrUpdateAdministrateur(Administrateur administrateur);
    public Administrateur findAdministrateurById(Long id);
    public List<Administrateur> findAllAdminstrateur();
    public void deleteAdministrateur(Long id);
}
