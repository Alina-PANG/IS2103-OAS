/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auctionclient;

import ejb.session.stateless.CustomerEntityControllerRemote;
import entity.CustomerEntity;

/**
 *
 * @author Amber
 */
public class AuctionModule {
    
    private CustomerEntity customer;
    private CustomerEntityControllerRemote customerentitycontrollerremote; 

    public AuctionModule() {
    }

    public AuctionModule(CustomerEntity customer) {
        this.customer = customer;
        
    }
    
    public void viewAuctionListing()
    {
        
    }
    
    public void viewBid()
    {
        
    }
    public void viewWonAuction()
    {
        
    }
          
    
    
    
}
