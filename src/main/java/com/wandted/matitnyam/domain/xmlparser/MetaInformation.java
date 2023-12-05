package com.wandted.matitnyam.domain.xmlparser;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "head")
@Getter
@NoArgsConstructor
public class MetaInformation {

    @XmlElement(name = "list_total_count")
    private int totalNoOfRestaurants;

    @XmlElement(name = "RESULT")
    private CallResult callResult;

}
