/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.AuctionEntity;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Schedule;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TimerService;
import util.enumeration.StatusEnum;
import util.exception.GeneralException;

/**
 *
 * @author alina
 */
@Stateless
@Local(TimerSessionBeanLocal.class)
@Remote(TimerSessionBeanRemote.class)
public class TimerSessionBean implements TimerSessionBeanRemote, TimerSessionBeanLocal {

    @EJB
    private AuctionEntityControllerLocal auctionEntityController;

    @Resource
    private SessionContext sessionContext;

    public TimerSessionBean() {
    }

    @Schedule(hour = "*", minute = "*", second = "30", info = "everyMinute")
    public void automaticTimer() throws GeneralException {
        System.out.println("******* [Timer: Every minute] *******");
        List<AuctionEntity> list = auctionEntityController.viewAllAuction();

        for (AuctionEntity ae : list) {
            if(ae.getStartingTime().after(new Date())){
                ae.setStatus(StatusEnum.PENDING);
                System.out.println("Auction " + ae.getId() + " is pending.");
            }
            else if (ae.getStartingTime().before(new Date()) && ae.getEndingTime().after(new Date()) && ae.getStatus() == StatusEnum.PENDING) {
                ae.setStatus(StatusEnum.ACTIVE);
                System.out.println("Auction " + ae.getId() + " has been opened.");
            } else if (ae.getEndingTime().before(new Date()) && ae.getStatus() == StatusEnum.ACTIVE){
                auctionEntityController.closeAuction(ae);
                ae.setStatus(StatusEnum.CLOSED);
                System.out.println("Auction " + ae.getId() + " has been closed.");
            }
        }
    }

}
