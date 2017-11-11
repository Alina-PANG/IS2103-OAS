/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.validation.constraints.Future;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import util.enumeration.StatusEnum;

/**
 *
 * @author alina
 */
@Entity
public class AuctionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long winningBidId;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Future
    private Date startingTime;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Future
    private Date endingTime;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    @Column(precision = 18, scale = 4)
    private BigDecimal reservePrice;
    @Column(length = 32, nullable = false)
    private String productName;
    @Column(length = 300)
    private String productDescription;
    @OneToMany(mappedBy = "auctionEntity", cascade = CascadeType.ALL)
    private List<BidEntity> bidEntities;
    @ManyToMany
    private List<CustomerEntity> customerEntities;

    public AuctionEntity() {
    }

    public AuctionEntity(Date startingTime, Date endingTime, BigDecimal reservePrice, String productName, String productDescription) {
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        if(this.startingTime.compareTo(new Date()) < 0){
            this.status = StatusEnum.ACTIVE;
        }
        else
            this.status = StatusEnum.CLOSED;
        this.reservePrice = reservePrice;
        this.productName = productName;
        this.productDescription = productDescription;
        this.bidEntities = new ArrayList<BidEntity>();
        this.customerEntities = new ArrayList<CustomerEntity>();
        this.winningBidId = new Long(0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuctionEntity)) {
            return false;
        }
        AuctionEntity other = (AuctionEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AuctionEntity[ id=" + id + " ]";
    }

    /**
     * @return the startingTime
     */
    public Date getStartingTime() {
        return startingTime;
    }

    /**
     * @param startingTime the startingTime to set
     */
    public void setStartingTime(Date startingTime) {
        this.startingTime = startingTime;
    }

    /**
     * @return the endingTime
     */
    public Date getEndingTime() {
        return endingTime;
    }

    /**
     * @param endingTime the endingTime to set
     */
    public void setEndingTime(Date endingTime) {
        this.endingTime = endingTime;
    }

    /**
     * @return the status
     */
    public StatusEnum isStatus() {
        return getStatus();
    }

    /**
     * @param status the status to set
     */
    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public boolean checkStatus() {
        if ((new Date()).compareTo(this.endingTime) > 0) {
            this.status = StatusEnum.CLOSED;
            return true;
        }
        return false;
    }

    /**
     * @return the reservePrice
     */
    public BigDecimal getReservePrice() {
        return reservePrice;
    }

    /**
     * @param reservePrice the reservePrice to set
     */
    public void setReservePrice(BigDecimal reservePrice) {
        this.reservePrice = reservePrice;
    }

    /**
     * @return the winningBid
     *
     * public Long getWinningBidId() { return winningBidId; }
     *
     * /
     **
     * @param winningBidId the winningBidId to set
     *
     * public void setWinningBidId(Long winningBidId) { this.winningBidId =
     * winningBidId; }
     */
    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the productDescription
     */
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * @param productDescription the productDescription to set
     */
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    /**
     * @return the bidEntities
     */
    public List<BidEntity> getBidEntities() {
        return bidEntities;
    }

    /**
     * @param bidEntities the bidEntities to set
     */
    public void setBidEntities(List<BidEntity> bidEntities) {
        this.bidEntities = bidEntities;
    }


    /**
     * @return the status
     */
    public StatusEnum getStatus() {
        return status;
    }

    /**
     * @return the customerEntities
     */
    public List<CustomerEntity> getCustomerEntities() {
        return customerEntities;
    }

    /**
     * @param customerEntities the customerEntities to set
     */
    public void setCustomerEntities(List<CustomerEntity> customerEntities) {
        this.customerEntities = customerEntities;
    }

    /**
     * @return the winningBidId
     */
    public Long getWinningBidId() {
        return winningBidId;
    }

    /**
     * @param winningBidId the winningBidId to set
     */
    public void setWinningBidId(Long winningBidId) {
        this.winningBidId = winningBidId;
    }
}
