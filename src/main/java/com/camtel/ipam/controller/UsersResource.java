/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.camtel.ipam.controller;

import com.camtel.ipam.controller.util.HeaderUtil;
import com.camtel.ipam.data.Users;
import com.camtel.ipam.services.IUsersService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author faouzi el mansour <faouzielmansour@yahoo.com>
 * 
 */
@RestController
@RequestMapping("/api")
public class UsersResource {
     private final Logger log = LoggerFactory.getLogger(BatimentResource.class);

    @Inject
    private IUsersService usersService;
    
    /**
     * POST  /users : Create a new user.
     *
     * @param user the user to create
     * @return the ResponseEntity with status 201 (Created) and with body the new user,
     * or with status 400 (Bad Request) if the user has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/user",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> createUsers(@RequestBody Users user) throws URISyntaxException {
        log.debug("REST request to save user : {}",user);
        if (user.getMatricule() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("user", "idexists", "A new user cannot already have an ID")).body(null);
        }
        Users result = usersService.createOrUpdate(user);
        
        return ResponseEntity.created(new URI("/api/users/" + result.getMatricule()))
            .headers(HeaderUtil.createEntityCreationAlert("user", result.getMatricule()))
            .body(result);
    }
    
     /**
     * PUT  /users : Updates an existing user.
     *
     * @param user the user to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated user,
     * or with status 400 (Bad Request) if the user is not valid,
     * or with status 500 (Internal Server Error) if the user couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/users/{matricule}",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> updateUsers(@RequestBody Users user) throws URISyntaxException {
        log.debug("REST request to update user : {}", user);
        if (user.getMatricule() == null) {
            return createUsers(user);
        }
        Users result = usersService.createOrUpdate(user);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("user ", user.getMatricule()))
                    .body(result);
    }
    
    /**
     * GET  /users : get all the susers.
     * @return the ResponseEntity with status 200 (OK) and the list of sous_reseaux in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = " /users ",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Users>> getAllUsers()
        throws URISyntaxException {
        log.debug("REST request to get a page of sous_reseaux");
        List<Users> list = usersService.getAllUsers();
        return new ResponseEntity<>(list, null, HttpStatus.OK);
    }
    
    /**
     * GET  /user/:id : get the "matricule" user.
     *
     * @param matricule the id of the user to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the user, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/users/{matricule}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> getUsers(@PathVariable String matricule) {
        log.debug("REST request to get user : {}", matricule);
        Users result =usersService.findByMatricule(matricule);
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }
    
    /**
     * DELETE  /users/:matricule : delete the "matricule" user.
     *
     * @param matricule the id of the reseau to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/users/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteUsrs(@PathVariable String matricule) {
        log.debug("REST request to delete user : {}", matricule);
        usersService.deleteUsers(matricule);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("user", matricule.toString())).build();
    }



}
