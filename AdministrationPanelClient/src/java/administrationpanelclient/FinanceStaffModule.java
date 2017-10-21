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
public class FinanceStaffModule {
    // Define entity controllers
    private CreditPackageEntityControllerRemote creditPackageEntityController;

    // Define entities
    private StaffEntity currentStaffEntity;

    public FinanceStaffModule() {
    }

    public FinanceStaffModule(CreditPackageEntityControllerRemote creditPackageEntityController, StaffEntity currentStaffEntity) {
        this.creditPackageEntityController = creditPackageEntityController;
        this.currentStaffEntity = currentStaffEntity;
    }

    void menu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
