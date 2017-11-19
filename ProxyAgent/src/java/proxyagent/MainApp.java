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
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import util.exception.AuctionNotFoundException;
import ws.client.AuctionClosedException_Exception;
import ws.client.AuctionEntity;
import ws.client.AuctionNotFoundException_Exception;
import ws.client.AuctionNotOpenException_Exception;
import ws.client.BidAlreadyExistException_Exception;
import ws.client.BidEntity;
import ws.client.BidLessThanIncrementException_Exception;
import ws.client.BidNotFoundException_Exception;
import ws.client.CustomerAlreadyPremiumException_Exception;
import ws.client.CustomerEntity;
import ws.client.CustomerNotFoundException_Exception;
import ws.client.CustomerNotPremiumException_Exception;
import ws.client.GeneralException_Exception;
import ws.client.IncorrectPasswordException_Exception;
import ws.client.NotEnoughCreditException_Exception;
import ws.client.ProxyBiddingEntity;
import ws.client.StatusEnum;

/**
 *
 * @author alina
 */
public class MainApp {

    private CustomerEntityControllerRemote customerEntityController;
    private BidEntityControllerRemote bidEntityController;
    private AuctionEntityControllerRemote auctionEntityController;
    private TimerSessionBeanRemote timerSessionBean;

    private CustomerEntity currentCustomerEntity;

    public MainApp() {
    }

    public MainApp(CustomerEntityControllerRemote customerEntityController, BidEntityControllerRemote bidEntityController, AuctionEntityControllerRemote auctionEntityController, TimerSessionBeanRemote timerSessionBean) {
        this.customerEntityController = customerEntityController;
        this.bidEntityController = bidEntityController;
        this.auctionEntityController = auctionEntityController;
        this.timerSessionBean = timerSessionBean;
    }

    public void runApp() {
        Scanner sc = new Scanner(System.in);
        int response;

        while (true) {
            System.out.println("");
            menu1();
            response = 0;
            try {
                response = sc.nextInt();
                switch (response) {
                    case 1:
                        readUsernameAndPassword(1);

                        break;
                    case 2:
                        readUsernameAndPassword(2);

                        break;
                    case 3:
                        break;
                    default:
                        System.err.println("[Warning] Invalid input! Please try again!");
                        break;
                }
                if (response == 3) {
                    break;
                }
            } catch (InputMismatchException ex) {
                System.err.println("[Warning] Invalid Type!");
                sc.nextLine();
            }
        }
    }

    private void menu2() {
        System.out.println("");
        System.out.println("******* [OAS System Web Service] Premium Customer Operations ******");
        System.out.println("1. Browse all auction listings");
        System.out.println("2. View won auction listings");
        System.out.println("3. View Auction Listing Details");
        System.out.println("4. View Credit Balance");
        System.out.println("5. Configure Proxy Bidding for Auction Listing");
        System.out.println("6. Configure Sniping for Auction Listing");
        System.out.println("7. Logout");
        System.out.print("Please enter number of the operation that you want to perform\n->");
    }

    private void postLogin() {
        Scanner sc = new Scanner(System.in);
        int response = 0;

        while (true) {
            menu2();
            try {
                response = sc.nextInt();
                switch (response) {
                    case 1:
                        System.out.println("");
                        System.out.println("******* [Premium Customer] View All Auction Listing *******");
                        viewAllAuction();
                        break;
                    case 2:
                        viewWonAuction();
                        break;
                    case 3:
                        viewAuctionListingDetail();
                        break;
                    case 4:
                        viewCreditBalance();
                        break;
                    case 5:
                        configureProxyBidding();
                        break;
                    case 6:
                        configureSnippingBidding();
                        break;
                    case 7:
                        break;
                    default:
                        System.err.println("[Warning] Invalid Option! Please try again!");
                        break;
                }
            } catch (InputMismatchException ex) {
                System.err.println("[Warning] Invalid Type!");
            }
            if (response == 7) {
                break;
            }
        }

    }

    private void configureProxyBidding() {
        Scanner sc = new Scanner(System.in);
        BigDecimal maxPrice;
        Long aid;

        System.out.println("");
        System.out.println("******* [Premium Customer] Configure Proxy Bidding for Auction Listing *******");
        viewAllAuction();
        System.out.print("Enter auction id that you want to put proxy bid\n->");
        aid = sc.nextLong();
        System.out.print("Enter the max price that you want to pay\n->");
        maxPrice = sc.nextBigDecimal();

        ProxyBiddingEntity bid = new ProxyBiddingEntity();
        bid.setMaxAmount(maxPrice);
        bid.setAmount(new BigDecimal(-77));
        try {
            createProxyBid(bid, aid, currentCustomerEntity.getId());
            System.out.println("[System] Proxy bid has been created successfully!");
        } catch (NotEnoughCreditException_Exception | AuctionNotOpenException_Exception | GeneralException_Exception | BidAlreadyExistException_Exception | AuctionNotFoundException_Exception | BidLessThanIncrementException_Exception | CustomerNotFoundException_Exception | AuctionClosedException_Exception ex) {
            System.err.println("[Warning] An error has occured: " + ex.getMessage());
        }
    }

