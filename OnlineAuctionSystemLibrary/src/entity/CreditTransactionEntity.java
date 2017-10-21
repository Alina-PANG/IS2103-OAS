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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import util.enumeration.TransactionTypeEnum;

/**
 *
 * @author alina
 */
@Entity
@XmlRootElement
@XmlType(name = "addressEntity", propOrder = {
    "id",
    "totalValue",
    "transactionTypeEnum",
    "unitOfPurchase",
    "creditPackageEntity",
    "customerEntity"
})
public class CreditTransactionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(precision = 18, scale = 4)
    private BigDecimal totalValue;
    @Enumerated(EnumType.STRING)
    private TransactionTypeEnum transactionTypeEnum;
    @Max(100)
    @Min(0)
    private Integer unitOfPurchase;
    @ManyToOne
    private CreditPackageEntity creditPackageEntity;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private CustomerEntity customerEntity;

    public CreditTransactionEntity() {
    }

    public CreditTransactionEntity(Long id, BigDecimal totalValue, TransactionTypeEnum transactionTypeEnum, Integer unitOfPurchase, CreditPackageEntity creditPackageEntity, CustomerEntity customerEntity) {
        this.id = id;
        this.totalValue = totalValue;
        this.transactionTypeEnum = transactionTypeEnum;
        this.unitOfPurchase = unitOfPurchase;
        this.creditPackageEntity = creditPackageEntity;
        this.customerEntity = customerEntity;
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
        if (!(object instanceof CreditTransactionEntity)) {
            return false;
        }
        CreditTransactionEntity other = (CreditTransactionEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CreditTransactionEntity[ id=" + id + " ]";
    }

    /**
     * @return the totalValue
     */
    public BigDecimal getTotalValue() {
        return totalValue;
    }

    /**
     * @param totalValue the totalValue to set
     */
    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    /**
     * @return the transactionTypeEnum
     */
    public TransactionTypeEnum getTransactionTypeEnum() {
        return transactionTypeEnum;
    }

    /**
     * @param transactionTypeEnum the transactionTypeEnum to set
     */
    public void setTransactionTypeEnum(TransactionTypeEnum transactionTypeEnum) {
        this.transactionTypeEnum = transactionTypeEnum;
    }

    /**
     * @return the unitOfPurchase
     */
    public Integer getUnitOfPurchase() {
        return unitOfPurchase;
    }

    /**
     * @param unitOfPurchase the unitOfPurchase to set
     */
    public void setUnitOfPurchase(Integer unitOfPurchase) {
        this.unitOfPurchase = unitOfPurchase;
    }

    /**
     * @return the creditPackageEntity
     */
    public CreditPackageEntity getCreditPackageEntity() {
        return creditPackageEntity;
    }

    /**
     * @param creditPackageEntity the creditPackageEntity to set
     */
    public void setCreditPackageEntity(CreditPackageEntity creditPackageEntity) {
        this.creditPackageEntity = creditPackageEntity;
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

}
