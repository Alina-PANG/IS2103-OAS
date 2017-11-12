
package ws.client;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for proxyBiddingEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="proxyBiddingEntity"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://ws.session.ejb/}bidEntity"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="maxAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "proxyBiddingEntity", propOrder = {
    "maxAmount"
})
public class ProxyBiddingEntity
    extends BidEntity
{

    protected BigDecimal maxAmount;

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

}
