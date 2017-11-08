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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author alina
 */
@Entity
public class SnipingBidEntity extends BidEntity implements Serializable {
    
    @Column(precision = 18, scale = 4)
    private BigDecimal maxAmount;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date timeDuration;

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

    
}
