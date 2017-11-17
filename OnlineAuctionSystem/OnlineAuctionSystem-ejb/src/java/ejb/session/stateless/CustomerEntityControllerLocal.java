/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CustomerEntity;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.NoResultException;
import util.exception.CustomerAlreadyExistException;
import util.exception.CustomerNotFoundException;
import util.exception.DuplicateException;
import util.exception.GeneralException;
import util.exception.IncorrectPasswordException;

/**
 *
 * @author alina
 */
public interface CustomerEntityControllerLocal {

    public CustomerEntity retrieveCustomerByEmail(String email) throws CustomerNotFoundException, NoResultException;

    public CustomerEntity createNewCustomerEntity(CustomerEntity customer) throws CustomerAlreadyExistException, GeneralException;

    public CustomerEntity customerLogin(String username, String password) throws CustomerNotFoundException, IncorrectPasswordException;

    public CustomerEntity retrieveCustomerById(Long id) throws CustomerNotFoundException, GeneralException;

    public CustomerEntity retrieveCustomerByIdentificationNumber(String number) throws CustomerNotFoundException;

    public CustomerEntity retrieveCustomerByUsername(String username) throws CustomerNotFoundException;

    public CustomerEntity changePassword(String currentPw, String newPw, Long id) throws IncorrectPasswordException, CustomerNotFoundException, GeneralException;

    public CustomerEntity updateCustomer(CustomerEntity newCustomer) throws CustomerNotFoundException, GeneralException;

    public void deleteCustomer(Long id) throws CustomerNotFoundException, GeneralException;

    public List<CustomerEntity> viewAllCustomer() throws GeneralException;

    public CustomerEntity updateCreditBalance(Long customerid, BigDecimal newamount) throws CustomerNotFoundException, GeneralException;
    
    

}
