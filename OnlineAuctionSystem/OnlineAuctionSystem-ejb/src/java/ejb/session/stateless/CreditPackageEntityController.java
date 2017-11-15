/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CreditPackageEntity;
import entity.CustomerEntity;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.exception.CreditPackageAlreadyExistException;
import util.exception.CreditPackageNotFoundException;
import util.exception.GeneralException;

/**
 *
 * @author alina
 */
@Stateless
@Local(CreditPackageEntityControllerLocal.class)
@Remote(CreditPackageEntityControllerRemote.class)
public class CreditPackageEntityController implements CreditPackageEntityControllerRemote, CreditPackageEntityControllerLocal {
    
    @PersistenceContext(unitName = "OnlineAuctionSystem-ejbPU")
    private EntityManager em;

    /**
     *
     * @param cp
     * @return
     * @throws CreditPackageAlreadyExistException
     * @throws GeneralException
     */
    @Override
    public CreditPackageEntity createNewCreditPackage(CreditPackageEntity cp) throws CreditPackageAlreadyExistException, GeneralException {
        try {
            em.persist(cp);
            em.flush();
            em.refresh(cp);
            
            return cp;
            
        } catch (PersistenceException ex) {
            if (ex.getCause() != null
                    && ex.getCause().getCause() != null
                    && ex.getCause().getCause().getClass().getSimpleName().equals("MySQLIntegrityConstraintViolationException")) {
                throw new CreditPackageAlreadyExistException("Credit Package with same name has already exist!");
            } else {
                throw new GeneralException("An unexpected error has occurred: " + ex.getMessage());
            }
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws CreditPackageNotFoundException
     */
    @Override
    public CreditPackageEntity retrieveCreditPackageById(Long id) throws CreditPackageNotFoundException {
        CreditPackageEntity cp = em.find(CreditPackageEntity.class, id);
        
        if (cp == null) {
            throw new CreditPackageNotFoundException("Credit Package with id = " + id + " does not exist!");
        } else {
            return cp;
        }
    }
    
    @Override
    public List<CreditPackageEntity> retrieveCreditPackageByName(String name) throws CreditPackageNotFoundException {
        Query query = em.createQuery("SELECT cp FROM CreditPackageEntity cp WHERE LOWER(cp.name) LIKE LOWER(:name)");

        query.setParameter("name", '%'+name+'%');
        
        try {
            return (List<CreditPackageEntity>) query.getResultList();
        } catch (NoResultException ex) {
            throw new CreditPackageNotFoundException("There is no credit package with name " + name + " exists!");
        }        
    }
    
    @Override
    public CreditPackageEntity updateCreditPackage(CreditPackageEntity newCp) throws CreditPackageNotFoundException, GeneralException, CreditPackageAlreadyExistException {
        CreditPackageEntity cp = retrieveCreditPackageById(newCp.getId());
        
        cp.setName(newCp.getName());
        cp.setPrice(newCp.getPrice());
        cp.setValue(newCp.getValue());
        cp.setIsDisabled(newCp.getIsDisabled());
        try {
            em.flush();
            em.refresh(cp);
            
            return cp;
        } catch (PersistenceException ex) {
            if (ex.getCause() != null
                    && ex.getCause().getCause() != null
                    && ex.getCause().getCause().getClass().getSimpleName().equals("MySQLIntegrityConstraintViolationException")) {
                throw new CreditPackageAlreadyExistException("Credit Package with same name has already exist!");
            } else {
                throw new GeneralException("An unexpected error has occurred: " + ex.getMessage());
            }
        }
    }
    
    @Override
    public List<CreditPackageEntity> viewAllCreditPackage() throws GeneralException {
        try {
            Query query = em.createQuery("SELECT cp FROM CreditPackageEntity cp");
            
            return (List<CreditPackageEntity>) query.getResultList();
        } catch (NoResultException ex) {
            throw new GeneralException("No credit package exists!");
        }
    }
    
    @Override
    // if disabled, return false; if removed successfully, return true
    public boolean deleteCreditPackage(Long id) throws CreditPackageNotFoundException {
        CreditPackageEntity cp = retrieveCreditPackageById(id);
        
        if(cp.getCustomerEntities() != null && cp.getCustomerEntities().size() != 0){
            cp.setIsDisabled(true);
            return false;
        }else{
            em.remove(cp);
            return true;
        }
    }
    
    @Override
    public void addCustomerToCreditPackage(Long creditpackageid, CustomerEntity customer) throws CreditPackageNotFoundException
    {
       CreditPackageEntity creditpackage = retrieveCreditPackageById(creditpackageid);
       creditpackage.getCustomerEntities().add(customer);
    }
}
