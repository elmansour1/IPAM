///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.camtel.ipam.security;
//
//import javax.sql.DataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// *
// * @author faouzi el mansour <faouzielmansour@yahoo.com>
// */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//    
//    @Autowired
//    public void globalConfig(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception{
//        auth.inMemoryAuthentication().withUser("Admin").password("admin").roles("User", "Admin");
////       auth.jdbcAuthentication()
////                  .dataSource(dataSource)
////                  .usersByUsernameQuery("SELECT name, password  FROM administrateur WHERE name = ? AND password = ?")
////                  .passwordEncoder(new Md5PasswordEncoder());
//    }
//    
//     @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                    .antMatchers().permitAll()
//                    .anyRequest()
//                        .authenticated()
//                            .and()
//                .formLogin()
//                    .loginPage("/login")
//                    .defaultSuccessUrl("/index.html")
//                    .permitAll();
//    }
//}
