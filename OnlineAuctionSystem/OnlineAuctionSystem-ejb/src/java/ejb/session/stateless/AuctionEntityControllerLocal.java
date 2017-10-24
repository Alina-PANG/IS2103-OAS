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
import util.exception.GeneralException;


/**
 *
 * @author alina
 */
public interface AuctionEntityControllerLocal {

    public AuctionEntity persist(AuctionEntity ae) throws AuctionAlreadyExistException, GeneralException;

    public List<BidEntity> viewAllBids() throws GeneralException;

    public void switchStatus(Long id, boolean status);
    
}
