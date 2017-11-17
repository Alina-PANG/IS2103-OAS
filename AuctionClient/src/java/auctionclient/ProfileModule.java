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
import entity.AddressEntity;
import entity.CreditPackageEntity;
import entity.CreditTransactionEntity;
import entity.CustomerEntity;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import util.enumeration.TransactionTypeEnum;
import util.exception.AddressAlreadyExistsException;
import util.exception.AddressNotFoundException;
import util.exception.CreditPackageNotFoundException;
import util.exception.CustomerNotFoundException;
import util.exception.GeneralException;
import util.exception.IncorrectPasswordException;

/*
 *
 * @author Amber
 */
public class ProfileModule {
 
    private CustomerEntityControllerRemote customerEntityControllerRemote;
    private CreditPackageEntityControllerRemote creditPackageEntityControllerRemote;
    private BidEntityControllerRemote bidEntityControllerRemote;
    private AuctionEntityControllerRemote auctionEntityControllerRemote;
    private CreditTransactionEntityControllerRemote creditTransactionEntityControllerRemote;
    private AddressEntityControllerRemote addressEntityControllerRemote;
    private TimerSessionBeanRemote timerSessionBean;
    private MainApp mainapp;
    private CustomerEntity customer;
    

    public ProfileModule() {
    }

    public ProfileModule(CustomerEntityControllerRemote customerEntityControllerRemote, CreditPackageEntityControllerRemote creditPackageEntityControllerRemote, BidEntityControllerRemote bidEntityControllerRemote, AuctionEntityControllerRemote auctionEntityControllerRemote, CreditTransactionEntityControllerRemote creditTransactionEntityControllerRemote, AddressEntityControllerRemote addressEntityControllerRemote, TimerSessionBeanRemote timerSessionBean, CustomerEntity customer) {
        this.customerEntityControllerRemote = customerEntityControllerRemote;
        this.creditPackageEntityControllerRemote = creditPackageEntityControllerRemote;
        this.bidEntityControllerRemote = bidEntityControllerRemote;
        this.auctionEntityControllerRemote = auctionEntityControllerRemote;
        this.creditTransactionEntityControllerRemote = creditTransactionEntityControllerRemote;
        this.addressEntityControllerRemote = addressEntityControllerRemote;
        this.timerSessionBean = timerSessionBean;
        this.customer = customer;
        
    }


    
    
