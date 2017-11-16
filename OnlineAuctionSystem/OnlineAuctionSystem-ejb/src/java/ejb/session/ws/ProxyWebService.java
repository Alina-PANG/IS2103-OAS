/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.ws;

import ejb.session.stateless.AuctionEntityControllerLocal;
import ejb.session.stateless.BidEntityControllerLocal;
import ejb.session.stateless.CustomerEntityControllerLocal;
import entity.AuctionEntity;
import entity.BidEntity;
import entity.CustomerEntity;
import entity.ProxyBiddingEntity;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.enumeration.CustomerTypeEnum;
import util.exception.AuctionClosedException;
import util.exception.AuctionNotFoundException;
import util.exception.AuctionNotOpenException;
import util.exception.BidAlreadyExistException;
import util.exception.BidLessThanIncrementException;
import util.exception.BidNotFoundException;
import util.exception.CustomerAlreadyPremiumException;
import util.exception.CustomerNotFoundException;
import util.exception.CustomerNotPremiumException;
import util.exception.DuplicateException;
import util.exception.GeneralException;
import util.exception.IncorrectPasswordException;
import util.exception.NotEnoughCreditException;

/**
 *
 * @author alina
 */
@WebService(serviceName = "ProxyWebService")
@Stateless()
public class ProxyWebService {

    @EJB
    private BidEntityControllerLocal bidEntityController;

    @EJB
    private AuctionEntityControllerLocal auctionEntityController;
    @PersistenceContext(unitName = "OnlineAuctionSystem-ejbPU")
    private EntityManager em;

    @EJB
    private CustomerEntityControllerLocal customerEntityController;

    @WebMethod(operationName = "registration")
    public CustomerEntity registration(@WebParam(name = "username") String username, @WebParam(name = "password") String password) throws CustomerNotFoundException, IncorrectPasswordException, CustomerAlreadyPremiumException {
        System.out.println("******* [OAS Web Service] Registration");
        CustomerEntity c = customerEntityController.customerLogin(username, password);
        if (c.getCustomerTypeEnum().equals(CustomerTypeEnum.PREMIUM)) {
            throw new CustomerAlreadyPremiumException("Customer " + username + " is already a premium customer!");
        }
        c.setCustomerTypeEnum(CustomerTypeEnum.PREMIUM);

        return c;
    }

    /**
     *
     * @param username
     * @param password
     * @return
     * @throws CustomerNotFoundException
     * @throws IncorrectPasswordException
     * @throws DuplicateException
     */
    @WebMethod(operationName = "customerLogin")
    public CustomerEntity customerLogin(@WebParam(name = "username") String username, @WebParam(name = "password") String password) throws CustomerNotFoundException, IncorrectPasswordException, CustomerNotPremiumException {
        System.out.println("******* [OAS Web Service] Remote Login");

        CustomerEntity c = customerEntityController.customerLogin(username, password);
        //     c.getAddressEntities().forEach((each) -> {
        //        each.setCustomerEntity(null);
        //     });
        //c.getAuctionEntities().forEach((each) -> {
        //    each.setCustomerEntities(null);
        //});
        //     c.getBidEntities().forEach((each) -> {
        //         each.setCustomerEntity(null);
        //      });
        c.setAddressEntities(null);
        c.setBidEntities(null);
        c.setAuctionEntities(null);
        c.setCreditTransactionEntities(null);
        if (c.getCustomerTypeEnum() == CustomerTypeEnum.NORMAL) {
            throw new CustomerNotPremiumException("Customer " + username + " is not a premium customer!");
        }
        return c;
    }

    /*
    private void detachEntities(List<BidEntity> bids, List<AuctionEntity> auctions, List<AddressEntity> addresses) {
        for (BidEntity b : bids) {
            em.detach(b);
            b.setAddressEntity(null);
            b.setCustomerEntity(null);
            b.setAuctionEntity(null);
        }
        for (AuctionEntity a : auctions) {
            em.detach(a);
            a.setBidEntities(null);
            a.setCustomerEntities(null);
        }
        for (AddressEntity a : addresses) {
            em.detach(a);
            a.setCustomerEntity(null);
            a.setBidEntities(null);
        }
    }
     */
    @WebMethod(operationName = "viewAuctionListDetails")
    public AuctionEntity viewAuctionListDetails(@WebParam(name = "id") Long id) throws AuctionNotFoundException {
        AuctionEntity a = auctionEntityController.retrieveAuctionById(id);
        a.setBidEntities(null);
        return a;
    }

