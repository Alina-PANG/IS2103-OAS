/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.AddressEntity;
import entity.BidEntity;
import entity.CustomerEntity;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
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
    private AddressEntityControllerLocal addressEntityControllerLocal;

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
    
    @Override
    public List<BidEntity> viewAllWinningBid(CustomerEntity customer) throws GeneralException
    {
        Query query = em.createQuery("SELECT be FROM BidEntity be WHERE be.customerEntity LIKE cust AND be.isWinningBid LIKE true")
                .setParameter("cust",customer).setParameter("true",true);
        return query.getResultList();
        
    }
  
    public BidEntity setAddressForWinningBid(Long addressid, Long bidid) throws GeneralException,BidNotFoundException
    {
        BidEntity bid = retrieveById(bidid);
        AddressEntity address = addressEntityControllerLocal.getAddressById(addressid);
        bid.setAddressEntity(address);
        return bid;
    }
}

