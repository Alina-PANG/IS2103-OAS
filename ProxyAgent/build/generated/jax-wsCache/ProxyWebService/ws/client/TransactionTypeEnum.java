
package ws.client;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for transactionTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="transactionTypeEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="TOPUP"/&gt;
 *     &lt;enumeration value="BIDDING"/&gt;
 *     &lt;enumeration value="REFUND"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "transactionTypeEnum")
@XmlEnum
public enum TransactionTypeEnum {

    TOPUP,
    BIDDING,
    REFUND;

    public String value() {
        return name();
    }

    public static TransactionTypeEnum fromValue(String v) {
        return valueOf(v);
    }

}
