/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author alina
 */
@Entity
public class CreditPackageEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(precision = 18, scale = 4, nullable = false)
    private BigDecimal value;
    @Column(precision = 18, scale = 4, nullable = false)
    private BigDecimal price;
    @Column(length = 32, unique = true, nullable = false)
    private String name;
    @ManyToMany
    private List<CustomerEntity> customerEntities;
    private Boolean isDisabled;
    //  @OneToMany(mappedBy="creditPackageEntity")
    // private List<CreditTransactionEntity> creditTransactionEntities;

    public CreditPackageEntity() {
        isDisabled = false;
    }

    public CreditPackageEntity(BigDecimal value, BigDecimal price, String name, Boolean isDisabled) {
        this.value = value;
        this.price = price;
        this.name = name;
        this.isDisabled = isDisabled;
        this.customerEntities = new ArrayList<CustomerEntity>();
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
        if (!(object instanceof CreditPackageEntity)) {
            return false;
        }
        CreditPackageEntity other = (CreditPackageEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CreditPackageEntity[ id=" + id + " ]";
    }

    /**
     * @return the value
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the isDisabled
     */
    public Boolean getIsDisabled() {
        return isDisabled;
    }

    /**
     * @param isDisabled the isDisabled to set
     */
    public void setIsDisabled(Boolean isDisabled) {
        this.isDisabled = isDisabled;
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
     * @return the creditTransactionEntities
     *
     * public List<CreditTransactionEntity> getCreditTransactionEntities() {
     * return creditTransactionEntities;
    }
     */
    /**
     * @param creditTransactionEntities the creditTransactionEntities to set
     *
     * public void setCreditTransactionEntities(List<CreditTransactionEntity>
     * creditTransactionEntities) { this.creditTransactionEntities =
     * creditTransactionEntities;
    }
     */
}
