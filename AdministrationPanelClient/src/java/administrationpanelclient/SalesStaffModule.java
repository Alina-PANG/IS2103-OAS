/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administrationpanelclient;

import ejb.session.stateless.AuctionEntityControllerRemote;
import ejb.session.stateless.BidEntityControllerRemote;
import ejb.session.stateless.CreditPackageEntityControllerRemote;
import ejb.session.stateless.StaffEntityControllerRemote;
import entity.StaffEntity;

/**
 *
 * @author alina
 */
public class SalesStaffModule {
    // Define entity controllers
    private BidEntityControllerRemote bidEntityController;
    private AuctionEntityControllerRemote auctionEntityController;

    // Define entities
    private StaffEntity currentStaffEntity;

    public SalesStaffModule() {
    }

    public SalesStaffModule(BidEntityControllerRemote bidEntityController, AuctionEntityControllerRemote auctionEntityController, StaffEntity currentStaffEntity) {
        this.bidEntityController = bidEntityController;
        this.auctionEntityController = auctionEntityController;
        this.currentStaffEntity = currentStaffEntity;
    }

    void menu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
