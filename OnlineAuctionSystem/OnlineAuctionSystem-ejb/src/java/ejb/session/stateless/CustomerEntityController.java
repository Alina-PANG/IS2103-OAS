/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CustomerEntity;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import util.exception.CustomerAlreadyExistsException;
import util.exception.CustomerNotFoundException;
import util.exception.CustomerPinChangeException;
import util.exception.GeneralException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.exception.CustomerAlreadyExistException;
import util.exception.DuplicateException;
import util.exception.GeneralException;
import util.exception.IncorrectPasswordException;
import util.exception.CustomerNotFoundException;
import util.exception.CustomerNotFoundException;

/**
 *
 * @author alina
 */
@Stateless
@Local(CustomerEntityControllerLocal.class)
@Remote(CustomerEntityControllerRemote.class)
public class CustomerEntityController implements CustomerEntityControllerRemote, CustomerEntityControllerLocal {

    @PersistenceContext(unitName = "OnlineAuctionSystem-ejbPU")
    private EntityManager em;

    
    @Override
    public CustomerEntity retrieveCustomerByEmail(String email) throws CustomerNotFoundException
    {
        try
        {
            CustomerEntity customer = em.find(CustomerEntity.class, email);//usage of jpa queru language
            
            return customer;
        }
        catch(PersistenceException ex)
        {
            throw new CustomerNotFoundException("This email "+email+" haven't registered an account!");
        }
        
    }
    
    public void changePassword(CustomerEntity customer, String oldpassword, String newpassword)throws CustomerPinChangeException
    {
        if(oldpassword.equals(customer.getPassword()))
        {
            customer.setPassword(newpassword);
        }
        else
        {
            throw new CustomerPinChangeException("Current password is invalid!");
        }
        
    }
    
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public CustomerEntity createNewCustomerEntity(CustomerEntity customer) throws CustomerAlreadyExistException, GeneralException {
        try {
            em.persist(customer);
            em.flush();
            em.refresh(customer);

            return customer;
        } catch (PersistenceException ex) {
            if (ex.getCause() != null
                    && ex.getCause().getCause() != null
                    && ex.getCause().getCause().getClass().getSimpleName().equals("MySQLIntegrityConstraintViolationException")) {
                throw new CustomerAlreadyExistException("Customer with same identification number already exist!");
            } else {
                throw new GeneralException("An unexpected error has occurred: " + ex.getMessage());
            }
        }
    }

    @Override
    public CustomerEntity customerLogin(String username, String password) throws CustomerNotFoundException, IncorrectPasswordException, DuplicateException {
        CustomerEntity customer = retrieveCustomerByUsername(username);
        if (customer.getPassword().equals(password)) {
            return customer;
        } else {
            throw new IncorrectPasswordException("Incorrect Password!");
        }
    }

    @Override
    public CustomerEntity retrieveCustomerById(Long id) throws CustomerNotFoundException, GeneralException {
        // retrieve the customer
        CustomerEntity customer = em.find(CustomerEntity.class, id);

        // check and throw exception
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with id = " + id + " does not exist!");
        } else {
            return customer;
        }
    }

    @Override
    public CustomerEntity retrieveCustomerByIdentificationNumber(String number) throws CustomerNotFoundException {
        // retrieve customer
        Query query = em.createQuery("SELECT s FROM CustomerEntity s WHERE s.identificationNumber = :num");
        query.setParameter("num", number);

        try {
            return (CustomerEntity) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new CustomerNotFoundException("Customer with identification number = " + number + " is not found!");
        }
    }
    @Override
    public CustomerEntity retrieveCustomerByUsername(String username) throws CustomerNotFoundException {
        // retrieve customer
        Query query = em.createQuery("SELECT s FROM CustomerEntity s WHERE s.username = :username");
        query.setParameter("username", username);

        try {
            return (CustomerEntity) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new CustomerNotFoundException("Customer with username = " + username + " is not found!");
        }
    }


    // manager can only update the firstname, lastname, accessRight, identificationNumber. others are cretical information, and must be hidden from the manager.
    @Override
    public CustomerEntity updateCustomer(CustomerEntity newCustomer) throws CustomerNotFoundException, GeneralException {
        CustomerEntity oldCustomer = retrieveCustomerById(newCustomer.getId());

        oldCustomer.setFirstName(newCustomer.getFirstName());
        // TO DO: add in all other attributes setter and getters

        try {
            em.flush();
            em.refresh(oldCustomer);
        } catch (PersistenceException ex) {
            if (ex.getCause() != null
                    && ex.getCause().getCause() != null
                    && ex.getCause().getCause().getClass().getSimpleName().equals("MySQLIntegrityConstraintViolationException")) {
                throw new GeneralException("Customer with same identification number already exist!");
            } else {
                throw new GeneralException("An unexpected error has occurred: " + ex.getMessage());
            }
        }

        return oldCustomer;
    }

    @Override
    public void deleteCustomer(Long id) throws CustomerNotFoundException, GeneralException {
        CustomerEntity customer = retrieveCustomerById(id);

        em.remove(customer);
    }

    @Override
    public List<CustomerEntity> viewAllCustomer() throws GeneralException {
        Query query = em.createQuery("SELECT s FROM CustomerEntity s");
        try {
            return (List<CustomerEntity>) query.getResultList();
        } catch (Exception ex) {
            throw new GeneralException("An unexpected exception happens: " + ex.getMessage());
        }
    }
}
