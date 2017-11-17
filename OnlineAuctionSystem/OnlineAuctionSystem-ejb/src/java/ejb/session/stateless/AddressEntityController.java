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
import javax.persistence.Query;
import util.exception.AddressAlreadyExistsException;
import util.exception.AddressNotFoundException;

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
    public AddressEntity createAddress(AddressEntity address) throws AddressAlreadyExistsException
    {
        em.persist(address);
        em.flush();
        em.refresh(address);
        
        return address;
    }
    
    
    @Override
    public List<AddressEntity> viewAllAddress(CustomerEntity customer)
    {
        //same as view all transaction entity where customer entity is customer that passed in
        Query query = em.createQuery("SELECT a FROM AddressEntity a WHERE a.customerEntity.email LIKE :cust").setParameter("cust",customer.getEmail());
        
        return query.getResultList();
    }
    
    //public void updateAddress

    
    @Override
    public AddressEntity getAddressById(Long addressid)
    {
        AddressEntity address = em.find(AddressEntity.class,addressid);
        return address;
                
    }
    
    public boolean deleteAddress(Long id) throws AddressNotFoundException {
        AddressEntity address = getAddressById(id);
        if(address==null)
        {
            throw new AddressNotFoundException("This address does not exist!");
        }
        
        if(address.getBidEntities() != null && address.getBidEntities().size() != 0){
            address.setIsDisabled(true);
            
            return false;
        }else{
            em.remove(address);
            return true;
        }
    }
    
    @Override
    public AddressEntity updateAddressLine(Long aid, String line) throws AddressNotFoundException{
        AddressEntity ae= getAddressById(aid);
        ae.setAddressLine(line);
        em.flush();
        em.refresh(ae);
        return ae;
    }
    
    @Override
    public AddressEntity updateAddressCode(Long aid,String code) throws AddressNotFoundException{
        AddressEntity ae= getAddressById(aid);
        ae.setPostCode(code);
        em.flush();
        em.refresh(ae);
        return ae;
    }

}
