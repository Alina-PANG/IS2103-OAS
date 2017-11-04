/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auctionclient;

import ejb.session.stateless.AddressEntityControllerRemote;
import ejb.session.stateless.CreditPackageEntityControllerRemote;
import ejb.session.stateless.CreditTransactionEntityControllerRemote;
import ejb.session.stateless.CustomerEntityControllerRemote;
import entity.AddressEntity;
import entity.CreditPackageEntity;
import entity.CreditTransactionEntity;
import entity.CustomerEntity;
import java.math.BigDecimal;
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
 
    private CustomerEntity customer;
    private CustomerEntityControllerRemote customerentitycontrollerremote; 
    MainApp mainapp;
    private CreditPackageEntityControllerRemote creditpackageentitycontrollerremote;
    private CreditTransactionEntityControllerRemote credittransactionentitycontrollerremote;
    private AddressEntityControllerRemote addressEntityControllerRemote;
    

    public ProfileModule() {
    }

    public ProfileModule(CustomerEntity customer) {
        this.customer = customer;
    }
    
    public void viewProfile()
    {
        Scanner scanner = new Scanner(System.in);
        Integer response=0;
                
        System.out.println("***Profile Details***\n");
        System.out.println("Name> "+customer.getFirstName()+" "+customer.getLastName());
        System.out.println("Email> "+ customer.getEmail());
        System.out.println("Contact Number> "+customer.getContactNumber());
        System.out.println("");
        System.out.println("1.Update your profile");
        System.out.println("2.Back");
        
        while(response<0||response>2)
        {
            System.out.println(">");
            response = scanner.nextInt();
            if(response==1)
            {
                updateProfile();
                
            }
            else if(response==2)
            {
             mainapp.menuMain(customer);
            }
            else
            {
                System.out.println("Invalid Option! Please try again!");
            }
        }

    }
    
    public void updateProfile()
    {
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;
        
        System.out.println("1.Update contact number");
        System.out.println("2.Change Password");
        System.out.println("3.Back");
        while(response<0||response>3)
        {
            System.out.println(">");
            response = scanner.nextInt();
            if(response==1)
            {
                System.out.println("Your current contact number is "+customer.getContactNumber());
                System.out.println("Enter new contact number->");
                customer.setContactNumber(scanner.nextLine().trim());
                System.out.println("New contact number updated successfullly!");
            }
            else if(response==2)
            {
                
                try
                {
                System.out.println("Enter your id->");
                Long id = scanner.nextLong();
                System.out.println("Enter current password->");
                String oldpassword = scanner.nextLine().trim();
                System.out.println("Enter new password->");
                String newpassword = scanner.nextLine().trim();
                System.out.println("Re-enter password->");
                String reenterpassword = scanner.nextLine().trim();
                if(newpassword.equals(reenterpassword))
                {
                    customerentitycontrollerremote.changePassword(oldpassword, newpassword,id);
                    System.out.println("New password changed successfully!");
                }
                else
                {
                    System.out.println("New passwords mismatched!");
                }
                }
                catch(IncorrectPasswordException| CustomerNotFoundException| GeneralException ex)
                {
                    System.out.println(ex.getMessage()+"!\n");
                }
            }
            else if(response==3)
            {
                break;
            }
            else
            {
                System.out.println("Invalid Option! Please try again!");
            }      
        }
    }
    
    public void manageCreditPackage()
    {
        try{
        
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;
        System.out.println("***Manage Credit***");
        System.out.println("1.View Credit Balance");
        System.out.println("2.Purchase New Credit Package");
        System.out.println("3.View Credit Transaction History");
        System.out.println("4.Back");
        while(response<0||response>4)
        {
            System.out.println(">");
            response = scanner.nextInt();
            if(response==1)
            {
                System.out.println("Your current credit balance is "+customer.getCreditBalance());
            }
            else if(response==2)
            {
                System.out.println("***Here are different types of credit packages offering now! ***");
                System.out.println("Package name---Package Value---Package Price");
                //list packages
                //retrieve all package details and loop to list info
                List<CreditPackageEntity> creditpackagelist = creditpackageentitycontrollerremote.viewAllCreditPackage();
                for(CreditPackageEntity creditpackage : creditpackagelist)
                {
                    System.out.println(creditpackage.getId()+"---");
                    System.out.print(creditpackage.getName()+"---");
                    System.out.print(creditpackage.getValue()+"---");
                    System.out.println(creditpackage.getPrice());
                    
                }
                System.out.println("Select id of the package that you want to purchase->");
                Long id = scanner.nextLong();
                
                System.out.println("How many of this credit package that you would like to purchase?->");
                Integer num = scanner.nextInt();
                
                //create CreditTransactionEntity
                credittransactionentitycontrollerremote.createNewTransaction(customer,id,num,TransactionTypeEnum.TOPUP);
         
                //add new customer entity in credit package entity list<customerentity>
                creditpackageentitycontrollerremote.addCustomerToCreditPackage(id, customer);
                
                //update customer's credit balance
                BigDecimal addvalue = new BigDecimal("0.00");
                addvalue = creditpackageentitycontrollerremote.retrieveCreditPackageById(id).getPrice().multiply(BigDecimal.valueOf(num));            
                BigDecimal currentvalue = customer.getCreditBalance();
                customer.setCreditBalance(currentvalue.add(addvalue));
                
                System.out.println("Your purchase is successful");
                System.out.println("Your current credit balance is "+customer.getCreditBalance()+" .");

            }
            else if(response==3)
            {
            List<CreditTransactionEntity> transactionlist = credittransactionentitycontrollerremote.viewAllCreditTransactionEntity(customer);
            for(CreditTransactionEntity transaction:transactionlist)
            {
                System.out.print(transaction.getId()+"---");
                System.out.print(transaction.getTransactionTypeEnum()+"---");
                System.out.println(transaction.getCreditPackageEntity().getName()+"---");
                System.out.println(transaction.getUnitOfPurchase()+"---");
                System.out.println(transaction.getTotalValue());     
            }
            }
            else if(response==4)
            {
                mainapp.menuMain(customer);
            }
            else
            {
                System.out.println("Invalid Option! Please try again!");
            }
        }
    }
        catch(GeneralException ex)
        {
            System.out.println(ex.getMessage()+" !");
        }
        catch(CreditPackageNotFoundException ex)
        {
            System.out.println(ex.getMessage()+" !");
        }
    }
    
    public void manageAddress()
    {
        
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;
        
        System.out.println("***Manage Address***");
        System.out.println("1.Create address");
        System.out.println("2.Update address");
        System.out.println("3.Delete address");
        while(response<0||response>3)
        {
            System.out.println(">");
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
            else
            {
                System.out.println("Invalid input! Please try again");
            }       
        }   
    }
    public void doCreateAddress()
    {
        
        try
        {
        Scanner scanner = new Scanner(System.in);
       
        AddressEntity address =  new AddressEntity();
        System.out.println("Enter address line->");
        address.setAddressLine(scanner.nextLine().trim());
        System.out.println("Enter postal code->");
        address.setPostCode(scanner.nextLine().trim());
        address.setIsDisabled(false);
        address.setCustomerEntity(customer);
        
        address= addressEntityControllerRemote.createAddress(address);
        System.out.println("Address created successfully!");
        }
        catch(AddressAlreadyExistsException ex)
        {
            System.out.println("This address has been created before."+ex.getMessage());
        }
        
    }
    public void doUpdateAddress()
    {
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;
        
        List<AddressEntity> addresslist = addressEntityControllerRemote.viewAllAddress(customer);
        for(AddressEntity address:addresslist)
        {
           System.out.print(address.getId()+"---");
            System.out.print(address.getAddressLine()+"---");
            System.out.println(address.getPostCode());
        }
        System.out.println("Enter id of the address to update");
        AddressEntity address = addressEntityControllerRemote.getAddressById(scanner.nextLong());
        System.out.println("1.Update its address line");
        System.out.println("2.Update its postal code");
        while(response<0||response>2)
        {
            System.out.println(">");
            response = scanner.nextInt();
            if(response==1)
            {
                System.out.println("Enter new address line->");
                address.setAddressLine(scanner.nextLine().trim());
                System.out.println("New address line updated successfully!");
            }
            else if(response==2)
            {
                System.out.println("Enter new postal code->");
                address.setPostCode(scanner.nextLine().trim());
                System.out.println("New postal code updated successfully!");
            }
        }
        System.out.println("Your updated address list:");
        addresslist = addressEntityControllerRemote.viewAllAddress(customer);
        for(AddressEntity newaddress:addresslist)
        {
            System.out.print(newaddress.getId()+"---");
            System.out.print(newaddress.getAddressLine()+"---");
            System.out.println(newaddress.getPostCode());
        }

    }
    public void doDeleteAddress()
    {
        
        try
        {
        Scanner scanner = new Scanner(System.in);

        List<AddressEntity> addresslist = addressEntityControllerRemote.viewAllAddress(customer);
        for(AddressEntity address:addresslist)
        {
           System.out.print(address.getId()+"---");
            System.out.print(address.getAddressLine()+"---");
            System.out.println(address.getPostCode());
        }

        System.out.println("Enter id of the address to delete");
        Long addressid = scanner.nextLong();
        //check whether "address" is associated with a winning bid, if yes, disable this address, if not just delete the address
        if(addressEntityControllerRemote.deleteAddress(addressid))
        {
            System.out.println("Address deleted successfully!");
        }
        else
        {
            System.out.println("This address is associated with your winning bid.");
            System.out.println("You cannot use this address in the future!");
        }
 
        }
        catch(AddressNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }

    }
}
