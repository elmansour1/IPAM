/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.camtel.ipam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 *
 * @author faouzi el mansour <faouzielmansour@yahoo.com>
 * 
 */
@SpringBootApplication
public class App {
//     private static final Logger log = LoggerFactory.getLogger(App.class);
//     
//     @Autowired
//     private IBatiment ibat;
//     @Autowired
//       private IReseau iReseau; 
//     @Autowired
//     private IVlan iVlan;
//    @Configuration
//    static class SecurityConfig extends GlobalAuthenticationConfigurerAdapter{
//        
//        public void init(AuthenticationManagerBuilder auth) throws Exception{
//            auth.inMemoryAuthentication()
//                    .withUser("user").password("user").roles("USER").and()
//                    .withUser("admin").password("admin").roles("ADMIN","USER");
//        }
//    }
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
