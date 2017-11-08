
package ws.client;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for customerEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="customerEntity"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="addressEntities" type="{http://ws.session.ejb/}addressEntity" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="auctionEntities" type="{http://ws.session.ejb/}auctionEntity" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="bidEntities" type="{http://ws.session.ejb/}bidEntity" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="contactNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="creditBalance" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="creditTransactionEntities" type="{http://ws.session.ejb/}creditTransactionEntity" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="customerTypeEnum" type="{http://ws.session.ejb/}customerTypeEnum" minOccurs="0"/&gt;
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerEntity", propOrder = {
    "addressEntities",
    "auctionEntities",
    "bidEntities",
    "contactNumber",
    "creditBalance",
    "creditTransactionEntities",
    "customerTypeEnum",
    "email",
    "firstName",
    "id",
    "lastName",
    "password",
    "username"
})
public class CustomerEntity {

    @XmlElement(nillable = true)
    protected List<AddressEntity> addressEntities;
    @XmlElement(nillable = true)
    protected List<AuctionEntity> auctionEntities;
    @XmlElement(nillable = true)
    protected List<BidEntity> bidEntities;
    protected String contactNumber;
    protected BigDecimal creditBalance;
    @XmlElement(nillable = true)
    protected List<CreditTransactionEntity> creditTransactionEntities;
    @XmlSchemaType(name = "string")
    protected CustomerTypeEnum customerTypeEnum;
    protected String email;
    protected String firstName;
    protected Long id;
    protected String lastName;
    protected String password;
    protected String username;

    /**
     * Gets the value of the addressEntities property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the addressEntities property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddressEntities().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AddressEntity }
     * 
     * 
     */
    public List<AddressEntity> getAddressEntities() {
        if (addressEntities == null) {
            addressEntities = new ArrayList<AddressEntity>();
        }
        return this.addressEntities;
    }

    /**
     * Gets the value of the auctionEntities property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the auctionEntities property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAuctionEntities().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AuctionEntity }
     * 
     * 
     */
    public List<AuctionEntity> getAuctionEntities() {
        if (auctionEntities == null) {
            auctionEntities = new ArrayList<AuctionEntity>();
        }
        return this.auctionEntities;
    }

    /**
     * Gets the value of the bidEntities property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bidEntities property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBidEntities().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BidEntity }
     * 
     * 
     */
    public List<BidEntity> getBidEntities() {
        if (bidEntities == null) {
            bidEntities = new ArrayList<BidEntity>();
        }
        return this.bidEntities;
    }

    /**
     * Gets the value of the contactNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * Sets the value of the contactNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactNumber(String value) {
        this.contactNumber = value;
    }

    /**
     * Gets the value of the creditBalance property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCreditBalance() {
        return creditBalance;
    }

    /**
     * Sets the value of the creditBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCreditBalance(BigDecimal value) {
        this.creditBalance = value;
    }

    /**
     * Gets the value of the creditTransactionEntities property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the creditTransactionEntities property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCreditTransactionEntities().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CreditTransactionEntity }
     * 
     * 
     */
    public List<CreditTransactionEntity> getCreditTransactionEntities() {
        if (creditTransactionEntities == null) {
            creditTransactionEntities = new ArrayList<CreditTransactionEntity>();
        }
        return this.creditTransactionEntities;
    }

    /**
     * Gets the value of the customerTypeEnum property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerTypeEnum }
     *     
     */
    public CustomerTypeEnum getCustomerTypeEnum() {
        return customerTypeEnum;
    }

    /**
     * Sets the value of the customerTypeEnum property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerTypeEnum }
     *     
     */
    public void setCustomerTypeEnum(CustomerTypeEnum value) {
        this.customerTypeEnum = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

}
