
package ws.client;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for creditTransactionEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="creditTransactionEntity"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="creditPackageEntity" type="{http://ws.session.ejb/}creditPackageEntity" minOccurs="0"/&gt;
 *         &lt;element name="customerEntity" type="{http://ws.session.ejb/}customerEntity" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="totalValue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="transactionTypeEnum" type="{http://ws.session.ejb/}transactionTypeEnum" minOccurs="0"/&gt;
 *         &lt;element name="unitOfPurchase" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "creditTransactionEntity", propOrder = {
    "creditPackageEntity",
    "customerEntity",
    "id",
    "totalValue",
    "transactionTypeEnum",
    "unitOfPurchase"
})
public class CreditTransactionEntity {

    protected CreditPackageEntity creditPackageEntity;
    protected CustomerEntity customerEntity;
    protected Long id;
    protected BigDecimal totalValue;
    @XmlSchemaType(name = "string")
    protected TransactionTypeEnum transactionTypeEnum;
    protected Integer unitOfPurchase;

    /**
     * Gets the value of the creditPackageEntity property.
     * 
     * @return
     *     possible object is
     *     {@link CreditPackageEntity }
     *     
     */
    public CreditPackageEntity getCreditPackageEntity() {
        return creditPackageEntity;
    }

    /**
     * Sets the value of the creditPackageEntity property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditPackageEntity }
     *     
     */
    public void setCreditPackageEntity(CreditPackageEntity value) {
        this.creditPackageEntity = value;
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

    /**
     * Gets the value of the totalValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalValue() {
        return totalValue;
    }

    /**
     * Sets the value of the totalValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalValue(BigDecimal value) {
        this.totalValue = value;
    }

    /**
     * Gets the value of the transactionTypeEnum property.
     * 
     * @return
     *     possible object is
     *     {@link TransactionTypeEnum }
     *     
     */
    public TransactionTypeEnum getTransactionTypeEnum() {
        return transactionTypeEnum;
    }

    /**
     * Sets the value of the transactionTypeEnum property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionTypeEnum }
     *     
     */
    public void setTransactionTypeEnum(TransactionTypeEnum value) {
        this.transactionTypeEnum = value;
    }

    /**
     * Gets the value of the unitOfPurchase property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUnitOfPurchase() {
        return unitOfPurchase;
    }

    /**
     * Sets the value of the unitOfPurchase property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUnitOfPurchase(Integer value) {
        this.unitOfPurchase = value;
    }

}
