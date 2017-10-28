/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administrationpanelclient;

import ejb.session.stateless.AuctionEntityControllerRemote;
import ejb.session.stateless.BidEntityControllerRemote;
import ejb.session.stateless.AuctionEntityControllerRemote;
import ejb.session.stateless.StaffEntityControllerRemote;
import entity.StaffEntity;
import java.util.InputMismatchException;
import java.util.Scanner;

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

    private void menu() {
        System.out.println("******* [Sales Staff] Homepage *******");
        System.out.println("1. Create New Auction Listing");
        System.out.println("2. Update Auction Listing");
        System.out.println("3. View Auction Listing ÏDetails");
        System.out.println("4. Delete Auction Listing");
        System.out.println("5. View All Auction Listings");
        System.out.println("6. View All Auction Listings with Bids but Below Reserve Price");
        System.out.println("7. Exit");
        System.out.println("Please input the operation that you want to perform:");
        System.out.print("> ");
    }

    protected void doMenu() {
        Scanner sc = new Scanner(System.in);
        int response = 0;

        try {
            while (true) {
                menu();
                response = sc.nextInt();
                switch (response) {
                    case 1:
                        createAuction();
                        break;
                    case 2:
                        updateAuction();
                        break;
                    case 3:
                        System.out.println("******* [Finance Staff] View Credit Package Details *******");
                        viewAuctionDetails();
                        break;
                    case 4:
                        deleteAuction();
                        break;
                    case 5:
                        viewAllAuction();
                        break;
                    case 6:
                        viewAuctionNoWinning();
                    case 7:
                        break;
                    default:
                        System.out.println("[Warning] Please input a valid response number.");
                        break;
                }
                if (response == 7) {
                    break;
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println("[Warning] Please input a valid response number.");
        }
    }

    private void createAuction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void updateAuction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void deleteAuction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void viewAllAuction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void viewAuctionDetails() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void viewAuctionNoWinning() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
