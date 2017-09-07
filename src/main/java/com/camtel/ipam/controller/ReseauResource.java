/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.camtel.ipam.controller;

import com.camtel.ipam.controller.util.HeaderUtil;
import com.camtel.ipam.data.Reseau;
import com.camtel.ipam.services.IReseauService;
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
public class ReseauResource {
    private final Logger log = LoggerFactory.getLogger(BatimentResource.class);

    @Inject
    private IReseauService reseauService;
    
      /**
     * POST  /reseaux : Create a new reseau.
     *
     * @param reseau the reseau to create
     * @return the ResponseEntity with status 201 (Created) and with body the new reseau, or with status 400 (Bad Request) if the reseau has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/networks",
        method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reseau> createReseau(@RequestBody Reseau reseau) throws URISyntaxException {
        log.debug("REST request to save reseau : {}",reseau);
        if (reseau.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("reseau", "idexists", "A new network cannot already have an ID"))
                    .body(null);
        }
        Reseau result = reseauService.createOrUpdateReseau(reseau);
        
        return ResponseEntity.created(new URI("/api/networks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("reseau", result.getId().toString()))
            .body(result);
    }
    
     /**
     * PUT  /reseaux : Updates an existing reseau.
     *
     * @param reseau the reseau to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated reseau,
     * or with status 400 (Bad Request) if the reseau is not valid,
     * or with status 500 (Internal Server Error) if the reseau couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/networks/{id}",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reseau> updateReseau(@RequestBody Reseau reseau) throws URISyntaxException {
        log.debug("REST request to update reseau : {}", reseau);
        if (reseau.getId() == null) {
            return createReseau(reseau);
        }
        Reseau result = reseauService.createOrUpdateReseau(reseau);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("reseau ", reseau.getId().toString()))
            .body(result);
    }
    
    /**
     * GET  /reseaux : get all the reseaux.
     * @return the ResponseEntity with status 200 (OK) and the list of reseaux in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/networks",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Reseau>> getAllReseaux()
        throws URISyntaxException {
        log.debug("REST request to get a page of reseaux");
        List<Reseau> list = reseauService.getAllNetwork();
        return new ResponseEntity<>(list, null, HttpStatus.OK);
    }
    
    /**
     * GET  /reseaux/:id : get the "id" reseau.
     *
     * @param id the id of the reseau to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the batiment, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/networks/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reseau> getReseau(@PathVariable Long id) {
        log.debug("REST request to get reseau : {}", id);
        Reseau result =reseauService.findNetworkById(id);
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }
    
    /**
     * DELETE  /reseaux/:id : delete the "id" reseau.
     *
     * @param id the id of the reseau to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/networks/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteReseau(@PathVariable Long id) {
        log.debug("REST request to delete network : {}", id);
        reseauService.deleteNetwork(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("reseau", id.toString())).build();
    }

}
