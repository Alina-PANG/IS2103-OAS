
package ws.client;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bidEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bidEntity"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="addressEntity" type="{http://ws.session.ejb/}addressEntity" minOccurs="0"/&gt;
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="auctionEntity" type="{http://ws.session.ejb/}auctionEntity" minOccurs="0"/&gt;
 *         &lt;element name="customerEntity" type="{http://ws.session.ejb/}customerEntity" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bidEntity", propOrder = {
    "addressEntity",
    "amount",
    "auctionEntity",
    "customerEntity",
    "id"
})
public class BidEntity {

    protected AddressEntity addressEntity;
    protected BigDecimal amount;
    protected AuctionEntity auctionEntity;
    protected CustomerEntity customerEntity;
    protected Long id;

    /**
     * Gets the value of the addressEntity property.
     * 
     * @return
     *     possible object is
     *     {@link AddressEntity }
     *     
     */
    public AddressEntity getAddressEntity() {
        return addressEntity;
    }

    /**
     * Sets the value of the addressEntity property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressEntity }
     *     
     */
    public void setAddressEntity(AddressEntity value) {
        this.addressEntity = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmount(BigDecimal value) {
        this.amount = value;
    }

    /**
     * Gets the value of the auctionEntity property.
     * 
     * @return
     *     possible object is
     *     {@link AuctionEntity }
     *     
     */
    public AuctionEntity getAuctionEntity() {
        return auctionEntity;
    }

    /**
     * Sets the value of the auctionEntity property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuctionEntity }
     *     
     */
    public void setAuctionEntity(AuctionEntity value) {
        this.auctionEntity = value;
    }

    /**
     * Gets the value of the customerEntity property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerEntity }
     *     
     */
    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    /**
     * Sets the value of the customerEntity property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerEntity }
     *     
     */
    public void setCustomerEntity(CustomerEntity value) {
        this.customerEntity = value;
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

}
