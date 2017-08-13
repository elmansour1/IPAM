/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.camtel.ipam.controller;

import com.camtel.ipam.controller.util.HeaderUtil;
import com.camtel.ipam.data.Batiment;
import com.camtel.ipam.data.Equipement;
import com.camtel.ipam.services.IEquipementService;
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
 */
@RestController
@RequestMapping("/api")
public class EquipementResource {
    
     private final Logger log = LoggerFactory.getLogger(BatimentResource.class);
    
    @Inject
    private IEquipementService iEquipementService;
    
     /**
     * POST  /equipements : Create a new batiment.
     *
     * @param equipement the batiment to create
     * @return the ResponseEntity with status 201 (Created) and with body the new batiment, or with status 400 (Bad Request) if the batiment has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/equipements",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Equipement> createEquipement(@RequestBody Equipement equipement) throws URISyntaxException {
        log.debug("REST request to save equipement : {}", equipement);
        if (equipement.getId() != null) {
            return ResponseEntity.badRequest()
                    .headers(HeaderUtil.createFailureAlert("equipement", "idexists", "A new equipement cannot already have an ID"))
                    .body(null);
        }
        Equipement result = iEquipementService.createOrUpdateEquipement(equipement);
        
        return ResponseEntity.created(new URI("/api/equipements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("equipement", result.getId().toString()))
            .body(result);
    }
    
    /**
     * PUT  /equipements : Updates an existing equipement.
     *
     * @param equipement the author to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated equipement,
     * or with status 400 (Bad Request) if the batiment is not valid,
     * or with status 500 (Internal Server Error) if the equipement couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
   
    
    @RequestMapping(value = "/equipements/{id}",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Equipement> updateEquipement(@RequestBody Equipement equipement) throws URISyntaxException {
        log.debug("REST request to update equipement : {}", equipement);
        if (equipement.getId() == null) {
            return createEquipement(equipement);
        }
        Equipement result = iEquipementService.createOrUpdateEquipement(equipement);
        
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("equipement ", equipement.getId().toString()))
            .body(result);
    }
    
    /**
     * GET  /equipements : get all the equipements.
     * @return the ResponseEntity with status 200 (OK) and the list of equipements in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = " /equipements ",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Equipement>> getAllEquipements()
        throws URISyntaxException {
        log.debug("REST request to get a page of equipements");
        List<Equipement> list = iEquipementService.findAllEquipement();
        return new ResponseEntity<>(list, null, HttpStatus.OK);
    }
    
    /**
     * GET  /equipements/:id : get the "id" equipement.
     *
     * @param id the id of the batiment to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the equipement, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/equipements/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Equipement> getEquipement(@PathVariable Long id) {
        log.debug("REST request to get equipement : {}", id);
        Equipement result =iEquipementService.findEquipementById(id);
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }
    
    /**
     * DELETE  /equipements/:id : delete the "id" equipement.
     *
     * @param id the id of the batiment to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/equipements/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.ALL_VALUE)
    public ResponseEntity<Void> deleteEquipements(@PathVariable Long id) {
        log.debug("REST request to delete equipement : {}", id);
        iEquipementService.deleteEquipement(id);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityDeletionAlert("equipement", id.toString()))
                .build();
    }
    
}
