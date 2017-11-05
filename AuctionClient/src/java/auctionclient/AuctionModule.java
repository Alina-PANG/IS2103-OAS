/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auctionclient;

import ejb.session.stateless.AuctionEntityControllerRemote;
import ejb.session.stateless.CustomerEntityControllerRemote;
import entity.AuctionEntity;
import entity.BidEntity;
import entity.CustomerEntity;
import java.util.List;
import java.util.Scanner;
import util.exception.AuctionNotFoundException;
import util.exception.BidAlreadyExistException;
import util.exception.GeneralException;

/**
 *
 * @author Amber
 */
public class AuctionModule {
    
    private CustomerEntity customer;
    private CustomerEntityControllerRemote customerentitycontrollerremote; 
    private AuctionEntityControllerRemote auctionEntityControllerRemote;
    MainApp mainapp;

    public AuctionModule() {
    }

    public AuctionModule(CustomerEntity customer) {
        this.customer = customer;
    }
    
    public void viewAuctionListing()
    {
        try
        {    
        Scanner scanner = new Scanner(System.in);
        List<AuctionEntity> availableAuctionList = auctionEntityControllerRemote.viewAvailableAuctionEntity();
        System.out.println("Product ID---Product name");
        
        for(AuctionEntity auctionentity: availableAuctionList)
        {
            System.out.print(auctionentity.getId()+"---");
            System.out.println(auctionentity.getProductName());
        }
        
        //ask whether want to view details of a specific auction item
        System.out.println("1.View details of auction item");
        System.out.println("2.Back to menu");
        Integer response=0;
        while(response<0||response>2)
        {
            System.out.println(">");
            response = scanner.nextInt();
            if(response==1)
            {
                break;
            }
            else if(response==2)
            {
                mainapp.menuMain(customer);
            }
            else
            {
                System.out.println("Invalid input! Please try again!");
            }
        }
        System.out.println("Enter ID of the auction item to view details->");
        Long id = scanner.nextLong();
        //if want to view details, direct to details of one specific item
        viewAuctionEntityDetails(id);
        }
        catch(GeneralException ex)
        {
            System.out.println(ex.getMessage());
        }
        
        
    }
    
    public void viewAuctionEntityDetails(Long productid)
    {
        try
        {
        AuctionEntity auctionentity = auctionEntityControllerRemote.retrieveAuctionById(productid);
       
        System.out.println("Product ID---"+auctionentity.getId());
        System.out.println("Product Name---"+auctionentity.getProductName());
        System.out.println("Product Description---"+auctionentity.getProductDescription());
        System.out.println("Product Ending Time---"+auctionentity.getEndingTime());
        System.out.println("Current Winning Bid Amount---"+auctionEntityControllerRemote.
                getCurrentWinningBidEntity(productid).getAmount());
        System.out.println("Current bid incremental based on current price---"+auctionEntityControllerRemote
                .getCurrentBidIncremental(auctionEntityControllerRemote.getCurrentWinningBidEntity(productid).getAmount()));
        
        System.out.println("1.Place new bid for this item");
        System.out.println("2.No thanks, I want to browse the auction list again.");
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;
        while(response<0||response>2)
        {
            System.out.println(">");
            response = scanner.nextInt();
            if(response==1)
            {
                placeNewBid(productid);
            }   
            else if(response==2)
            {
                viewAuctionListing();
            }
            else
                System.out.println("Invalid input! Please try again!");
        }
        }
        catch(AuctionNotFoundException ex)
        {
            System.out.println("The ID is invalid!");
        }
    }
    
    
    public void placeNewBid(Long productid)
    {
        try
        {//call controller reomte to place new bid 
        BidEntity newbid = auctionEntityControllerRemote.placeNewBid(productid, customer);
        System.out.println("Your new bid has been placed successfully!");
        System.out.println("Your bid amount for product:"+newbid.getAuctionEntity().getProductName()+" is "+newbid.getAmount());
        //provide the option of refresh the auction listing bids and view auctionlisting detals again 
        System.out.println("1.Refresh auction listing details");
        //or view auction listing again to browse for more good deals!
        System.out.println("2.View auction listing againg to browse for more good deals!");
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;
        while(response<0||response>2)
        {
            System.out.println(">");
            response = scanner.nextInt();
            if(response==1)
            {
                viewAuctionEntityDetails(productid);
            }
            else if(response==2)
            {
                viewAuctionListing();
            }
            else
            {
                System.out.println("Invalid input! Please try again!");
            }

        }
        }
        catch(AuctionNotFoundException|BidAlreadyExistException|GeneralException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
        
    
   
    
    public void viewBid()
    {
        
    }
    public void viewWonAuction()
    {
        Scanner scanner = new Scanner(System.in);
        
        
    }
          
    
    
    
}
