/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.camtel.ipam.services.impl;

import com.camtel.ipam.data.Users;
import com.camtel.ipam.dao.IUsers;
import com.camtel.ipam.services.IUsersService;
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
public class UsersService implements IUsersService{
    
    @Inject
    private IUsers iusers;

    @Override
    public Users createOrUpdate(Users users) {
        return iusers.save(users);
    }

    @Override
    public List<Users> getAllUsers() {
        return iusers.findAll();
    }

    @Override
    public void deleteUsers(Long id) {
        iusers.delete(id);
    }

    @Override
    public Users findById(Long id) {
        return iusers.findOne(id);
    }
}
