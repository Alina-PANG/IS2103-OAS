/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;

/**
 *
 * @author alina
 */
@Entity
public class SnippingBidEntity extends BidEntity implements Serializable {
    @Column(precision = 18, scale = 4)
    private BigDecimal maxAmount;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date Timeduration;

    public SnippingBidEntity() {
    }

    public SnippingBidEntity(BigDecimal amount) {
        super(amount);
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

    /**
     * @return the Timeduration
     */
    public Date getTimeduration() {
        return Timeduration;
    }

    /**
     * @param Timeduration the Timeduration to set
     */
    public void setTimeduration(Date Timeduration) {
        this.Timeduration = Timeduration;
    }
    
    
    
}
