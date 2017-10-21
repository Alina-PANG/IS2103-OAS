/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administrationpanelclient;

import ejb.session.stateless.AuctionEntityControllerRemote;
import ejb.session.stateless.BidEntityControllerRemote;
import ejb.session.stateless.CreditPackageEntityControllerRemote;
import ejb.session.stateless.StaffEntityControllerRemote;
import entity.StaffEntity;
import java.util.Scanner;

/**
 *
 * @author alina
 */
public class MainApp {
    
    // Define entity controllers
    private StaffEntityControllerRemote staffEntityController;
    private CreditPackageEntityControllerRemote creditPackageEntityController;
    private BidEntityControllerRemote bidEntityController;
    private AuctionEntityControllerRemote auctionEntityController;
    
    // Define entities
    private StaffEntity currentStaffEntity;
    
    // Define java classes being called
    private EmployeeModule employee;
    private SystemAdministratorModule systemAdministrator;
    private FinanceStaffModule financeStaff;
    private SalesStaffModule salesStaff;

    public MainApp() {
    }

    public MainApp(StaffEntityControllerRemote staffEntityController, CreditPackageEntityControllerRemote creditPackageEntityController, BidEntityControllerRemote bidEntityController, AuctionEntityControllerRemote auctionEntityController) {
        this.staffEntityController = staffEntityController;
        this.creditPackageEntityController = creditPackageEntityController;
        this.bidEntityController = bidEntityController;
        this.auctionEntityController = auctionEntityController;
    }
    
    public void runApp(){
        // define attributes
        Scanner sc = new Scanner(System.in);
        Integer response = 0;
        
        // while loop to collect response
        while(true){
            menu01();
            response = sc.nextInt();
            
            switch(response){
                case 1: 
                    doLogin();
                    employee = new EmployeeModule();
                    break;
                case 2:
                    break;
                default:
                    System.out.println("[Warning] Wrong response! Please response accordingly.");
                    break;
            }
            
            if(response == 2)
                break;
        }
    }
    
    private void menu01(){
        System.out.println("******* [OAS System] Employee Homepage *******");
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.println("Please input the operation that you want to perform:");
        System.out.print("> ");
    }
    
    private void menu02(){
        System.out.println("******* [OAS System] Employee Basic Operation *******");
        System.out.println("1. Enter Employee Portal");
        System.out.println("2. Change Password");
        System.out.println("3. Logout");
        System.out.println("Please input the operation that you want to perform:");
        System.out.print("> ");
    }
    
    private void doLogin(){
        // define attributes
        Scanner sc = new Scanner(System.in);
        String username, password;
        
        // login
        System.out.println("******* [OAS System] Employee Login *******");
        System.out.println("Username: ");
        username = sc.nextLine().trim();
        System.out.println("Password: ");
        password = sc.nextLine().trim();
        
                
        
    }
}
