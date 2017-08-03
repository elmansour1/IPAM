/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.camtel.ipam.controller;

import com.camtel.ipam.controller.util.HeaderUtil;
import com.camtel.ipam.data.Users;
import com.camtel.ipam.data.Vlan;
import com.camtel.ipam.services.IVlanService;
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
public class VlanResource {
    private final Logger log = LoggerFactory.getLogger(BatimentResource.class);

    @Inject
    private IVlanService vlanService;
    
    /**
     * POST  /vlans : Create a new vlan.
     *
     * @param vlan the user to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vlan,
     * or with status 400 (Bad Request) if the vlan has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/vlans",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE) 
            public ResponseEntity<Vlan> createVlan(@RequestBody Vlan vlan) throws URISyntaxException {
        log.debug("REST request to save vlan : {}",vlan);
        if (vlan.getNumber() != 0) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("vlan", "idexists", "A new vlan cannot already have an ID")).body(null);
        }
        Vlan result = vlanService.createOrUpdatevlan(vlan);
        
        return ResponseEntity.created(new URI("/api/vlan/" + result.getNumber()))
            .headers(HeaderUtil.createEntityCreationAlert("vlan", result.getName()))
            .body(result);
    }
    
     /**
     * PUT  /vlans : Updates an existing vlan.
     *
     * @param vlan the vlan to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vlan,
     * or with status 400 (Bad Request) if the vlan is not valid,
     * or with status 500 (Internal Server Error) if the vlan couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/vlans/{id}",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vlan> updateVlan(@RequestBody Vlan vlan) throws URISyntaxException {
        log.debug("REST request to update user : {}", vlan);
        if (vlan.getId() == null) {
            return createVlan(vlan);
        }
        Vlan result = vlanService.createOrUpdatevlan(vlan);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("vlan ", vlan.getId().toString()))
                    .body(result);
    }
    
    /**
     * GET  /vlans : get all the vlans.
     * @return the ResponseEntity with status 200 (OK) and the list of vlan in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = " /vlans ",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Vlan>> getAllVlans()
        throws URISyntaxException {
        log.debug("REST request to get a page of vlan");
        List<Vlan> list = vlanService.getAllVlan();
        return new ResponseEntity<>(list, null, HttpStatus.OK);
    }
    
    /**
     * GET  /vlan/:id : get the "id" vlan.
     *
     * @param id the id of the vlan to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vlan, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/vlans/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vlan> getVlans(@PathVariable Long id) {
        log.debug("REST request to get vlan : {}", id);
        Vlan result =vlanService.findVlanById(id);
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }
    
    /**
     * DELETE  /vlans/:id : delete the "id" vlan.
     *
     * @param id the id of the vlan to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/vlans/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteVlan(@PathVariable Long id) {
        log.debug("REST request to delete vlan : {}", id);
        vlanService.deleteVlan(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("vlan", id.toString())).build();
    }




}
