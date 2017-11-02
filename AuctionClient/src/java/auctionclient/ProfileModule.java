/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auctionclient;

import ejb.session.stateless.CreditPackageEntityControllerRemote;
import ejb.session.stateless.CreditTransactionEntityControllerRemote;
import ejb.session.stateless.CustomerEntityControllerRemote;
import entity.CreditPackageEntity;
import entity.CustomerEntity;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import static util.enumeration.TransactionTypeEnum.TOPUP;
import util.exception.CustomerPinChangeException;
import util.exception.GeneralException;

/**
 *
 * @author Amber
 */
public class ProfileModule {
 
    private CustomerEntity customer;
    private CustomerEntityControllerRemote customerentitycontrollerremote; 
    MainApp mainapp;
    private CreditPackageEntityControllerRemote creditpackageentitycontrollerremote;
    private CreditTransactionEntityControllerRemote credittransactionentitycontrollerremote;
    

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
                System.out.println("Enter current password->");
                String oldpassword = scanner.nextLine().trim();
                System.out.println("Enter new password->");
                String newpassword = scanner.nextLine().trim();
                System.out.println("Re-enter password->");
                String reenterpassword = scanner.nextLine().trim();
                if(newpassword.equals(reenterpassword))
                {
                    customerentitycontrollerremote.changePassword(customer,oldpassword, newpassword);
                    System.out.println("New password changed successfully!");
                }
                else
                {
                    System.out.println("New passwords mismatched!");
                }
                }
                catch(CustomerPinChangeException ex)
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
                    System.out.print(creditpackage.getName()+"---");
                    System.out.print(creditpackage.getValue()+"---");
                    System.out.println(creditpackage.getPrice());
                    
                }
                System.out.println("Select id of the package that you want to purchase->");
                Long id = scanner.nextLong();
                
                
                System.out.println("How many of this credit package that you would like to purchase?->");
                Integer num = scanner.nextInt();
                
                //create CreditTransactionEntity, update new credit balance for this customer,
                //create entity
                //credittransactionentitycontrollerremote.createNewTransaction(customer,id,num,TOPUP);
                //havent finalised this method yet
                //update new credit balance
                
                
                
            }
            else if(response==3)
            {
                //view transaction entity where enumtype is topup and fefund and bidding where customer entity is the one that has passed in
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
    }
    
    public void manageAddress()
    {
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;
        
        System.out.println("");
        
        
    }
}
