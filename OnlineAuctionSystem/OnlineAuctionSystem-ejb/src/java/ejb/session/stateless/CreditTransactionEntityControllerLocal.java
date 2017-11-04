/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CreditTransactionEntity;
import entity.CustomerEntity;
import java.util.List;
import util.enumeration.TransactionTypeEnum;


/**
 *
 * @author alina
 */
public interface CreditTransactionEntityControllerLocal {

    public List<CreditTransactionEntity> viewAllCreditTransactionEntity(CustomerEntity customer);
    
    public void createNewTransaction(CustomerEntity customer, Long id, Integer num, TransactionTypeEnum type);
    
}
