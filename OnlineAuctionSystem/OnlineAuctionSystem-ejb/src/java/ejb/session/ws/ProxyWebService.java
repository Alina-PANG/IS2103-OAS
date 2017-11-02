/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.ws;

import ejb.session.stateless.CustomerEntityControllerLocal;
import entity.CustomerEntity;
import entity.PremiumCustomerEntity;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
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
    private CustomerEntityControllerLocal customerEntityController;

    /**
     * This is a sample web service operation
     * @param c
     * @return 
     * @throws util.exception.CustomerAlreadyExistException
     * @throws util.exception.GeneralException
     */
    @WebMethod(operationName = "createNewCustomerEntity")
    public PremiumCustomerEntity createNewCustomerEntity(@WebParam(name = "c") CustomerEntity c, @WebParam(name = "price") BigDecimal price, @WebParam(name = "c") ) throws CustomerAlreadyExistException, GeneralException {
        System.out.println("******* [OAS Web Service] Remote Register");
        PremiumCustomerEntity pc = (PremiumCustomerEntity) customerEntityController.createNewCustomerEntity(c);
        pc.setMaxWillingPrice(price);
        pc.setOneTimeHighestBid(hb);
        pc.setTimeDuration(td);
        return pc;
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
    
    
    
}
