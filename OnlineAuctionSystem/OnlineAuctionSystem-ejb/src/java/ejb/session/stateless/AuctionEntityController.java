/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.AuctionEntity;
import entity.BidEntity;
import entity.CreditTransactionEntity;
import entity.CustomerEntity;
import java.math.BigDecimal;
import java.util.Date;
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
import util.enumeration.StatusEnum;
import util.enumeration.TransactionTypeEnum;
import util.exception.AuctionAlreadyExistException;
import util.exception.GeneralException;
import util.exception.AuctionNotFoundException;
import util.exception.BidAlreadyExistException;
import util.exception.CustomerNotFoundException;

/**
 *
 * @author alina
 */
@Stateless
@Local(AuctionEntityControllerLocal.class)
@Remote(AuctionEntityControllerRemote.class)
public class AuctionEntityController implements AuctionEntityControllerRemote, AuctionEntityControllerLocal {

    @EJB
    private CreditTransactionEntityControllerLocal creditTransactionEntityController;

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

            if (ae.getStartingTime().after(ae.getEndingTime())) {
                throw new GeneralException("starting time is after ending time!");
            }
            if (ae.getStartingTime().before(new Date())) {
                throw new GeneralException("starting date cannot be before the current time!");
            }
            if (ae.getEndingTime().before(new Date())) {
                throw new GeneralException("ending date cannot be before the current time!");
            }

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
        }
    }


    public BidEntity closeAuction(AuctionEntity ae) {
        if (ae.getBidEntities().size() != 0) {
            try {
                Query query = em.createQuery("SELECT b FROM BidEntity b WHERE b.auctionEntity.id = :aid AND b.auctionEntity.reservePrice < b.amount AND b.amount = (SELECT MAX(b.amount) FROM BidEntity b WHERE b.auctionEntity.id = :aid)");
                query.setParameter("aid", ae.getId());
                // if highest bid is above the reserve price
                BidEntity bid = (BidEntity) query.getSingleResult();
                ae.setWinningBidId(bid.getId());

                List<BidEntity> bidList = ae.getBidEntities();
                for (BidEntity b : bidList) {
                    if (!b.getId().equals(bid.getId())) {
                        CustomerEntity c = b.getCustomerEntity();
                        c.addCreditBalance(b.getAmount());
                        CreditTransactionEntity ct = new CreditTransactionEntity(b.getAmount(), TransactionTypeEnum.REFUND);
                        c.getCreditTransactionEntities().add(ct);
                        ct.setCustomerEntity(c);
                        em.remove(b);
                    }
                }

                return bid;
            } catch (NoResultException ex) {
                System.out.println("Auction " + ae.getId() + " closed without having a winner.");
            }
        }
        return null;
    }

    private BidEntity closingFindWinningBid(AuctionEntity ae) {
        Query query = em.createQuery("SELECT b FROM BidEntity b, AuctionEntity al WHERE b MEMBER OF al.bidEntities AND al.id = :id AND b.amount = (SELECT MAX(b.amount) FROM b WHERE b MEMBER OF al)");
        query.setParameter(":id", ae.getId());

        List<BidEntity> list = (List<BidEntity>) query.getResultList();

        if (list == null || list.size() == 0) {
            return null;
        }

        return list.get(0);
    }

    @Override
    public void assignWinningBid(Long aid, Long bid) throws AuctionNotFoundException {
        AuctionEntity ae = retrieveAuctionById(aid);
        ae.setWinningBidId(bid);
    }

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
        query.setParameter("name", "%" + name + "%");

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
        if (newAuction.getStartingTime().after(newAuction.getEndingTime())) {
            throw new GeneralException("starting time is after ending time!");
        }

        AuctionEntity oldAuction = retrieveAuctionById(newAuction.getId());

        oldAuction.setStatus(newAuction.getStatus());
        oldAuction.setReservePrice(newAuction.getReservePrice());
        //oldAuction.setWinningBidId(newAuction.getWinningBidId());
        //oldAuction.setProductCode(newAuction.getProductCode());
        oldAuction.setProductName(newAuction.getProductName());
        oldAuction.setProductDescription(newAuction.getProductDescription());
        if (!newAuction.getStartingTime().equals(oldAuction.getStartingTime())) {
            if (newAuction.getStartingTime().before(new Date())) {
                throw new GeneralException("starting date cannot be before the current time!");
            }
            oldAuction.setStartingTime(newAuction.getStartingTime());
        }
        if (!newAuction.getEndingTime().equals(oldAuction.getEndingTime())) {
            if (newAuction.getEndingTime().before(new Date())) {
                throw new GeneralException("ending date cannot be before the current time!");
            }
            oldAuction.setEndingTime(newAuction.getEndingTime());
        }

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
                try{
                creditTransactionEntityController.createNewTransaction(c.getId(), TransactionTypeEnum.REFUND, bid.getAmount());
                } catch(CustomerNotFoundException ex){
                    System.out.println("Create transaction entity: Customer "+c.getId()+" not found!");
                }
            }
            return false;
        } else {
            em.remove(ae);
            return true;
        }
    }

    @Override
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
        Query query = em.createQuery("SELECT al FROM AuctionEntity al WHERE al.status = :status AND al.reservePrice > (SELECT MAX(b.amount) FROM BidEntity b WHERE b MEMBER OF al.bidEntities)");
        query.setParameter("status", StatusEnum.CLOSED);
        try {
            return (List<AuctionEntity>) query.getResultList();
        } catch (NoResultException ex) {
            throw new GeneralException("No auction listing exists!");
        }
    }

    /**
     *
     * @param cid
     * @return
     * @throws GeneralException
     */
    @Override
    public List<AuctionEntity> viewWonAuction(Long cid) throws GeneralException {
        Query query = em.createQuery("SELECT al FROM AuctionEntity al, BidEntity b WHERE al.winningBidId = b.id AND b.customerEntity.id = :cid");
        query.setParameter(":cid", cid);
        try {
            return (List<AuctionEntity>) query.getResultList();
        } catch (Exception ex) {
            throw new GeneralException(ex.getMessage());
        }

    }

    @Override
    public List<BidEntity> viewBidEntity(Long aid) throws AuctionNotFoundException {
        AuctionEntity ae = retrieveAuctionById(aid);
        List<BidEntity> list = ae.getBidEntities();

        for (BidEntity b : list) {
            b.getAuctionEntity();
        }

        return list;
    }

    @Override
    public List<AuctionEntity> viewAvailableAuctionEntity() throws GeneralException {
        Query query = em.createQuery("SELECT al FROM AuctionEntity al WHERE al.status =:status").setParameter("status", StatusEnum.ACTIVE);
        try {
            return (List<AuctionEntity>) query.getResultList();
        } catch (NoResultException ex) {
            throw new GeneralException("No auction listing exists!");
        }
    }

    @Override
    public AuctionEntity retrieveAvailabeAuctionById(Long productid) throws AuctionNotFoundException {
        Query query = em.createQuery("SELECT ae FROM AuctionEntity ae WHERE ae.id =:aeid AND ae.status =:status")
                .setParameter("aeid", productid).setParameter("status", StatusEnum.ACTIVE);
        if((AuctionEntity) query.getSingleResult()!=null)
        return (AuctionEntity) query.getSingleResult();
        else
            throw new AuctionNotFoundException("This product id is not valid!");
    }

    @Override
    public BidEntity getCurrentWinningBidEntity(Long productid) throws AuctionNotFoundException {
        
        List<BidEntity> bidlist = viewBidEntity(productid);
        if (!bidlist.isEmpty()) {
            BidEntity highestbid = new BidEntity(new BigDecimal("0.00"));
            for (BidEntity bid : bidlist) {
                if (bid.getAmount().compareTo(highestbid.getAmount()) > 0) {
                    highestbid = bid;
                }
            }
            return highestbid;
        } else {
            return null;
        }
    }

    @Override
    public BigDecimal getCurrentBidIncremental(BigDecimal currentprice) {
        if (currentprice == null) {
            currentprice = new BigDecimal(0);
        }
        BigDecimal incremental = new BigDecimal(0);
        if (currentprice.compareTo(BigDecimal.valueOf(0.00)) == 1
                && currentprice.compareTo(BigDecimal.valueOf(0.99)) == -1) {
            incremental = new BigDecimal(0.05);
        } else if (currentprice.compareTo(BigDecimal.valueOf(5.00)) == -1) {
            incremental = new BigDecimal(0.25);
        } else if (currentprice.compareTo(BigDecimal.valueOf(25.00)) == -1) {
            incremental = new BigDecimal(0.50);
        } else if (currentprice.compareTo(BigDecimal.valueOf(100.00)) == -1) {
            incremental = new BigDecimal(1.00);
        } else if (currentprice.compareTo(BigDecimal.valueOf(250.00)) == -1) {
            incremental = new BigDecimal(2.50);
        } else if (currentprice.compareTo(BigDecimal.valueOf(500.00)) == -1) {
            incremental = new BigDecimal(5.00);
        } else if (currentprice.compareTo(BigDecimal.valueOf(1000.00)) == -1) {
            incremental = new BigDecimal(10.00);
        } else if (currentprice.compareTo(BigDecimal.valueOf(2500.00)) == -1) {
            incremental = new BigDecimal(25.00);
        } else if (currentprice.compareTo(BigDecimal.valueOf(5000.00)) == -1) {
            incremental = new BigDecimal(50.00);
        } else {
            incremental = new BigDecimal(100.00);
        }

        return incremental;
    }


}
