package entity;

import entity.CreditPackageEntity;
import entity.CustomerEntity;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import util.enumeration.TransactionTypeEnum;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-20T20:43:43")
@StaticMetamodel(CreditTransactionEntity.class)
public class CreditTransactionEntity_ { 

    public static volatile SingularAttribute<CreditTransactionEntity, BigDecimal> totalValue;
    public static volatile SingularAttribute<CreditTransactionEntity, CustomerEntity> customerEntity;
    public static volatile SingularAttribute<CreditTransactionEntity, TransactionTypeEnum> transactionTypeEnum;
    public static volatile SingularAttribute<CreditTransactionEntity, Integer> unitOfPurchase;
    public static volatile SingularAttribute<CreditTransactionEntity, Long> id;
    public static volatile SingularAttribute<CreditTransactionEntity, CreditPackageEntity> creditPackageEntity;

}