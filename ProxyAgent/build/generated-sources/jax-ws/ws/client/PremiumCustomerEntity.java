
package ws.client;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for premiumCustomerEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="premiumCustomerEntity"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://ws.session.ejb/}customerEntity"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="maxWillingPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="timeDuration" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="oneTimeHighestBid" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "premiumCustomerEntity", propOrder = {
    "maxWillingPrice",
    "timeDuration",
    "oneTimeHighestBid"
})
public class PremiumCustomerEntity
    extends CustomerEntity
{

    protected BigDecimal maxWillingPrice;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timeDuration;
    protected BigDecimal oneTimeHighestBid;

    /**
     * Gets the value of the maxWillingPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMaxWillingPrice() {
        return maxWillingPrice;
    }

    /**
     * Sets the value of the maxWillingPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMaxWillingPrice(BigDecimal value) {
        this.maxWillingPrice = value;
    }

    /**
     * Gets the value of the timeDuration property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimeDuration() {
        return timeDuration;
    }

    /**
     * Sets the value of the timeDuration property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimeDuration(XMLGregorianCalendar value) {
        this.timeDuration = value;
    }

    /**
     * Gets the value of the oneTimeHighestBid property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOneTimeHighestBid() {
        return oneTimeHighestBid;
    }

    /**
     * Sets the value of the oneTimeHighestBid property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOneTimeHighestBid(BigDecimal value) {
        this.oneTimeHighestBid = value;
    }

}
