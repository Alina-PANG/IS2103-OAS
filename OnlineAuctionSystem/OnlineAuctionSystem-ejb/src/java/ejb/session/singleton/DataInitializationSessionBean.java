/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.singleton;

import ejb.session.stateless.AddressEntityControllerLocal;
import ejb.session.stateless.AuctionEntityControllerLocal;
import ejb.session.stateless.BidEntityControllerLocal;
import ejb.session.stateless.CreditPackageEntityControllerLocal;
import ejb.session.stateless.CustomerEntityControllerLocal;
import ejb.session.stateless.StaffEntityControllerLocal;
import entity.AddressEntity;
import entity.AuctionEntity;
import entity.BidEntity;
import entity.CreditPackageEntity;
import entity.CustomerEntity;
import entity.StaffEntity;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.enumeration.CustomerTypeEnum;
import util.enumeration.EmployeeAccessRightEnum;
import util.exception.AddressAlreadyExistsException;
import util.exception.AuctionAlreadyExistException;
import util.exception.AuctionClosedException;
import util.exception.AuctionNotFoundException;
import util.exception.AuctionNotOpenException;
import util.exception.BidAlreadyExistException;
import util.exception.BidLessThanIncrementException;
import util.exception.CreditPackageAlreadyExistException;
import util.exception.CustomerAlreadyExistException;
import util.exception.CustomerNotFoundException;
import util.exception.DuplicateException;
import util.exception.GeneralException;
import util.exception.NotEnoughCreditException;
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
    private AddressEntityControllerLocal addressEntityController;

    @EJB
    private CustomerEntityControllerLocal customerEntityController;

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
        if (em.find(StaffEntity.class, new Long(1)) == null) {
            initializeDataStaff();
        }

        if (em.find(CustomerEntity.class, new Long(1)) == null) {
            initializeCustomer();
        }

    }

    private void initializeDataStaff() {
        try {
            staffEntityController.createNewStaffEntity(new StaffEntity("Anthony", "Young", "01", "manager", "password", EmployeeAccessRightEnum.MANAGER));
            staffEntityController.createNewStaffEntity(new StaffEntity("Yue", "Ling", "02", "yueling", "000000", EmployeeAccessRightEnum.FINANCESTAFF));
            staffEntityController.createNewStaffEntity(new StaffEntity("Wei Liang", "Tan", "03", "weiliang", "000000", EmployeeAccessRightEnum.SALESSTAFF));

        } catch (StaffAlreadyExistException | GeneralException ex) {
            System.out.println("Error in Singleton 1: "+ex.getMessage());
        }
    }

    private void initializeCustomer() {
        try {
            CustomerEntity c = customerEntityController.createNewCustomerEntity(new CustomerEntity("Jinzheng", "Xu", "jinzheng", "000000", "87111111", "jinzhengxu@u.nus.edu", CustomerTypeEnum.NORMAL));
            CustomerEntity c2 = customerEntityController.createNewCustomerEntity(new CustomerEntity("Yingshi", "Huang", "yingshi", "000000", "87122222", "jinsssgxu@u.nus.edu", CustomerTypeEnum.NORMAL));
            c.setCreditBalance(new BigDecimal(2000));
            c2.setCreditBalance(new BigDecimal(3000));
            AddressEntity a = addressEntityController.createAddress(new AddressEntity("PGPR", "118133", false, c));

            creditPackageEntityController.createNewCreditPackage(new CreditPackageEntity(new BigDecimal(5), new BigDecimal(5), "5 for 5", false));
            creditPackageEntityController.createNewCreditPackage(new CreditPackageEntity(new BigDecimal(10), new BigDecimal(9), "9 for 10", false));
            creditPackageEntityController.createNewCreditPackage(new CreditPackageEntity(new BigDecimal(100), new BigDecimal(85), "85 for 100", false));

            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
            AuctionEntity ae1 = auctionEntityController.createNewAuction(new AuctionEntity(formatter.parse("16:34:59 11/11/2017"), formatter.parse("16:36:22 11/11/2017"), new BigDecimal(77), "Totoro", "Cute Totoro!"));
            AuctionEntity ae2 = auctionEntityController.createNewAuction(new AuctionEntity(formatter.parse("23:59:59 11/11/2017"), formatter.parse("23:59:59 20/11/2018"), new BigDecimal(10), "Cup", "Drink Water"));
            auctionEntityController.createNewAuction(new AuctionEntity(formatter.parse("23:59:59 28/11/2018"), formatter.parse("23:59:59 03/11/2019"), new BigDecimal(92), "Apple", "Sweet Apple"));

            bidEntityController.createNewBid(new BidEntity(new BigDecimal(20)), c.getId(), ae1.getId());
            bidEntityController.createNewBid(new BidEntity(new BigDecimal(30)), c.getId(), ae1.getId());
            bidEntityController.createNewBid(new BidEntity(new BigDecimal(60)), c2.getId(), ae1.getId());
            bidEntityController.createNewBid(new BidEntity(new BigDecimal(30)), c.getId(), ae1.getId());

        } catch (AuctionClosedException | AuctionNotOpenException |NotEnoughCreditException| AuctionNotFoundException | CustomerNotFoundException | BidLessThanIncrementException | AddressAlreadyExistsException | CustomerAlreadyExistException | GeneralException | CreditPackageAlreadyExistException | ParseException | AuctionAlreadyExistException | BidAlreadyExistException ex) {
            System.out.println("Error in Singleton 2: "+ex.getMessage());
        }
    }


}

//private void initializeDataAuction() {
//  AuctionEntity auction = new AuctionEntity(Long id, Date startingTime, Date endingTime, Boolean status, BigDecimal reservePrice, BigDecimal winningBid, String productName, String productDescription, Long winningCustomerId, List<BidEntity> bidEntities);
//}
