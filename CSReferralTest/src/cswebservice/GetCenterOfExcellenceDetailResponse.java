
package cswebservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetCenterOfExcellenceDetailResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getCenterOfExcellenceDetailResult"
})
@XmlRootElement(name = "GetCenterOfExcellenceDetailResponse")
public class GetCenterOfExcellenceDetailResponse {

    @XmlElement(name = "GetCenterOfExcellenceDetailResult")
    protected String getCenterOfExcellenceDetailResult;

    /**
     * Gets the value of the getCenterOfExcellenceDetailResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetCenterOfExcellenceDetailResult() {
        return getCenterOfExcellenceDetailResult;
    }

    /**
     * Sets the value of the getCenterOfExcellenceDetailResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetCenterOfExcellenceDetailResult(String value) {
        this.getCenterOfExcellenceDetailResult = value;
    }

}
