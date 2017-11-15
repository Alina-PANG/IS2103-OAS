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
import entity.CustomerEntity;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;
import util.enumeration.CustomerTypeEnum;
import util.exception.CustomerAlreadyExistException;
import util.exception.CustomerNotFoundException;
import util.exception.GeneralException;

/**
 *
 * @author Amber
 */
public class MainApp {
    private CustomerEntityControllerRemote customerEntityControllerRemote;
    private CreditPackageEntityControllerRemote creditPackageEntityControllerRemote;
    private BidEntityControllerRemote bidEntityControllerRemote;
    private AuctionEntityControllerRemote auctionEntityControllerRemote;
    private CreditTransactionEntityControllerRemote creditTransactionEntityControllerRemote;
    private AddressEntityControllerRemote addressEntityControllerRemote;
    private ProfileModule profilemodule;
    private AuctionModule auctionmodule;
    
    private TimerSessionBeanRemote timerSessionBean;
    private CustomerEntity customer;
    

    public MainApp() {
    }

    public MainApp(CustomerEntityControllerRemote customerEntityControllerRemote, CreditPackageEntityControllerRemote creditPackageEntityControllerRemote, BidEntityControllerRemote bidEntityControllerRemote, AuctionEntityControllerRemote auctionEntityControllerRemote, CreditTransactionEntityControllerRemote creditTransactionEntityControllerRemote, AddressEntityControllerRemote addressEntityControllerRemote, TimerSessionBeanRemote timerSessionBean) {
        this.customerEntityControllerRemote = customerEntityControllerRemote;
        this.creditPackageEntityControllerRemote = creditPackageEntityControllerRemote;
        this.bidEntityControllerRemote = bidEntityControllerRemote;
        this.auctionEntityControllerRemote = auctionEntityControllerRemote;
        this.creditTransactionEntityControllerRemote = creditTransactionEntityControllerRemote;
        this.addressEntityControllerRemote = addressEntityControllerRemote;
        this.timerSessionBean = timerSessionBean;
        
    }

    

    
    public void runApp()
    {
        
        try{
        Scanner scanner = new Scanner(System.in);
       
        Integer response = 0;
        
        while(true)
        {
            System.out.println("******* [Customer] Welcome to Online Auction System *******");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit\n");
            System.out.println("Enter number of the operation that you want to perform");
            response = 0;
            
            while(response<1 || response>3)
            {
                System.out.println("->");
                response = scanner. nextInt();
                
                if(response==1)
                {
                     doLogin();
           
                }
                else if(response==2)
                {
                   
                       doRegistration();//menumain is called by this method

                }
                else if(response==3)
                {
                    break;
                }
                else
                {
                    System.err.println("[Warning] Invalid option! Please try again!");
                }

            }
            
            if(response==3)
            {
                break;
            
            }   
        }
        }
        catch(InputMismatchException ex){
            System.err.println("[Warning] Invalid Type!");
        }
    }
    
    public void doRegistration()
    {
        try
        {
        Scanner scanner = new Scanner(System.in);
        CustomerEntity customer = new CustomerEntity();//create a Customer Entity at the beginning so that can set parameters later
        System.out.println("******* [Customer] Registration *******");
        //set the parameters into customer entity directly
        System.out.print("Enter your firstname->");
        customer.setFirstName(scanner.nextLine());
        System.out.print("Enter your lastname->");
        customer.setLastName(scanner.nextLine());
        System.out.print("Enter your username->");
        customer.setUsername(scanner.nextLine());
        System.out.print("Enter your email->");
        customer.setEmail(scanner.nextLine());
        System.out.print("Enter your contact number->");
        customer.setContactNumber(scanner.nextLine());
        System.out.print("Enter your password->");
        customer.setPassword(scanner.nextLine().trim());
        System.out.print("Do you want to become a premium customer?(Y/N)->");
        if(scanner.nextLine().trim()=="Y"){
            customer.setCustomerTypeEnum(CustomerTypeEnum.PREMIUM);
        }
        else{
            customer.setCustomerTypeEnum(CustomerTypeEnum.NORMAL);
        }
        
        customer.setCreditBalance(BigDecimal.ZERO);
        
        customer = customerEntityControllerRemote.createNewCustomerEntity(customer);
        System.out.println("[System] Customer"+ customer.getUsername()+" is registered successfully!");
        
        }
        catch(CustomerAlreadyExistException ex) 
        {
            System.err.println("[Warning] This customer already exists!");
        }
        catch(GeneralException ex){
            System.err.println("[Warning] "+ ex.getMessage());
        }
        catch(InputMismatchException ex){
            System.err.println("[Warning] Invalid Type!");
        }
    }
    
