/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxyagent;

import ejb.session.stateless.AuctionEntityControllerRemote;
import ejb.session.stateless.BidEntityControllerRemote;
import ejb.session.stateless.CustomerEntityControllerRemote;
import entity.PremiumCustomerEntity;
import java.util.InputMismatchException;
import java.util.Scanner;
import util.exception.DuplicateException;
import util.exception.IncorrectPasswordException;
import util.exception.CustomerNotFoundException;

/**
 *
 * @author alina
 */
public class MainApp {
    
    private CustomerEntityControllerRemote customerEntityController;
    private BidEntityControllerRemote bidEntityController;
    private AuctionEntityControllerRemote auctionEntityController;
 
    private PremiumCustomerEntity currentCustomerEntity;
    
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
        int response2 = 0;

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
                        break;
                    default:
                        System.err.println("[Warning] Please input a valid response number.");
                        break;
                }

                if (response1 == 2) {
                    break;
                }
            } catch (InputMismatchException ex) {
                System.err.println("[Warning] Invalid type!");
            }

        }
    }

    private void menu01() {
        System.out.println("");
        System.out.println("******* [OAS System] Employee Homepage *******");
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.println("Please input the operation that you want to perform:");
        System.out.print("> ");
    }

    private void menu02() {
        System.out.println("");
        System.out.println("******* [OAS System] Employee Basic Operation *******");
        System.out.println("1. Enter Employee Portal");
        System.out.println("2. Change Password");
        System.out.println("3. Logout");
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

    }*/
    
    
}
