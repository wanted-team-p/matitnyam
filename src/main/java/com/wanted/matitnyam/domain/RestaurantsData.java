package com.wanted.matitnyam.domain;

import com.wanted.matitnyam.domain.xmlparser.MetaInformation;
import com.wanted.matitnyam.domain.xmlparser.Restaurant;
import java.util.List;

public interface RestaurantsData {

    MetaInformation getMetaInformation();

    List<Restaurant> getRestaurants();

    void preprocess();

}