package com.wanted.matitnyam.domain.xmlparser;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "row")
@Getter
@NoArgsConstructor
@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seq;

    @XmlElement(name = "SIGUN_NM")
    private String cityName;

    @XmlElement(name = "BIZPLC_NM")
    private String restaurantName;

    @XmlElement(name = "BSN_STATE_NM")
    private String closeOrOpen;

    @XmlElement(name = "SANITTN_BIZCOND_NM")
    private String typeOfFoods;

    @XmlElement(name = "REFINE_LOTNO_ADDR")
    private String addressAsLocationName;

    @XmlElement(name = "REFINE_ROADNM_ADDR")
    private String addressAsRoadName;

    @XmlElement(name = "REFINE_WGS84_LAT")
    private Double latitude;

    @XmlElement(name = "REFINE_WGS84_LOGT")
    private Double longitude;

    @Builder
    public Restaurant(final String cityName, final String restaurantName, final String closeOrOpen,
                      final String typeOfFoods, final String addressAsLocationName, final String addressAsRoadName,
                      final Double longitude, final Double latitude) {
        this.cityName = cityName;
        this.restaurantName = restaurantName;
        this.closeOrOpen = closeOrOpen;
        this.typeOfFoods = typeOfFoods;
        this.addressAsLocationName = addressAsLocationName;
        this.addressAsRoadName = addressAsRoadName;
        this.longitude = longitude;
        this.latitude = latitude;
    }

}
