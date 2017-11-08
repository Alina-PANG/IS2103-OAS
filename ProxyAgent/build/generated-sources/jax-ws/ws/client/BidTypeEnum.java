
package ws.client;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bidTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="bidTypeEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="SNIPING"/&gt;
 *     &lt;enumeration value="PROXY"/&gt;
 *     &lt;enumeration value="NORMAL"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "bidTypeEnum")
@XmlEnum
public enum BidTypeEnum {

    SNIPING,
    PROXY,
    NORMAL;

    public String value() {
        return name();
    }

    public static BidTypeEnum fromValue(String v) {
        return valueOf(v);
    }

}
