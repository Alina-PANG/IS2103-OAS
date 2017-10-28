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
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author alina
 */
@Entity
@XmlRootElement
@XmlType(name = "creditPackageEntity", propOrder = {
    "id",
    "value",
    "price",
    "name",
    "creditTransactionEntities"
})
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
  //  @OneToMany(mappedBy="creditPackageEntity")
  //  private List<CreditTransactionEntity> creditTransactionEntities;

    public CreditPackageEntity() {
    }

    public CreditPackageEntity(BigDecimal value, BigDecimal price, String name) {
        this.value = value;
        this.price = price;
        this.name = name;
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
     * @return the creditTransactionEntities
     
    public List<CreditTransactionEntity> getCreditTransactionEntities() {
        return creditTransactionEntities;
    }

    /**
     * @param creditTransactionEntities the creditTransactionEntities to set
     
    public void setCreditTransactionEntities(List<CreditTransactionEntity> creditTransactionEntities) {
        this.creditTransactionEntities = creditTransactionEntities;
    }
*/
}
