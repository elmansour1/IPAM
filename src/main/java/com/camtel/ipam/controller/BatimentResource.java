/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.camtel.ipam.controller;

import com.camtel.ipam.controller.util.HeaderUtil;
import com.camtel.ipam.data.Batiment;
import com.camtel.ipam.services.IBatimentService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
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
public class BatimentResource {

    private final Logger log = LoggerFactory.getLogger(BatimentResource.class);

    @Autowired
    private IBatimentService iBatimentService;
    
      /**
     * POST  /batiments : Create a new batiment.
     *
     * @param batiment the batiment to create
     * @return the ResponseEntity with status 201 (Created) and with body the new batiment, or with status 400 (Bad Request) if the batiment has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/batiments",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Batiment> createBatiment(@RequestBody Batiment batiment) throws URISyntaxException {
        log.debug("REST request to save batiment : {}", batiment);
        if (batiment.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("batiment", "idexists", "A new batiment cannot already have an ID")).body(null);
        }
        Batiment result = iBatimentService.createOrUpdateBatiment(batiment);
        
        return ResponseEntity.created(new URI("/api/batiments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("batiment", result.getId().toString()))
            .body(result);
    }
    
    /**
     * PUT  /batiments : Updates an existing batiment.
     *
     * @param batiment the author to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated batiment,
     * or with status 400 (Bad Request) if the batiment is not valid,
     * or with status 500 (Internal Server Error) if the batiment couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
   
    
    @RequestMapping(value = "/batiments/{id}",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Batiment> updateBatiment(@RequestBody Batiment batiment) throws URISyntaxException {
        log.debug("REST request to update batiment : {}", batiment);
        if (batiment.getId() == null) {
            return createBatiment(batiment);
        }
        Batiment result = iBatimentService.createOrUpdateBatiment(batiment);
        
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("batiment ", batiment.getId().toString()))
            .body(result);
    }
    
    /**
     * GET  /batiments : get all the batiments.
     * @return the ResponseEntity with status 200 (OK) and the list of batiments in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/batiments",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Batiment>> getAllBatiments()
        throws URISyntaxException {
        log.debug("REST request to get a page of batiments");
        List<Batiment> list = iBatimentService.getAllBatiment();
        return new ResponseEntity<>(list, null, HttpStatus.OK);
    }
    
    /**
     * GET  /batiments/:id : get the "id" batiment.
     *
     * @param id the id of the batiment to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the batiment, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/batiments/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Batiment> getBatiment(@PathVariable Long id) {
        log.debug("REST request to get batiment : {}", id);
        Batiment result =iBatimentService.findbatimentById(id);
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }
    
    /**
     * DELETE  /batiments/:id : delete the "id" batiment.
     *
     * @param id the id of the batiment to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/batiments/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.ALL_VALUE)
    public ResponseEntity<Void> deleteBatiment(@PathVariable Long id) {
        log.debug("REST request to delete batiment : {}", id);
        iBatimentService.deleteBatimemt(id);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityDeletionAlert("batiment", id.toString()))
                .build();
    }
    
    
}