    private void configureSnippingBidding() {
        Scanner sc = new Scanner(System.in);
        BigDecimal maxPrice;
        Long aid;
        int duration;
        AuctionEntity a;

        System.out.println("******* [Premium Customer] Configure Sniping for Auction Listing *******");
        System.out.print("Enter the auction id that you want to put for snipping bid\n-> ");
        aid = sc.nextLong();
        System.out.print("Enter the max price that you want to pay\n->");
        maxPrice = sc.nextBigDecimal();

        System.out.print("Please input the time duration before the listing expired to place your bid (in minutes): \n> ");
        duration = sc.nextInt();

        try {
            a = viewAuctionListDetails(aid);
            Date endTime = a.getEndingTime().toGregorianCalendar().getTime();
            Date startTime = a.getStartingTime().toGregorianCalendar().getTime();

            if (endTime.before(new Date())) {
                System.err.println("The auction has already been closed, no more bid is allowed!");
            } else if (startTime.after(new Date())) {
                System.err.println("The auction has not been opende yet, please wait patiently!");
            } else {
                // calculate and put snipping bid
                Timer timer = new Timer("snipping bid with cid=" + currentCustomerEntity.getId());
                GregorianCalendar cal = a.getEndingTime().toGregorianCalendar();
                duration = 0 - duration;
                cal.add(Calendar.MINUTE, duration);
                if (cal.getTime().before(startTime)) {
                    System.err.println("Your Snipping Bid time cannot be before the auction starting time!");
                } else if (cal.getTime().after(endTime)) {
                    System.err.println("Your Snipping Bid time cannot be after the auction ending time!");
                } else {
                    timer.schedule(new TimerTask() {
                        public void run() {
                            try {
                                BidEntity bid = new BidEntity();
                                bid.setAmount(maxPrice);
                                createSnippingBid(bid, aid, currentCustomerEntity.getId());
                            } catch (BidLessThanIncrementException_Exception | CustomerNotFoundException_Exception | AuctionNotFoundException_Exception | AuctionClosedException_Exception | BidAlreadyExistException_Exception | AuctionNotOpenException_Exception | NotEnoughCreditException_Exception | GeneralException_Exception ex) {

                            }
                        }
                    }, cal.getTime());
                }
            }
            System.out.println("Your snipping bid has been put successfully!");
        } catch (AuctionNotFoundException_Exception ex) {
            System.err.println("An error has occured: " + ex.getMessage());//added
        }

        /*try {
            SnippingBidEntity bid = new SnippingBidEntity();
            bid.setAmount(new BigDecimal(-101));
            createSnippingBid(bid, maxPrice, timeDuration, aid, currentCustomerEntity.getId());
            System.out.println("[System] Proxy bid has been created successfully!");

        } catch (AuctionNotOpenException_Exception | BidLessThanIncrementException_Exception | NotEnoughCreditException_Exception | AuctionClosedException_Exception | CustomerNotFoundException_Exception | AuctionNotFoundException_Exception | BidAlreadyExistException_Exception | GeneralException_Exception ex) {
<<<<<<< HEAD
            System.err.println("[Warning] and error has occured: " + ex.getMessage());
        }*/
    }

    private void viewCreditBalance() {
        System.out.println("");
        System.out.println("******* [Premium Customer] View Credit Balance *******");
        try {
            BigDecimal value = viewCreditBalance(currentCustomerEntity.getId());
            System.out.println("Your Credit Balance is " + value);
        } catch (GeneralException_Exception | CustomerNotFoundException_Exception ex) {
            System.err.println("[Warning] An error has occured: " + ex.getMessage());
        }
    }

