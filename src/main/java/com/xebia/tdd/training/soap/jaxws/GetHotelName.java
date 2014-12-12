
package com.xebia.tdd.training.soap.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getHotelName", namespace = "http://soap.hotelreservation.training.tdd.xebia.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getHotelName", namespace = "http://soap.hotelreservation.training.tdd.xebia.com/")
public class GetHotelName {

    @XmlElement(name = "arg0", namespace = "")
    private Long arg0;

    /**
     * 
     * @return
     *     returns Long
     */
    public Long getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(Long arg0) {
        this.arg0 = arg0;
    }

}
