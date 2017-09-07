/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.camtel.ipam.controller;

import com.camtel.ipam.controller.util.HeaderUtil;
import com.camtel.ipam.data.Administrateur;
import com.camtel.ipam.services.IAdministrateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.inject.Inject;
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
public class AdministrateurResource {
    
     private final Logger log = LoggerFactory.getLogger(AdministrateurResource.class);
    
    @Inject
    private IAdministrateurService iAdministrateurService;
    
    /**
     * POST  /administrateurs : Create a new batiment.
     *
     * @param administrateur the batiment to create
     * @return the ResponseEntity with status 201 (Created) and with body the new batiment, or with status 400 (Bad Request) if the batiment has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/administrateurs",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Administrateur> createAdministrateur(@RequestBody Administrateur administrateur) throws URISyntaxException {
        log.debug("REST request to save administrateur : {}", administrateur);
        if (administrateur.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("administrateur", "idexists", "A new batiment cannot already have an ID"))
                    .body(null);
        }
        Administrateur result = iAdministrateurService.createOrUpdateAdministrateur(administrateur);
        
        return ResponseEntity.created(new URI("/api/administrateurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("administrateur", result.getId().toString()))
            .body(result);
    }
    
    /**
     * PUT  /administrateurs : Updates an existing administrateur.
     *
     * @param administrateur the author to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated batiment,
     * or with status 400 (Bad Request) if the batiment is not valid,
     * or with status 500 (Internal Server Error) if the batiment couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
   
    
    @RequestMapping(value = "/administrateurs/{id}",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Administrateur> updateAdministrateur(@RequestBody Administrateur administrateur) throws URISyntaxException {
        log.debug("REST request to update administrateur : {}", administrateur);
        if (administrateur.getId() == null) {
            return createAdministrateur(administrateur);
        }
        Administrateur result = iAdministrateurService.createOrUpdateAdministrateur(administrateur);
        
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("administrateur ", administrateur.getId().toString()))
            .body(result);
    }
    
    /**
     * GET  /administrateurs : get all the batiments.
     * @return the ResponseEntity with status 200 (OK) and the list of batiments in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/administrateurs",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Administrateur>> getAllAdministrateurs()
        throws URISyntaxException {
        log.debug("REST request to get a page of administrateurs");
        List<Administrateur> list = iAdministrateurService.findAllAdminstrateur();
        return new ResponseEntity<>(list, null, HttpStatus.OK);
    }
    
    /**
     * GET  /administrateurs/:id : get the "id" administrateur.
     *
     * @param id the id of the administrateur to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the batiment, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/administrateurs/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Administrateur> getAdministrateur(@PathVariable Long id) {
        log.debug("REST request to get administrateur : {}", id);
        Administrateur result =iAdministrateurService.findAdministrateurById(id);
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }
    
    /**
     * DELETE  /batiments/:id : delete the "id" batiment.
     *
     * @param id the id of the batiment to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/administrateurs/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteAdministrateur(@PathVariable Long id) {
        log.debug("REST request to delete administrateur : {}", id);
        iAdministrateurService.deleteAdministrateur(id);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityDeletionAlert("administrateur", id.toString()))
                .build();
    }
    
    
    
}
