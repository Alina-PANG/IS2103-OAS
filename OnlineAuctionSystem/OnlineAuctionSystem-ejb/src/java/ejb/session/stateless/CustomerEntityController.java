/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.AddressEntity;
import entity.CustomerEntity;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;
import util.exception.CustomerAlreadyExistException;
import util.exception.GeneralException;
import util.exception.IncorrectPasswordException;
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
    public CustomerEntity retrieveCustomerByEmail(String email) throws CustomerNotFoundException, NoResultException {

        Query query;
        query = em.createQuery("SELECT c FROM CustomerEntity c WHERE c.email = :e").setParameter("e", email);

        try {
            return (CustomerEntity) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new NoResultException("This customer is not found!");
        }

    }

    @Override
    public CustomerEntity updateCreditBalance(java.lang.Long customerid, BigDecimal newamount) throws CustomerNotFoundException, GeneralException {
        CustomerEntity customer = retrieveCustomerById(customerid);
        customer.setCreditBalance(newamount);
        //em.persist(customer);
        try {
            em.flush();
            em.refresh(customer);
            return customer;
        } catch (PersistenceException ex) {
            throw new GeneralException("An unexpected persistence error has occurred!");

        } catch (Exception ex2) {
            throw new GeneralException("An unexpected error has occured!");
        }
    }

    @Override
    public CustomerEntity changePassword(String currentPw, String newPw, Long id) throws IncorrectPasswordException, CustomerNotFoundException, GeneralException {
        CustomerEntity customer = retrieveCustomerById(id);

        try {
            if (customer.getPassword().equals(currentPw)) {
                customer.setPassword(newPw);
                em.flush();
                em.refresh(customer);

                return customer;
            } else {
                throw new IncorrectPasswordException("You must enter correct old password to change your new password!");

            }
        } catch (PersistenceException ex) {
            throw new GeneralException("An unexpected persistence error has occurred");

        } catch (Exception ex2) {
            throw new GeneralException("An unexpected error has occured!");
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
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
        }catch(ConstraintViolationException ex3){
            throw new GeneralException("Constraint has been violated! There is at least one value does not fulfill requirement!");
        }
        catch (Exception ex2) {
            throw new GeneralException("An unexpected error has occured!");
        }
    }

    @Override
    public CustomerEntity customerLogin(String username, String password) throws CustomerNotFoundException, IncorrectPasswordException {
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
            throw new CustomerNotFoundException("Customer does not exist!");
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
        } catch (Exception ex2) {
            throw new GeneralException("An unexpected error has occured!");
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
            throw new GeneralException("An unexpected exception happens!");
        }
    }

    public List<AddressEntity> getAddressByCustomer(Long cid) throws NoResultException {
        Query query = em.createQuery("SELECT al FROM AddressEntity al WHERE al.customerEntity.id=:cus").setParameter("cus", cid);

        try {
            return (List<AddressEntity>) query.getResultList();
        } catch (NoResultException ex) {
            throw new NoResultException("You do not have any address!");
        }
    }

}
