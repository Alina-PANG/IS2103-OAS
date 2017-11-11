/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.BidEntity;
import entity.CustomerEntity;
import java.math.BigDecimal;
import java.util.List;
import util.exception.AuctionNotFoundException;
import util.exception.BidAlreadyExistException;
import util.exception.BidLessThanIncrementException;
import util.exception.BidNotFoundException;
import util.exception.CustomerNotFoundException;
import util.exception.GeneralException;

/**
 *
 * @author alina
 */
public interface BidEntityControllerRemote {

    public BidEntity retrieveById(Long id) throws BidNotFoundException;

    public BidEntity createNewBid(BidEntity bid, Long cid, Long aid) throws BidLessThanIncrementException, CustomerNotFoundException, AuctionNotFoundException, BidAlreadyExistException, GeneralException;

    public void deleteBid(Long id) throws BidNotFoundException;

    public void createSnipingBid(int duration, BidEntity bid, Long cid, Long aid, BigDecimal maxPrice) throws GeneralException, CustomerNotFoundException, AuctionNotFoundException;

    public List<BidEntity> viewAllWinningBid(CustomerEntity customer) throws GeneralException;

    public BidEntity setAddressForWinningBid(Long addressid, Long bidid) throws GeneralException, BidNotFoundException;

    public List<BidEntity> viewMyBidsInProcess(CustomerEntity customer) throws GeneralException;
}
