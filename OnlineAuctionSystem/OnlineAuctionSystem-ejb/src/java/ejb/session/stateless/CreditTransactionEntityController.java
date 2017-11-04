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
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.enumeration.TransactionTypeEnum;
import util.exception.CreditPackageNotFoundException;

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
    
    private CreditPackageEntityControllerLocal creditPackageEntityControllerLocal;

    //comment for future modification
    @Override
    public void createNewTransaction(CustomerEntity customer, Long id, Integer num, TransactionTypeEnum type)
    {
        try{
        CreditTransactionEntity credittransaction = new CreditTransactionEntity();
        credittransaction.setCustomerEntity(customer);
        credittransaction.setTransactionTypeEnum(type);//
        credittransaction.setUnitOfPurchase(num);//how many package has the customer purchased
        credittransaction.setCreditPackageEntity(creditPackageEntityControllerLocal.retrieveCreditPackageById(id));
        credittransaction.setTotalValue(creditPackageEntityControllerLocal.retrieveCreditPackageById(id).getPrice().multiply(BigDecimal.valueOf(num)));
        
        em.persist(credittransaction);
        em.flush();
        em.refresh(credittransaction);
        
        }
        catch(CreditPackageNotFoundException ex)
        {
            System.out.println(ex.getMessage()+" !");
        }
    }


    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
   
    @Override
    public List<CreditTransactionEntity> viewAllCreditTransactionEntity(CustomerEntity customer)
    {
        Query query = em.createQuery("SELECT c FROM CreditTransactionEntity c WHERE c.customerEntity LIKE :cust").setParameter("cust",customer);
        
        return query.getResultList();

        //find all credit transactionentity list provided with customer entity class
       
    }


}
