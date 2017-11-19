/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.AddressEntity;
import entity.AuctionEntity;
import entity.BidEntity;
import entity.CustomerEntity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.enumeration.StatusEnum;
import util.enumeration.TransactionTypeEnum;
import util.exception.AuctionClosedException;
import util.exception.AuctionNotFoundException;
import util.exception.AuctionNotOpenException;
import util.exception.GeneralException;
import util.exception.BidAlreadyExistException;
import util.exception.BidLessThanIncrementException;
import util.exception.BidNotFoundException;
import util.exception.CustomerNotFoundException;
import util.exception.NotEnoughCreditException;

/**
 *
 * @author alina
 */
@Stateless
@Local(BidEntityControllerLocal.class)
@Remote(BidEntityControllerRemote.class)
public class BidEntityController implements BidEntityControllerRemote, BidEntityControllerLocal {

    @EJB
    private AddressEntityControllerLocal addressEntityControllerLocal;

    @EJB
    private CustomerEntityControllerLocal customerEntityController;

    @EJB
    private AuctionEntityControllerLocal auctionEntityController;

    @EJB
    private CreditTransactionEntityControllerLocal ctController;

    @PersistenceContext(unitName = "OnlineAuctionSystem-ejbPU")
    private EntityManager em;

    @Resource
    private SessionContext sessionContext;

    @Override
    public BidEntity createNewBid(BidEntity bid, Long cid, Long aid) throws NotEnoughCreditException, AuctionClosedException, AuctionNotOpenException, BidLessThanIncrementException, CustomerNotFoundException, AuctionNotFoundException, BidAlreadyExistException, GeneralException {
        CustomerEntity c = customerEntityController.retrieveCustomerById(cid);
        AuctionEntity a = auctionEntityController.retrieveAuctionById(aid);
        BidEntity currentWinningBid = null;
        try {
            currentWinningBid = retrieveById(a.getWinningBidId());
        } catch (BidNotFoundException ex) {
            System.out.println("BidEntitycontroller - createNewBid - No current winning bid");
        }

        // check whether auction is open
        if (a.getEndingTime().before(new Date())) {
            throw new AuctionClosedException("The auction has already been closed, no more bid is allowed!");
        } else if (a.getStartingTime().after(new Date())) {
            throw new AuctionClosedException("The auction has not been opened yet, please wait patiently!");
        }

        // calculate the currentPrice and minPrice the new bid should be
        BigDecimal minPrice = auctionEntityController.getMinPrice(aid);
        System.out.println("Current minimum price: " + minPrice);//debug

        // If the bid is a proxyBiddingEntity
        // requirement: maxAmount > minPrice
        if (bid.getAmount().equals(new BigDecimal(-77))) {
            if (bid.getMaxAmount().compareTo(minPrice) < 0) {
                throw new BidLessThanIncrementException("The proxy bidding max price " + bid.getMaxAmount() + " is less than the increment amount " + minPrice + " !");
            }

            try {
                System.out.println("remove previous proxy bid");
                Query query = em.createQuery("SELECT b FROM BidEntity b WHERE b.customerEntity.id = :cid AND b.auctionEntity.id = :aid AND b.amount = -77");
                query.setParameter("cid", cid);
                query.setParameter("aid", aid);
                BidEntity p = (BidEntity) query.getSingleResult();
                deleteBid(p.getId());
            } catch (NoResultException ex) {
                System.out.println("No previous proxy bid");
            } catch (BidNotFoundException ex) {
                System.out.println(ex.getMessage());
            }

            bid.setCustomerEntity(c);
            bid.setAuctionEntity(a);

            em.persist(bid);
            em.flush();
            em.refresh(bid);

            c.getBidEntities().add(bid);
            a.getBidEntities().add(bid);

            createBidForProxy(new BidEntity(minPrice), cid, aid);

            executeProxyBid(a, bid, minPrice);
            return bid;
        } // A normal bid entity
        // requirement: 1. amount > minPrice
        //              2. remove previous bid
        //              3. customer credit balance > bid amount
        //              4. if customer has not bid for this particular auction before, need to add each other as bidirectiona relationship
        else {
            // bid is less than min bid
            if (bid.getAmount().compareTo(minPrice) < 0) {
                throw new BidLessThanIncrementException("The bid is less than the increment amount!");
            }

            try {
                // to show whether the customer has bid for this auction before or not
                boolean flag = true;

                // remove previous bids
                try {
                    System.out.println("remove previous bid");
                    Query query = em.createQuery("SELECT b FROM BidEntity b WHERE b.customerEntity.id = :cid AND b.auctionEntity.id = :aid AND b.amount != -77");
                    query.setParameter("cid", cid);
                    query.setParameter("aid", aid);
                    BidEntity b = (BidEntity) query.getSingleResult();

                    flag = false; // no exception throws -> have previous bid -> customer has bid for this auction before

                    ctController.createNewTransaction(cid, TransactionTypeEnum.REFUND, b.getAmount());
                    c.setCreditBalance(c.getCreditBalance().add(b.getAmount()));
                    deleteBid(b.getId());
                } catch (NoResultException ex) {
                    System.out.println("Customer " + c.getUsername() + " has no previous bid in auction " + a.getId() + "!");
                } catch (BidNotFoundException ex) {
                    System.out.println("Create New Bid- Bid Not Found");
                }

                if (c.getCreditBalance().compareTo(bid.getAmount()) < 0) {
                    throw new NotEnoughCreditException("Not Enough Balance! (Your Balance: " + c.getCreditBalance() + "; Required: " + bid.getAmount() + ")");
                }

                if (flag) {
                    c.getAuctionEntities().add(a);
                    a.getCustomerEntities().add(c);
                }
                bid.setCustomerEntity(c);
                bid.setAuctionEntity(a);

                em.persist(bid);
                em.flush();
                em.refresh(bid);

                a.setWinningBidId(bid.getId());

                c.getBidEntities().add(bid);
                c.setCreditBalance(c.getCreditBalance().subtract(bid.getAmount()));
                a.getBidEntities().add(bid);

                ctController.createNewTransaction(cid, TransactionTypeEnum.BIDDING, bid.getAmount());

                minPrice = auctionEntityController.getMinPrice(aid);
                executeProxyBid(a, bid, minPrice);
                return bid;
            } catch (PersistenceException ex) {
                throw new GeneralException("An unexpected error has occurred: " + ex.getMessage());
            }
            /*catch (Exception ex2) {
                throw new GeneralException("An unexpected error has occured: " + ex2.getMessage());
            }*/
        }
    }

