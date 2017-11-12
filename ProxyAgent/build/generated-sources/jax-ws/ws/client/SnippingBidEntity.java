
package ws.client;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for snippingBidEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="snippingBidEntity"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://ws.session.ejb/}bidEntity"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="maxAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="timeduration" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "snippingBidEntity", propOrder = {
    "maxAmount",
    "timeduration"
})
public class SnippingBidEntity
    extends BidEntity
{

    protected BigDecimal maxAmount;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timeduration;

    /**
     * Gets the value of the maxAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    /**
     * Sets the value of the maxAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMaxAmount(BigDecimal value) {
        this.maxAmount = value;
    }

    /**
     * Gets the value of the timeduration property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimeduration() {
        return timeduration;
    }

    /**
     * Sets the value of the timeduration property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimeduration(XMLGregorianCalendar value) {
        this.timeduration = value;
    }

}
