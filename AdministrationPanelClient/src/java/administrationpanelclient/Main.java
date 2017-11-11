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
import ejb.session.stateless.TimerSessionBeanRemote;
import javax.ejb.EJB;

/**
 *
 * @author alina
 */
public class Main {

    @EJB
    private static StaffEntityControllerRemote staffEntityController;

    @EJB
    private static CreditPackageEntityControllerRemote creditPackageEntityController;

    @EJB
    private static BidEntityControllerRemote bidEntityController;

    @EJB
    private static AuctionEntityControllerRemote auctionEntityController;

    @EJB
    private static TimerSessionBeanRemote timerSessionBean;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainApp mainApp = new MainApp(timerSessionBean, staffEntityController, creditPackageEntityController, bidEntityController, auctionEntityController);
        mainApp.runApp();
    }

}
