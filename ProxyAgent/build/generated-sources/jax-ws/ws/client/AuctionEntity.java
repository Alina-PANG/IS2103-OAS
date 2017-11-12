
package ws.client;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for auctionEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="auctionEntity"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="bidEntities" type="{http://ws.session.ejb/}bidEntity" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="customerEntities" type="{http://ws.session.ejb/}customerEntity" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="endingTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="productDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="productName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="reservePrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="startingTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="status" type="{http://ws.session.ejb/}statusEnum" minOccurs="0"/&gt;
 *         &lt;element name="winningBidId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "auctionEntity", propOrder = {
    "bidEntities",
    "customerEntities",
    "endingTime",
    "id",
    "productDescription",
    "productName",
    "reservePrice",
    "startingTime",
    "status",
    "winningBidId"
})
public class AuctionEntity {

    @XmlElement(nillable = true)
    protected List<BidEntity> bidEntities;
    @XmlElement(nillable = true)
    protected List<CustomerEntity> customerEntities;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endingTime;
    protected Long id;
    protected String productDescription;
    protected String productName;
    protected BigDecimal reservePrice;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startingTime;
    @XmlSchemaType(name = "string")
    protected StatusEnum status;
    protected Long winningBidId;

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
     * Gets the value of the customerEntities property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the customerEntities property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustomerEntities().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerEntity }
     * 
     * 
     */
    public List<CustomerEntity> getCustomerEntities() {
        if (customerEntities == null) {
            customerEntities = new ArrayList<CustomerEntity>();
        }
        return this.customerEntities;
    }

    /**
     * Gets the value of the endingTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndingTime() {
        return endingTime;
    }

    /**
     * Sets the value of the endingTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndingTime(XMLGregorianCalendar value) {
        this.endingTime = value;
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
     * Gets the value of the productDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * Sets the value of the productDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductDescription(String value) {
        this.productDescription = value;
    }

    /**
     * Gets the value of the productName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the value of the productName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductName(String value) {
        this.productName = value;
    }

    /**
     * Gets the value of the reservePrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getReservePrice() {
        return reservePrice;
    }

    /**
     * Sets the value of the reservePrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setReservePrice(BigDecimal value) {
        this.reservePrice = value;
    }

    /**
     * Gets the value of the startingTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartingTime() {
        return startingTime;
    }

    /**
     * Sets the value of the startingTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartingTime(XMLGregorianCalendar value) {
        this.startingTime = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link StatusEnum }
     *     
     */
    public StatusEnum getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusEnum }
     *     
     */
    public void setStatus(StatusEnum value) {
        this.status = value;
    }

    /**
     * Gets the value of the winningBidId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getWinningBidId() {
        return winningBidId;
    }

    /**
     * Sets the value of the winningBidId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setWinningBidId(Long value) {
        this.winningBidId = value;
    }

}
