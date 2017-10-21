package entity;

import entity.AddressEntity;
import entity.BidEntity;
import entity.CreditTransactionEntity;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import util.enumeration.CustomerTypeEnum;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-20T20:43:43")
@StaticMetamodel(CustomerEntity.class)
public class CustomerEntity_ { 

    public static volatile ListAttribute<CustomerEntity, AddressEntity> addressEntities;
    public static volatile SingularAttribute<CustomerEntity, BigDecimal> maxWillingAmount;
    public static volatile SingularAttribute<CustomerEntity, String> lastName;
    public static volatile SingularAttribute<CustomerEntity, BigDecimal> oneTimeHighestBid;
    public static volatile ListAttribute<CustomerEntity, BidEntity> bidEntities;
    public static volatile SingularAttribute<CustomerEntity, BigDecimal> creditBalance;
    public static volatile ListAttribute<CustomerEntity, CreditTransactionEntity> creditTransactionEntities;
    public static volatile SingularAttribute<CustomerEntity, String> firstName;
    public static volatile SingularAttribute<CustomerEntity, String> password;
    public static volatile SingularAttribute<CustomerEntity, CustomerTypeEnum> customerTypeEnum;
    public static volatile SingularAttribute<CustomerEntity, String> contactNumber;
    public static volatile SingularAttribute<CustomerEntity, Long> id;
    public static volatile SingularAttribute<CustomerEntity, String> email;
    public static volatile SingularAttribute<CustomerEntity, String> username;

}