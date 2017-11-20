/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CreditTransactionEntity;
import entity.CustomerEntity;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;
import util.enumeration.TransactionTypeEnum;
import util.exception.CreditPackageNotFoundException;
import util.exception.CustomerNotFoundException;
import util.exception.GeneralException;

/**
 *
 * @author alina
 */
@Stateless
@Local(CreditTransactionEntityControllerLocal.class)
@Remote(CreditTransactionEntityControllerRemote.class)

public class CreditTransactionEntityController implements CreditTransactionEntityControllerRemote, CreditTransactionEntityControllerLocal {

    @PersistenceContext(unitName = "OnlineAuctionSystem-ejbPU")
    private EntityManager em;

    @EJB
    private CreditPackageEntityControllerLocal creditPackageEntityControllerLocal;
    @EJB
    private CustomerEntityControllerLocal customerEntityController;

    //comment for future modification
    @Override
    public void createNewTransaction(Long cid, Long id, Integer num, TransactionTypeEnum type) throws GeneralException, CustomerNotFoundException {
        try {
            CustomerEntity customer = customerEntityController.retrieveCustomerById(cid);

            CreditTransactionEntity credittransaction = new CreditTransactionEntity();
            credittransaction.setCustomerEntity(customer);
            credittransaction.setTransactionTypeEnum(type);//
            credittransaction.setUnitOfPurchase(num);//how many package has the customer purchased
            credittransaction.setCreditPackageEntity(creditPackageEntityControllerLocal.retrieveCreditPackageById(id));
            credittransaction.setTotalValue(creditPackageEntityControllerLocal.retrieveCreditPackageById(id).getValue().multiply(BigDecimal.valueOf(num)));

            em.persist(credittransaction);
            em.flush();
            em.refresh(credittransaction);
            
            customer.getCreditTransactionEntities().add(credittransaction);
        } catch (CreditPackageNotFoundException ex) {
            System.out.println(ex.getMessage() + " !");
        } catch (PersistenceException ex) {
            if (ex.getCause() != null
                    && ex.getCause().getCause() != null
                    && ex.getCause().getCause().getClass().getSimpleName().equals("MySQLIntegrityConstraintViolationException")) {
            } else {
                throw new GeneralException("An unexpected error has occurred: " + ex.getMessage());
            }
        } 
        catch(ConstraintViolationException ex3){
            throw new GeneralException("Constraint has been violated! There is at least one value does not fulfill requirement!");
        }catch (Exception ex2) {
            throw new GeneralException("An unexpected error has occured!");
        }
    }

    @Override
    public void createNewTransaction(Long cid, TransactionTypeEnum type, BigDecimal amount) throws GeneralException, CustomerNotFoundException {
        try {
            CreditTransactionEntity ct = new CreditTransactionEntity(amount, type);
            CustomerEntity c = customerEntityController.retrieveCustomerById(cid);
            ct.setCustomerEntity(c);

            em.persist(ct);
            em.flush();
            em.refresh(ct);
            c.getCreditTransactionEntities().add(ct);
        } catch (PersistenceException ex) {
            if (ex.getCause() != null
                    && ex.getCause().getCause() != null
                    && ex.getCause().getCause().getClass().getSimpleName().equals("MySQLIntegrityConstraintViolationException")) {
            } else {
                throw new GeneralException("An unexpected error has occurred: " + ex.getMessage());
            }
        } catch (Exception ex2) {
            throw new GeneralException("An unexpected error has occured!");
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<CreditTransactionEntity> viewAllCreditTransactionEntity(CustomerEntity customer) {
        Query query = em.createQuery("SELECT c FROM CreditTransactionEntity c WHERE c.customerEntity.id =:cust").setParameter("cust", customer.getId());

        return query.getResultList();

        //find all credit transactionentity list provided with customer entity class
    }

}
