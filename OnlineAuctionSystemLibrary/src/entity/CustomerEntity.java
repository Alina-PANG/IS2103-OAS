/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import util.enumeration.CustomerTypeEnum;

/**
 *
 * @author alina
 */
@Entity
@XmlRootElement
@XmlType(name = "customerEntity", propOrder = {
    "id",
    "firstName",
    "lastName",
    "username",
    "password",
    "contactNumber", 
    "email",
    "creditBalance",
    "creditTransactionEntities",
    "bidEntities",
    "addressEntities",
    "customerTypeEnum"
})
public class CustomerEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 32, nullable = false)
    private String firstName;
    @Column(length = 32, nullable = false)
    private String lastName;
    @Column(length = 32, nullable = false, unique = true)
    private String username;
    @Size(min = 6, max = 32)
    private String password;
    @Column(length = 8, nullable = false, unique = true)
    private String contactNumber;
    @Column(length = 32, nullable = false, unique = true)
    private String email;
    @Column(precision = 18, scale = 4)
    private BigDecimal creditBalance;
    @OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL)
    private List<CreditTransactionEntity> creditTransactionEntities;
    @OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL)
    private List<BidEntity> bidEntities;
    @OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL)
    private List<AddressEntity> addressEntities;
    @Enumerated(EnumType.STRING)
    private CustomerTypeEnum customerTypeEnum;
    
    // Premium Customer
    @Column(precision = 18, scale = 4)
    private BigDecimal maxWillingAmount;
    @Column(precision = 18, scale = 4)
    private BigDecimal oneTimeHighestBid;

    public CustomerEntity() {
        this.creditBalance = new BigDecimal(0);
    }

    public CustomerEntity(String firstName, String lastName, String contactNumber, String email, BigDecimal creditBalance, List<CreditTransactionEntity> creditTransactionEntities, List<BidEntity> bidEntities, List<AddressEntity> addressEntities, CustomerTypeEnum customerTypeEnum) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.email = email;
        this.creditBalance = creditBalance;
        this.creditTransactionEntities = creditTransactionEntities;
        this.bidEntities = bidEntities;
        this.addressEntities = addressEntities;
        this.customerTypeEnum = customerTypeEnum;
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
        if (!(object instanceof CustomerEntity)) {
            return false;
        }
        CustomerEntity other = (CustomerEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CustomerEntity[ id=" + id + " ]";
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the contactNumber
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * @param contactNumber the contactNumber to set
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the creditBalance
     */
    public BigDecimal getCreditBalance() {
        return creditBalance;
    }

    /**
     * @param creditBalance the creditBalance to set
     */
    public void setCreditBalance(BigDecimal creditBalance) {
        this.creditBalance = creditBalance;
    }

    /**
     * @return the creditTransactionEntities
     */
    public List<CreditTransactionEntity> getCreditTransactionEntities() {
        return creditTransactionEntities;
    }

    /**
     * @param creditTransactionEntities the creditTransactionEntities to set
     */
    public void setCreditTransactionEntities(List<CreditTransactionEntity> creditTransactionEntities) {
        this.creditTransactionEntities = creditTransactionEntities;
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

    /**
     * @return the customerTypeEnum
     */
    public CustomerTypeEnum getCustomerTypeEnum() {
        return customerTypeEnum;
    }

    /**
     * @param customerTypeEnum the customerTypeEnum to set
     */
    public void setCustomerTypeEnum(CustomerTypeEnum customerTypeEnum) {
        this.customerTypeEnum = customerTypeEnum;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the maxWillingAmount
     */
    public BigDecimal getMaxWillingAmount() {
        return maxWillingAmount;
    }

    /**
     * @param maxWillingAmount the maxWillingAmount to set
     */
    public void setMaxWillingAmount(BigDecimal maxWillingAmount) {
        this.maxWillingAmount = maxWillingAmount;
    }

    /**
     * @return the oneTimeHighestBid
     */
    public BigDecimal getOneTimeHighestBid() {
        return oneTimeHighestBid;
    }

    /**
     * @param oneTimeHighestBid the oneTimeHighestBid to set
     */
    public void setOneTimeHighestBid(BigDecimal oneTimeHighestBid) {
        this.oneTimeHighestBid = oneTimeHighestBid;
    }

}