    private void executeProxyBid(AuctionEntity a, BidEntity currentProxy, BigDecimal minPrice) throws AuctionClosedException, AuctionNotOpenException, NotEnoughCreditException, BidAlreadyExistException, BidLessThanIncrementException, GeneralException, CustomerNotFoundException, AuctionNotFoundException {
        Query query = em.createQuery("SELECT b FROM BidEntity b WHERE b.amount = -77 AND b.auctionEntity.id = :aid");
        query.setParameter("aid", a.getId());

        try {
            BidEntity temp2 = (BidEntity) query.getSingleResult();
            // if input is proxy bid and minPrice exceeds max amount, stop
            System.out.println("One Proxy Bid - Input is a proxy bid");
            if (currentProxy.getAmount().equals(new BigDecimal(-77))) {
                return;
            }
            // if input is a normal bid, and the proxy is the same person, stop
            System.out.println("One Proxy Bid - Input is a normal bid");
            if (currentProxy.getCustomerEntity().getId().equals(temp2.getCustomerEntity().getId())) {
                return;
            }

            createNewBid(new BidEntity(minPrice), temp2.getCustomerEntity().getId(), a.getId());
        } catch (NonUniqueResultException ex) {
            System.out.println("Two Proxy Bids");
            List<BidEntity> proxyList = (List<BidEntity>) query.getResultList();
            int index = proxyList.indexOf(currentProxy);
            index = (index + 1) % 2;

            // there will be max 2 proxyBiddingEntity exist simultaneously
            while (true) {
                if (minPrice.compareTo(proxyList.get(index).getMaxAmount()) > 0) {
                    try {
                        deleteBid(proxyList.get(index).getId());
                    } catch (BidNotFoundException ex1) {
                        System.out.println("executeProxyBid - deleteBid - BidNotFound: " + ex1.getMessage());
                    }
                    break;
                }
                createBidForProxy(new BidEntity(minPrice), proxyList.get(index).getCustomerEntity().getId(), a.getId());
                minPrice = auctionEntityController.getMinPrice(a.getId());
                index = (index + 1) % 2;
            }
        } catch (NoResultException ex2) {
            System.out.println("No Proxy Bids");
        }
        /*catch (Exception ex2) {
            throw new GeneralException("An unexpected error has occured: " + ex2.getMessage());
        }*/

    }

