/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.ws;

import ejb.session.stateless.AuctionEntityControllerLocal;
import ejb.session.stateless.CustomerEntityControllerLocal;
import entity.AuctionEntity;
import entity.CustomerEntity;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import util.exception.AuctionNotFoundException;
import util.exception.CustomerAlreadyExistException;
import util.exception.CustomerNotFoundException;
import util.exception.DuplicateException;
import util.exception.GeneralException;
import util.exception.IncorrectPasswordException;

/**
 *
 * @author alina
 */
@WebService(serviceName = "ProxyWebService")
@Stateless()
public class ProxyWebService {

    @EJB
    private AuctionEntityControllerLocal auctionEntityController;

    @EJB
    private CustomerEntityControllerLocal customerEntityController;

    /**
     * This is a sample web service operation
     * @param c
     * @return 
     * @throws util.exception.CustomerAlreadyExistException
     * @throws util.exception.GeneralException
     */
    @WebMethod(operationName = "createNewCustomerEntity")
    public CustomerEntity createNewCustomerEntity(@WebParam(name = "c") CustomerEntity c) throws CustomerAlreadyExistException, GeneralException {
        System.out.println("******* [OAS Web Service] Remote Register");
        CustomerEntity pc = customerEntityController.createNewCustomerEntity(c);
        return c;
    }

  
    
    /**
     *
     * @param username
     * @param password
     * @return
     * @throws CustomerNotFoundException
     * @throws IncorrectPasswordException
     * @throws DuplicateException
     */
    @WebMethod(operationName = "customerLogin")
    public CustomerEntity customerLogin(@WebParam(name = "username") String username, @WebParam(name = "password") String password) throws CustomerNotFoundException, IncorrectPasswordException, DuplicateException {
        System.out.println("******* [OAS Web Service] Remote Login");
        return customerEntityController.customerLogin(username, password);
    }
    
    @WebMethod(operationName = "viewAuctionListDetails")
    public AuctionEntity viewAuctionListDetails(@WebParam(name = "id") Long id) throws AuctionNotFoundException {
        System.out.println("******* [OAS Web Service] View Auction List Details");
        return auctionEntityController.retrieveAuctionById(id);
    }
    
    @WebMethod(operationName = "viewCreditBalance")
    public BigDecimal viewCreditBalance(@WebParam(name = "id") Long id)  throws CustomerNotFoundException, GeneralException{
        System.out.println("******* [OAS Web Service] View Credit Balance");
        return customerEntityController.retrieveCustomerById(id).getCreditBalance();
    }
    
}
