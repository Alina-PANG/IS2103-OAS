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
import java.util.InputMismatchException;
import java.util.Scanner;
import util.exception.DuplicateException;
import util.exception.GeneralException;
import util.exception.IncorrectPasswordException;
import util.exception.StaffNotFoundException;

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

    public void runApp() {
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
                        System.out.println("[Warning] Please input a valid response number.");
                        break;
                }

                if (response1 == 2) {
                    break;
                }
            } catch (InputMismatchException ex) {
                System.out.println("[Warning] Invalid type!");
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
            currentStaffEntity = staffEntityController.staffLogin(username, password);
            System.out.println("[System] You have successfully logged in as " + currentStaffEntity.getFirstName() + " " + currentStaffEntity.getLastName() + "!");
            postLoginOperation();
        } catch (StaffNotFoundException | IncorrectPasswordException | DuplicateException ex) {
            System.out.println("[Warning] An error has occured while trying to login: " + ex.getMessage());
        }
    }

    private void postLoginOperation() {
        Scanner sc = new Scanner(System.in);
        int response2 = 0;
        
        try {
            while (true) {
                menu02();
                response2 = sc.nextInt();

                switch (response2) {
                    case 1:
                        redirectAccordingToRole();
                        break;
                    case 2:
                        changePassword();
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("[Warning] Please input a valid response number.");
                }

                if (response2 == 3) {
                    break;
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println("[Warning] Please input a valid response number.");
        }
    }

    private void redirectAccordingToRole() {
        switch (currentStaffEntity.getAccessRight()) {
            case MANAGER:
                systemAdministrator = new SystemAdministratorModule(staffEntityController, currentStaffEntity);
                systemAdministrator.doMenu();
                break;
            case FINANCESTAFF:
                financeStaff = new FinanceStaffModule(creditPackageEntityController, currentStaffEntity);
                financeStaff.doMenu();
                break;
            case SALESSTAFF:
                salesStaff = new SalesStaffModule(bidEntityController, auctionEntityController, currentStaffEntity);
                salesStaff.doMenu();
                break;
        }
    }

    private void changePassword() {
        Scanner sc = new Scanner(System.in);
        String currentPw;
        String newPw1 = "a", newPw2 = "b";

        System.out.println("");
        System.out.println("******* [OAS System] Change Password *******");
        System.out.println("Please input your old password: ");
        currentPw = sc.nextLine().trim();
        while (!newPw1.equals(newPw2)) {
            System.out.println("Please input your new password: ");
            newPw1 = sc.nextLine().trim();
            System.out.println("Please input your new password again: ");
            newPw2 = sc.nextLine().trim();

            if (!newPw1.equals(newPw2)) {
                System.out.println("[Warning] The two passwords you input are inconsistent with each other!");
            }
        }

        try {
            currentStaffEntity = staffEntityController.changePassword(currentPw, newPw1, currentStaffEntity.getId());
            System.out.println("[System] Password Changed Successfully!");
        } catch (IncorrectPasswordException | StaffNotFoundException | GeneralException ex) {
            System.out.println("[Warning] An error has occured while changing password: " + ex.getMessage());
        }
    }
}
