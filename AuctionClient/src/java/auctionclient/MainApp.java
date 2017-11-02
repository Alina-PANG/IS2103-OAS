/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auctionclient;

import ejb.session.stateless.CustomerEntityControllerRemote;
import entity.CustomerEntity;
import java.math.BigDecimal;
import java.util.Scanner;
import util.enumeration.CustomerTypeEnum;
import util.exception.CustomerAlreadyExistsException;
import util.exception.CustomerNotFoundException;
import util.exception.GeneralException;

/**
 *
 * @author Amber
 */
public class MainApp {
    private CustomerEntityControllerRemote customerEntityControllerRemote;
    private ProfileModule profilemodule;
    private AuctionModule auctionmodule;
    

    public MainApp() {
    }
    
    
    
    public void runApp()
    {
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;
        
        while(true)
        {
            System.out.println("*** Welcome to Online Auction System ***");
            System.out.println("1.Login");
            System.out.println("2.Register");
            System.out.println("3.Exit\n");
            response = 0;
            
            while(response<1 || response>3)
            {
                System.out.println(">");
                response = scanner. nextInt();
                
                if(response==1)
                {
                     doRegistration();
           
                }
                else if(response==2)
                {
                   
                       doLogin();//menumain is called by this method

                }
                else if(response==3)
                {
                    break;
                }
                else
                {
                    System.out.println("Invalid option! Please try again!");
                }
                
                
            }
            
            if(response==3)
            {
                break;
            
            }
            
        }
        
    }
    
    public void doRegistration()
    {
        try
        {
        Scanner scanner = new Scanner(System.in);
        CustomerEntity customer = new CustomerEntity();//create a Customer Entity at the beginning so that can set parameters later
        
        System.out.println("Welcome on joining us! Please fill in your information!");
        
        //set the parameters into customer entity directly
        System.out.print("Firstname->");
        customer.setFirstName(scanner.nextLine());
        System.out.print("Lastname->");
        customer.setLastName(scanner.nextLine());
        System.out.print("username->");
        customer.setUsername(scanner.nextLine());
        System.out.print("Email->");
        customer.setEmail(scanner.nextLine());
        System.out.print("contanct->");
        customer.setContactNumber(scanner.nextLine());
        customer.setCustomerTypeEnum(CustomerTypeEnum.NORMAL);
        customer.setCreditBalance(BigDecimal.ZERO);
        //System.out.print("Join  us as a premium mumber and enjoy more priviledges? Y/N->");
        //assume that we only have "normal" type customer now
        
        customer = customerEntityControllerRemote.persistCustomerEntity(customer);
        System.out.println("Customer");
        
        }
        catch(CustomerAlreadyExistsException | GeneralException ex)
        {
            System.out.println("An error has occured while creating the new customer "+ex.getMessage()+"!");
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
            System.out.println("Invalid email or password!");//customer doesnot exist or wrong password
        }
        }
        catch(CustomerNotFoundException ex)
        {
            System.out.println(ex.getMessage()+"!\n");
        }
   
        
    }
    
    public void menuMain(CustomerEntity customer)//pass in customer entity who just logged in
    {
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;
        
        System.out.println("***Welcome "+customer.getUsername()+" to your online auction world!");
        System.out.println("1.View Profile");//include view and update which includes changing password
        System.out.println("2.View my Credit Package");// view my balance, view credit package transaction, also can buy a new package
        System.out.println("3.Update Address");//create address and delete address
        System.out.println("4.View All Auction Listing");//display all available auciotn items and place bids
        System.out.println("5.View my Bid");//view all the auction that i have bidded and can update the bid and refresh the bid list
        System.out.println("6.Browse Won Acution Listing");//all the auction that i have won and can select address for successful bid
        System.out.println("7.Logout");
        
        
        //if the response is invalid, keep key in
        while(response<0||response>7)
        {
            System.out.println(">");
            response = scanner.nextInt();
            

            if(response==1)
            {
                profilemodule.viewProfile();
            }
            else if(response==2)
            {
                profilemodule.manageCreditPackage();
            }
            else if(response==3)
            {
                profilemodule.manageAddress();
            }
            

            if(response==4)
            {
                auctionmodule.viewAuctionListing();
            }
            else if(response==5)
            {
                auctionmodule.viewBid();
            }
            else if(response==6)
            {
                auctionmodule.viewWonAuction();
            }
            
            else if(response==7)
            {
                break;
            }
            else
            {
                System.out.println("Invalid Option! Please try again!");
            }
            
        }
        
        
    }
}
