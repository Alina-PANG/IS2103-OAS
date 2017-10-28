/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administrationpanelclient;

import ejb.session.stateless.StaffEntityControllerRemote;
import entity.StaffEntity;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import util.enumeration.EmployeeAccessRightEnum;
import util.exception.GeneralException;
import util.exception.StaffAlreadyExistException;
import util.exception.StaffNotFoundException;

/**
 *
 * @author alina
 */
public class SystemAdministratorModule {
    // Define entity controllers

    private StaffEntityControllerRemote staffEntityController;

    // Define entities
    private StaffEntity currentStaffEntity;

    public SystemAdministratorModule() {
    }

    public SystemAdministratorModule(StaffEntityControllerRemote staffEntityController, StaffEntity currentStaffEntity) {
        this.staffEntityController = staffEntityController;
        this.currentStaffEntity = currentStaffEntity;
    }

    protected void doMenu() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("");
            menu();
            try {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("");
                        createNewEmployee();
                        break;
                    case 2:
                        System.out.println("");
                        updateEmployee();
                        break;
                    case 3:
                        System.out.println("");
                        System.out.println("******* [System Administrator] View Employee Details *******");
                        viewEmployeeDetails();
                        break;
                    case 4:
                        System.out.println("");
                        deleteEmployee();
                        break;
                    case 5:
                        System.out.println("");
                        viewAllEmployee();
                        break;
                    case 6:
                        break;
                    default:
                        System.out.println("[Warning] Please input a valid response number.");
                        break;
                }
                if (choice == 6) {
                    break;
                }
            } catch (InputMismatchException ex) {
                System.out.println("[Warning] Invalid Type!");
            }
        }
    }

    private void menu() {
        System.out.println("******* [System Administrator] Homepage *******");
        System.out.println("1. Create New Employee");
        System.out.println("2. Update Employee");
        System.out.println("3. View Employee Details");
        System.out.println("4. Delete Employee");
        System.out.println("5. View All Employees");
        System.out.println("6. Exit");
        System.out.println("Please input the operation that you want to perform:");
        System.out.print("> ");
    }

    private void createNewEmployee() {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("******* [System Administrator] Create New Employee *******");
            System.out.print("Acess Right (manager/financestaff/salesstaff): ");
            EmployeeAccessRightEnum accessRight = null;
            accessRight = EmployeeAccessRightEnum.valueOf(sc.nextLine().trim().toUpperCase());
            System.out.print("First Name: ");
            String firstName = sc.nextLine().trim();
            System.out.print("Last Name: ");
            String lastName = sc.nextLine().trim();
            System.out.print("Identification Number: ");
            String idNum = sc.nextLine().trim();
            System.out.print("Username: ");
            String username = sc.nextLine().trim();
            System.out.print("Password: ");
            String password = sc.nextLine().trim();

            try {
                StaffEntity staff = staffEntityController.createNewStaffEntity(new StaffEntity(firstName, lastName, idNum, username, password, accessRight));
                System.out.println("[System] Staff with id = " + staff.getId() + " has been created successfully!");
            } catch (StaffAlreadyExistException | GeneralException ex) {
                System.out.println("[Warning] An error has occured while creating employee: " + ex.getMessage());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("[Warning] Invalid input, Please retry."); //This is given on invalid input.  Put whatever type of error message you want here.
        }

    }

    private void updateEmployee() {
        Scanner sc = new Scanner(System.in);
        StaffEntity staff;
        System.out.println("******* [System Administrator] Update Employee *******");

        int option = 0;

        try {
            staff = findEmployee();

            while (true) {
                viewEmployeeDetails(staff);
                System.out.println("7. Finish");
                System.out.print("Input the number you want to change:");
                option = sc.nextInt();
                sc.nextLine();

                switch (option) {
                    case 1:
                        System.out.print("First Name: ");
                        staff.setFirstName(sc.nextLine().trim());
                        break;
                    case 2:
                        System.out.println("Last Name:");
                        staff.setLastName(sc.nextLine().trim());
                        break;
                    case 3:
                        System.out.print("Identification Number: ");
                        staff.setIdentificationNumber(sc.nextLine().trim());
                        break;
                    case 4:
                        System.out.print("Username: ");
                        staff.setUsername(sc.nextLine().trim());
                        break;
                    case 5:
                        System.out.print("Password: ");
                        staff.setPassword(sc.nextLine().trim());
                        break;
                    case 6:
                        System.out.print("Acess Right: ");
                        EmployeeAccessRightEnum accessRight = null;
                        do {
                            try {
                                accessRight = EmployeeAccessRightEnum.valueOf(sc.nextLine().trim().toUpperCase());
                            } catch (IllegalArgumentException e) {
                                System.out.println("Invalid input, Please retry."); //This is given on invalid input.  Put whatever type of error message you want here.
                                accessRight = null;
                            }
                        } while (accessRight != null);
                        staff.setAccessRight(accessRight);
                        break;
                    case 7:
                        break;
                    default:
                        System.out.println("Please put a valid input!");
                }
                if (option == 7) {
                    break;
                }
            }
            staffEntityController.updateEmployee(staff);
            System.out.println("[System] Staff with id = " + staff.getId() + " has been updated successfully!");
        } catch (StaffNotFoundException | GeneralException ex) {
            System.out.println("[Warning] An error has occured while updating employee: " + ex.getMessage());
        }catch (InputMismatchException ex) {
            System.out.println("[Warning] Please input a valid response number.");
        }
    }

    private StaffEntity findEmployee() throws GeneralException, StaffNotFoundException, InputMismatchException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please input the id of the employee: ");
        Long id = sc.nextLong();
        StaffEntity staff = staffEntityController.retrieveStaffById(id);
        return staff;
    }

    private void viewEmployeeDetails() {
        try {
            StaffEntity staff = findEmployee();
            viewEmployeeDetails(staff);
        } catch (StaffNotFoundException | GeneralException ex) {
            System.out.println("[Warning] An error has occured while viewing employee details: " + ex.getMessage());
        }
    }

    private void viewEmployeeDetails(StaffEntity staff) {
        System.out.println("[Staff] id=" + staff.getId() + " content: ");
        System.out.println("1. First Name: " + staff.getFirstName());
        System.out.println("2. Last Name: " + staff.getLastName());
        System.out.println("3. Identification Number: " + staff.getIdentificationNumber());
        System.out.println("4. Username: " + staff.getUsername());
        System.out.println("5. Password: " + staff.getPassword());
        System.out.println("6. Access Right: " + staff.getAccessRight());
    }

    private void deleteEmployee() {
        try {
            StaffEntity staff = findEmployee();
            staffEntityController.deleteEmployee(staff.getId());
            System.out.println("[System] Staff with id = " + staff.getId() + " has been deleted successfully!");
        } catch (GeneralException | StaffNotFoundException ex) {
            System.out.println("[Warning] An error has occured while viewing employee details: " + ex.getMessage());
        }
    }

    private void viewAllEmployee() {
        try {
            List<StaffEntity> list = staffEntityController.viewAllEmployee();
            System.out.printf("%4s%15s%15s%30s%15s\n", "ID|", "FIRSTNAME|", "LASTNAME|", "IDENTIFICATION_NUMBER|", "ACCESSRIGHT");

            for (StaffEntity staff : list) {
                System.out.printf("%4s%15s%15s%30s%10s\n", staff.getId() + "|", staff.getFirstName() + "|", staff.getLastName() + "|", staff.getIdentificationNumber() + "|", staff.getAccessRight());
            }
        } catch (GeneralException ex) {
            System.out.println("[Warning] An error has occured while viewing employee details: " + ex.getMessage());
        }
    }
}
