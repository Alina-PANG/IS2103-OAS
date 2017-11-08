/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author alina
 */
@Entity
public class ProxyBiddingEntity extends BidEntity implements Serializable {

    @Column(precision = 18, scale = 4)
    private BigDecimal maxAmount;

    public ProxyBiddingEntity() {
    }

    public ProxyBiddingEntity(BigDecimal maxAmount, BigDecimal amount, AuctionEntity auctionEntity, CustomerEntity customerEntity, AddressEntity addressEntities) {
        super(amount, auctionEntity, customerEntity, addressEntities);
        this.maxAmount = maxAmount;
    }
    
    /**
     * @return the maxAmount
     */
    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    /**
     * @param maxAmount the maxAmount to set
     */
    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }
    
}
