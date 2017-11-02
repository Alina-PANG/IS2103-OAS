/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CustomerEntity;
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
    public CustomerEntity persistCustomerEntity (CustomerEntity customer) throws CustomerAlreadyExistsException, GeneralException
    {
        try
        {
            em.persist(customer);
            em.flush();
            em.refresh(customer);
            
            return customer;
        }
        catch(PersistenceException ex)
        {
            if(ex.getCause()!=null
                    &&ex.getCause().getCause()!=null
                    &&ex.getCause().getCause().getClass().getSimpleName().equals("MySQLIntegrityConstraintViolationException"))
                {
                throw new CustomerAlreadyExistsException("Customer with same identification number already exist");
            }
            else
            {
                throw new GeneralException("An unexpected error has occurred: " + ex.getMessage());
            }
        }
            
        
    }

    
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
    
}
