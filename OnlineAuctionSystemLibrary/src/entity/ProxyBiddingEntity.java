/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author alina
 */
@Entity
@DiscriminatorValue("PROXY")
public class ProxyBiddingEntity extends BidEntity implements Serializable {

    @Column(precision = 18, scale = 4)
    private BigDecimal maxAmount;

    public ProxyBiddingEntity() {
        super(new BigDecimal(-77));
    }

    public ProxyBiddingEntity(BigDecimal maxAmount) {
        super(new BigDecimal(-77));
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
