/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author alina
 */
@Entity
@XmlRootElement
@XmlType(name = "bidEntity", propOrder = {
    "id",
    "amount",
    "isWinningBid",
    "auctionEntity",
    "customerEntity",
    "addressEntities"
})
public class BidEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(precision = 18, scale = 4)
    private BigDecimal amount;
    private boolean isWinningBid;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private AuctionEntity auctionEntity;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private CustomerEntity customerEntity;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private List<AddressEntity> addressEntities;

    public BidEntity() {
    }

    public BidEntity(Long id, BigDecimal amount, boolean isWinningBid, AuctionEntity auctionEntity, CustomerEntity customerEntity, List<AddressEntity> addressEntities) {
        this.id = id;
        this.amount = amount;
        this.isWinningBid = isWinningBid;
        this.auctionEntity = auctionEntity;
        this.customerEntity = customerEntity;
        this.addressEntities = addressEntities;
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
        if (!(object instanceof BidEntity)) {
            return false;
        }
        BidEntity other = (BidEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.BidEntity[ id=" + id + " ]";
    }

    /**
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return the isWinningBid
     */
    public boolean isIsWinningBid() {
        return isWinningBid;
    }

    /**
     * @param isWinningBid the isWinningBid to set
     */
    public void setIsWinningBid(boolean isWinningBid) {
        this.isWinningBid = isWinningBid;
    }

    /**
     * @return the auctionEntity
     */
    public AuctionEntity getAuctionEntity() {
        return auctionEntity;
    }

    /**
     * @param auctionEntity the auctionEntity to set
     */
    public void setAuctionEntity(AuctionEntity auctionEntity) {
        this.auctionEntity = auctionEntity;
    }

    /**
     * @return the customerEntity
     */
    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    /**
     * @param customerEntity the customerEntity to set
     */
    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    /**
     * @return the addressEntities
     */
    public List<AddressEntity> getAddressEntities() {
        return addressEntities;
    }

    /**
     * @param addressEntities the addressEntities to set
     */
    public void setAddressEntities(List<AddressEntity> addressEntities) {
        this.addressEntities = addressEntities;
    }

}
