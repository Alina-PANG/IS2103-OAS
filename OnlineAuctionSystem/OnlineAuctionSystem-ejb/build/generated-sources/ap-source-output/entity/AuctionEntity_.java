package entity;

import entity.BidEntity;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-20T20:43:43")
@StaticMetamodel(AuctionEntity.class)
public class AuctionEntity_ { 

    public static volatile SingularAttribute<AuctionEntity, Long> winningCustomerId;
    public static volatile SingularAttribute<AuctionEntity, BigDecimal> reservePrice;
    public static volatile SingularAttribute<AuctionEntity, BigDecimal> winningBid;
    public static volatile SingularAttribute<AuctionEntity, Date> startingTime;
    public static volatile SingularAttribute<AuctionEntity, Date> endingTime;
    public static volatile SingularAttribute<AuctionEntity, Long> id;
    public static volatile ListAttribute<AuctionEntity, BidEntity> bidEntities;
    public static volatile SingularAttribute<AuctionEntity, String> productName;
    public static volatile SingularAttribute<AuctionEntity, String> productDescription;
    public static volatile SingularAttribute<AuctionEntity, Boolean> status;

}