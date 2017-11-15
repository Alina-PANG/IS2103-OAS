/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auctionclient;

import ejb.session.stateless.AddressEntityControllerRemote;
import ejb.session.stateless.AuctionEntityControllerRemote;
import ejb.session.stateless.BidEntityControllerRemote;
import ejb.session.stateless.CreditPackageEntityControllerRemote;
import ejb.session.stateless.CreditTransactionEntityControllerRemote;
import ejb.session.stateless.CustomerEntityControllerRemote;
import ejb.session.stateless.TimerSessionBeanRemote;
import javax.ejb.EJB;

/**
 *
 * @author alina
 */
public class Main {

    @EJB
    private static CreditTransactionEntityControllerRemote creditTransactionEntityController;

    @EJB
    private static CreditPackageEntityControllerRemote creditPackageEntityController;

    @EJB
    private static BidEntityControllerRemote bidEntityController;

    @EJB
    private static AuctionEntityControllerRemote auctionEntityController;

    @EJB
    private static AddressEntityControllerRemote addressEntityController;

    @EJB
    private static CustomerEntityControllerRemote customerEntityController;

    @EJB
    private static TimerSessionBeanRemote timerSessionBean;
    
    
    

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.err.println("************* 01");
        
        MainApp mainApp = new MainApp(customerEntityController,creditPackageEntityController,bidEntityController,auctionEntityController,creditTransactionEntityController,addressEntityController,timerSessionBean);
        mainApp.runApp();
    }
    
}
