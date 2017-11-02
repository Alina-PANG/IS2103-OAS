/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import util.enumeration.CustomerTypeEnum;

/**
 *
 * @author alina
 */
@Entity
@XmlRootElement
@XmlType(name = "customerEntity", propOrder = {
    "id",
    "firstName",
    "lastName",
    "username",
    "password",
    "contactNumber",
    "email",
    "creditBalance",
    "creditTransactionEntities",
    "bidEntities",
    "addressEntities",
    "customerTypeEnum",
    "maxWillingPrice",
    "timeDuration",
    "oneTimeHighestBid"
})
public class PremiumCustomerEntity extends CustomerEntity implements Serializable {

    @Column(precision = 18, scale = 4)
    private BigDecimal maxWillingPrice;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date timeDuration;
    @Column(precision = 18, scale = 4)
    private BigDecimal oneTimeHighestBid;

    public PremiumCustomerEntity() {
    }

    public PremiumCustomerEntity(BigDecimal maxWillingPrice, Date timeDuration, BigDecimal oneTimeHighestBid, String firstName, String lastName, String contactNumber, String email, BigDecimal creditBalance, List<CreditTransactionEntity> creditTransactionEntities, List<BidEntity> bidEntities, List<AddressEntity> addressEntities, CustomerTypeEnum customerTypeEnum) {
        super(firstName, lastName, contactNumber, email, creditBalance, creditTransactionEntities, bidEntities, addressEntities, customerTypeEnum);
        this.maxWillingPrice = maxWillingPrice;
        this.timeDuration = timeDuration;
        this.oneTimeHighestBid = oneTimeHighestBid;
    }

    
    /**
     * @return the maxWillingPrice
     */
    public BigDecimal getMaxWillingPrice() {
        return maxWillingPrice;
    }

    /**
     * @param maxWillingPrice the maxWillingPrice to set
     */
    public void setMaxWillingPrice(BigDecimal maxWillingPrice) {
        this.maxWillingPrice = maxWillingPrice;
    }

    /**
     * @return the timeDuration
     */
    public Date getTimeDuration() {
        return timeDuration;
    }

    /**
     * @param timeDuration the timeDuration to set
     */
    public void setTimeDuration(Date timeDuration) {
        this.timeDuration = timeDuration;
    }

    /**
     * @return the oneTimeHighestBid
     */
    public BigDecimal getOneTimeHighestBid() {
        return oneTimeHighestBid;
    }

    /**
     * @param oneTimeHighestBid the oneTimeHighestBid to set
     */
    public void setOneTimeHighestBid(BigDecimal oneTimeHighestBid) {
        this.oneTimeHighestBid = oneTimeHighestBid;
    }
}
