/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.camtel.ipam;

import com.camtel.ipam.data.Batiment;
import com.camtel.ipam.dao.IBatiment;
import com.camtel.ipam.dao.IReseau;
import com.camtel.ipam.dao.IVlan;
import com.camtel.ipam.data.Reseau;
import com.camtel.ipam.data.Vlan;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author faouzi el mansour <faouzielmansour@yahoo.com>
 * 
 */
@SpringBootApplication
public class App {
     private static final Logger log = LoggerFactory.getLogger(App.class);
     
     @Autowired
     private IBatiment ibat;
     @Autowired
       private IReseau iReseau; 
     @Autowired
     private IVlan iVlan;
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        
       
        
    }
    
    
//    public void run(){
//        Vlan vlan10 = iVlan.save(new Vlan(10,"vlan10"));
//        Reseau res = iReseau.save(new Reseau("192.168.1.2","reseau batiment technique","255.255.255.0","192.168.1.1",vlan10));
//    }
//
//    @Override
//    public void run(String... strings) throws Exception {
//               Vlan vlan10 = iVlan.save(new Vlan(10,"vlan10"));
//        Reseau res = iReseau.save(new Reseau("192.168.1.2","reseau batiment technique","255.255.255.0","192.168.1.1",vlan10));
//   
//    }
    
}
