
package ws.client;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for customerTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="customerTypeEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="PREMIUM"/&gt;
 *     &lt;enumeration value="NORMAL"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "customerTypeEnum")
@XmlEnum
public enum CustomerTypeEnum {

    PREMIUM,
    NORMAL;

    public String value() {
        return name();
    }

    public static CustomerTypeEnum fromValue(String v) {
        return valueOf(v);
    }

}
