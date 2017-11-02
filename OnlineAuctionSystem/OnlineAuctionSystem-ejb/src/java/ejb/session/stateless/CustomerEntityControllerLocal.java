/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CustomerEntity;
import util.exception.CustomerAlreadyExistsException;
import util.exception.CustomerNotFoundException;
import util.exception.CustomerPinChangeException;
import util.exception.GeneralException;


/**
 *
 * @author alina
 */
public interface CustomerEntityControllerLocal {

    public CustomerEntity persistCustomerEntity(CustomerEntity customer) throws CustomerAlreadyExistsException, GeneralException;

    public CustomerEntity retrieveCustomerByEmail(String email) throws CustomerNotFoundException;

    public void changePassword(CustomerEntity customer, String oldpassword, String newpassword) throws CustomerPinChangeException;
    
}
