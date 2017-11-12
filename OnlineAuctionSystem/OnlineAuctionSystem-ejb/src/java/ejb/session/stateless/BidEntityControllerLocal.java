/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.BidEntity;
import entity.CustomerEntity;
import entity.ProxyBiddingEntity;
import java.math.BigDecimal;
import java.util.List;
import util.exception.AuctionClosedException;
import util.exception.AuctionNotFoundException;
import util.exception.AuctionNotOpenException;
import util.exception.BidAlreadyExistException;
import util.exception.BidLessThanIncrementException;
import util.exception.BidNotFoundException;
import util.exception.CustomerNotFoundException;
import util.exception.GeneralException;
import util.exception.NotEnoughCreditException;

/**
 *
 * @author alina
 */
public interface BidEntityControllerLocal {

    public BidEntity retrieveById(Long id) throws BidNotFoundException;

    public void deleteBid(Long id) throws BidNotFoundException;

    public List<BidEntity> viewAllWinningBid(CustomerEntity customer) throws GeneralException;

    public BidEntity setAddressForWinningBid(Long addressid, Long bidid) throws GeneralException, BidNotFoundException;

    public List<BidEntity> viewMyBidsInProcess(CustomerEntity customer) throws GeneralException;

    public BidEntity createNewBid(BidEntity bid, Long cid, Long aid) throws NotEnoughCreditException, AuctionClosedException, AuctionNotOpenException, BidLessThanIncrementException, CustomerNotFoundException, AuctionNotFoundException, BidAlreadyExistException, GeneralException;

   

    public void createProxyBid(ProxyBiddingEntity bid, Long cid, Long aid) throws NotEnoughCreditException, AuctionClosedException, AuctionNotOpenException, BidAlreadyExistException, BidLessThanIncrementException, GeneralException, CustomerNotFoundException, AuctionNotFoundException;

    public void createSnipingBid(int duration, BidEntity bid, Long cid, Long aid, BigDecimal maxPrice) throws AuctionClosedException, GeneralException, CustomerNotFoundException, AuctionNotFoundException;

    public BidEntity viewMyBidInAuction(Long aid, Long cid) throws BidNotFoundException;

}
