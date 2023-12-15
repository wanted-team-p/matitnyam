package com.wanted.matitnyam.domain.xmlparser;

import com.wanted.matitnyam.domain.Restaurant;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Genrestrtjpnfood")
@Getter
@NoArgsConstructor
public class JapaneseRestaurants implements RestaurantsData {

    @XmlElement(name = "head")
    private MetaInformation metaInformation;

    @XmlElement(name = "row")
    private List<Restaurant> restaurants;

    public RestaurantsData preprocess() {
        this.restaurants = this.restaurants.stream()
                .filter(RestaurantsDataPreprocessor::hasEssentialInformation)
                .toList();
        return this;
    }

}