    @WebMethod(operationName = "viewAuctionListByName")
    public List<AuctionEntity> viewAuctionListByName(@WebParam(name = "productName") String productName) throws AuctionNotFoundException {
        List<AuctionEntity> aList = auctionEntityController.retrieveAuctionByProductName(productName);
        for (AuctionEntity a : aList) {
            a.setBidEntities(null);
        }
        return aList;
    }

    @WebMethod(operationName = "viewCreditBalance")
    public BigDecimal viewCreditBalance(@WebParam(name = "id") Long id) throws CustomerNotFoundException, GeneralException {
        System.out.println("******* [OAS Web Service] View Credit Balance");
        CustomerEntity c = customerEntityController.retrieveCustomerById(id);
        return c.getCreditBalance();
    }

    @WebMethod(operationName = "viewAllAuctionListings")
    public List<AuctionEntity> viewAllAuctionListings() throws GeneralException {
        System.out.println("******* [OAS Web Service] View All Auction Listings");
        List<AuctionEntity> aList = auctionEntityController.viewAllAuction();
        for (AuctionEntity a : aList) {
            a.setBidEntities(null);
            a.setCustomerEntities(null);
        }
        return aList;
    }

    @WebMethod(operationName = "viewWonAuctionListings")
    public List<AuctionEntity> viewWonAuctionListings(@WebParam(name = "id") Long cid) throws GeneralException {
        System.out.println("******* [OAS Web Service] View Won Auction Listings");
        List<AuctionEntity> aList = auctionEntityController.viewWonAuction(cid);

        for (AuctionEntity a : aList) {
            a.setBidEntities(null);
            a.setCustomerEntities(null);
        }
        return aList;
    }

    @WebMethod(operationName = "createSnippingBid")
    public void createSnippingBid(@WebParam(name = "bid") BidEntity bid, @WebParam(name = "aid") Long aid, @WebParam(name = "cid") Long cid) throws AuctionNotOpenException, AuctionClosedException, BidLessThanIncrementException, NotEnoughCreditException, CustomerNotFoundException, AuctionNotFoundException, BidAlreadyExistException, GeneralException {
        bidEntityController.createNewBid(bid, cid, aid);
    }

    @WebMethod(operationName = "createProxyBid")
    public void createProxyBid(@WebParam(name = "bid") ProxyBiddingEntity bid, @WebParam(name = "aid") Long aid, @WebParam(name = "cid") Long cid) throws AuctionClosedException, AuctionNotOpenException, BidAlreadyExistException, NotEnoughCreditException, BidLessThanIncrementException, GeneralException, CustomerNotFoundException, AuctionNotFoundException {
        bidEntityController.createProxyBid(bid, cid, aid);
    }

    @WebMethod(operationName = "viewCurrentHighestBid")
    public BidEntity viewCurrentHighestBid(@WebParam(name = "aid") Long aid) throws AuctionNotFoundException {
        BidEntity b = auctionEntityController.getCurrentWinningBidEntity(aid);
        if (b != null) {
            CustomerEntity c = b.getCustomerEntity();
            AuctionEntity a = b.getAuctionEntity();
            c.setBidEntities(null);
            a.setBidEntities(null);
            b.setAddressEntity(null);
        }

        return b;

    }

    @WebMethod(operationName = "viewMyBidInAuction")
    public BidEntity viewMyBidInAuction(@WebParam(name = "aid") Long aid, @WebParam(name = "cid") Long cid) throws BidNotFoundException {
        BidEntity b = bidEntityController.viewMyBidInAuction(aid, cid);
        CustomerEntity c = b.getCustomerEntity();
        AuctionEntity a = b.getAuctionEntity();
        c.setBidEntities(null);
        a.setBidEntities(null);
        b.setAddressEntity(null);

        return b;
    }

    @WebMethod(operationName = "viewWinningBid")
    public BidEntity viewWinningBid(@WebParam(name = "bid") Long bid) throws BidNotFoundException {
        BidEntity b = bidEntityController.retrieveById(bid);

        CustomerEntity c = b.getCustomerEntity();
        AuctionEntity a = b.getAuctionEntity();
        c.setBidEntities(null);
        a.setBidEntities(null);
        b.setAddressEntity(null);

        return b;
    }
}
