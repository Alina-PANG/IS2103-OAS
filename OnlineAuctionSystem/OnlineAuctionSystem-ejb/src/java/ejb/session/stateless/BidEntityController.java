/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.BidEntity;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import util.exception.GeneralException;
import util.exception.BidAlreadyExistException;
import util.exception.BidNotFoundException;

/**
 *
 * @author alina
 */
@Stateless
@Local(BidEntityControllerLocal.class)
@Remote(BidEntityControllerRemote.class)
public class BidEntityController implements BidEntityControllerRemote, BidEntityControllerLocal {

    @PersistenceContext(unitName = "OnlineAuctionSystem-ejbPU")
    private EntityManager em;

    @Override
    public BidEntity createNewBid(BidEntity bid) throws BidAlreadyExistException, GeneralException {
        try {
            em.persist(bid);
            em.flush();
            em.refresh(bid);

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
    public void assignWinningBid(Long id) throws BidNotFoundException {
        BidEntity bid = retrieveById(id);
        
        bid.setIsWinningBid(true);
    }
    
  
}