    private void createBidForProxy(BidEntity bid, Long cid, Long aid) throws AuctionClosedException {
        try {
            CustomerEntity c = customerEntityController.retrieveCustomerById(cid);
            AuctionEntity a = auctionEntityController.retrieveAuctionById(aid);

            // check whether auction is open
            if (a.getEndingTime().before(new Date())) {
                throw new AuctionClosedException("The auction has already been closed, no more bid is allowed!");
            } else if (a.getStartingTime().after(new Date())) {
                throw new AuctionClosedException("The auction has not been opened yet, please wait patiently!");
            }
            // remove previous bid
            try {
                System.out.println("remove previous bid");
                Query query = em.createQuery("SELECT b FROM BidEntity b WHERE b.customerEntity.id = :cid AND b.auctionEntity.id = :aid AND b.amount != -77");
                query.setParameter("cid", cid);
                query.setParameter("aid", aid);
                BidEntity b = (BidEntity) query.getSingleResult();

                ctController.createNewTransaction(cid, TransactionTypeEnum.REFUND, b.getAmount());
                c.setCreditBalance(c.getCreditBalance().add(b.getAmount()));
                deleteBid(b.getId());
            } catch (NoResultException ex) {
                System.out.println("Customer " + c.getUsername() + " has no previous bid in auction " + a.getId() + "!");
            }

            bid.setCustomerEntity(c);
            bid.setAuctionEntity(a);

            em.persist(bid);
            em.flush();
            em.refresh(bid);

            a.setWinningBidId(bid.getId());

            c.getBidEntities().add(bid);
            c.setCreditBalance(c.getCreditBalance().subtract(bid.getAmount()));
            a.getBidEntities().add(bid);

            ctController.createNewTransaction(cid, TransactionTypeEnum.BIDDING, bid.getAmount());
        } catch (Exception ex) {
            System.out.println("Exception in createBidForProxy in BidEntityController");
        }
    }

    @Override
    public BidEntity retrieveById(Long id) throws BidNotFoundException {
        BidEntity bid = em.find(BidEntity.class, id);

        if (bid == null) {
            throw new BidNotFoundException("Bid with id = " + id + " is not found!");
        } else {
            return bid;
        }
    }

    @Override
    public void deleteBid(Long id) throws BidNotFoundException {
        BidEntity bid = retrieveById(id);
        bid.getCustomerEntity().getBidEntities().remove(bid);
        bid.getAuctionEntity().getBidEntities().remove(bid);
        if (!(bid.getAddressEntity() == null)) {
            bid.getAddressEntity().getBidEntities().remove(bid);
        }

        em.remove(bid);
    }

    @Override
    public BidEntity setAddressForWinningBid(Long addressid, Long bidid) throws GeneralException, BidNotFoundException {
        BidEntity bid = retrieveById(bidid);
        AddressEntity address = addressEntityControllerLocal.getAddressById(addressid);
        bid.setAddressEntity(address);
        return bid;
    }

    //customer has placed bids but the auction item has not reached the ending time yet
    @Override
    public List<BidEntity> viewMyBidsInProcess(CustomerEntity customer) throws GeneralException {
        Query query = em.createQuery("SELECT be FROM BidEntity be WHERE be.customerEntity.id = :custId AND be.auctionEntity.status = :status");
        query.setParameter("custId", customer.getId());
        query.setParameter("status", StatusEnum.ACTIVE);

        List<BidEntity> list = query.getResultList();
        for (BidEntity bid : list) {
            bid.getAuctionEntity();
        }

        if (list == null || list.size() == 0) {
            throw new GeneralException("You have not placed any bid!");
        }

        return list;
    }

    @Override
    public BidEntity viewMyBidInAuction(Long aid, Long cid) throws BidNotFoundException {
        Query query = em.createQuery("SELECT b FROM BidEntity b WHERE b.customerEntity.id = :cid AND b.auctionEntity.id = :aid");
        query.setParameter("cid", cid);
        query.setParameter("aid", aid);

        try {
            return (BidEntity) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new BidNotFoundException("You currently have not placed any bid in auction " + aid + " !");
        }
    }
}
