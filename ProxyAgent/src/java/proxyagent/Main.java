/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxyagent;

import ejb.session.stateless.AuctionEntityControllerRemote;
import ejb.session.stateless.BidEntityControllerRemote;
import ejb.session.stateless.CustomerEntityControllerRemote;
import ejb.session.stateless.TimerSessionBeanRemote;
import javax.ejb.EJB;

/**
 *
 * @author alina
 */
public class Main {

    @EJB
    private static TimerSessionBeanRemote timerSessionBean;

    @EJB
    private static CustomerEntityControllerRemote customerEntityController;

    @EJB
    private static BidEntityControllerRemote bidEntityController;

    @EJB
    private static AuctionEntityControllerRemote auctionEntityController;
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainApp mainApp = new MainApp(customerEntityController, bidEntityController, auctionEntityController,timerSessionBean);
        mainApp.runApp();
    }

}
