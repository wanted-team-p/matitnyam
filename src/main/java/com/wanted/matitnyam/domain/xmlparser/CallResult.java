package com.wanted.matitnyam.domain.xmlparser;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "RESULT")
@Getter
@NoArgsConstructor
public class CallResult {

    @XmlElement(name = "CODE")
    private String code;

    @XmlElement(name = "MESSAGE")
    private String message;

}