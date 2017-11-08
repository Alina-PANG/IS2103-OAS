/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxyagent;

import ejb.session.stateless.AuctionEntityControllerRemote;
import ejb.session.stateless.BidEntityControllerRemote;
import ejb.session.stateless.CustomerEntityControllerRemote;
import ws.client.CustomerEntity;

/**
 *
 * @author alina
 */
public class MainApp {
    
    private CustomerEntityControllerRemote customerEntityController;
    private BidEntityControllerRemote bidEntityController;
    private AuctionEntityControllerRemote auctionEntityController;
 
    private CustomerEntity currentCustomerEntity;
    
    public MainApp() {
    }

    public MainApp(CustomerEntityControllerRemote customerEntityController, BidEntityControllerRemote bidEntityController, AuctionEntityControllerRemote auctionEntityController) {
        this.customerEntityController = customerEntityController;
        this.bidEntityController = bidEntityController;
        this.auctionEntityController = auctionEntityController;
    }

 /*
    public void runApp(){
        // define attributes
        Scanner sc = new Scanner(System.in);
        int response1 = 0;

        // while loop to collect response
        while (true) {
            menu01();
            try {
                response1 = sc.nextInt();
                switch (response1) {
                    case 1:
                        doLogin();
                        break;
                    case 2: 
                        registration();
                        break;
                    case 3:
                        break;
                    default:
                        System.err.println("[Warning] Please input a valid response number.");
                        break;
                }

                if (response1 == 3) {
                    break;
                }
            } catch (InputMismatchException ex) {
                System.err.println("[Warning] Invalid type!");
            }

        }
    }

    private void menu01() {
        System.out.println("");
        System.out.println("******* [OAS System] Premium Client Homepage *******");
        System.out.println("1. Login");
        System.out.println("2. Registration");
        System.out.println("3. Exit");
        System.out.println("Please input the operation that you want to perform:");
        System.out.print("> ");
    }

    private void menu02() {
        System.out.println("");
        System.out.println("******* [OAS System] Premium Client Operation *******");
        System.out.println("1. View Credit Balance");
        System.out.println("2. View Auction Listing Details");
        System.out.println("3. Configure Proxy Bidding for Aucton Listing");
        System.out.println("4. Configure Sniping for Auction Listing");
        System.out.println("5. Browse All Auction Listings");
        System.out.println("6. View Won Auction Listings");
        System.out.println("7. Logout");
        System.out.println("Please input the operation that you want to perform:");
        System.out.print("> ");
    }

    private void doLogin() {
        // define attributes
        Scanner sc = new Scanner(System.in);
        String username, password;

        // login
        System.out.println("");
        System.out.println("******* [OAS System] Employee Login *******");
        System.out.print("Username: ");
        username = sc.nextLine().trim();
        System.out.print("Password: ");
        password = sc.nextLine().trim();

        try {
            currentCustomerEntity = customerLogin(username, password);
            System.out.println("[System] You have successfully logged in as " + currentCustomerEntity.getFirstName() + " " + currentCustomerEntity.getLastName() + "!");
        } catch (CustomerNotFoundException | IncorrectPasswordException | DuplicateException ex) {
            System.err.println("[Warning] An error has occured while trying to login: " + ex.getMessage());
        }

    }

    private void registration() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
*/
    
    
}
