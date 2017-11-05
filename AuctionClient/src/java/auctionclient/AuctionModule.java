/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auctionclient;

import ejb.session.stateless.AuctionEntityControllerRemote;
import ejb.session.stateless.BidEntityControllerRemote;
import ejb.session.stateless.CustomerEntityControllerRemote;
import entity.AddressEntity;
import entity.AuctionEntity;
import entity.BidEntity;
import entity.CustomerEntity;
import java.util.List;
import java.util.Scanner;
import util.exception.AuctionNotFoundException;
import util.exception.BidAlreadyExistException;
import util.exception.BidNotFoundException;
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
    private BidEntityControllerRemote bidEntityControllerRemote; 

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
        try{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Below are the auctions that you have placed bids but haven't reached auction ending time yet:");
        List<BidEntity> bidlist = bidEntityControllerRemote.viewMyBidsInProcess(customer);
        System.out.println("Product ID---Product name---Your bid---Current Winning Bid");
        for(BidEntity bid :bidlist)
        {
            System.out.print(bid.getAuctionEntity().getId());
            System.out.print(bid.getAuctionEntity().getProductName()+"---");
            System.out.print(bid.getAmount()+"---");
            System.out.println(auctionEntityControllerRemote
                    .getCurrentWinningBidEntity(bid.getAuctionEntity().getId())
                    .getAmount());
        }
        System.out.println("Do you want to place new bid?(Y/N)->");
        if(scanner.nextLine().trim().equals("Y"))
        {
            System.out.println("Enter id of the auction item that you want to place new bids->");
            placeNewBid(scanner.nextLong());
        }
        else
        {
            System.out.println("Back to menu?(Y/N)>");
                while(scanner.nextLine().trim().equals("N"))
                {
                    System.out.println(">");
                    if(scanner.nextLine().trim().equals("Y"))
                    mainapp.menuMain(customer);
                } 
        }
        
        }
        catch(GeneralException|AuctionNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public void viewWonAuction()
    {
        try{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Below are the auctions that you have won");
        List<BidEntity> winningbidlist= bidEntityControllerRemote.viewAllWinningBid(customer);
        if(winningbidlist!=null)
        {
            Boolean nulladdressexists=false;
            System.out.println("Bid ID---Product name---Bid amount---Delivery Address");
            for(BidEntity bid:winningbidlist)
            {
                System.out.print(bid.getId()+"---");
                System.out.print(bid.getAuctionEntity().getProductName()+"---");
                System.out.print(bid.getAmount()+"---");
                if(bid.getAddressEntity()!=null)
                {
                    System.out.println(bid.getAddressEntity().getAddressLine()+"---");
                    System.out.print(bid.getAddressEntity().getPostCode());
                    nulladdressexists=true;
                }
                    
                else
                    System.out.println("No address linked to this winning bid yet!");
            }
            if(nulladdressexists)
            {
                selectaddressforwinningbid();
            }
            else
            {
                System.out.println("Back to menu?(Y/N)>");
                while(scanner.nextLine().trim().equals("N"))
                {
                    System.out.println(">");
                    if(scanner.nextLine().trim().equals("Y"))
                    mainapp.menuMain(customer);
                } 
            }
        }
        else
        {
            System.out.println("There are no winning auctions now");
        }
        }
        catch(GeneralException ex)
        {
            System.out.println("Error has occured! "+ex.getMessage());
        }
    }
    
    public void selectaddressforwinningbid()
    {
        try{
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("There are winning bids that don't have a delivery address");
        System.out.println("Enter id of the winning bid that you want to select an address->");
        Long bidid = scanner.nextLong();
        System.out.println("Your current address list:");
        List<AddressEntity> addresslist = customer.getAddressEntities();
        for(AddressEntity address:addresslist)
        {
            System.out.print(address.getId()+"---");
            System.out.print(address.getAddressLine()+"---");
            System.out.println(address.getPostCode());
        }
        System.out.print("Select the id of the address that you want to assign as the delivery address for product");
        Long addressid = scanner.nextLong();
        BidEntity bid = bidEntityControllerRemote.setAddressForWinningBid(addressid, bidid);
        System.out.println("Address updated successfully!");
        
        viewWonAuction();//refresh the list and check if there is still won auction that does not have an address 
        }
        catch(BidNotFoundException|GeneralException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
          
    
    
    
}
