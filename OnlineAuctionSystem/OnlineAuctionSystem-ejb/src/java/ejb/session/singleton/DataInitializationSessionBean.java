/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.singleton;

import ejb.session.stateless.AuctionEntityControllerLocal;
import ejb.session.stateless.BidEntityControllerLocal;
import ejb.session.stateless.CreditPackageEntityControllerLocal;
import ejb.session.stateless.StaffEntityControllerLocal;
import entity.CreditPackageEntity;
import entity.StaffEntity;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.enumeration.EmployeeAccessRightEnum;
import util.exception.CreditPackageAlreadyExistException;
import util.exception.DuplicateException;
import util.exception.GeneralException;
import util.exception.StaffAlreadyExistException;
import util.exception.StaffNotFoundException;

/**
 *
 * @author alina
 */
@Singleton
@LocalBean
@Startup
public class DataInitializationSessionBean {

    @EJB
    private CreditPackageEntityControllerLocal creditPackageEntityController;

    @EJB
    private BidEntityControllerLocal bidEntityController;

    @EJB
    private AuctionEntityControllerLocal auctionEntityController;

    @EJB
    private StaffEntityControllerLocal staffEntityController;
    
    

    @PersistenceContext(unitName = "OnlineAuctionSystem-ejbPU")
    private EntityManager em;

    @PostConstruct
    public void postConstruct() {
        try {
            staffEntityController.retrieveStaffByIdentificationNumber("01");
        } catch (StaffNotFoundException| DuplicateException ex) {
            initializeDataStaff1();
        }
    }
    
    

    private void initializeDataStaff1() {
        try {
            staffEntityController.createNewStaffEntity(new StaffEntity("Anthony", "Young", "01", "manager", "password", EmployeeAccessRightEnum.MANAGER));
            staffEntityController.createNewStaffEntity(new StaffEntity("Yue", "Ling", "02", "yueling", "000000", EmployeeAccessRightEnum.FINANCESTAFF));
            staffEntityController.createNewStaffEntity(new StaffEntity("Wei Liang", "Tan", "03", "weiliang", "000000", EmployeeAccessRightEnum.SALESSTAFF));
            creditPackageEntityController.createNewCreditPackage(new CreditPackageEntity(new BigDecimal(5), new BigDecimal(5), "5 for 5", false));
            creditPackageEntityController.createNewCreditPackage(new CreditPackageEntity(new BigDecimal(10), new BigDecimal(9), "9 for 10", false));
            creditPackageEntityController.createNewCreditPackage(new CreditPackageEntity(new BigDecimal(100), new BigDecimal(85), "85 for 100", false));
        } catch (StaffAlreadyExistException | GeneralException | CreditPackageAlreadyExistException ex) {
            System.out.println("Error in Singleton");
        }
    }

}

//private void initializeDataAuction() {
//  AuctionEntity auction = new AuctionEntity(Long id, Date startingTime, Date endingTime, Boolean status, BigDecimal reservePrice, BigDecimal winningBid, String productName, String productDescription, Long winningCustomerId, List<BidEntity> bidEntities);
    //}