    public void viewProfile()
    {
        Scanner scanner = new Scanner(System.in);
        Integer response=0;
                
        System.out.println("******* [Customer] Profile Details *******");
        System.out.println("Name: "+customer.getFirstName()+" "+customer.getLastName());
        System.out.println("Email: "+ customer.getEmail());
        System.out.println("Contact Number: "+customer.getContactNumber());
        System.out.println("");
        System.out.println("1. Update your profile");
        System.out.println("2. Back");
        System.out.println("Enter number of the operation that you want to perform");
        
        while(response<1||response>2)
        {
            try{
            System.out.println("->");
            response = scanner.nextInt();
            if(response==1)
            {
                updateProfile();
                
            }
            else if(response==2)
            {
             mainapp = new MainApp(customerEntityControllerRemote, creditPackageEntityControllerRemote, bidEntityControllerRemote, auctionEntityControllerRemote, creditTransactionEntityControllerRemote, addressEntityControllerRemote, timerSessionBean);
             mainapp.menuMain(customer);
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
    
    public void updateProfile()
    {
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;
        
        System.out.println("******* [Customer] Profile Update *******");
        System.out.println("1. Update contact number");
        System.out.println("2. Change Password");
        System.out.println("3. Back");
        System.out.println("Enter number of the operation that you want to perform");
        while(response<1||response>3)
        {
            System.out.println("->");
            response = scanner.nextInt();
            if(response==1)
            {
                scanner.nextLine();
                System.out.println("Your current contact number is "+customer.getContactNumber());
                System.out.println("Enter new contact number->");
                customer.setContactNumber(scanner.nextLine().trim());
                System.out.println("[System] New contact number updated successfullly!\n");
                viewProfile();
            }
            else if(response==2)
            {
                
                try
                {
                scanner.nextLine();
                System.out.println("Enter your email->");
                String email = scanner.nextLine().trim();
                System.out.println("Enter current password->");
                String oldpassword = scanner.nextLine().trim();
                System.out.println("Enter new password->");
                String newpassword = scanner.nextLine().trim();
                System.out.println("Re-enter password->");
                String reenterpassword = scanner.nextLine().trim();
                if(newpassword.equals(reenterpassword))
                {
                    Long id = customerEntityControllerRemote.retrieveCustomerByEmail(email).getId();
                    customerEntityControllerRemote.changePassword(oldpassword, newpassword,id);
                    System.out.println("[System] New password changed successfully!\n");
                    viewProfile();
                }
                else
                {
                    System.err.println("[Warning] New passwords mismatched!");
                    updateProfile();
                }
                }
                catch(IncorrectPasswordException| CustomerNotFoundException| GeneralException ex)
                {
                    System.err.println("[Warning] "+ex.getMessage()+"!\n");
                    viewProfile();
                }
                catch(InputMismatchException ex){
                  System.err.println("[Warning] Invalid Type!");
                  viewProfile();
                 }
            }
            else if(response==3)
            {
                mainapp = new MainApp(customerEntityControllerRemote, creditPackageEntityControllerRemote, bidEntityControllerRemote, auctionEntityControllerRemote, creditTransactionEntityControllerRemote, addressEntityControllerRemote, timerSessionBean);
                mainapp.menuMain(customer);
            }
            else
            {
                System.err.println("[Warning] Invalid Option! Please try again!");
                viewProfile();
            }      
        }
    }
    
    public void manageCreditPackage()
    {
        try{
        
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;
        System.out.println("******* [Customer] Credit Managing *******\n");
        System.out.println("1. View Credit Balance");
        System.out.println("2. Purchase New Credit Package");
        System.out.println("3. View Credit Transaction History");
        System.out.println("4. Back");
        System.out.println("Enter number of the operation that you want to perform");
        while(response<1||response>4)
        {
            System.out.println("->");
            response = scanner.nextInt();
            if(response==1)
            {
                System.out.println("Your current credit balance is "+customer.getCreditBalance());
                manageCreditPackage();
            }
            else if(response==2)
            {
                System.out.println("***Here are different types of credit packages offering now! ***");
                System.out.println("Package name---Package Value---Package Price");
                //list packages
                //retrieve all package details and loop to list info
                List<CreditPackageEntity> creditpackagelist = creditPackageEntityControllerRemote.viewAllCreditPackage();
                for(CreditPackageEntity creditpackage : creditpackagelist)
                {
                    System.out.println(creditpackage.getId()+"---");
                    System.out.print(creditpackage.getName()+"---");
                    System.out.print(creditpackage.getValue()+"---");
                    System.out.println(creditpackage.getPrice());
                }
                System.out.println("Enter id of the package that you want to purchase->");
                Long id = scanner.nextLong();
                
                System.out.println("How many of this credit package that you would like to purchase?->");
                Integer num = scanner.nextInt();
                
                //create CreditTransactionEntity
                creditTransactionEntityControllerRemote.createNewTransaction(customer,id,num,TransactionTypeEnum.TOPUP);
         
                //add new customer entity in credit package entity list<customerentity>
                //creditPackageEntityControllerRemote.addCustomerToCreditPackage(id, customer);
                
                //update customer's credit balance
                BigDecimal addvalue = new BigDecimal(BigInteger.ONE);
                addvalue = creditPackageEntityControllerRemote.retrieveCreditPackageById(id).getPrice().multiply(BigDecimal.valueOf(num));            
                BigDecimal currentvalue = customer.getCreditBalance();
                System.out.println("Incremental of credit balance:"+currentvalue.add(addvalue));//debug
                customer = customerEntityControllerRemote.updateCreditBalance(customer.getId(), currentvalue.add(addvalue));
                
                System.out.println("[System] Your purchase is successful!");
                System.out.println("Your current credit balance is "+customer.getCreditBalance()+" .");
                manageCreditPackage();

            }
            else if(response==3)
            {
            List<CreditTransactionEntity> transactionlist = creditTransactionEntityControllerRemote.viewAllCreditTransactionEntity(customer);
            if(!transactionlist.isEmpty()){
              System.out.printf("%5s%10s%10s\n","ID|","Type|","Total Value");
            for(CreditTransactionEntity transaction:transactionlist)
            {
                System.out.printf("%5s%10s%10s\n",transaction.getId()+"|",transaction.getTransactionTypeEnum()+"|",transaction.getTotalValue());
            }
            manageCreditPackage();
            }
            else{
                System.out.println("You don't have any transaction history so far!\n");
            }
            }
            else if(response==4)
            {
                mainapp = new MainApp(customerEntityControllerRemote, creditPackageEntityControllerRemote, bidEntityControllerRemote, auctionEntityControllerRemote, creditTransactionEntityControllerRemote, addressEntityControllerRemote, timerSessionBean);
                mainapp.menuMain(customer);
            }
            else
            {
                System.err.println("[Warning] Invalid Option! Please try again!");
                manageCreditPackage();
            }
        }
    }
        catch(GeneralException | CreditPackageNotFoundException| CustomerNotFoundException ex)
        {
            System.err.println("[Waring] "+ex.getMessage()+" !");
            manageCreditPackage();
        }
        catch(InputMismatchException ex){
            System.err.println("[Warning] Invalid Type!");
            manageCreditPackage();
        }
    }
    
    public void manageAddress()
    {
        
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;
        
        System.out.println("******* [Customer] Address Managing *******");
        System.out.println("1. Create address");
        System.out.println("2. Update address");
        System.out.println("3. Delete address");
        System.out.println("4. Back");
        System.out.println("Enter number of the operation that you want to perform");
        while(response<1||response>4)
        {
            try{
            System.out.println("->");
            response = scanner.nextInt();
            if(response==1)
            {
                doCreateAddress();
            }
            else if(response==2)
            {
                doUpdateAddress();
            }
            else if(response==3)
            {
                doDeleteAddress();
            }
            else if(response==4){
                mainapp = new MainApp(customerEntityControllerRemote, creditPackageEntityControllerRemote, bidEntityControllerRemote, auctionEntityControllerRemote, creditTransactionEntityControllerRemote, addressEntityControllerRemote, timerSessionBean);
                mainapp.menuMain(customer);
                
            }
            else
            {
                System.err.println("[Warning] Invalid input! Please try again");
                manageAddress();
            }
            }
            catch(InputMismatchException ex){
            System.err.println("[Warning] Invalid Type!");
            manageAddress();
             }
        }   
    }
    
    public void doCreateAddress()
    {
        
        try
        {
        Scanner scanner = new Scanner(System.in);
       
        AddressEntity address =  new AddressEntity();
        System.out.println("******* [Customer] New Address Creation *******");
        System.out.println("Enter address line->");
        address.setAddressLine(scanner.nextLine().trim());
        System.out.println("Enter postal code->");
        address.setPostCode(scanner.nextLine().trim());
        address.setIsDisabled(false);
        address.setCustomerEntity(customer);
        
        address= addressEntityControllerRemote.createAddress(address);
        System.out.println("[System] Address created successfully!");
        manageAddress();
        }
        catch(AddressAlreadyExistsException ex)
        {
            System.err.println("[Warning] This address has been created before!");
            manageAddress();
        }
        catch(InputMismatchException ex){
            System.err.println("[Warning] Invalid Type!");
            manageAddress();
        }
        
    }
    public void doUpdateAddress()
    {
        try{
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;
        
        List<AddressEntity> addresslist = addressEntityControllerRemote.viewAllAddress(customer);
        if(!addresslist.isEmpty()){
        for(AddressEntity address:addresslist)
        {
           System.out.print(address.getId()+"---");
            System.out.print(address.getAddressLine()+"---");
            System.out.println(address.getPostCode());
        }
        System.out.println("******* [Customer] Address Update *******");
        System.out.println("Enter id of the address to update->");
        Long aid=scanner.nextLong();
        AddressEntity address = addressEntityControllerRemote.getAddressById(aid);
        System.out.println("1. Update address line");
        System.out.println("2. Update postal code");
        System.out.println("Enter number of the operation that you want to perform");
        while(response<1||response>2)
        {
            System.out.println("->");
            response = scanner.nextInt();
            if(response==1)
            {
                scanner.nextLine();
                System.out.println("Enter new address line->");
                address = addressEntityControllerRemote.updateAddressLine(aid, scanner.nextLine().trim());
                System.out.println("[System] New address line updated successfully!");
                manageAddress();
            }
            else if(response==2)
            {
                scanner.nextLine();
                System.out.println("Enter new postal code->");
                address = addressEntityControllerRemote.updateAddressCode(aid, scanner.nextLine().trim());
                System.out.println("[System] New postal code updated successfully!");
                manageAddress();
            }
        }
        manageAddress();
        }
        else{
            System.out.println("You do not have any address yet!");
            manageAddress();
        }
        }
        catch(InputMismatchException ex){
            System.err.println("[Warning] Invalid Type!");
            manageAddress();
        }
        catch(AddressNotFoundException ex){
            System.err.println("[Warning] "+ex.getMessage());
        }

    }
    public void doDeleteAddress()
    {
        
        try
        {
        Scanner scanner = new Scanner(System.in);

        List<AddressEntity> addresslist = addressEntityControllerRemote.viewAllAddress(customer);
        if(addresslist!=null){
        for(AddressEntity address:addresslist)
        {
           System.out.print(address.getId()+"---");
            System.out.print(address.getAddressLine()+"---");
            System.out.println(address.getPostCode());
        }
        System.out.println("******* [Customer] Address Deletion *******");
        System.out.println("Enter id of the address to delete->");
        Long addressid = scanner.nextLong();
        //check whether "address" is associated with a winning bid, if yes, disable this address, if not just delete the address
        if(addressEntityControllerRemote.deleteAddress(addressid))
        {
            System.out.println("[System] Address deleted successfully!");
            manageAddress();
        }
        else
        {
            System.out.println("[Sysytem] This address is associated with your winning bid! You cannot use this address in the future!");
            manageAddress();
        }
        }
        else{
            System.err.println("[Warning] You have not created any address!");
        }
 
        }
        catch(AddressNotFoundException ex)
        {
            System.err.println("[Warning] "+ex.getMessage());
            manageAddress();
                 
        }
        catch(InputMismatchException ex){
            System.err.println("[Warning] Invalid Type!");
            manageAddress();
        }

    }
}
