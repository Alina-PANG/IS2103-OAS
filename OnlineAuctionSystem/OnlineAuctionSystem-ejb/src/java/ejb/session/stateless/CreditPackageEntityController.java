/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CreditPackageEntity;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.exception.CreditPackageAlreadyExistException;
import util.exception.CreditPackageNotFoundException;
import util.exception.DuplicateException;
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

    public CreditPackageEntity retrieveCreditPackageById(Long id) throws CreditPackageNotFoundException {
        CreditPackageEntity cp = em.find(CreditPackageEntity.class, id);

        if (cp == null) {
            throw new CreditPackageNotFoundException("Credit Package with id = " + id + " does not exist!");
        } else {
            return cp;
        }
    }

    public CreditPackageEntity retrieveCreditPackageByName(String name) throws CreditPackageNotFoundException, DuplicateException {
        Query query = em.createQuery("SELECT cp FROM CREDITPACKAGEENTITY cp WHERE :name = cp.name");
        query.setParameter("name", name);
        
        try{
            return (CreditPackageEntity) query.getSingleResult();
        } catch(NoResultException ex){
            throw new CreditPackageNotFoundException("There is no credit package with name "+ name +" exists!");
        } catch(NonUniqueResultException ex){
            throw new DuplicateException("More than one credit packages with name "+name+" exists -> error!");
        }
    }

    //public CreditPackageEntity updateCreditPackage(String name) throws CreditPackageNotFoundException, GeneralException, DuplicateException {
      //  CreditPackageEntity cp = retrieveCreditPackageByName(name);
        
    //}

}
