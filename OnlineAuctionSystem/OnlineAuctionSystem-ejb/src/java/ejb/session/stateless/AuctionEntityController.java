/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.AuctionEntity;
import entity.BidEntity;
import entity.CustomerEntity;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;
import util.enumeration.StatusEnum;
import util.exception.AuctionAlreadyExistException;
import util.exception.GeneralException;
import util.exception.AuctionNotFoundException;
import util.exception.BidAlreadyExistException;
import util.exception.BidNotFoundException;

/**
 *
 * @author alina
 */
@Stateless
@Local(AuctionEntityControllerLocal.class)
@Remote(AuctionEntityControllerRemote.class)
public class AuctionEntityController implements AuctionEntityControllerRemote, AuctionEntityControllerLocal {

    @EJB
    private CustomerEntityControllerLocal customerEntityController;

    @EJB
    private BidEntityControllerLocal bidEntityController;

    @PersistenceContext(unitName = "OnlineAuctionSystem-ejbPU")
    private EntityManager em;

    /**
     *
     * @param ae
     * @return
     * @throws AuctionAlreadyExistException
     * @throws GeneralException
     */
    @Override
    public AuctionEntity createNewAuction(AuctionEntity ae) throws AuctionAlreadyExistException, GeneralException {
        try {
            em.persist(ae);
            em.flush();
            em.refresh(ae);

            return ae;
        } catch (PersistenceException ex) {
            if (ex.getCause() != null
                    && ex.getCause().getCause() != null
                    && ex.getCause().getCause().getClass().getSimpleName().equals("MySQLIntegrityConstraintViolationException")) {
                throw new AuctionAlreadyExistException("Auction with same identification number already exist!");
            } else {
                throw new GeneralException("An unexpected error has occurred: " + ex.getMessage());
            }
        } catch (ConstraintViolationException ex) {
            if (ex.getCause() != null
                    && ex.getCause().getCause() != null
                    && ex.getCause().getCause().getClass().getSimpleName().equals("MySQLIntegrityConstraintViolationException")) {
                throw new AuctionAlreadyExistException("Auction with same identification number already exist!");
            } else {
                throw new GeneralException("Start and End Date and time cannot be later than today!");
            }
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws util.exception.AuctionNotFoundException
     * @throws GeneralException
     */
    @Override
    public AuctionEntity retrieveAuctionById(Long id) throws AuctionNotFoundException {
        // retrieve the ae
        AuctionEntity ae = em.find(AuctionEntity.class, id);

        // check and throw exception
        if (ae == null) {
            throw new AuctionNotFoundException("Auction with id = " + id + " does not exist!");
        } else {
            return ae;
        }
    }

    @Override
    public List<AuctionEntity> retrieveAuctionByProductName(String name) throws AuctionNotFoundException {
        // retrieve ae
        Query query = em.createQuery("SELECT s FROM AuctionEntity s WHERE LOWER(s.productName) LIKE :name");
        query.setParameter("name", name);

        try {
            return (List<AuctionEntity>) query.getResultList();
        } catch (NoResultException ex) {
            throw new AuctionNotFoundException("Auction with product name like " + name + " is not found!");
        }
    }

    /**
     *
     * @param newAuction
     * @return
     * @throws AuctionNotFoundException
     * @throws GeneralException
     */
    @Override
    public AuctionEntity updateAuction(AuctionEntity newAuction) throws AuctionNotFoundException, GeneralException, AuctionAlreadyExistException {
        AuctionEntity oldAuction = retrieveAuctionById(newAuction.getId());

        oldAuction.setStartingTime(newAuction.getStartingTime());
        oldAuction.setEndingTime(newAuction.getEndingTime());
        oldAuction.setStatus(newAuction.getStatus());
        oldAuction.setReservePrice(newAuction.getReservePrice());
        // oldAuction.setWinningBidId(newAuction.getWinningBidId());
        //oldAuction.setProductCode(newAuction.getProductCode());
        oldAuction.setProductName(newAuction.getProductName());
        oldAuction.setProductDescription(newAuction.getProductDescription());

        try {
            em.flush();
            em.refresh(oldAuction);
        } catch (PersistenceException ex) {
            if (ex.getCause() != null
                    && ex.getCause().getCause() != null
                    && ex.getCause().getCause().getClass().getSimpleName().equals("MySQLIntegrityConstraintViolationException")) {
                throw new AuctionAlreadyExistException("Auction with same identification number already exist!");
            } else {
                throw new GeneralException("An unexpected error has occurred: " + ex.getMessage());
            }
        }

        return oldAuction;
    }

    /**
     *
     * @param id
     * @throws AuctionNotFoundException
     * @throws GeneralException
     */
    @Override
    public boolean deleteAuction(Long id) throws AuctionNotFoundException, GeneralException {
        AuctionEntity ae = retrieveAuctionById(id);

        if (ae.getCustomerEntities() != null && ae.getCustomerEntities().size() != 0 && ae.getStatus().equals(StatusEnum.ACTIVE)) {
            // disable the ae
            ae.setStatus(StatusEnum.DISABLED);

            // refund and delete the bid
            List<BidEntity> bidList = ae.getBidEntities();
            for (BidEntity bid : bidList) {
                CustomerEntity c = bid.getCustomerEntity();
                c.setCreditBalance(c.getCreditBalance().add(bid.getAmount()));
                try {
                    bidEntityController.deleteBid(bid.getId());
                } catch (BidNotFoundException ex) {
                }
            }
            return false;
        } else {
            em.remove(ae);
            return true;
        }
    }

    public List<AuctionEntity> viewAllAuction() throws GeneralException {
        Query query = em.createQuery("SELECT al FROM AuctionEntity al");

        try {
            return (List<AuctionEntity>) query.getResultList();
        } catch (NoResultException ex) {
            throw new GeneralException("No auction listing exists!");
        }
    }

    @Override
    public List<AuctionEntity> viewNoWinningAuction() throws GeneralException {
        Query query = em.createQuery("(SELECT al FROM AuctionEntity al, BidEntity b WHERE b.amount >= al.reservePrice GROUP BY al.id HAVING COUNT(b) == 0) MINUS (SELECT al.id GROUP BY al.id HAVING COUNT(al.bidEntities) == 0)");
        try {
            return (List<AuctionEntity>) query.getResultList();
        } catch (NoResultException ex) {
            throw new GeneralException("No auction listing exists!");
        }
    }
    
    @Override
    public List<BidEntity> viewBidEntity(Long aid) throws AuctionNotFoundException{
    AuctionEntity ae = retrieveAuctionById(aid);
        
        return ae.getBidEntities();
    }
    
    @Override
    public List<AuctionEntity> viewAvailableAuctionEntity() throws GeneralException
    {
        Query query = em.createQuery("SELECT al FROM AuctionEntity al,WHERE al.status LIKE status").setParameter("status",StatusEnum.ACTIVE);
        try {
            return (List<AuctionEntity>) query.getResultList();
        } catch (NoResultException ex) {
            throw new GeneralException("No auction listing exists!");
        }
    }
    
    @Override
    public AuctionEntity retrieveAvailabeAuctionById(Long productid) throws AuctionNotFoundException
    {
        Query query = em.createQuery("SELECT ae FROM AuctionEntity ae WHERE ae.id LIKE aeid AND ae.status LIKE status")
                .setParameter("aeid",productid).setParameter("status",StatusEnum.ACTIVE);
        return (AuctionEntity)query.getSingleResult();
    }
    
    
    
    @Override
    public BidEntity getCurrentWinningBidEntity (Long productid) throws AuctionNotFoundException
    {
        List<BidEntity> bidlist = viewBidEntity(productid);
        if(bidlist!=null)
        {
            BidEntity highestbid= new BidEntity();
            for(BidEntity bid:bidlist)
            {
                if(bid.getAmount().compareTo(highestbid.getAmount())==1)
                    highestbid=bid;
            }
           return highestbid;
        }
        else 
            return null;
        
    }
    
    @Override
    public Double getCurrentBidIncremental(BigDecimal currentprice)
    {
        Double incremental =0.00;
        if(currentprice.compareTo(BigDecimal.valueOf(0.00))==1
                &&currentprice.compareTo(BigDecimal.valueOf(0.99))==-1)
        {
            incremental = 0.05;
        }
        else if(currentprice.compareTo(BigDecimal.valueOf(5.00))==-1)
        {
            incremental = 0.25;
        }
        else if(currentprice.compareTo(BigDecimal.valueOf(25.00))==-1)
        {
            incremental = 0.50;
        }
        else if(currentprice.compareTo(BigDecimal.valueOf(100.00))==-1)
        {
            incremental = 1.00;
        }
        else if(currentprice.compareTo(BigDecimal.valueOf(250.00))==-1)
        {
            incremental = 2.50;
        }
        else if(currentprice.compareTo(BigDecimal.valueOf(500.00))==-1)
        {
            incremental = 5.00;
        }
        else if(currentprice.compareTo(BigDecimal.valueOf(1000.00))==-1)
        {
            incremental = 10.00;
        }
        else if(currentprice.compareTo(BigDecimal.valueOf(2500.00))==-1)
        {
            incremental = 25.00;
        }
        else if(currentprice.compareTo(BigDecimal.valueOf(5000.00))==-1)
        {
            incremental = 50.00;
        }
        else
        {
            incremental = 100.00;
        }
        
        return incremental;
    }
    
    
    
    @Override
    public BidEntity placeNewBid(Long productid,CustomerEntity customer) throws AuctionNotFoundException,BidAlreadyExistException,GeneralException
    {
       
        //create new bid entity
        BidEntity newbid = new BidEntity();
        AuctionEntity auctionentity = retrieveAvailabeAuctionById(productid);
        newbid.setAuctionEntity(auctionentity);
        newbid.setCustomerEntity(customer);
        BigDecimal currentprice = getCurrentWinningBidEntity(productid).getAmount();
        BigDecimal currentincremental = BigDecimal.valueOf(getCurrentBidIncremental(getCurrentWinningBidEntity(productid).getAmount()));
        BigDecimal newprice = currentprice.add(currentincremental);
        newbid.setAmount(newprice);
        newbid = bidEntityController.createNewBid(newbid);
        
        //add customerentity and bidentity into auction entity
        auctionentity.getBidEntities().add(newbid);
        auctionentity.getCustomerEntities().add(customer);
        
        //add bid entity to customer entity
        customer.getBidEntities().add(newbid);
       
        return newbid;
    }
    
}
