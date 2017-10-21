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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.validation.constraints.Future;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author alina
 */
@Entity
@XmlRootElement
@XmlType(name = "auctionEntity", propOrder = {
    "id",
    "startingTime",
    "endingTime",
    "status",
    "reservePrice",
    "winningBid",
    "productName",
    "productDescription",
    "winningCustomerId",
    "bidEntities"
})
public class AuctionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Future
    private Date startingTime;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Future
    private Date endingTime;
    private Boolean status;
    @Column(precision = 18, scale = 4)
    private BigDecimal reservePrice;
    @Column(precision = 18, scale = 4)
    private BigDecimal winningBid;
    @Column(length = 32)
    private String productName;
    @Column(length = 300)
    private String productDescription;
    private Long winningCustomerId;
    @OneToMany(mappedBy="auctionEntity", cascade = CascadeType.ALL)
    private List<BidEntity> bidEntities;

    public AuctionEntity() {
    }

    public AuctionEntity(Long id, Date startingTime, Date endingTime, Boolean status, BigDecimal reservePrice, BigDecimal winningBid, String productName, String productDescription, Long winningCustomerId, List<BidEntity> bidEntities) {
        this.id = id;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.status = status;
        this.reservePrice = reservePrice;
        this.winningBid = winningBid;
        this.productName = productName;
        this.productDescription = productDescription;
        this.winningCustomerId = winningCustomerId;
        this.bidEntities = bidEntities;
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
    public Boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Boolean status) {
        this.status = status;
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
     */
    public BigDecimal getWinningBid() {
        return winningBid;
    }

    /**
     * @param winningBid the winningBid to set
     */
    public void setWinningBid(BigDecimal winningBid) {
        this.winningBid = winningBid;
    }

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
     * @return the winningCustomerId
     */
    public Long getWinningCustomerId() {
        return winningCustomerId;
    }

    /**
     * @param winningCustomerId the winningCustomerId to set
     */
    public void setWinningCustomerId(Long winningCustomerId) {
        this.winningCustomerId = winningCustomerId;
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

}
