/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.AuctionEntity;
import entity.BidEntity;
import entity.CustomerEntity;
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

    
           public List<AuctionEntity> viewAllAuctionNoWinning() throws GeneralException{
                 Query query = em.createQuery("SELECT al FROM AuctionEntity al");
               
              try {
            return (List<AuctionEntity>) query.getResultList();
        } catch (NoResultException ex) {
            throw new GeneralException("No auction listing exists!");
        }
           }
    /**
     *
     * @return @throws GeneralException
     *
     * @Override public List<BidEntity> viewAllBids() throws GeneralException {
     * Query query = em.createQuery("SELECT * FROM BidEntity s"); try { return
     * (List<BidEntity>) query.getResultList(); } catch (Exception ex) { throw
     * new GeneralException("An unexpected exception happens: " +
     * ex.getMessage()); } } /* public BidEntity getWinningBid(Long aid) throws
     * AuctionNotFoundException, GeneralException, BidNotFoundException {
     * AuctionEntity a = retrieveAuctionById(aid);
     *
     * BidEntity bOld = bidEntityController.retrieveById(a.getWinningBidId());
     * return bOld; }
     *
     * public boolean isWinningBid(Long bid, Long aid, Long cid) throws
     * AuctionNotFoundException, GeneralException, BidNotFoundException {
     * AuctionEntity a = retrieveAuctionById(aid); BidEntity bNew =
     * bidEntityController.retrieveById(bid); try { BidEntity bOld =
     * bidEntityController.retrieveById(a.getWinningBidId()); BigDecimal
     * oldAmount = bOld.getAmount(); BigDecimal newAmount = bNew.getAmount(); if
     * (newAmount.compareTo(oldAmount) > 0) { a.setWinningBidId(bid);
     * a.setWinningCustomerId(cid); bOld.setIsWinningBid(Boolean.FALSE);
     * bNew.setIsWinningBid(Boolean.TRUE); return true; } else { return false; }
     * } catch (BidNotFoundException ex) { a.setWinningBidId(bid);
     * a.setWinningCustomerId(cid); bNew.setIsWinningBid(Boolean.TRUE); return
     * false; } }
     */
    /*
    @Override
    public void switchStatus(Long id, boolean status) throws AuctionNotFoundException, GeneralException {
        AuctionEntity ae;

        ae = retrieveAuctionById(id);
        ae.setStatus(status);
    }
     */
}
