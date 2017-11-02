/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.ws;

import ejb.session.stateless.CustomerEntityControllerLocal;
import entity.CustomerEntity;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import util.exception.CustomerAlreadyExistException;
import util.exception.GeneralException;

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
     */
    @WebMethod(operationName = "createNewCustomerEntity")
    public CustomerEntity createNewCustomerEntity(@WebParam(name = "c") CustomerEntity c) throws CustomerAlreadyExistException, GeneralException {
        return customerEntityController.createNewCustomerEntity(c);
    }
    
        @WebMethod(operationName = "createNewCustomerEntity")
    public CustomerEntity createNewCustomerEntity(@WebParam(name = "c") CustomerEntity c) throws CustomerAlreadyExistException, GeneralException {
        return customerEntityController.createNewCustomerEntity(c);
    }
}
