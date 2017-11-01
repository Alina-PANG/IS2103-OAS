/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.AuctionEntity;
import entity.BidEntity;
import java.util.List;
import util.exception.AuctionAlreadyExistException;
import util.exception.AuctionNotFoundException;
import util.exception.DuplicateException;
import util.exception.GeneralException;



/**
 *
 * @author alina
 */
public interface AuctionEntityControllerRemote {
  
    public boolean deleteAuction(Long id) throws AuctionNotFoundException, GeneralException;

    public AuctionEntity updateAuction(AuctionEntity newAuction) throws AuctionNotFoundException, GeneralException, AuctionAlreadyExistException;

    public List<AuctionEntity> retrieveAuctionByProductName(String name) throws AuctionNotFoundException;

    public AuctionEntity retrieveAuctionById(Long id) throws AuctionNotFoundException;

    public AuctionEntity createNewAuction(AuctionEntity ae) throws AuctionAlreadyExistException, GeneralException;

    public List<AuctionEntity> viewAllAuction() throws GeneralException;

    public List<AuctionEntity> viewNoWinningAuction() throws GeneralException;

    public List<BidEntity> viewBidEntity(Long aid) throws AuctionNotFoundException;

}