    private void viewAuctionListingDetail() {
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.println("******* [Premium Customer] View Auction Listing Detail *******");
        System.out.print("Enter the relevant auction / product name for searching\n->");

        String input = sc.nextLine().trim();

        try {
            List<ws.client.AuctionEntity> list = viewAuctionListByName(input);//???
            showList(list);
            System.out.print("Enter id of the Auction List\n->");
            ws.client.AuctionEntity al = viewAuctionListDetails(sc.nextLong());//???
            System.out.println("");
            System.out.println("******* [Auction Listing] id = " + al.getId() + " Content ******* ");
            System.out.println("0. Status: " + al.getStatus());
            System.out.println("1. Start Date: " + al.getStartingTime());
            System.out.println("2. End Date: " + al.getEndingTime());
            System.out.println("3. Reserve Price: " + al.getReservePrice());
            System.out.println("4. Product Name: " + al.getProductName());
            System.out.println("5. Product Description: " + al.getProductDescription());
            if (al.getStatus() == StatusEnum.ACTIVE) {
                try {
                    BigDecimal amount = viewCurrentHighestBid(al.getId());
                    System.out.print("6. Current Highest Bid Amount: ");
                    System.out.println("" + amount);
                } catch (AuctionNotFoundException_Exception ex) {
                }
            } else if (al.getStatus() == StatusEnum.CLOSED) {
                System.out.print("6. Winning Bid Amount: ");
                try {
                    BidEntity b = viewWinningBid(al.getId());
                    System.out.println("" + b.getAmount());
                } catch (BidNotFoundException_Exception | AuctionNotFoundException_Exception | GeneralException_Exception ex) {
                    System.err.println("An error has occured: " + ex.getMessage());
                }
            }

            if (al.getStatus() != StatusEnum.PENDING && al.getStatus() != StatusEnum.CLOSED) {
                System.out.print("7. Your Bid Amount: ");
                try {
                    BidEntity b = viewMyBidInAuction(currentCustomerEntity.getId(), al.getId());
                    System.out.println("" + b.getAmount());
                    System.out.print("Your Bid Type: ");
                    if (b instanceof ProxyBiddingEntity) {
                        System.out.println("Proxy Bid");
                    } else {
                        System.out.println("Normal Bid");
                    }
                } catch (BidNotFoundException_Exception ex) {
                    System.err.println("No bid has been placed by you in this auction yet.");
                }

                if (al.getStatus() == StatusEnum.DISABLED) {
                    System.out.println("The amount of your bid has been refund due to the auction is currently disabled.");
                }
            }

        } catch (Exception ex) {
            System.err.println("[Warning] An error has occured: " + ex.getMessage());
        }
    }

    private void viewAllAuction() {

        try {
            List<ws.client.AuctionEntity> list = viewAllAuctionListings();
            showList(list);
        } catch (Exception ex) {
            System.err.println("[Warning] An error has occured while viewing all auctions: " + ex.getMessage());
        }
    }

    private void viewWonAuction() {
        System.out.println("");
        System.out.println("******* [Premium Customer] View Won Auction Listing *******");

        try {
            List<ws.client.AuctionEntity> list = viewWonAuctionListings(currentCustomerEntity.getId());
            showList(list);
        } catch (GeneralException_Exception ex) {
            System.err.println("[Warning] An error has occured while viewing winning auction listing: " + ex.getMessage());
        }
    }

    private void menu1() {
        System.out.println("");
        System.out.println("******* [OAS System Web Service] Menu ******");

        System.out.println("1. Registration");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Please input number of the operation that you want to perform\n->");
    }

    private void readUsernameAndPassword(int input) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("");
            System.out.println("******* [OAS System Web Service] Login *******");
            System.out.print("Enter your username\n-> ");
            String username = sc.nextLine().trim();
            System.out.print("Enter your password\n-> ");
            String password = sc.nextLine().trim();

            if (input == 1) {
                currentCustomerEntity = registration(username, password);
            } else if (input == 2) {
                currentCustomerEntity = customerLogin(username, password);
            }

