/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.camtel.ipam.services;

import com.camtel.ipam.data.Users;
import java.util.List;

/**
 *
 * @author faouzi el mansour <faouzielmansour@yahoo.com>
 */
public interface IUsersService {
    public Users createOrUpdate(Users users);
    public List<Users>  getAllUsers();
    public void deleteUsers(String matricule);
    public Users findByMatricule(String matricule);
    
}
