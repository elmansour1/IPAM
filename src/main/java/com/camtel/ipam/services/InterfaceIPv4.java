/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.camtel.ipam.services;

import java.util.List;

/**
 *
 * @author faouzi el mansour <faouzielmansour@yahoo.com>
 */
public interface InterfaceIPv4 {
    public String getIP();
    public String convertNumericIPToSymbolic();
    public String getNetMask();
    public String getCIDR();
    public List<String> getAvailableIPs(int numberOfIPs);
    public String getHostAddressRange();
    public Long getNumberOfHosts();
    public String getWildcardMask();
    public String getBroadcastAddress() ;
    public String getNetmaskInBinary();
    public boolean contains(String IPaddress);
    public boolean validateIPAddress();
    
    
}
