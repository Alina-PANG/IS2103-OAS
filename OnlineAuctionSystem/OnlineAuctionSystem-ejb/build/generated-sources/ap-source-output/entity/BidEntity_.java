package entity;

import entity.AuctionEntity;
import entity.CustomerEntity;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-20T20:43:43")
@StaticMetamodel(BidEntity.class)
public class BidEntity_ { 

    public static volatile ListAttribute<BidEntity, List> addressEntities;
    public static volatile SingularAttribute<BidEntity, CustomerEntity> customerEntity;
    public static volatile SingularAttribute<BidEntity, BigDecimal> amount;
    public static volatile SingularAttribute<BidEntity, AuctionEntity> auctionEntity;
    public static volatile SingularAttribute<BidEntity, Long> id;
    public static volatile SingularAttribute<BidEntity, Boolean> isWinningBid;

}