    public void doLogin()
    {
        try
        {
            Scanner scanner = new Scanner(System.in);
        
        //use email and password to login because email is unique but username is not unique
        String email;
        String password;
        System.out.println("******* [Customer] Login *******");
        System.out.println("Enter email->");
        email = scanner.nextLine().trim();
        CustomerEntity customer = customerEntityControllerRemote.retrieveCustomerByEmail(email);
        System.out.println("Enter password->");
        password = scanner.nextLine().trim();
        if(password.equals(customer.getPassword()))
        {
            menuMain(customer);
            
        }    
        else
        {
            System.err.println("[Warning] Invalid email or password!");//customer doesnot exist or wrong password
        }
        }
        catch(CustomerNotFoundException ex)
        {
            System.err.println("[Warning] "+ex.getMessage()+"!\n");
        }
        catch(InputMismatchException ex){
            System.err.println("[Warning] Invalid Type!");
        }
        
    }
    
    public void menuMain(CustomerEntity customer)//pass in customer entity who just logged in
    {
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;
        
        System.out.println("****** Welcome "+customer.getUsername()+" to the online auction world! *******");
        System.out.println("1. View Profile");//include view and update which includes changing password
        System.out.println("2. View my Credit Package");// view my balance, view credit package transaction, also can buy a new package
        System.out.println("3. Update Address");//create address and delete address
        System.out.println("4. View All Auction Listing");//display all available auction items and place bids
        System.out.println("5. View my Bid");//view all the auction that i have bidded and can update the bid and refresh the bid list
        System.out.println("6. Browse Won Acution Listing");//all the auction that i have won and can select address for successful bid
        System.out.println("7. Logout");
        System.out.println("Enter number of the operation that you want to perform");
        
        
        //pass customer entity into sub-modules
        ProfileModule profile = new ProfileModule(customerEntityControllerRemote, creditPackageEntityControllerRemote, bidEntityControllerRemote, auctionEntityControllerRemote, creditTransactionEntityControllerRemote, addressEntityControllerRemote, timerSessionBean, customer);
        AuctionModule auction = new AuctionModule(customerEntityControllerRemote, creditPackageEntityControllerRemote, bidEntityControllerRemote, auctionEntityControllerRemote, creditTransactionEntityControllerRemote, addressEntityControllerRemote, timerSessionBean, customer);
        
        //if the response is invalid, keep key in
        while(response<1||response>7)
        {
            System.out.println("->");
            response = scanner.nextInt();
            
            try{
            if(response==1){
            
                profile.viewProfile();
            }
            else if(response==2)
            {
                profile.manageCreditPackage();
            }
            else if(response==3)
            {
                profile.manageAddress();
            }
           else if(response==4)
            {
                auction.viewAuctionListing();
            }
            else if(response==5)
            {
                auction.viewBid();
            }
            else if(response==6)
            {
                auction.viewWonAuction();
            }
            
            else if(response==7)
            {
                break;
            }
            else
            {
                System.err.println("[Warning] Invalid Option! Please try again!");
            }
            }
            catch(InputMismatchException ex){
            System.err.println("[Warning] Invalid Type!");
            }
        }        
    }
}
