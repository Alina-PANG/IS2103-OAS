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
import util.enumeration.TransactionTypeEnum;
import util.exception.CustomerNotFoundException;
import util.exception.GeneralException;

/**
 *
 * @author alina
 */
public interface CreditTransactionEntityControllerRemote {

    public List<CreditTransactionEntity> viewAllCreditTransactionEntity(CustomerEntity customer);

    public void createNewTransaction(Long cid, Long id, Integer num, TransactionTypeEnum type) throws GeneralException, CustomerNotFoundException;

    public void createNewTransaction(Long cid, TransactionTypeEnum type, BigDecimal amount) throws GeneralException, CustomerNotFoundException;
}
