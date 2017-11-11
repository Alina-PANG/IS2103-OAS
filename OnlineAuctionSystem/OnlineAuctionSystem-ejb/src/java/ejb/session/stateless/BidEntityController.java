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
import entity.ProxyBiddingEntity;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javafx.util.Pair;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
        BigDecimal more = bid.getAmount();

        if (a.getEndingTime().after(new Date())) {
            throw new AuctionClosedException("The auction has already been closed, no more bid is allowed!Ï");
        }

        BigDecimal currentPrice;
        BidEntity currentWinningBid = auctionEntityController.getCurrentWinningBidEntity(aid);
        if (currentWinningBid == null) {
            currentPrice = new BigDecimal(0);
        } else {
            currentPrice = currentWinningBid.getAmount();
        }

        BigDecimal minPrice = currentPrice.add(auctionEntityController.getCurrentBidIncremental(currentPrice));
        if (bid.getAmount().compareTo(minPrice) < 0) {
            throw new BidLessThanIncrementException("The bid is less than the increment amount!");
        }

        try {
            boolean flag = true;

            try {
                System.out.println("remove previous bid");
                Query query = em.createQuery("SELECT b FROM BidEntity b WHERE b.customerEntity.id = :cid AND b.auctionEntity.id = :aid");
                query.setParameter("cid", cid);
                query.setParameter("aid", aid);
                BidEntity b = (BidEntity) query.getSingleResult();
                more.subtract(b.getAmount());
                c.getBidEntities().remove(b);
                a.getBidEntities().remove(b);
                flag = false;
                em.remove(b);
                ctController.createNewTransaction(cid, TransactionTypeEnum.REFUND, b.getAmount());
            } catch (NoResultException ex) {
                System.out.println("No previous bid.");
            }

            if (c.getCreditBalance().compareTo(more) < 0) {
                throw new NotEnoughCreditException("Not Enough Balance! (Your Balance: " + c.getCreditBalance() + "; Required: " + more + ")");
            }

            c.getBidEntities().add(bid);
            c.setCreditBalance(c.getCreditBalance().subtract(more));
            a.getBidEntities().add(bid);
            a.setWinningBidId(new Long(0));
            if (flag) {
                c.getAuctionEntities().add(a);
                a.getCustomerEntities().add(c);
            }
            bid.setCustomerEntity(c);
            bid.setAuctionEntity(a);

            em.persist(bid);
            em.flush();
            em.refresh(bid);
            
            ctController.createNewTransaction(cid, TransactionTypeEnum.BIDDING, bid.getAmount());
            checkProxyBid(a);

            return bid;
        } catch (PersistenceException ex) {
            if (ex.getCause() != null
                    && ex.getCause().getCause() != null
                    && ex.getCause().getCause().getClass().getSimpleName().equals("MySQLIntegrityConstraintViolationException")) {
                throw new BidAlreadyExistException("Bid with same identification number already exist!");
            } else {
                throw new GeneralException("An unexpected error has occurred: " + ex.getMessage());
            }
        }
    }

    private void checkProxyBid(AuctionEntity a) throws AuctionClosedException, AuctionNotOpenException, NotEnoughCreditException, BidAlreadyExistException, BidLessThanIncrementException, GeneralException, CustomerNotFoundException, AuctionNotFoundException {
        List<BidEntity> list = a.getBidEntities();
        for (BidEntity b : list) {
            if (b instanceof ProxyBiddingEntity) {
                createProxyBid((ProxyBiddingEntity) b, b.getCustomerEntity().getId(), a.getId());
            }
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

    public void deleteBid(Long id) throws BidNotFoundException {
        BidEntity bid = retrieveById(id);

        em.remove(bid);
    }

    @Override
    public List<BidEntity> viewAllWinningBid(CustomerEntity customer) throws GeneralException {
        Query query = em.createQuery("SELECT b FROM BidEntity b, AuctionEntity a, CustomerEntity c WHERE a MEMBER OF c.auctionEntities AND b MEMBER OF a.bidEntities AND b MEMBER OF c.bidEntities AND b.id = a.winningBidId AND c.id = :id");
        query.setParameter(":id", customer.getId());

        try {
            return (List<BidEntity>) query.getResultList();
        } catch (Exception ex) {
            throw new GeneralException("[System] " + ex.getMessage());
        }

    }

    public BidEntity setAddressForWinningBid(Long addressid, Long bidid) throws GeneralException, BidNotFoundException {
        BidEntity bid = retrieveById(bidid);
        AddressEntity address = addressEntityControllerLocal.getAddressById(addressid);
        bid.setAddressEntity(address);
        return bid;
    }

    //customer has placed bids but the auction item has not reached the ending time yet
    public List<BidEntity> viewMyBidsInProcess(CustomerEntity customer) throws GeneralException {
        Query query = em.createQuery("SELECT be FROM BidEntity be WHERE be.customerEntity LIKE cust AND be.auctionEntity.status LIKE active")
                .setParameter("cust", customer).setParameter("active", StatusEnum.ACTIVE);

        return query.getResultList();
    }

    public void createSnipingBid(int duration, BidEntity bid, Long cid, Long aid, BigDecimal maxPrice) throws GeneralException, CustomerNotFoundException, AuctionNotFoundException {
        CustomerEntity c = customerEntityController.retrieveCustomerById(cid);
        AuctionEntity a = auctionEntityController.retrieveAuctionById(aid);
        bid.setAuctionEntity(a);
        bid.setCustomerEntity(c);

        TimerService timerService = sessionContext.getTimerService();
        Calendar cal = Calendar.getInstance();
        cal.setTime(bid.getAuctionEntity().getEndingTime());
        duration = 0 - duration;
        cal.add(Calendar.MINUTE, duration);

        Timer timer = timerService.createSingleActionTimer(cal.getTime(), new TimerConfig(new Pair<BidEntity, BigDecimal>(bid, maxPrice), true));
    }

    @Timeout
    private void putSnipingBid(Timer timer) {
        BigDecimal maxPrice = (BigDecimal) ((Pair) timer.getInfo()).getValue();
        BidEntity bid = (BidEntity) ((Pair) timer.getInfo()).getKey();

        CustomerEntity c = bid.getCustomerEntity();
        AuctionEntity a = bid.getAuctionEntity();

        try {
            BigDecimal currentPrice = auctionEntityController.getCurrentWinningBidEntity(a.getId()).getAmount();
            BigDecimal minPrice = currentPrice.add(auctionEntityController.getCurrentBidIncremental(currentPrice));

            if (maxPrice.compareTo(minPrice) >= 0) {
                bid.setAmount(minPrice);
                try {
                    createNewBid(bid, c.getId(), a.getId());
                } catch (AuctionClosedException | NotEnoughCreditException | AuctionNotOpenException | BidLessThanIncrementException | CustomerNotFoundException | AuctionNotFoundException | BidAlreadyExistException | GeneralException ex) {
                    System.err.println("An error has occured: " + ex.getMessage());
                }
            }
        } catch (AuctionNotFoundException ex) {
            System.err.println("An errro has occured: " + ex.getMessage());
        }
    }

    @Override
    public void createProxyBid(ProxyBiddingEntity bid, Long cid, Long aid) throws NotEnoughCreditException, AuctionClosedException, AuctionNotOpenException, BidAlreadyExistException, BidLessThanIncrementException, GeneralException, CustomerNotFoundException, AuctionNotFoundException {
        try {
            createNewBid(bid, cid, aid);
        } catch (BidAlreadyExistException ex) {
        }

        BidEntity b = auctionEntityController.getCurrentWinningBidEntity(aid);
        BigDecimal minPrice = auctionEntityController.getCurrentBidIncremental(b.getAmount()).add(b.getAmount());

        if (bid.getMaxAmount().compareTo(minPrice) >= 0) {
            BidEntity newBid = new BidEntity(minPrice);
            createNewBid(newBid, cid, aid);
        }
    }
}
