/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.camtel.ipam.controller;

import com.camtel.ipam.controller.util.HeaderUtil;
import com.camtel.ipam.data.Sous_Reseau;
import com.camtel.ipam.services.ISous_ReseauService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SousReseauResource {
    private final Logger log = LoggerFactory.getLogger(BatimentResource.class);

    @Autowired
    private ISous_ReseauService sous_ReseauService;
    
     /**
     * POST  /sous_reseaux : Create a new sous_reseau.
     *
     * @param sous_reseau the sous_reseau to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sous_reseau,
     * or with status 400 (Bad Request) if the sous_reseau has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/sous_reseaux",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sous_Reseau> createSousReseau(@RequestBody Sous_Reseau sous_reseau) throws URISyntaxException {
        log.debug("REST request to save rsous_reseau : {}",sous_reseau);
        if (sous_reseau.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("sous_reseau", "idexists", "A new sous_reseau cannot already have an ID")).body(null);
        }
        Sous_Reseau result = sous_ReseauService.createOrUpdate(sous_reseau);
        
        return ResponseEntity.created(new URI("/api/sous_reseaux/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("sous_reseau", result.getId().toString()))
            .body(result);
    }
    
     /**
     * PUT  /sous_reseaux : Updates an existing sous_reseau.
     *
     * @param sous_reseau the sous_reseau to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sous_reseau,
     * or with status 400 (Bad Request) if the sous_reseau is not valid,
     * or with status 500 (Internal Server Error) if the sous_reseau couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/sous_reseaux/{id}",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sous_Reseau> updateReseau(@RequestBody Sous_Reseau sous_reseau) throws URISyntaxException {
        log.debug("REST request to update sous_reseau : {}", sous_reseau);
        if (sous_reseau.getId() == null) {
            return createSousReseau(sous_reseau);
        }
        Sous_Reseau result = sous_ReseauService.createOrUpdate(sous_reseau);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("sous_reseau ", sous_reseau.getId().toString()))
            .body(result);
    }
    
    /**
     * GET  /sous_reseaux : get all the sous_reseaux.
     * @return the ResponseEntity with status 200 (OK) and the list of sous_reseaux in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = " /sous_reseaux",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Sous_Reseau>> getAllSousReseaux()
        throws URISyntaxException {
        log.debug("REST request to get a page of sous_reseaux");
        List<Sous_Reseau> list = sous_ReseauService.getAllSubNetwork();
        return new ResponseEntity<>(list, null, HttpStatus.OK);
    }
    
    /**
     * GET  /sous_reseaux/:id : get the "id"sous_reseau.
     *
     * @param id the id of the reseau to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sous_reseau, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/sous_reseaux/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sous_Reseau> getSousReseau(@PathVariable Long id) {
        log.debug("REST request to get sous_reseau : {}", id);
        Sous_Reseau result =sous_ReseauService.findById(id);
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }
    
    /**
     * DELETE  /sous_reseaux/:id : delete the "id" sous_reseau.
     *
     * @param id the id of the reseau to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/sous_reseau/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteSousReseau(@PathVariable Long id) {
        log.debug("REST request to delete sous_reseau : {}", id);
        sous_ReseauService.deleteSubNetwork(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("sous_reseau", id.toString())).build();
    }


}
