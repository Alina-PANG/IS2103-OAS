
package ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for placeBid complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="placeBid"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="type" type="{http://ws.session.ejb/}bidTypeEnum" minOccurs="0"/&gt;
 *         &lt;element name="bid" type="{http://ws.session.ejb/}bidEntity" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "placeBid", propOrder = {
    "type",
    "bid"
})
public class PlaceBid {

    @XmlSchemaType(name = "string")
    protected BidTypeEnum type;
    protected BidEntity bid;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link BidTypeEnum }
     *     
     */
    public BidTypeEnum getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link BidTypeEnum }
     *     
     */
    public void setType(BidTypeEnum value) {
        this.type = value;
    }

    /**
     * Gets the value of the bid property.
     * 
     * @return
     *     possible object is
     *     {@link BidEntity }
     *     
     */
    public BidEntity getBid() {
        return bid;
    }

    /**
     * Sets the value of the bid property.
     * 
     * @param value
     *     allowed object is
     *     {@link BidEntity }
     *     
     */
    public void setBid(BidEntity value) {
        this.bid = value;
    }

}
