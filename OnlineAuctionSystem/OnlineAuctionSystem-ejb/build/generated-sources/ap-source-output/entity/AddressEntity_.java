package entity;

import entity.BidEntity;
import entity.CustomerEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-20T20:43:43")
@StaticMetamodel(AddressEntity.class)
public class AddressEntity_ { 

    public static volatile SingularAttribute<AddressEntity, CustomerEntity> customerEntity;
    public static volatile SingularAttribute<AddressEntity, String> postCode;
    public static volatile SingularAttribute<AddressEntity, Long> id;
    public static volatile SingularAttribute<AddressEntity, Boolean> isDisabled;
    public static volatile ListAttribute<AddressEntity, BidEntity> bidEntities;
    public static volatile SingularAttribute<AddressEntity, String> addressLine;

}