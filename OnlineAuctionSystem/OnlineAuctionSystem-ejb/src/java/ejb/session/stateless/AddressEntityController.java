/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.AddressEntity;
import entity.CustomerEntity;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;
import util.exception.AddressAlreadyExistsException;
import util.exception.AddressNotFoundException;
import util.exception.AuctionAlreadyExistException;
import util.exception.GeneralException;

/**
 *
 * @author alina
 */
@Stateless
@Local(AddressEntityControllerLocal.class)
@Remote(AddressEntityControllerRemote.class)
public class AddressEntityController implements AddressEntityControllerRemote, AddressEntityControllerLocal {

    @PersistenceContext(unitName = "OnlineAuctionSystem-ejbPU")
    private EntityManager em;

    public AddressEntityController() {
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public AddressEntity createAddress(AddressEntity address) throws GeneralException {
        try {
            em.persist(address);
            em.flush();
            em.refresh(address);

            return address;
        } catch (PersistenceException ex) {
            throw new GeneralException("This address has been created before!");

        } catch(ConstraintViolationException ex3){
            throw new GeneralException("Constraint has been violated! There is at least one value does not fulfill requirement!");
        }catch (Exception ex2) {
            throw new GeneralException(ex2.getMessage());
        }
    }

    @Override
    public List<AddressEntity> viewAllAddress(CustomerEntity customer) {
        //same as view all transaction entity where customer entity is customer that passed in
        Query query = em.createQuery("SELECT a FROM AddressEntity a WHERE a.customerEntity.email LIKE :cust").setParameter("cust", customer.getEmail());

        return query.getResultList();
    }

    //public void updateAddress
    @Override
    public AddressEntity getAddressById(Long addressid) {
        AddressEntity address = em.find(AddressEntity.class, addressid);
        return address;

    }

    public boolean deleteAddress(Long id) throws AddressNotFoundException {
        AddressEntity address = getAddressById(id);
        if (address == null) {
            throw new AddressNotFoundException("This address does not exist!");
        }

        if (address.getBidEntities() != null && address.getBidEntities().size() != 0) {
            address.setIsDisabled(true);

            return false;
        } else {
            em.remove(address);
            return true;
        }
    }

    @Override
    public AddressEntity updateAddressLine(Long aid, String line) throws AddressNotFoundException, GeneralException {
        AddressEntity ae = getAddressById(aid);
        ae.setAddressLine(line);
        try{
        em.flush();
        em.refresh(ae);
        return ae;
        } catch (PersistenceException ex) {
            throw new GeneralException("An unexpected error has occurred: " + ex.getMessage());

        } catch(ConstraintViolationException ex3){
            throw new GeneralException("Constraint has been violated! There is at least one value does not fulfill requirement!");
        }catch (Exception ex2) {
            throw new GeneralException("An unexpected error has occured: " + ex2.getMessage());
        }

    }

    @Override
    public AddressEntity updateAddressCode(Long aid, String code) throws AddressNotFoundException, GeneralException {
        AddressEntity ae = getAddressById(aid);
        ae.setPostCode(code);
        try{
        em.flush();
        em.refresh(ae);
        return ae;
        } catch (PersistenceException ex) {
            throw new GeneralException("An unexpected error has occurred: " + ex.getMessage());

        } catch(ConstraintViolationException ex3){
            throw new GeneralException("Constraint has been violated! There is at least one value does not fulfill requirement!");
        }catch (Exception ex2) {
            throw new GeneralException("An unexpected error has occured: " + ex2.getMessage());
        }
    }

}
