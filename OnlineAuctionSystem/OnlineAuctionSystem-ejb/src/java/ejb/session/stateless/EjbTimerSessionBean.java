/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.AuctionEntity;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.NoSuchObjectLocalException;
import javax.ejb.Remote;
import javax.ejb.Schedule;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import util.exception.GeneralException;

/**
 *
 * @author alina
 */
@Stateless
@Local(EjbTimerSessionBeanLocal.class)
@Remote(EjbTimerSessionBeanRemote.class)
public class EjbTimerSessionBean implements EjbTimerSessionBeanRemote, EjbTimerSessionBeanLocal {

    @EJB
    private AuctionEntityControllerLocal auctionEntityController;
    @Resource
    private SessionContext sessionContext;

    public EjbTimerSessionBean() {
    }

    @Override
    public void createTimers() {
        TimerService timerService = sessionContext.getTimerService();

        timerService.createTimer(60000, 60000, "createTimer1minInterval");
    }

    @Override
    public void cancelTimers() {
        TimerService timerService = sessionContext.getTimerService();
        Collection<Timer> timers = timerService.getTimers();

        for (Timer timer : timers) {
            if (timer.getInfo() != null) {
                if (timer.getInfo().toString().equals("createTimer1minInterval")) {
                    try {
                        timer.cancel();
                        System.out.println("********** EjbTimerSession.cancelTimers(): " + timer.getInfo().toString());
                    } catch (NoSuchObjectLocalException ex) {
                        System.err.println("********** EjbTimerSession.cancelTimers(): ERROR CANCELING, timer already cancelled or does not exist");
                    }
                }
            }
        }
    }

    @Schedule(hour = "*", minute = "*/1", second = "*", info = "scheduleEvery1min")
    public void automaticTimer() {
        System.out.println("********** EjbTimerSession.automaticTimer(): scheduleEvery1min");
        try{
            List<AuctionEntity> ls = auctionEntityController.viewAllAuction();
            for(AuctionEntity ae: ls){
                if(ae.checkStatus())
                    System.out.println("Close auction id = "+ae.getId());
            }
        } catch(GeneralException ex){
    }
        
    }
}
