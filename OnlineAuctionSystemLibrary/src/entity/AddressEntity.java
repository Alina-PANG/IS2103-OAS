/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author alina
 */
@Entity
@XmlRootElement
@XmlType(name = "addressEntity", propOrder = {
    "id",
    "addressLine",
    "postCode",
    "isDisabled",
    "bidEntities",
    "customerEntity"
})

public class AddressEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 64, unique = true, nullable = false)
    private String addressLine;
    @Column(length = 6)
    private String postCode;
    private Boolean isDisabled;
    @OneToMany(mappedBy = "addressEntity")
    private List<BidEntity> bidEntities;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private CustomerEntity customerEntity;

    public AddressEntity() {
    }

    public AddressEntity(Long id, String addressLine, String postCode, boolean isDisabled, List<BidEntity> bidEntities, CustomerEntity customerEntity) {
        this.id = id;
        this.addressLine = addressLine;
        this.postCode = postCode;
        this.isDisabled = isDisabled;
        this.bidEntities = bidEntities;
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
        if (!(object instanceof AddressEntity)) {
            return false;
        }
        AddressEntity other = (AddressEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AddressEntity[ id=" + id + " ]";
    }

    /**
     * @return the addressLine
     */
    public String getAddressLine() {
        return addressLine;
    }

    /**
     * @param addressLine the addressLine to set
     */
    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    /**
     * @return the postCode
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * @param postCode the postCode to set
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    /**
     * @return the isDisabled
     */
    public Boolean isIsDisabled() {
        return isDisabled;
    }

    /**
     * @param isDisabled the isDisabled to set
     */
    public void setIsDisabled(Boolean isDisabled) {
        this.isDisabled = isDisabled;
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
