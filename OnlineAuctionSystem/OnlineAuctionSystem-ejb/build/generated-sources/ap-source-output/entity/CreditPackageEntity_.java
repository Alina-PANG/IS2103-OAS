package entity;

import entity.CreditTransactionEntity;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-20T20:43:43")
@StaticMetamodel(CreditPackageEntity.class)
public class CreditPackageEntity_ { 

    public static volatile SingularAttribute<CreditPackageEntity, BigDecimal> price;
    public static volatile SingularAttribute<CreditPackageEntity, String> name;
    public static volatile SingularAttribute<CreditPackageEntity, Long> id;
    public static volatile SingularAttribute<CreditPackageEntity, BigDecimal> value;
    public static volatile ListAttribute<CreditPackageEntity, CreditTransactionEntity> creditTransactionEntities;

}