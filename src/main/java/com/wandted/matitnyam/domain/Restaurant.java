package com.wandted.matitnyam.domain;

import com.wandted.matitnyam.dto.RestaurantDetailDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @XmlElement(name = "SIGUN_NM")
    private String city;

    @XmlElement(name = "BIZPLC_NM")
    private String name;

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

    @XmlTransient
    private Long numberOfReviews = 0L;

    @XmlTransient
    private Long totalRatings = 0L;

    @XmlTransient
    private Double rating = 0.0;

    @Builder
    public Restaurant(final String city, final String name, final String closeOrOpen, final String typeOfFoods,
                      final String addressAsLocationName, final String addressAsRoadName, final Double latitude,
                      final Double longitude) {
        this.city = city;
        this.name = name;
        this.closeOrOpen = closeOrOpen;
        this.typeOfFoods = typeOfFoods;
        this.addressAsLocationName = addressAsLocationName;
        this.addressAsRoadName = addressAsRoadName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Restaurant(final Long seq, final Restaurant restaurant) {
        this.seq = seq;
        this.city = restaurant.city;
        this.name = restaurant.name;
        this.closeOrOpen = restaurant.closeOrOpen;
        this.typeOfFoods = restaurant.typeOfFoods;
        this.addressAsLocationName = restaurant.addressAsLocationName;
        this.addressAsRoadName = restaurant.addressAsRoadName;
        this.latitude = restaurant.latitude;
        this.longitude = restaurant.longitude;
        this.numberOfReviews = restaurant.numberOfReviews;
        this.totalRatings = restaurant.totalRatings;
        this.rating = restaurant.rating;
    }

    public void updateRatings(long totalRatings, long numberOfReviews) {
        this.totalRatings = totalRatings;
        this.numberOfReviews = numberOfReviews;
        this.rating = (double) totalRatings / numberOfReviews;
    }

    public RestaurantDetailDto toDetailDtoWithDistance() {
        return RestaurantDetailDto.builder()
                .city(this.city)
                .name(this.name)
                .closeOrOpen(this.closeOrOpen)
                .typeOfFoods(this.typeOfFoods)
                .addressAsLocationName(this.addressAsLocationName)
                .addressAsRoadName(this.addressAsRoadName)
                .numberOfReviews(this.numberOfReviews)
                .rating(this.rating)
                .build();
    }

}
