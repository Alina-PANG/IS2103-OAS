/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administrationpanelclient;

import ejb.session.stateless.BidEntityControllerRemote;
import ejb.session.stateless.AuctionEntityControllerRemote;
import entity.AuctionEntity;
import entity.BidEntity;
import entity.StaffEntity;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import util.enumeration.StatusEnum;
import util.exception.AuctionAlreadyExistException;
import util.exception.AuctionNotFoundException;
import util.exception.BidNotFoundException;
import util.exception.GeneralException;

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
                System.out.println("");
                menu();
                response = sc.nextInt();
                switch (response) {
                    case 1:
                        createAuction();
                        break;

                    case 2:
                        System.out.println("");
                        updateAuction();
                        break;
                    case 3:
                        System.out.println("");
                        System.out.println("******* [Sales Staff] View Auction Listing Details *******");
                        viewAuctionDetails();
                        break;
                    case 4:
                        System.out.println("");
                        deleteAuction();
                        break;
                    case 5:
                        System.out.println("");
                        viewAllAuction();
                        break;
                    case 6:
                        System.out.println("");
                        viewAuctionNoWinning();
                    case 7:
                        break;
                    default:
                        System.err.println("[Warning] Please input a valid response number.");
                        break;
                }
                if (response == 7) {
                    break;
                }
            }
        } catch (InputMismatchException ex) {
            System.err.println("[Warning] Invalide Type!");
        }
    }

    private void createAuction() {
        Scanner sc = new Scanner(System.in);
        BigDecimal reservePrice;
        String startDateStr, endDateStr, productName, productDes;
        Date startDate, endDate;
        
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

            System.out.println("******* [Sales Staff] Create New Auction Listing *******");
            do {
                System.out.print("Start Date (in format hh:mm:ss dd/mm/yyyy): ");
                startDateStr = sc.nextLine().trim();
                startDate = formatter.parse(startDateStr);
                System.out.print("End Date (in format hh:mm:ss dd/mm/yyyy): ");
                endDateStr = sc.nextLine().trim();
                endDate = formatter.parse(endDateStr);

                if (startDate.compareTo(endDate) > 0) {
                    System.err.println("[Warning] End Date cannot be later than Start Date!");
                }
            } while (startDate.compareTo(endDate) > 0);
            System.out.print("Reserve Price: ");
            reservePrice = sc.nextBigDecimal();
            sc.nextLine();
            System.out.print("Product Name: ");
            productName = sc.nextLine().trim().toLowerCase();
            System.out.println("Product Description: ");
            System.out.print("> ");
            productDes = sc.nextLine().trim();

            AuctionEntity al = auctionEntityController.createNewAuction(new AuctionEntity(startDate, endDate, StatusEnum.CLOSED, reservePrice, productName, productDes));
            System.out.println("[System] Auction Listing with id = " + al.getId() + "' has been created successfully!");
        } catch (AuctionAlreadyExistException | GeneralException ex) {
            System.err.println("[Warning] An error has occured while creating credit package: " + ex.getMessage());
        } catch (InputMismatchException ex) {
            System.err.println("[Warning] Invalid input type!");
        } catch (ParseException ex) {
            System.err.println("[Warning] Invalid Datetime type!");
        }
    }

    private void updateAuction() {
        Scanner sc = new Scanner(System.in);
        int response = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

        System.out.println("******* [Sales Staff] Update Auction Listing *******");

        try {
            AuctionEntity al = findAuction();

            viewAuctionDetails(al);
            System.out.println("6. Finish");

            while (true) {
                System.out.println("Please input an option that you want to change: ");
                response = sc.nextInt();
                sc.nextLine();

                switch (response) {
                    case 1:
                        System.out.print("Please input new starting date (hh:mm:ss dd/mm/yyyy): ");
                        al.setStartingTime(formatter.parse(sc.nextLine().trim()));
                        break;
                    case 2:
                        System.out.print("Please input new ending date (hh:mm:ss dd/mm/yyyy): ");
                        al.setEndingTime(formatter.parse(sc.nextLine().trim()));
                        break;
                    case 3:
                        System.out.println("Please input new reserve price: ");
                        al.setReservePrice(sc.nextBigDecimal());
                        break;
                    case 4:
                        System.out.println("Please input new product name: ");
                        al.setProductName(sc.nextLine().trim().toLowerCase());
                        break;
                    case 5:
                        System.out.println("Please input new product description: ");
                        al.setProductDescription(sc.nextLine().trim());
                        break;
                    case 6:
                        break;
                    default:
                        System.err.println("[Warning] Please input a valid response number.");
                        break;
                }
                if (response == 6) {
                    break;
                }
            }
            auctionEntityController.updateAuction(al);
            System.out.println("[System] Auction Listing with id = " + al.getId() + " has been updated successfully!");
        } catch (AuctionNotFoundException | GeneralException | AuctionAlreadyExistException ex) {
            System.err.println("[Warning] An error has occured while creating employee: " + ex.getMessage());
        } catch (InputMismatchException ex) {
            System.err.println("[Warning] Invalid input!");
        } catch (ParseException ex) {
            System.err.println("[Warning] Invalid Datetime type!");
        }
    }

    private void viewAuctionDetails() {
        try {
            viewAuctionDetails(findAuction());
        } catch (AuctionNotFoundException | GeneralException ex) {
            System.err.println("[Warning] An error has occured while creating employee: " + ex.getMessage());
        }

    }

    private void deleteAuction() {
        System.out.println("******* [Sales Staff] Delete Auction Listing*******");

        try {
            AuctionEntity al = findAuction();
            boolean result = auctionEntityController.deleteAuction(al.getId());
            if (result) {
                System.out.println("[System] Auction Listing with id = " + al.getId() + " has been deleted successfully!");
            } else {
                System.out.println("[System] Auction Listing with id = " + al.getId() + " is in used, thus it can only be disabled.");
            }
        } catch (AuctionNotFoundException | GeneralException ex) {
            System.err.println("[Warning] An error has occured while creating employee: " + ex.getMessage());
        }
    }

    private void viewAllAuction() {
        System.out.println("******* [Sales Staff] View All Auction Listing *******");

        try {
            List<AuctionEntity> list = auctionEntityController.viewAllAuction();
            showList(list);
        } catch (GeneralException ex) {
            System.err.println("[Warning] An error has occured while creating employee: " + ex.getMessage());
        }
    }

    private void showList(List<AuctionEntity> list) {
        //  startDate, endDate, false, reservePrice, productName, productDes, new Long(-1), null));
        System.out.printf("%5s%25s%25s%10s%15s%20s\n", "ID|", "Start Date|", "End Date|", "Status|", "Reserve Price|","Product Name");
        for (AuctionEntity al : list) {
            System.out.printf("%5s%25s%25s%10s%15s%20s\n", al.getId() + "|", al.getStartingTime() + "|", al.getEndingTime() + "|", al.getStatus() + "|", al.getReservePrice() + "|", al.getProductName());
        }
    }

    private AuctionEntity findAuction() throws AuctionNotFoundException, GeneralException {
        Scanner sc = new Scanner(System.in);
        String name;

        System.out.print("Input the auction product name: ");
        name = sc.nextLine().trim();

        List<AuctionEntity> list = auctionEntityController.retrieveAuctionByProductName(name);
        if (list.size() == 0) {
            throw new AuctionNotFoundException("No Auction Listing has name like " + name);
        } else {
            showList(list);
            System.out.println("Input the ID of the package that you want to retrieve: ");
            return auctionEntityController.retrieveAuctionById(sc.nextLong());
        }
    }

    private void viewAuctionDetails(AuctionEntity al) {
        System.out.println("******* [Auction Listing] id = " + al.getId() + " Content ******* ");
        System.out.println("[Immutable] Status: " + al.getStatus());
        System.out.print("[Immutable] Winning Bid: ");
        if(al.getWinningBid() != null)
            System.out.println(""+al.getWinningBid().getAmount());
        else 
            System.out.println("No winning bid yet.");
        System.out.println("1. Start Date: " + al.getStartingTime());
        System.out.println("2. End Date: " + al.getEndingTime());
        System.out.println("3. Reserve Price: " + al.getReservePrice());
        System.out.println("4. Product Name: " + al.getProductName());
        System.out.println("5. Product Description: " + al.getProductDescription());
    }

    private void viewAuctionNoWinning() {
        System.out.println("******* [Auction Listing] View All Auction Listings with Bids but Below Researve Price ******* ");
        Scanner sc = new Scanner(System.in);
        try{
            showList(auctionEntityController.viewNoWinningAuction());
            System.out.print("Input the id of the Auction Listing that you want to retrieve:");
            List<BidEntity> list = auctionEntityController.viewBidEntity(sc.nextLong());
            showBid(list);
            System.out.println("Input the id of the Bid tht you want to assign as the winning bid (0 for cancel): ");
            Long bid = sc.nextLong();
            if(!bid.equals(0)){
                bidEntityController.assignWinningBid(bid);
                System.out.println("[System] Assign successful!");
            }
        } catch(GeneralException ex){
            System.err.println("[Warning] No requested Auction Listing exist!");
        } catch(InputMismatchException ex){
            System.err.println("[Warning] Incompatible format!");
        } catch(AuctionNotFoundException | BidNotFoundException ex){
            System.err.println("[Warning] An error has incurred while retrieving auction: "+ex.getMessage());
        }
    }

    private void showBid(List<BidEntity> list) {
        System.out.printf("%5s%10s\n", "ID|", "Amount");
        for (BidEntity b : list) {
            System.out.printf("%5s%10s\n", b.getId() + "|", b.getAmount());
        }
    }

}