            postLogin();
        } catch (InputMismatchException ex) {
            System.err.println("[Warning] Invalid Type!");
        } catch (CustomerNotFoundException_Exception | IncorrectPasswordException_Exception | CustomerAlreadyPremiumException_Exception | CustomerNotPremiumException_Exception ex) {
            System.err.println("[Warning] An error has occured: " + ex.getMessage());
        }
    }

    private void showList(List<AuctionEntity> list) {
        //  startDate, endDate, false, reservePrice, productName, productDes, new Long(-1), null));
        System.out.printf("%5s%20s%20s%10s%15s%20s\n", "ID|", "Current Winning Bid|", "Your Bid|", "Status|", "Reserve Price|", "Product Name");
        for (AuctionEntity al : list) {
            try {
                System.out.printf("%5s%20s%20s%10s%15s%20s\n", al.getId() + "|", viewCurrentHighestBid(al.getId()) + "|", getMyBidAmount(al.getId(), currentCustomerEntity.getId()) + "|", al.getStatus() + "|", al.getReservePrice() + "|", al.getProductName());
            } catch (AuctionNotFoundException_Exception ex) {
            }
        }
    }

    private static CustomerEntity registration(java.lang.String username, java.lang.String password) throws CustomerNotFoundException_Exception, IncorrectPasswordException_Exception, CustomerAlreadyPremiumException_Exception {
        ws.client.ProxyWebService_Service service = new ws.client.ProxyWebService_Service();
        ws.client.ProxyWebService port = service.getProxyWebServicePort();
        return port.registration(username, password);
    }

    private static CustomerEntity customerLogin(java.lang.String username, java.lang.String password) throws IncorrectPasswordException_Exception, CustomerNotFoundException_Exception, CustomerNotPremiumException_Exception {
        ws.client.ProxyWebService_Service service = new ws.client.ProxyWebService_Service();
        ws.client.ProxyWebService port = service.getProxyWebServicePort();
        return port.customerLogin(username, password);
    }

    private static BigDecimal viewCreditBalance(java.lang.Long id) throws GeneralException_Exception, CustomerNotFoundException_Exception {
        ws.client.ProxyWebService_Service service = new ws.client.ProxyWebService_Service();
        ws.client.ProxyWebService port = service.getProxyWebServicePort();
        return port.viewCreditBalance(id);
    }

    private static java.util.List<ws.client.AuctionEntity> viewAllAuctionListings() throws GeneralException_Exception {
        ws.client.ProxyWebService_Service service = new ws.client.ProxyWebService_Service();
        ws.client.ProxyWebService port = service.getProxyWebServicePort();
        return port.viewAllAuctionListings();
    }

    private static java.util.List<ws.client.AuctionEntity> viewWonAuctionListings(java.lang.Long id) throws GeneralException_Exception {
        ws.client.ProxyWebService_Service service = new ws.client.ProxyWebService_Service();
        ws.client.ProxyWebService port = service.getProxyWebServicePort();
        return port.viewWonAuctionListings(id);
    }

    private static java.util.List<ws.client.AuctionEntity> viewAuctionListByName(java.lang.String productName) throws AuctionNotFoundException_Exception {
        ws.client.ProxyWebService_Service service = new ws.client.ProxyWebService_Service();
        ws.client.ProxyWebService port = service.getProxyWebServicePort();
        return port.viewAuctionListByName(productName);
    }

    private static ws.client.AuctionEntity viewAuctionListDetails(java.lang.Long id) throws AuctionNotFoundException_Exception {
        ws.client.ProxyWebService_Service service = new ws.client.ProxyWebService_Service();
        ws.client.ProxyWebService port = service.getProxyWebServicePort();
        return port.viewAuctionListDetails(id);
    }

    private static void createProxyBid(ws.client.ProxyBiddingEntity bid, java.lang.Long aid, java.lang.Long cid) throws NotEnoughCreditException_Exception, AuctionNotOpenException_Exception, GeneralException_Exception, BidAlreadyExistException_Exception, AuctionNotFoundException_Exception, BidLessThanIncrementException_Exception, CustomerNotFoundException_Exception, AuctionClosedException_Exception {
        ws.client.ProxyWebService_Service service = new ws.client.ProxyWebService_Service();
        ws.client.ProxyWebService port = service.getProxyWebServicePort();
        port.createProxyBid(bid, aid, cid);
    }

    private static BidEntity viewMyBidInAuction(java.lang.Long aid, java.lang.Long cid) throws BidNotFoundException_Exception {
        ws.client.ProxyWebService_Service service = new ws.client.ProxyWebService_Service();
        ws.client.ProxyWebService port = service.getProxyWebServicePort();
        return port.viewMyBidInAuction(aid, cid);
    }

    private static void createSnippingBid(ws.client.BidEntity bid, java.lang.Long aid, java.lang.Long cid) throws BidLessThanIncrementException_Exception, CustomerNotFoundException_Exception, AuctionNotFoundException_Exception, AuctionClosedException_Exception, BidAlreadyExistException_Exception, AuctionNotOpenException_Exception, NotEnoughCreditException_Exception, GeneralException_Exception {
        ws.client.ProxyWebService_Service service = new ws.client.ProxyWebService_Service();
        ws.client.ProxyWebService port = service.getProxyWebServicePort();
        port.createSnippingBid(bid, aid, cid);
    }

    private static BidEntity viewWinningBid(java.lang.Long aid) throws BidNotFoundException_Exception, AuctionNotFoundException_Exception, GeneralException_Exception {
        ws.client.ProxyWebService_Service service = new ws.client.ProxyWebService_Service();
        ws.client.ProxyWebService port = service.getProxyWebServicePort();
        return port.viewWinningBid(aid);
    }

    private static BigDecimal viewCurrentHighestBid(java.lang.Long aid) throws AuctionNotFoundException_Exception {
        ws.client.ProxyWebService_Service service = new ws.client.ProxyWebService_Service();
        ws.client.ProxyWebService port = service.getProxyWebServicePort();
        return port.viewCurrentHighestBid(aid);
    }

    private static BigDecimal getMyBidAmount(java.lang.Long arg0, java.lang.Long arg1) {
        ws.client.ProxyWebService_Service service = new ws.client.ProxyWebService_Service();
        ws.client.ProxyWebService port = service.getProxyWebServicePort();
        return port.getMyBidAmount(arg0, arg1);
    }

}
