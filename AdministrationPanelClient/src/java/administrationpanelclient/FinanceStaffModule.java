/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administrationpanelclient;

import ejb.session.stateless.CreditPackageEntityControllerRemote;
import entity.StaffEntity;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author alina
 */
public class FinanceStaffModule {

    // Define entity controllers
    private CreditPackageEntityControllerRemote creditPackageEntityController;

    // Define entities
    private StaffEntity currentStaffEntity;

    public FinanceStaffModule() {

    }

    public FinanceStaffModule(CreditPackageEntityControllerRemote creditPackageEntityController, StaffEntity currentStaffEntity) {
        this.creditPackageEntityController = creditPackageEntityController;
        this.currentStaffEntity = currentStaffEntity;
    }

    private void menu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected void doMenu() {
        Scanner sc = new Scanner(System.in);
        int reponse = 0;

        try {
            
        } catch (InputMismatchException ex) {
            System.out.println("[Warning] Please input a valid response number.");
        }
    }
}

}
