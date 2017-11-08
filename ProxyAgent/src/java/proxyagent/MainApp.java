/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxyagent;

import ejb.session.stateless.AuctionEntityControllerRemote;
import ejb.session.stateless.BidEntityControllerRemote;
import ejb.session.stateless.CustomerEntityControllerRemote;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;
import util.enumeration.CustomerTypeEnum;
import util.exception.CustomerAlreadyExistException;
import util.exception.GeneralException;
import ws.client.CustomerAlreadyExistException_Exception;
import ws.client.CustomerEntity;
import ws.client.CustomerNotFoundException;
import ws.client.CustomerNotFoundException_Exception;
import ws.client.GeneralException_Exception;
import ws.client.IncorrectPasswordException_Exception;

/**
 *
 * @author alina
 */
public class MainApp {

    private CustomerEntityControllerRemote customerEntityController;
    private BidEntityControllerRemote bidEntityController;
    private AuctionEntityControllerRemote auctionEntityController;

    private CustomerEntity currentCustomerEntity;

    public MainApp() {
    }

    public MainApp(CustomerEntityControllerRemote customerEntityController, BidEntityControllerRemote bidEntityController, AuctionEntityControllerRemote auctionEntityController) {
        this.customerEntityController = customerEntityController;
        this.bidEntityController = bidEntityController;
        this.auctionEntityController = auctionEntityController;
    }


}
